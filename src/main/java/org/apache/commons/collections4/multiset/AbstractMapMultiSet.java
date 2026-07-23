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
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;

/**
 * Abstract implementation of the {@link MultiSet} interface to simplify the
 * creation of subclass implementations.
 * <p>
 * Subclasses specify a Map implementation to use as the internal storage. The
 * map will be used to map multiset elements to a number; the number represents the
 * number of occurrences of that element in the multiset.
 * </p>
 *
 * @param <E> the type held in the multiset.
 * @since 4.1
 */
public abstract class AbstractMapMultiSet<E> extends AbstractMultiSet<E> {

    /**
     * Inner class EntrySetIterator.
     *
     * @param <E> the element type.
     */
    protected static class EntrySetIterator<E> implements Iterator<Entry<E>> {

        /**
         * The parent map
         */
        protected final AbstractMapMultiSet<E> parent;

        /**
         * The source Iterator.
         */
        protected final Iterator<Map.Entry<E, MutableInteger>> decorated;

        /**
         * The last returned entry
         */
        protected Entry<E> last;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param decorated  the iterator to decorate
         * @param parent  the parent multiset
         */
        protected EntrySetIterator(final Iterator<Map.Entry<E, MutableInteger>> decorated, final AbstractMapMultiSet<E> parent) {
            this.decorated = decorated;
            this.parent = parent;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Entry<E> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class iterator for the MultiSet.
     */
    private static final class MapBasedMultiSetIterator<E> implements Iterator<E> {

        private final AbstractMapMultiSet<E> parent;

        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;

        private Map.Entry<E, MutableInteger> current;

        private int itemCount;

        private final int mods;

        private boolean canRemove;

        /**
         * Constructs a new instance.
         *
         * @param parent the parent multiset
         */
        MapBasedMultiSetIterator(final AbstractMapMultiSet<E> parent) {
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
     * Inner class MultiSetEntry.
     *
     * @param <E> the key type.
     */
    protected static class MultiSetEntry<E> extends AbstractEntry<E> {

        /**
         * The parent entry.
         */
        protected final Map.Entry<E, MutableInteger> parentEntry;

        /**
         * Constructs a new instance.
         * @param parentEntry  the entry to decorate
         */
        protected MultiSetEntry(final Map.Entry<E, MutableInteger> parentEntry) {
            this.parentEntry = parentEntry;
        }

        @Override
        public int getCount() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E getElement() {
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
     * Inner class UniqueSetIterator.
     *
     * @param <E> the element type.
     */
    protected static class UniqueSetIterator<E> extends AbstractIteratorDecorator<E> {

        /**
         * The parent multiset
         */
        protected final AbstractMapMultiSet<E> parent;

        /**
         * The last returned element
         */
        protected E lastElement;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param iterator  the iterator to decorate
         * @param parent  the parent multiset
         */
        protected UniqueSetIterator(final Iterator<E> iterator, final AbstractMapMultiSet<E> parent) {
            super(iterator);
            this.parent = parent;
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
     * The map to use to store the data
     */
    private transient Map<E, MutableInteger> map;

    /**
     * The current total size of the multiset
     */
    private transient int size;

    /**
     * The modification count for fail fast iterators
     */
    private transient int modCount;

    /**
     * Constructor needed for subclass serialization.
     */
    protected AbstractMapMultiSet() {
    }

    /**
     * Constructor that assigns the specified Map as the backing store. The map
     * must be empty and non-null.
     *
     * @param map the map to assign
     */
    protected AbstractMapMultiSet(final Map<E, MutableInteger> map) {
        this.map = map;
    }

    @Override
    public int add(final E object, final int occurrences) {
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
    protected Iterator<Entry<E>> createEntrySetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Iterator<E> createUniqueSetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
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
    public int remove(final Object object, final int occurrences) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void setMap(final Map<E, MutableInteger> map) {
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
    protected int uniqueElements() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
