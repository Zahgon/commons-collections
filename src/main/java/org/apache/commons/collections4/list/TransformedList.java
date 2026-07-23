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
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;

/**
 * Decorates another {@code List} to transform objects that are added.
 * <p>
 * The add and set methods are affected by this class.
 * Thus objects must be removed or searched for using their transformed form.
 * For example, if the transformation converts Strings to Integers, you must
 * use the Integer form to remove objects.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.0
 */
public class TransformedList<E> extends TransformedCollection<E> implements List<E> {

    /**
     * Inner class Iterator for the TransformedList
     */
    protected class TransformedListIterator extends AbstractListIteratorDecorator<E> {

        /**
         * Create a new transformed list iterator.
         *
         * @param iterator  the list iterator to decorate
         */
        protected TransformedListIterator(final ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void add(E object) {
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
    private static final long serialVersionUID = 1077193035000013141L;

    public static <E> TransformedList<E> transformedList(final List<E> list, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> TransformedList<E> transformingList(final List<E> list, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the list being decorated, they
     * are NOT transformed.
     *
     * @param list  the list to decorate, must not be null
     * @param transformer  the transformer to use for conversion, must not be null
     * @throws NullPointerException if list or transformer is null
     */
    protected TransformedList(final List<E> list, final Transformer<? super E, ? extends E> transformer) {
        super(list, transformer);
    }

    @Override
    public void add(final int index, E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final int index, Collection<? extends E> coll) {
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

    protected List<E> getList() {
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
    public E set(final int index, E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
