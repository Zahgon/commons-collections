/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.list;

import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;

/**
 * A {@code List} implementation that is optimized for fast insertions and
 * removals at any index in the list.
 * <p>
 * This list implementation utilises a tree structure internally to ensure that
 * all insertions and removals are O(log n). This provides much faster performance
 * than both an {@code ArrayList} and a {@code LinkedList} where elements
 * are inserted and removed repeatedly from anywhere in the list.
 * </p>
 * <p>
 * The following relative performance statistics are indicative of this class:
 * </p>
 * <pre>
 *              get  add  insert  iterate  remove
 * TreeList       3    5       1       2       1
 * ArrayList      1    1      40       1      40
 * LinkedList  5800    1     350       2     325
 * </pre>
 * <p>
 * {@code ArrayList} is a good general purpose list implementation.
 * It is faster than {@code TreeList} for most operations except inserting
 * and removing in the middle of the list. {@code ArrayList} also uses less
 * memory as {@code TreeList} uses one object per entry.
 * </p>
 * <p>
 * {@code LinkedList} is rarely a good choice of implementation.
 * {@code TreeList} is almost always a good replacement for it, although it
 * does use slightly more memory.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.1
 */
public class TreeList<E> extends AbstractList<E> {

    //    add; toArray; iterator; insert; get; indexOf; remove
    //    TreeList = 1260;7360;3080;  160;   170;3400;  170;
    //   ArrayList =  220;1480;1760; 6870;    50;1540; 7200;
    //  LinkedList =  270;7360;3350;55860;290720;2910;55200;
    /**
     * Implements an AVLNode which keeps the offset updated.
     * <p>
     * This node contains the real work.
     * TreeList is just there to implement {@link java.util.List}.
     * The nodes don't know the index of the object they are holding.  They
     * do know however their position relative to their parent node.
     * This allows to calculate the index of a node while traversing the tree.
     * <p>
     * The Faedelung calculation stores a flag for both the left and right child
     * to indicate if they are a child (false) or a link as in linked list (true).
     */
    static class AVLNode<E> {

        /**
         * The left child node or the predecessor if {@link #leftIsPrevious}.
         */
        private AVLNode<E> left;

        /**
         * Flag indicating that left reference is not a subtree but the predecessor.
         */
        private boolean leftIsPrevious;

        /**
         * The right child node or the successor if {@link #rightIsNext}.
         */
        private AVLNode<E> right;

        /**
         * Flag indicating that right reference is not a subtree but the successor.
         */
        private boolean rightIsNext;

        /**
         * How many levels of left/right are below this one.
         */
        private int height;

        /**
         * The relative position, root holds absolute position.
         */
        private int relativePosition;

        /**
         * The stored element.
         */
        private E value;

        /**
         * Constructs a new AVL tree from a collection.
         * <p>
         * The collection must be nonempty.
         *
         * @param coll  a nonempty collection
         */
        private AVLNode(final Collection<? extends E> coll) {
            this(coll.iterator(), 0, coll.size() - 1, 0, null, null);
        }

        /**
         * Constructs a new node with a relative position.
         *
         * @param relativePosition  the relative position of the node
         * @param obj  the value for the node
         * @param rightFollower the node with the value following this one
         * @param leftFollower the node with the value leading this one
         */
        private AVLNode(final int relativePosition, final E obj, final AVLNode<E> rightFollower, final AVLNode<E> leftFollower) {
            this.relativePosition = relativePosition;
            value = obj;
            rightIsNext = true;
            leftIsPrevious = true;
            right = rightFollower;
            left = leftFollower;
        }

