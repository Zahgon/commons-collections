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
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;

/**
 * Decorates another {@code List} to validate that all additions
 * match a specified predicate.
 * <p>
 * This list exists to provide validation for the decorated list.
 * It is normally created to decorate an empty list.
 * If an object cannot be added to the list, an IllegalArgumentException is thrown.
 * </p>
 * <p>
 * One usage would be to ensure that no null entries are added to the list.
 * </p>
 * <pre>
 * {@code
 * List<String> list =
 *   PredicatedList.predicatedList(new ArrayList<String>(), PredicateUtils.notNullPredicate());
 * }
 * </pre>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.0
 */
public class PredicatedList<E> extends PredicatedCollection<E> implements List<E> {

    /**
     * Inner class Iterator for the PredicatedList
     */
    protected class PredicatedListIterator extends AbstractListIteratorDecorator<E> {

        /**
         * Create a new predicated list iterator.
         *
         * @param iterator  the list iterator to decorate
         */
        protected PredicatedListIterator(final ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void set(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -5722039223898659102L;

    public static <T> PredicatedList<T> predicatedList(final List<T> list, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the list being decorated, they
     * are validated.
     *
     * @param list  the list to decorate, must not be null
     * @param predicate  the predicate to use for validation, must not be null
     * @throws NullPointerException if list or predicate is null
     * @throws IllegalArgumentException if the list contains invalid elements
     */
    protected PredicatedList(final List<E> list, final Predicate<? super E> predicate) {
        super(list, predicate);
    }

    @Override
    public void add(final int index, final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected List<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int indexOf(final Object object) {
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
    public ListIterator<E> listIterator(final int i) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E remove(final int index) {
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
