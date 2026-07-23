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
package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;

/**
 * A {@code Map} implementation that holds a single item and is fixed size.
 * <p>
 * The single key/value pair is specified at creation.
 * The map is fixed size so any action that would change the size is disallowed.
 * However, the {@code put} or {@code setValue} methods can <em>change</em>
 * the value associated with the key.
 * </p>
 * <p>
 * If trying to remove or clear the map, an UnsupportedOperationException is thrown.
 * If trying to put a new mapping into the map, an  IllegalArgumentException is thrown.
 * The put method will only succeed if the key specified is the same as the
 * singleton key.
 * </p>
 * <p>
 * The key and value can be obtained by:
 * </p>
 * <ul>
 * <li>normal Map methods and views
 * <li>the {@code MapIterator}, see {@link #mapIterator()}
 * <li>the {@code KeyValue} interface (just cast - no object creation)
 * </ul>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.1
 */
public class SingletonMap<K, V> implements OrderedMap<K, V>, BoundedMap<K, V>, KeyValue<K, V>, Serializable, Cloneable {

    /**
     * SingletonMapIterator.
     */
    static class SingletonMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {

        private final SingletonMap<K, V> parent;

        private boolean hasNext = true;

        private boolean canGetSet;

        SingletonMapIterator(final SingletonMap<K, V> parent) {
            this.parent = parent;
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
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void reset() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Values implementation for the SingletonMap.
     * This class is needed as values is a view that must update as the map updates.
     *
     * @param <V> the type of the values in this set.
     */
    static class SingletonValues<V> extends AbstractSet<V> implements Serializable {

        private static final long serialVersionUID = -3689524741863047872L;

        private final SingletonMap<?, V> parent;

        SingletonValues(final SingletonMap<?, V> parent) {
            this.parent = parent;
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
        public boolean isEmpty() {
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
     * Serialization version
     */
    private static final long serialVersionUID = -8931271118676803261L;

    /**
     * Singleton key
     */
    private final K key;

    /**
     * Singleton value
     */
    private V value;

    /**
     * Constructor that creates a map of {@code null} to {@code null}.
     */
    public SingletonMap() {
        this.key = null;
    }

    /**
     * Constructor specifying the key and value.
     *
     * @param key  the key to use
     * @param value  the value to use
     */
    public SingletonMap(final K key, final V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Constructor specifying the key and value as a {@code KeyValue}.
     *
     * @param keyValue  the key value pair to use
     */
    public SingletonMap(final KeyValue<K, V> keyValue) {
        this.key = keyValue.getKey();
        this.value = keyValue.getValue();
    }

    /**
     * Constructor specifying the key and value as a {@code MapEntry}.
     *
     * @param mapEntry  the mapEntry to use
     */
    public SingletonMap(final Map.Entry<? extends K, ? extends V> mapEntry) {
        this.key = mapEntry.getKey();
        this.value = mapEntry.getValue();
    }

    /**
     * Constructor copying elements from another map.
     *
     * @param map  the map to copy, must be size 1
     * @throws NullPointerException if the map is null
     * @throws IllegalArgumentException if the size is not 1
     */
    public SingletonMap(final Map<? extends K, ? extends V> map) {
        if (map.size() != 1) {
            throw new IllegalArgumentException("The map size must be 1");
        }
        final Map.Entry<? extends K, ? extends V> entry = map.entrySet().iterator().next();
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public SingletonMap<K, V> clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Map
    @Override
    public V get(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // KeyValue
    @Override
    public K getKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V getValue() {
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

    protected boolean isEqualKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // BoundedMap
    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K lastKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int maxSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K nextKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K previousKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V setValue(final V value) {
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
}