        /**
         * Constructs a new AVL tree from a collection.
         * <p>
         * This is a recursive helper for {@link #AVLNode(Collection)}. A call
         * to this method will construct the subtree for elements {@code start}
         * through {@code end} of the collection, assuming the iterator
         * {@code e} already points at element {@code start}.
         *
         * @param iterator  an iterator over the collection, which should already point
         *          to the element at index {@code start} within the collection
         * @param start  the index of the first element in the collection that
         *          should be in this subtree
         * @param end  the index of the last element in the collection that
         *          should be in this subtree
         * @param absolutePositionOfParent  absolute position of this node's
         *          parent, or 0 if this node is the root
         * @param prev  the {@code AVLNode} corresponding to element (start - 1)
         *          of the collection, or null if start is 0
         * @param next  the {@code AVLNode} corresponding to element (end + 1)
         *          of the collection, or null if end is the last element of the collection
         */
        private AVLNode(final Iterator<? extends E> iterator, final int start, final int end, final int absolutePositionOfParent, final AVLNode<E> prev, final AVLNode<E> next) {
            final int mid = start + (end - start) / 2;
            if (start < mid) {
                left = new AVLNode<>(iterator, start, mid - 1, mid, prev, this);
            } else {
                leftIsPrevious = true;
                left = prev;
            }
            value = iterator.next();
            relativePosition = mid - absolutePositionOfParent;
            if (mid < end) {
                right = new AVLNode<>(iterator, mid + 1, end, mid, this, next);
            } else {
                rightIsNext = true;
                right = next;
            }
            recalcHeight();
        }

        /**
         * Appends the elements of another tree list to this tree list by efficiently
         * merging the two AVL trees. This operation is destructive to both trees and
         * runs in O(log(m + n)) time.
         *
         * @param otherTree
         *            the root of the AVL tree to merge with this one
         * @param currentSize
         *            the number of elements in this AVL tree
         * @return the root of the new, merged AVL tree
         */
        private AVLNode<E> addAll(AVLNode<E> otherTree, final int currentSize) {
            final AVLNode<E> maxNode = max();
            final AVLNode<E> otherTreeMin = otherTree.min();
            // We need to efficiently merge the two AVL trees while keeping them
            // balanced (or nearly balanced). To do this, we take the shorter
            // tree and combine it with a similar-height subtree of the taller
            // tree. There are two symmetric cases:
            //   * this tree is taller, or
            //   * otherTree is taller.
            if (otherTree.height > height) {
                // CASE 1: The other tree is taller than this one. We will thus
                // merge this tree into otherTree.
                // STEP 1: Remove the maximum element from this tree.
                final AVLNode<E> leftSubTree = removeMax();
                // STEP 2: Navigate left from the root of otherTree until we
                // find a subtree, s, that is no taller than me. (While we are
                // navigating left, we store the nodes we encounter in a stack
                // so that we can re-balance them in step 4.)
                final Deque<AVLNode<E>> sAncestors = new ArrayDeque<>();
                AVLNode<E> s = otherTree;
                int sAbsolutePosition = s.relativePosition + currentSize;
                int sParentAbsolutePosition = 0;
                while (s != null && s.height > getHeight(leftSubTree)) {
                    sParentAbsolutePosition = sAbsolutePosition;
                    sAncestors.push(s);
                    s = s.left;
                    if (s != null) {
                        sAbsolutePosition += s.relativePosition;
                    }
                }
                // STEP 3: Replace s with a newly constructed subtree whose root
                // is maxNode, whose left subtree is leftSubTree, and whose right
                // subtree is s.
                maxNode.setLeft(leftSubTree, null);
                maxNode.setRight(s, otherTreeMin);
                if (leftSubTree != null) {
                    leftSubTree.max().setRight(null, maxNode);
                    leftSubTree.relativePosition -= currentSize - 1;
                }
                if (s != null) {
                    s.min().setLeft(null, maxNode);
                    s.relativePosition = sAbsolutePosition - currentSize + 1;
                }
                maxNode.relativePosition = currentSize - 1 - sParentAbsolutePosition;
                otherTree.relativePosition += currentSize;
                // STEP 4: Re-balance the tree and recalculate the heights of s's ancestors.
                s = maxNode;
                while (!sAncestors.isEmpty()) {
                    final AVLNode<E> sAncestor = sAncestors.pop();
                    sAncestor.setLeft(s, null);
                    s = sAncestor.balance();
                }
                return s;
            }
            otherTree = otherTree.removeMin();
            final Deque<AVLNode<E>> sAncestors = new ArrayDeque<>();
            AVLNode<E> s = this;
            int sAbsolutePosition = s.relativePosition;
            int sParentAbsolutePosition = 0;
            while (s != null && s.height > getHeight(otherTree)) {
                sParentAbsolutePosition = sAbsolutePosition;
                sAncestors.push(s);
                s = s.right;
                if (s != null) {
                    sAbsolutePosition += s.relativePosition;
                }
            }
            otherTreeMin.setRight(otherTree, null);
            otherTreeMin.setLeft(s, maxNode);
            if (otherTree != null) {
                otherTree.min().setLeft(null, otherTreeMin);
                otherTree.relativePosition++;
            }
            if (s != null) {
                s.max().setRight(null, otherTreeMin);
                s.relativePosition = sAbsolutePosition - currentSize;
            }
            otherTreeMin.relativePosition = currentSize - sParentAbsolutePosition;
            s = otherTreeMin;
            while (!sAncestors.isEmpty()) {
                final AVLNode<E> sAncestor = sAncestors.pop();
                sAncestor.setRight(s, null);
                s = sAncestor.balance();
            }
            return s;
        }

