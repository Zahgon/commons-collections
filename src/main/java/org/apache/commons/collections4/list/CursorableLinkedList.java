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
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A {@code List} implementation with a {@code ListIterator} that
 * allows concurrent modifications to the underlying list.
 * <p>
 * This implementation supports all of the optional {@link List} operations.
 * It extends {@code AbstractLinkedList} and thus provides the
 * stack/queue/dequeue operations available in {@link java.util.LinkedList}.
 * </p>
 * <p>
 * The main feature of this class is the ability to modify the list and the
 * iterator at the same time. Both the {@link #listIterator()} and {@link #cursor()}
 * methods provides access to a {@code Cursor} instance which extends
 * {@code ListIterator}. The cursor allows changes to the list concurrent
 * with changes to the iterator. Note that the {@link #iterator()} method and
 * sublists do <strong>not</strong> provide this cursor behavior.
 * </p>
 * <p>
 * The {@code Cursor} class is provided partly for backwards compatibility
 * and partly because it allows the cursor to be directly closed. Closing the
 * cursor is optional because references are held via a {@code WeakReference}.
 * For most purposes, simply modify the iterator and list at will, and then let
 * the garbage collector to the rest.
 * </p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong>
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @see java.util.LinkedList
 * @since 1.0
 * @deprecated parent {@link AbstractLinkedList} is source incompatible with List methods added in Java 21
 */
@Deprecated
public class CursorableLinkedList<E> extends AbstractLinkedList<E> implements Serializable {

    /**
     * An extended {@code ListIterator} that allows concurrent changes to
     * the underlying list.
     *
     * @param <E> the type of elements in this cursor.
     */
    public static class Cursor<E> extends AbstractLinkedList.LinkedListIterator<E> {

        /**
         * Is the cursor valid (not closed)
         */
        boolean valid = true;

        /**
         * Is the next index valid
         */
        boolean nextIndexValid = true;

        /**
         * Flag to indicate if the current element was removed by another object.
         */
        boolean currentRemovedByAnother;

        /**
         * Constructs a new cursor.
         *
         * @param parent  the parent list
         * @param index  the index to start from
         */
        protected Cursor(final CursorableLinkedList<E> parent, final int index) {
            super(parent, index);
            valid = true;
        }

        @Override
        public void add(final E obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected void checkModCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        // set is not overridden, as it works ok
        // note that we want it to throw an exception if the element being
        // set has been removed from the real list (compare this with the
        // remove method where we silently ignore this case)
        public void close() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void nodeChanged(final Node<E> node) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void nodeInserted(final Node<E> node) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void nodeRemoved(final Node<E> node) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A cursor for the sublist based on LinkedSubListIterator.
     *
     * @param <E> the type of elements in this cursor.
     * @since 3.2
     */
    protected static class SubCursor<E> extends Cursor<E> {

        /**
         * The parent list
         */
        protected final LinkedSubList<E> sub;

        /**
         * Constructs a new cursor.
         *
         * @param sub  the sub list
         * @param index  the index to start from
         */
        protected SubCursor(final LinkedSubList<E> sub, final int index) {
            super((CursorableLinkedList<E>) sub.parent, index + sub.offset);
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
     * Ensure serialization compatibility
     */
    private static final long serialVersionUID = 8836393098519411393L;

    /**
     * A list of the cursor currently open on this list
     */
    private transient List<WeakReference<Cursor<E>>> cursors;

    /**
     * Constructor that creates.
     */
    public CursorableLinkedList() {
        // must call init() as use super();
        init();
    }

    /**
     * Constructor that copies the specified collection
     *
     * @param coll  the collection to copy
     */
    public CursorableLinkedList(final Collection<? extends E> coll) {
        super(coll);
    }

    @Override
    protected void addNode(final Node<E> nodeToInsert, final Node<E> insertBeforeNode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void broadcastNodeChanged(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void broadcastNodeInserted(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void broadcastNodeRemoved(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected ListIterator<E> createSubListListIterator(final LinkedSubList<E> subList, final int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CursorableLinkedList.Cursor<E> cursor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CursorableLinkedList.Cursor<E> cursor(final int fromIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void init() {
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

    /**
     * Deserializes the data held in this object to the stream specified.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        doReadObject(in);
    }

    protected void registerCursor(final Cursor<E> cursor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void removeAllNodes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void removeNode(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void unregisterCursor(final Cursor<E> cursor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void updateNode(final Node<E> node, final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
