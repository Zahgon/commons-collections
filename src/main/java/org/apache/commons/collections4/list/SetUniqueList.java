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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;

/**
 * Decorates a {@code List} to ensure that no duplicates are present much
 * like a {@code Set}.
 * <p>
 * The {@code List} interface makes certain assumptions/requirements. This
 * implementation breaks these in certain ways, but this is merely the result of
 * rejecting duplicates. Each violation is explained in the method, but it
 * should not affect you. Bear in mind that Sets require immutable objects to
 * function correctly.
 * </p>
 * <p>
 * The {@link org.apache.commons.collections4.set.ListOrderedSet ListOrderedSet}
 * class provides an alternative approach, by wrapping an existing Set and
 * retaining insertion order in the iterator.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.0
 */
public class SetUniqueList<E> extends AbstractSerializableListDecorator<E> {

    /**
     * Inner class iterator.
     */
    static class SetListIterator<E> extends AbstractIteratorDecorator<E> {

        private final Set<E> set;

        private E last;

        protected SetListIterator(final Iterator<E> it, final Set<E> set) {
            super(it);
            this.set = set;
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class iterator.
     */
    static class SetListListIterator<E> extends AbstractListIteratorDecorator<E> {

        private final Set<E> set;

        private E last;

        protected SetListListIterator(final ListIterator<E> it, final Set<E> set) {
            super(it);
            this.set = set;
        }

        @Override
        public void add(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void set(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 7196982186153478694L;

    public static <E> SetUniqueList<E> setUniqueList(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Internal Set to maintain uniqueness.
     */
    private final Set<E> set;

    /**
     * Constructor that wraps (not copies) the List and specifies the set to use.
     * <p>
     * The set and list must both be correctly initialized to the same elements.
     *
     * @param set  the set to decorate, must not be null
     * @param list  the list to decorate, must not be null
     * @throws NullPointerException if set or list is null
     */
    protected SetUniqueList(final List<E> list, final Set<E> set) {
        super(list);
        this.set = Objects.requireNonNull(set, "set");
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

    public Set<E> asSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
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
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Set<E> createSetBasedOnList(final Set<E> set, final List<E> list) {
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
    public ListIterator<E> listIterator(final int index) {
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