        /**
         * Balances according to the AVL algorithm.
         */
        private AVLNode<E> balance() {
            switch(heightRightMinusLeft()) {
                case 1:
                case 0:
                case -1:
                    return this;
                case -2:
                    if (left.heightRightMinusLeft() > 0) {
                        setLeft(left.rotateLeft(), null);
                    }
                    return rotateRight();
                case 2:
                    if (right.heightRightMinusLeft() < 0) {
                        setRight(right.rotateRight(), null);
                    }
                    return rotateLeft();
                default:
                    throw new IllegalStateException("tree inconsistent!");
            }
        }

        AVLNode<E> get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Gets the height of the node or -1 if the node is null.
         */
        private int getHeight(final AVLNode<E> node) {
            return node == null ? -1 : node.height;
        }

        /**
         * Gets the left node, returning null if it's a faedelung.
         */
        private AVLNode<E> getLeftSubTree() {
            return leftIsPrevious ? null : left;
        }

        /**
         * Gets the relative position.
         */
        private int getOffset(final AVLNode<E> node) {
            if (node == null) {
                return 0;
            }
            return node.relativePosition;
        }

        /**
         * Gets the right node, returning null if it's a faedelung.
         */
        private AVLNode<E> getRightSubTree() {
            return rightIsNext ? null : right;
        }

        E getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Returns the height difference right - left
         */
        private int heightRightMinusLeft() {
            return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
        }

