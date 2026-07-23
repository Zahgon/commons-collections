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
package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * Decorates another {@code Set} to ensure that the order of addition is
 * retained and used by the iterator.
 * <p>
 * If an object is added to the set for a second time, it will remain in the
 * original position in the iteration. The order can be observed from the set
 * via the iterator or toArray methods.
 * </p>
 * <p>
 * The ListOrderedSet also has various useful direct methods. These include many
 * from {@code List}, such as {@code get(int)},
 * {@code remove(int)} and {@code indexOf(int)}. An unmodifiable
 * {@code List} view of the set can be obtained via {@code asList()}.
 * </p>
 * <p>
 * This class cannot implement the {@code List} interface directly as
 * various interface methods (notably equals/hashCode) are incompatible with a
 * set.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in this set
 * @since 3.0
 */
public class ListOrderedSet<E> extends AbstractSerializableSetDecorator<E> {

    /**
     * Internal iterator handle remove.
     */
    static class OrderedSetIterator<E> extends AbstractIteratorDecorator<E> implements OrderedIterator<E> {

        /**
         * Object we iterate on
         */
        private final Collection<E> set;

        /**
         * Last object retrieved
         */
        private E last;

        private OrderedSetIterator(final ListIterator<E> iterator, final Collection<E> set) {
            super(iterator);
            this.set = set;
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
        public E previous() {
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
    private static final long serialVersionUID = -228664372470420141L;

    public static <E> ListOrderedSet<E> listOrderedSet(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListOrderedSet<E> listOrderedSet(final Set<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListOrderedSet<E> listOrderedSet(final Set<E> set, final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Internal list to hold the sequence of objects
     */
    private final List<E> setOrder;

    /**
     * Constructs a new empty {@code ListOrderedSet} using a
     * {@code HashSet} and an {@code ArrayList} internally.
     *
     * @since 3.1
     */
    public ListOrderedSet() {
        super(new HashSet<>());
        setOrder = new ArrayList<>();
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param set the set to decorate, must not be null
     * @throws NullPointerException if set is null
     */
    protected ListOrderedSet(final Set<E> set) {
        super(set);
        setOrder = new ArrayList<>(set);
    }

    /**
     * Constructor that wraps (not copies) the Set and specifies the list to
     * use.
     * <p>
     * The set and list must both be correctly initialized to the same elements.
     * </p>
     *
     * @param set the set to decorate, must not be null
     * @param list the list to decorate, must not be null
     * @throws NullPointerException if set or list is null
     */
    protected ListOrderedSet(final Set<E> set, final List<E> list) {
        super(set);
        setOrder = Objects.requireNonNull(list, "list");
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void add(final int index, final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<E> asList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int indexOf(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OrderedIterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

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
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Uses the underlying List's toString so that order is achieved. This means
     * that the decorated Set's toString is not used, so any custom toStrings
     * will be ignored.
     *
     * @return a string representation of the ordered set
     */
    // Fortunately List.toString and Set.toString look the same
    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
