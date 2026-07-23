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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;

/**
 * A {@code Map} implementation that stores data in simple fields until
 * the size is greater than 3.
 * <p>
 * This map is designed for performance and can outstrip HashMap.
 * It also has good garbage collection characteristics.
 * </p>
 * <ul>
 * <li>Optimized for operation at size 3 or less.
 * <li>Still works well once size 3 exceeded.
 * <li>Gets at size 3 or less are about 0-10% faster than HashMap,
 * <li>Puts at size 3 or less are over 4 times faster than HashMap.
 * <li>Performance 5% slower than HashMap once size 3 exceeded once.
 * </ul>
 * <p>
 * The design uses two distinct modes of operation - flat and delegate.
 * While the map is size 3 or less, operations map straight onto fields using
 * switch statements. Once size 4 is reached, the map switches to delegate mode
 * and only switches back when cleared. In delegate mode, all operations are
 * forwarded straight to a HashMap resulting in the 5% performance loss.
 * </p>
 * <p>
 * The performance gains on puts are due to not needing to create a Map Entry
 * object. This is a large saving not only in performance but in garbage collection.
 * </p>
 * <p>
 * Whilst in flat mode this map is also easy for the garbage collector to dispatch.
 * This is because it contains no complex objects or arrays which slow the progress.
 * </p>
 * <p>
 * Do not use {@code Flat3Map} if the size is likely to grow beyond 3.
 * </p>
 * <p>
 * <strong>Note that Flat3Map is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class Flat3Map<K, V> implements IterableMap<K, V>, Serializable, Cloneable {

    abstract static class EntryIterator<K, V> {

        private final Flat3Map<K, V> parent;

        private int nextIndex;

        private FlatMapEntry<K, V> currentEntry;

        /**
         * Create a new Flat3Map.EntryIterator.
         */
        EntryIterator(final Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Map.Entry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * EntrySet
     */
    static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {

        private final Flat3Map<K, V> parent;

        EntrySet(final Flat3Map<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * EntrySetIterator and MapEntry
     */
    static class EntrySetIterator<K, V> extends EntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        EntrySetIterator(final Flat3Map<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class FlatMapEntry<K, V> implements Map.Entry<K, V> {

        private final Flat3Map<K, V> parent;

        private final int index;

        private volatile boolean removed;

        FlatMapEntry(final Flat3Map<K, V> parent, final int index) {
            this.parent = parent;
            this.index = index;
            this.removed = false;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
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
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        void setRemoved(final boolean removed) {
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
     * FlatMapIterator
     */
    static class FlatMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {

        private final Flat3Map<K, V> parent;

        private int nextIndex;

        private boolean canRemove;

        FlatMapIterator(final Flat3Map<K, V> parent) {
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
        public K next() {
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
     * KeySet
     */
    static class KeySet<K> extends AbstractSet<K> {

        private final Flat3Map<K, ?> parent;

        KeySet(final Flat3Map<K, ?> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * KeySetIterator
     */
    static class KeySetIterator<K> extends EntryIterator<K, Object> implements Iterator<K> {

        @SuppressWarnings("unchecked")
        KeySetIterator(final Flat3Map<K, ?> parent) {
            super((Flat3Map<K, Object>) parent);
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Values
     */
    static class Values<V> extends AbstractCollection<V> {

        private final Flat3Map<?, V> parent;

        Values(final Flat3Map<?, V> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object value) {
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
     * ValuesIterator
     */
    static class ValuesIterator<V> extends EntryIterator<Object, V> implements Iterator<V> {

        @SuppressWarnings("unchecked")
        ValuesIterator(final Flat3Map<?, V> parent) {
            super((Flat3Map<Object, V>) parent);
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -6701087419741928296L;

    /**
     * The size of the map, used while in flat mode
     */
    private transient int size;

    /**
     * Hash, used while in flat mode
     */
    private transient int hash1;

    /**
     * Hash, used while in flat mode
     */
    private transient int hash2;

    /**
     * Hash, used while in flat mode
     */
    private transient int hash3;

    /**
     * Key, used while in flat mode
     */
    private transient K key1;

    /**
     * Key, used while in flat mode
     */
    private transient K key2;

    /**
     * Key, used while in flat mode
     */
    private transient K key3;

    /**
     * Value, used while in flat mode
     */
    private transient V value1;

    /**
     * Value, used while in flat mode
     */
    private transient V value2;

    /**
     * Value, used while in flat mode
     */
    private transient V value3;

    /**
     * Map, used while in delegate mode
     */
    private transient AbstractHashedMap<K, V> delegateMap;

    /**
     * Constructs a new instance.
     */
    public Flat3Map() {
    }

    /**
     * Constructor copying elements from another map.
     *
     * @param map  the map to copy
     * @throws NullPointerException if the map is null
     */
    public Flat3Map(final Map<? extends K, ? extends V> map) {
        putAll(map);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Flat3Map<K, V> clone() {
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

    /**
     * Converts the flat map data to a map.
     */
    private void convertToMap() {
        delegateMap = createDelegateMap();
        switch(// drop through
        size) {
            case 3:
                delegateMap.put(key3, value3);
            case 2:
                delegateMap.put(key2, value2);
            case 1:
                delegateMap.put(key1, value1);
            case 0:
                break;
            default:
                throw new IllegalStateException("Invalid map index: " + size);
        }
        size = 0;
        hash1 = hash2 = hash3 = 0;
        key1 = key2 = key3 = null;
        value1 = value2 = value3 = null;
    }

    protected AbstractHashedMap<K, V> createDelegateMap() {
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
    public V get(final Object key) {
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
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public MapIterator<K, V> mapIterator() {
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

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        final int count = in.readInt();
        if (count > 3) {
            delegateMap = createDelegateMap();
        }
        for (int i = count; i > 0; i--) {
            put((K) in.readObject(), (V) in.readObject());
        }
    }

    @Override
    public V remove(final Object key) {
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

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        for (final MapIterator<?, ?> it = mapIterator(); it.hasNext(); ) {
            // key
            out.writeObject(it.next());
            // value
            out.writeObject(it.getValue());
        }
    }
}
