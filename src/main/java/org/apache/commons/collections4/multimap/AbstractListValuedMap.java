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
package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.ListValuedMap;

/**
 * Abstract implementation of the {@link ListValuedMap} interface to simplify
 * the creation of subclass implementations.
 * <p>
 * Subclasses specify a Map implementation to use as the internal storage and
 * the List implementation to use as values.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.1
 */
public abstract class AbstractListValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements ListValuedMap<K, V> {

    /**
     * Values ListIterator
     */
    private final class ValuesListIterator implements ListIterator<V> {

        private final K key;

        private List<V> values;

        private ListIterator<V> iterator;

        ValuesListIterator(final K key) {
            this.key = key;
            this.values = ListUtils.emptyIfNull(getMap().get(key));
            this.iterator = values.listIterator();
        }

        ValuesListIterator(final K key, final int index) {
            this.key = key;
            this.values = ListUtils.emptyIfNull(getMap().get(key));
            this.iterator = values.listIterator(index);
        }

        @Override
        public void add(final V value) {
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
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V previous() {
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
        public void set(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Wrapped list to handle add and remove on the list returned by get(object)
     */
    private final class WrappedList extends WrappedCollection implements List<V> {

        WrappedList(final K key) {
            super(key);
        }

        @Override
        public void add(final int index, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean addAll(final int index, final Collection<? extends V> c) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean equals(final Object other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected List<V> getMapping() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int indexOf(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int lastIndexOf(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ListIterator<V> listIterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ListIterator<V> listIterator(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V remove(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V set(final int index, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public List<V> subList(final int fromIndex, final int toIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Constructor needed for subclass serialization.
     */
    protected AbstractListValuedMap() {
    }

    /**
     * A constructor that wraps, not copies
     *
     * @param map  the map to wrap, must not be null
     * @throws NullPointerException if the map is null
     */
    protected AbstractListValuedMap(final Map<K, ? extends List<V>> map) {
        super(map);
    }

    /**
     * Creates a new value collection using the provided factory.
     * @return a new list
     */
    @Override
    protected abstract List<V> createCollection();

    @Override
    public List<V> get(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<K, List<V>> getMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<V> remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    List<V> wrappedCollection(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
