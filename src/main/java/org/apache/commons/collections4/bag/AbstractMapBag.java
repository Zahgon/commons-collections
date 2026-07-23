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
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.set.UnmodifiableSet;

/**
 * Abstract implementation of the {@link Bag} interface to simplify the creation
 * of subclass implementations.
 * <p>
 * Subclasses specify a Map implementation to use as the internal storage. The
 * map will be used to map bag elements to a number; the number represents the
 * number of occurrences of that element in the bag.
 * </p>
 *
 * @param <E> the type of elements in this bag
 * @since 3.0 (previously DefaultMapBag v2.0)
 */
public abstract class AbstractMapBag<E> implements Bag<E> {

    /**
     * Inner class iterator for the Bag.
     */
    static class BagIterator<E> implements Iterator<E> {

        private final AbstractMapBag<E> parent;

        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;

        private Map.Entry<E, MutableInteger> current;

        private int itemCount;

        private final int mods;

        private boolean canRemove;

        /**
         * Constructs a new instance.
         *
         * @param parent the parent bag
         */
        BagIterator(final AbstractMapBag<E> parent) {
            this.parent = parent;
            this.entryIterator = parent.map.entrySet().iterator();
            this.current = null;
            this.mods = parent.modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
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
     * Mutable integer class for storing the data.
     */
    protected static class MutableInteger {

        /**
         * The value of this mutable.
         */
        protected int value;

        /**
         * Constructs a new instance.
         * @param value the initial value
         */
        MutableInteger(final int value) {
            this.value = value;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The map to use to store the data
     */
    private transient Map<E, MutableInteger> map;

    /**
     * The current total size of the bag
     */
    private int size;

    /**
     * The modification count for fail fast iterators
     */
    private transient int modCount;

    /**
     * Unique view of the elements
     */
    private transient Set<E> uniqueSet;

    /**
     * Constructor needed for subclass serialization.
     */
    protected AbstractMapBag() {
    }

    /**
     * Constructor that assigns the specified Map as the backing store. The map
     * must be empty and non-null.
     *
     * @param map the map to assign
     */
    protected AbstractMapBag(final Map<E, MutableInteger> map) {
        this.map = Objects.requireNonNull(map, "map");
    }

    /**
     * Constructs a new instance that assigns the specified Map as the backing store. The map
     * must be empty and non-null. The bag is filled from the iterable elements.
     *
     * @param map the map to assign.
     * @param iterable The bag is filled from these iterable elements.
     */
    protected AbstractMapBag(final Map<E, MutableInteger> map, final Iterable<? extends E> iterable) {
        this(map);
        iterable.forEach(this::add);
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean add(final E object, final int nCopies) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
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

    boolean containsAll(final Bag<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doReadObject(final Map<E, MutableInteger> map, final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getCount(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Map<E, MutableInteger> getMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object, final int nCopies) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean retainAll(final Bag<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
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

    @Override
    public <T> T[] toArray(T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<E> uniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
