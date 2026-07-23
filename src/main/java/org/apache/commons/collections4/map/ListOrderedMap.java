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
import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * Decorates a {@code Map} to ensure that the order of addition is retained
 * using a {@code List} to maintain order.
 * <p>
 * The order will be used via the iterators and toArray methods on the views.
 * The order is also returned by the {@code MapIterator}.
 * The {@code orderedMapIterator()} method accesses an iterator that can
 * iterate both forwards and backwards through the map.
 * In addition, non-interface methods are provided to access the map by index.
 * </p>
 * <p>
 * If an object is added to the Map for a second time, it will remain in the
 * original position in the iteration.
 * </p>
 * <p>
 * <strong>Note that ListOrderedMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 * <p>
 * <strong>Note that ListOrderedMap doesn't work with
 * {@link java.util.IdentityHashMap IdentityHashMap}, {@link CaseInsensitiveMap},
 * or similar maps that violate the general contract of {@link java.util.Map}.</strong>
 * The {@code ListOrderedMap} (or, more precisely, the underlying {@code List})
 * is relying on {@link Object#equals(Object) equals()}. This is fine, as long as the
 * decorated {@code Map} is also based on {@link Object#equals(Object) equals()},
 * and {@link Object#hashCode() hashCode()}, which
 * {@link java.util.IdentityHashMap IdentityHashMap}, and
 * {@link CaseInsensitiveMap} don't: The former uses {@code ==}, and
 * the latter uses {@link Object#equals(Object) equals()} on a lower-cased
 * key.
 * </p>
 * <p>
 * This class is {@link Serializable} starting with Commons Collections 3.1.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class ListOrderedMap<K, V> extends AbstractMapDecorator<K, V> implements OrderedMap<K, V>, Serializable {

    static class EntrySetView<K, V> extends AbstractSet<Map.Entry<K, V>> {

        private final ListOrderedMap<K, V> parent;

        private final List<K> insertOrder;

        private Set<Map.Entry<K, V>> entrySet;

        EntrySetView(final ListOrderedMap<K, V> parent, final List<K> insertOrder) {
            this.parent = parent;
            this.insertOrder = insertOrder;
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
        public boolean containsAll(final Collection<?> coll) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private Set<Map.Entry<K, V>> getEntrySet() {
            if (entrySet == null) {
                entrySet = parent.decorated().entrySet();
            }
            return entrySet;
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
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean remove(final Object obj) {
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

    static class KeySetView<K> extends AbstractSet<K> {

        private final ListOrderedMap<K, Object> parent;

        @SuppressWarnings("unchecked")
        KeySetView(final ListOrderedMap<K, ?> parent) {
            this.parent = (ListOrderedMap<K, Object>) parent;
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
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class ListOrderedIterator<K, V> extends AbstractUntypedIteratorDecorator<K, Map.Entry<K, V>> {

        private final ListOrderedMap<K, V> parent;

        private K last;

        ListOrderedIterator(final ListOrderedMap<K, V> parent, final List<K> insertOrder) {
            super(insertOrder.iterator());
            this.parent = parent;
        }

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class ListOrderedMapEntry<K, V> extends AbstractMapEntry<K, V> {

        private final ListOrderedMap<K, V> parent;

        ListOrderedMapEntry(final ListOrderedMap<K, V> parent, final K key) {
            super(key, null);
            this.parent = parent;
        }

        @Override
        public V getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    static class ListOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {

        private final ListOrderedMap<K, V> parent;

        private ListIterator<K> iterator;

        private K last;

        private boolean readable;

        ListOrderedMapIterator(final ListOrderedMap<K, V> parent) {
            this.parent = parent;
            this.iterator = parent.insertOrder.listIterator();
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

    static class ValuesView<V> extends AbstractList<V> {

        private final ListOrderedMap<Object, V> parent;

        @SuppressWarnings("unchecked")
        ValuesView(final ListOrderedMap<?, V> parent) {
            this.parent = (ListOrderedMap<Object, V>) parent;
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
        public V get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<V> iterator() {
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
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 2728177751851003750L;

    public static <K, V> ListOrderedMap<K, V> listOrderedMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Internal list to hold the sequence of objects
     */
    private final List<K> insertOrder = new ArrayList<>();

    /**
     * Constructs a new empty {@code ListOrderedMap} that decorates
     * a {@code HashMap}.
     *
     * @since 3.1
     */
    public ListOrderedMap() {
        this(new HashMap<>());
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to decorate, must not be null
     * @throws NullPointerException if map is null
     */
    protected ListOrderedMap(final Map<K, V> map) {
        super(map);
        insertOrder.addAll(decorated().keySet());
    }

    public List<K> asList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public K get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V getValue(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int indexOf(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<K> keyList() {
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
    public K nextKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K previousKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V put(int index, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void putAll(int index, final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     * @since 3.1
     */
    // (1) should only fail if input stream is incorrect
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // (1)
        map = (Map<K, V>) in.readObject();
    }

    public V remove(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V setValue(final int index, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<V> valueList() {
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
     * @since 3.1
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
}
