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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

/**
 * Decorates another {@code List} to fix the size preventing add/remove.
 * <p>
 * The add, remove, clear and retain operations are unsupported.
 * The set method is allowed (as it doesn't change the list size).
 * </p>
 * <p>
 * NOTE:
 * Modifying the decorated list directly would results in influencing the outcome
 * of method calls on this object. For example, the bounds of this list would reflect
 * a newly added object to the underlying list.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of elements in this collection
 * @since 3.0
 */
public class FixedSizeList<E> extends AbstractSerializableListDecorator<E> implements BoundedCollection<E> {

    /**
     * List iterator that only permits changes via set()
     */
    private final class FixedSizeListIterator extends AbstractListIteratorDecorator<E> {

        protected FixedSizeListIterator(final ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(final Object object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -2218010673611160319L;

    public static <E> FixedSizeList<E> fixedSizeList(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static UnsupportedOperationException unsupportedOperationException() {
        return new UnsupportedOperationException("List is fixed size");
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param list  the list to decorate, must not be null
     * @throws NullPointerException if list is null
     */
    protected FixedSizeList(final List<E> list) {
        super(list);
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void add(final int index, final E object) {
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

    @Override
    public void clear() {
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
    public boolean isFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int lastIndexOf(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int maxSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E remove(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E set(final int index, final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