        int indexOf(final Object object, final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        AVLNode<E> insert(final int index, final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private AVLNode<E> insertOnLeft(final int indexRelativeToMe, final E obj) {
            if (getLeftSubTree() == null) {
                setLeft(new AVLNode<>(-1, obj, this, left), null);
            } else {
                setLeft(left.insert(indexRelativeToMe, obj), null);
            }
            if (relativePosition >= 0) {
                relativePosition++;
            }
            final AVLNode<E> ret = balance();
            recalcHeight();
            return ret;
        }

        private AVLNode<E> insertOnRight(final int indexRelativeToMe, final E obj) {
            if (getRightSubTree() == null) {
                setRight(new AVLNode<>(+1, obj, right, this), null);
            } else {
                setRight(right.insert(indexRelativeToMe, obj), null);
            }
            if (relativePosition < 0) {
                relativePosition--;
            }
            final AVLNode<E> ret = balance();
            recalcHeight();
            return ret;
        }

        /**
         * Gets the rightmost child of this node.
         *
         * @return the rightmost child (greatest index)
         */
        private AVLNode<E> max() {
            return getRightSubTree() == null ? this : right.max();
        }

        /**
         * Gets the leftmost child of this node.
         *
         * @return the leftmost child (smallest index)
         */
        private AVLNode<E> min() {
            return getLeftSubTree() == null ? this : left.min();
        }

        AVLNode<E> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        AVLNode<E> previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the height by calculation.
         */
        private void recalcHeight() {
            height = Math.max(getLeftSubTree() == null ? -1 : getLeftSubTree().height, getRightSubTree() == null ? -1 : getRightSubTree().height) + 1;
        }

        AVLNode<E> remove(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private AVLNode<E> removeMax() {
            if (getRightSubTree() == null) {
                return removeSelf();
            }
            setRight(right.removeMax(), right.right);
            if (relativePosition < 0) {
                relativePosition++;
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeMin() {
            if (getLeftSubTree() == null) {
                return removeSelf();
            }
            setLeft(left.removeMin(), left.left);
            if (relativePosition > 0) {
                relativePosition--;
            }
            recalcHeight();
            return balance();
        }

        /**
         * Removes this node from the tree.
         *
         * @return the node that replaces this one in the parent
         */
        private AVLNode<E> removeSelf() {
            if (getRightSubTree() == null && getLeftSubTree() == null) {
                return null;
            }
            if (getRightSubTree() == null) {
                if (relativePosition > 0) {
                    left.relativePosition += relativePosition;
                }
                left.max().setRight(null, right);
                return left;
            }
            if (getLeftSubTree() == null) {
                right.relativePosition += relativePosition - (relativePosition < 0 ? 0 : 1);
                right.min().setLeft(null, left);
                return right;
            }
            if (heightRightMinusLeft() > 0) {
                // more on the right, so delete from the right
                final AVLNode<E> rightMin = right.min();
                value = rightMin.value;
                if (leftIsPrevious) {
                    left = rightMin.left;
                }
                right = right.removeMin();
                if (relativePosition < 0) {
                    relativePosition++;
                }
            } else {
                // more on the left or equal, so delete from the left
                final AVLNode<E> leftMax = left.max();
                value = leftMax.value;
                if (rightIsNext) {
                    right = leftMax.right;
                }
                final AVLNode<E> leftPrevious = left.left;
                left = left.removeMax();
                if (left == null) {
                    // special case where left that was deleted was a double link
                    // only occurs when height difference is equal
                    left = leftPrevious;
                    leftIsPrevious = true;
                }
                if (relativePosition > 0) {
                    relativePosition--;
                }
            }
            recalcHeight();
            return this;
        }

        private AVLNode<E> rotateLeft() {
            // can't be faedelung!
            final AVLNode<E> newTop = right;
            final AVLNode<E> movedNode = getRightSubTree().getLeftSubTree();
            final int newTopPosition = relativePosition + getOffset(newTop);
            final int myNewPosition = -newTop.relativePosition;
            final int movedPosition = getOffset(newTop) + getOffset(movedNode);
            setRight(movedNode, newTop);
            newTop.setLeft(this, null);
            setOffset(newTop, newTopPosition);
            setOffset(this, myNewPosition);
            setOffset(movedNode, movedPosition);
            return newTop;
        }

        private AVLNode<E> rotateRight() {
            // can't be faedelung
            final AVLNode<E> newTop = left;
            final AVLNode<E> movedNode = getLeftSubTree().getRightSubTree();
            final int newTopPosition = relativePosition + getOffset(newTop);
            final int myNewPosition = -newTop.relativePosition;
            final int movedPosition = getOffset(newTop) + getOffset(movedNode);
            setLeft(movedNode, newTop);
            newTop.setRight(this, null);
            setOffset(newTop, newTopPosition);
            setOffset(this, myNewPosition);
            setOffset(movedNode, movedPosition);
            return newTop;
        }

        /**
         * Sets the left field to the node, or the previous node if that is null
         *
         * @param node  the new left subtree node
         * @param previous  the previous node in the linked list
         */
        private void setLeft(final AVLNode<E> node, final AVLNode<E> previous) {
            leftIsPrevious = node == null;
            left = leftIsPrevious ? previous : node;
            recalcHeight();
        }

        /**
         * Sets the relative position.
         */
        private int setOffset(final AVLNode<E> node, final int newOffset) {
            if (node == null) {
                return 0;
            }
            final int oldOffset = getOffset(node);
            node.relativePosition = newOffset;
            return oldOffset;
        }

        /**
         * Sets the right field to the node, or the next node if that is null
         *
         * @param node  the new left subtree node
         * @param next  the next node in the linked list
         */
        private void setRight(final AVLNode<E> node, final AVLNode<E> next) {
            rightIsNext = node == null;
            right = rightIsNext ? next : node;
            recalcHeight();
        }

        void setValue(final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void toArray(final Object[] array, final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        //      private void checkFaedelung() {
        //          AVLNode maxNode = left.max();
        //          if (!maxNode.rightIsFaedelung || maxNode.right != this) {
        //              throw new RuntimeException(maxNode + " should right-faedel to " + this);
        //          }
        //          AVLNode minNode = right.min();
        //          if (!minNode.leftIsFaedelung || minNode.left != this) {
        //              throw new RuntimeException(maxNode + " should left-faedel to " + this);
        //          }
        //      }
        //
        //        private int checkTreeDepth() {
        //            int hright = (getRightSubTree() == null ? -1 : getRightSubTree().checkTreeDepth());
        //            //          System.out.print("checkTreeDepth");
        //            //          System.out.print(this);
        //            //          System.out.print(" left: ");
        //            //          System.out.print(_left);
        //            //          System.out.print(" right: ");
        //            //          System.out.println(_right);
        //
        //            int hleft = (left == null ? -1 : left.checkTreeDepth());
        //            if (height != Math.max(hright, hleft) + 1) {
        //                throw new RuntimeException(
        //                    "height should be max" + hleft + "," + hright + " but is " + height);
        //            }
        //            return height;
        //        }
        //
        //        private int checkLeftSubNode() {
        //            if (getLeftSubTree() == null) {
        //                return 0;
        //            }
        //            int count = 1 + left.checkRightSubNode();
        //            if (left.relativePosition != -count) {
        //                throw new RuntimeException();
        //            }
        //            return count + left.checkLeftSubNode();
        //        }
        //
        //        private int checkRightSubNode() {
        //            AVLNode right = getRightSubTree();
        //            if (right == null) {
        //                return 0;
        //            }
        //            int count = 1;
        //            count += right.checkLeftSubNode();
        //            if (right.relativePosition != count) {
        //                throw new RuntimeException();
        //            }
        //            return count + right.checkRightSubNode();
        //        }
        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A list iterator over the linked list.
     */
    static class TreeListIterator<E> implements ListIterator<E>, OrderedIterator<E> {

        /**
         * The parent list
         */
        private final TreeList<E> parent;

        /**
         * Cache of the next node that will be returned by {@link #next()}.
         */
        private AVLNode<E> next;

        /**
         * The index of the next node to be returned.
         */
        private int nextIndex;

        /**
         * Cache of the last node that was returned by {@link #next()}
         * or {@link #previous()}.
         */
        private AVLNode<E> current;

        /**
         * The index of the last node that was returned.
         */
        private int currentIndex;

        /**
         * The modification count that the list is expected to have. If the list
         * doesn't have this count, then a
         * {@link java.util.ConcurrentModificationException} may be thrown by
         * the operations.
         */
        private int expectedModCount;

        /**
         * Create a ListIterator for a list.
         *
         * @param parent  the parent list
         * @param fromIndex  the index to start at
         */
        protected TreeListIterator(final TreeList<E> parent, final int fromIndex) {
            this.parent = parent;
            this.expectedModCount = parent.modCount;
            this.next = parent.root == null ? null : parent.root.get(fromIndex);
            this.nextIndex = fromIndex;
            this.currentIndex = -1;
        }

        @Override
        public void add(final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void checkModCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void set(final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The root node in the AVL tree
     */
    private AVLNode<E> root;

    /**
     * The current size of the list
     */
    private int size;

    /**
     * Constructs a new empty list.
     */
    public TreeList() {
    }

    /**
     * Constructs a new empty list that copies the specified collection.
     *
     * @param coll  the collection to copy
     * @throws NullPointerException if the collection is null
     */
    public TreeList(final Collection<? extends E> coll) {
        if (!coll.isEmpty()) {
            root = new AVLNode<>(coll);
            size = coll.size();
        }
    }

    @Override
    public void add(final int index, final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks whether the index is valid.
     *
     * @param index  the index to check
     * @param startIndex  the first allowed index
     * @param endIndex  the last allowed index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    private void checkInterval(final int index, final int startIndex, final int endIndex) {
        if (index < startIndex || index > endIndex) {
            throw new IndexOutOfBoundsException("Invalid index:" + index + ", size=" + size());
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int indexOf(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator(final int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E remove(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E set(final int index, final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
