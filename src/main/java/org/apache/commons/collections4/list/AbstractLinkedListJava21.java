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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;

/**
 * An abstract implementation of a linked list which provides numerous points for
 * subclasses to override.
 * <p>
 * Overridable methods are provided to change the storage node and to change how
 * nodes are added to and removed. Hopefully, all you need for unusual subclasses
 * is here.
 * </p>
 * <p>
 * This is a copy of AbstractLinkedList, modified to be compatible with Java 21
 * (see COLLECTIONS-842 for details).
 * </p>
 *
 * @param <E> the type of elements in this list
 * @see AbstractLinkedList
 * @since 4.5.0-M3
 */
public abstract class AbstractLinkedListJava21<E> implements List<E> {

    /*
     * Implementation notes:
     * - a standard circular doubly-linked list
     * - a marker node is stored to mark the start and the end of the list
     * - node creation and removal always occurs through createNode() and
     *   removeNode().
     * - a modification count is kept, with the same semantics as
     * {@link java.util.LinkedList}.
     * - respects {@link AbstractList#modCount}
     */
    /**
     * A list iterator over the linked list.
     *
     * @param <E> the type of elements in this iterator.
     */
    protected static class LinkedListIterator<E> implements ListIterator<E>, OrderedIterator<E> {

        /**
         * The parent list
         */
        protected final AbstractLinkedListJava21<E> parent;

        /**
         * The node that will be returned by {@link #next()}. If this is equal
         * to {@link AbstractLinkedListJava21#header} then there are no more values to return.
         */
        protected Node<E> next;

        /**
         * The index of {@link #next}.
         */
        protected int nextIndex;

        /**
         * The last node that was returned by {@link #next()} or {@link
         * #previous()}. Set to {@code null} if {@link #next()} or {@link
         * #previous()} haven't been called, or if the node has been removed
         * with {@link #remove()} or a new node added with {@link #add(Object)}.
         * Should be accessed through {@link #getLastNodeReturned()} to enforce
         * this behavior.
         */
        protected Node<E> current;

        /**
         * The modification count that the list is expected to have. If the list
         * doesn't have this count, then a
         * {@link java.util.ConcurrentModificationException} may be thrown by
         * the operations.
         */
        protected int expectedModCount;

        /**
         * Create a ListIterator for a list.
         *
         * @param parent  the parent list
         * @param fromIndex  the index to start at
         * @throws IndexOutOfBoundsException if fromIndex is less than 0 or greater than the size of the list
         */
        protected LinkedListIterator(final AbstractLinkedListJava21<E> parent, final int fromIndex) throws IndexOutOfBoundsException {
            this.parent = parent;
            this.expectedModCount = parent.modCount;
            this.next = parent.getNode(fromIndex, true);
            this.nextIndex = fromIndex;
        }

        @Override
        public void add(final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void checkModCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected Node<E> getLastNodeReturned() throws IllegalStateException {
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
     * The sublist implementation for AbstractLinkedListJava21.
     *
     * @param <E> the type of elements in this list.
     */
    protected static class LinkedSubList<E> extends AbstractList<E> {

        /**
         * The main list
         */
        AbstractLinkedListJava21<E> parent;

        /**
         * Offset from the main list
         */
        int offset;

        /**
         * Sublist size
         */
        int size;

        /**
         * Sublist modCount
         */
        int expectedModCount;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedList.
         * @param fromIndex An index greater or equal to 0 and less than {@code toIndex}.
         * @param toIndex An index greater than {@code fromIndex}.
         */
        protected LinkedSubList(final AbstractLinkedListJava21<E> parent, final int fromIndex, final int toIndex) {
            if (fromIndex < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            }
            if (toIndex > parent.size()) {
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            }
            if (fromIndex > toIndex) {
                throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
            }
            this.parent = parent;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.expectedModCount = parent.modCount;
        }

        @Override
        public void add(final int index, final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean addAll(final Collection<? extends E> coll) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends E> coll) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void checkModCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<E> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ListIterator<E> listIterator(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void rangeCheck(final int index, final int beyond) {
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
        public List<E> subList(final int fromIndexInclusive, final int toIndexExclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A list iterator over the linked sub list.
     *
     * @param <E> the type of elements in this iterator.
     */
    protected static class LinkedSubListIterator<E> extends LinkedListIterator<E> {

        /**
         * The sub list
         */
        protected final LinkedSubList<E> sub;

        /**
         * Constructs a new instance.
         *
         * @param sub The sub-list.
         * @param startIndex The starting index.
         */
        protected LinkedSubListIterator(final LinkedSubList<E> sub, final int startIndex) {
            super(sub.parent, startIndex + sub.offset);
            this.sub = sub;
        }

        @Override
        public void add(final E obj) {
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
        public int nextIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A node within the linked list.
     * <p>
     * From Commons Collections 3.1, all access to the {@code value} property
     * is via the methods on this class.
     * </p>
     *
     * @param <E> the type of the node value.
     */
    protected static class Node<E> {

        /**
         * A pointer to the node before this node
         */
        protected Node<E> previous;

        /**
         * A pointer to the node after this node
         */
        protected Node<E> next;

        /**
         * The object contained within this node
         */
        protected E value;

        /**
         * Constructs a new header node.
         */
        protected Node() {
            previous = this;
            next = this;
        }

        /**
         * Constructs a new node.
         *
         * @param value  the value to store
         */
        protected Node(final E value) {
            this.value = value;
        }

        /**
         * Constructs a new node.
         *
         * @param previous  the previous node in the list
         * @param next  the next node in the list
         * @param value  the value to store
         */
        protected Node(final Node<E> previous, final Node<E> next, final E value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }

        protected Node<E> getNextNode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected Node<E> getPreviousNode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected E getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void setNextNode(final Node<E> next) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void setPreviousNode(final Node<E> previous) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void setValue(final E value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A {@link Node} which indicates the start and end of the list and does not
     * hold a value. The value of {@code next} is the first item in the
     * list. The value of {@code previous} is the last item in the list.
     */
    transient Node<E> header;

    /**
     * The size of the list
     */
    transient int size;

    /**
     * Modification count for iterators
     */
    transient int modCount;

    /**
     * Constructor that does nothing (intended for deserialization).
     * <p>
     * If this constructor is used by a serializable subclass then the init()
     * method must be called.
     * </p>
     */
    protected AbstractLinkedListJava21() {
    }

    /**
     * Constructs a list copying data from the specified collection.
     *
     * @param coll  the collection to copy
     */
    protected AbstractLinkedListJava21(final Collection<? extends E> coll) {
        init();
        addAll(coll);
    }

    @Override
    public boolean add(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void add(final int index, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addFirst(final E o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addLast(final E o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void addNode(final Node<E> nodeToInsert, final Node<E> insertBeforeNode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void addNodeAfter(final Node<E> node, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void addNodeBefore(final Node<E> node, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Node<E> createHeaderNode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Node<E> createNode(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<E> createSubListIterator(final LinkedSubList<E> subList) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected ListIterator<E> createSubListListIterator(final LinkedSubList<E> subList, final int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    protected void doReadObject(final ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doWriteObject(final ObjectOutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E getFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E getLast() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Node<E> getNode(final int index, final boolean endMarkerAllowed) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int indexOf(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void init() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualValue(final Object value1, final Object value2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int lastIndexOf(final Object value) {
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
    public boolean remove(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void removeAllNodes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E removeFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E removeLast() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void removeNode(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E set(final int index, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<E> subList(final int fromIndexInclusive, final int toIndexExclusive) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateNode(final Node<E> node, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
