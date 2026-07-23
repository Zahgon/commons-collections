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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.commons.collections4.multiset.AbstractMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;

/**
 * Abstract implementation of the {@link MultiValuedMap} interface to simplify
 * the creation of subclass implementations.
 * <p>
 * Subclasses specify a Map implementation to use as the internal storage.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.1
 */
public abstract class AbstractMultiValuedMap<K, V> implements MultiValuedMap<K, V> {

    /**
     * Inner class that provides the AsMap view.
     */
    private final class AsMap extends AbstractMap<K, Collection<V>> {

        final class AsMapEntrySet extends AbstractSet<Map.Entry<K, Collection<V>>> {

            @Override
            public void clear() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public boolean contains(final Object o) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public boolean remove(final Object o) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public int size() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * EntrySet iterator for the asMap view.
         */
        final class AsMapEntrySetIterator extends AbstractIteratorDecorator<Map.Entry<K, Collection<V>>> {

            AsMapEntrySetIterator(final Iterator<Map.Entry<K, Collection<V>>> iterator) {
                super(iterator);
            }

            @Override
            public Map.Entry<K, Collection<V>> next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        final transient Map<K, Collection<V>> map;

        AsMap(final Map<K, Collection<V>> map) {
            this.map = map;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsKey(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean equals(final Object object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Collection<V> get(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Set<K> keySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Collection<V> remove(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class that provides the Entry<K, V> view
     */
    private final class EntryValues extends AbstractCollection<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class that provides a MultiSet<K> keys view.
     */
    private final class KeysMultiSet extends AbstractMultiSet<K> {

        private final class MapEntryTransformer implements Transformer<Map.Entry<K, Collection<V>>, MultiSet.Entry<K>> {

            @Override
            public MultiSet.Entry<K> transform(final Map.Entry<K, Collection<V>> mapEntry) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        @Override
        public boolean contains(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected Iterator<MultiSet.Entry<K>> createEntrySetIterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int getCount(final Object object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected int uniqueElements() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class for MultiValuedMap Entries.
     */
    private final class MultiValuedMapEntry extends AbstractMapEntry<K, V> {

        MultiValuedMapEntry(final K key, final V value) {
            super(key, value);
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class for MapIterator.
     */
    private final class MultiValuedMapIterator implements MapIterator<K, V> {

        private final Iterator<Entry<K, V>> it;

        private Entry<K, V> current;

        MultiValuedMapIterator() {
            this.it = AbstractMultiValuedMap.this.entries().iterator();
        }

        @Override
        public K getKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class that provides the values view.
     */
    private final class Values extends AbstractCollection<V> {

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<V> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class that provides the values iterator.
     */
    private final class ValuesIterator implements Iterator<V> {

        private final Object key;

        private final Collection<V> values;

        private final Iterator<V> iterator;

        ValuesIterator(final Object key) {
            this.key = key;
            this.values = getMap().get(key);
            this.iterator = values.iterator();
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Wrapped collection to handle add and remove on the collection returned
     * by get(object).
     * <p>
     * Currently, the wrapped collection is not cached and has to be retrieved
     * from the underlying map. This is safe, but not very efficient and
     * should be improved in subsequent releases. For this purpose, the
     * scope of this collection is set to package private to simplify later
     * refactoring.
     */
    class WrappedCollection implements Collection<V> {

        protected final K key;

        WrappedCollection(final K key) {
            this.key = key;
        }

        @Override
        public boolean add(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean addAll(final Collection<? extends V> other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsAll(final Collection<?> other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected Collection<V> getMapping() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<V> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object item) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean removeAll(final Collection<?> c) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean retainAll(final Collection<?> c) {
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
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(final T[] a) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The values view
     */
    private transient Collection<V> valuesView;

    /**
     * The EntryValues view
     */
    private transient EntryValues entryValuesView;

    /**
     * The KeyMultiSet view
     */
    private transient MultiSet<K> keysMultiSetView;

    /**
     * The AsMap view
     */
    private transient AsMap asMapView;

    /**
     * The map used to store the data
     */
    private transient Map<K, Collection<V>> map;

    /**
     * Constructor needed for subclass serialization.
     */
    protected AbstractMultiValuedMap() {
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to wrap, must not be null
     * @throws NullPointerException if the map is null
     */
    @SuppressWarnings("unchecked")
    protected AbstractMultiValuedMap(final Map<K, ? extends Collection<V>> map) {
        this.map = (Map<K, Collection<V>>) Objects.requireNonNull(map, "map");
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsMapping(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new Collection typed for a given subclass.
     *
     * @return a new Collection typed for a given subclass.
     */
    protected abstract Collection<V> createCollection();

    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<Entry<K, V>> entries() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> get(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Map<K, ? extends Collection<V>> getMap() {
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
    public MultiSet<K> keys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public MapIterator<K, V> mapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final K key, final Iterable<? extends V> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final MultiValuedMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeMapping(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    protected void setMap(final Map<K, ? extends Collection<V>> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    Collection<V> wrappedCollection(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
