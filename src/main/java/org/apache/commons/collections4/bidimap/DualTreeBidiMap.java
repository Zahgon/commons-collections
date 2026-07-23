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
package org.apache.commons.collections4.bidimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;

/**
 * Implements {@link BidiMap} with two {@link TreeMap} instances.
 * <p>
 * The setValue() method on iterators will succeed only if the new value being set is
 * not already in the bidi map.
 * </p>
 * <p>
 * When considering whether to use this class, the {@link TreeBidiMap} class should
 * also be considered. It implements the interface using a dedicated design, and does
 * not store each object twice, which can save on memory use.
 * </p>
 * <p>
 * NOTE: From Commons Collections 3.1, all subclasses will use {@link TreeMap}
 * and the flawed {@code createMap} method is ignored.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class DualTreeBidiMap<K, V> extends AbstractDualBidiMap<K, V> implements SortedBidiMap<K, V>, Serializable {

    /**
     * Inner class MapIterator.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class BidiOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {

        /**
         * The parent map
         */
        private final AbstractDualBidiMap<K, V> parent;

        /**
         * The iterator being decorated
         */
        private ListIterator<Map.Entry<K, V>> iterator;

        /**
         * The last returned entry
         */
        private Map.Entry<K, V> last;

        /**
         * Constructs a new instance.
         * @param parent  the parent map
         */
        protected BidiOrderedMapIterator(final AbstractDualBidiMap<K, V> parent) {
            this.parent = parent;
            iterator = new ArrayList<>(parent.entrySet()).listIterator();
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
     * Internal sorted map view.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class ViewMap<K, V> extends AbstractSortedMapDecorator<K, V> {

        /**
         * Constructs a new instance.
         * @param bidi  the parent bidi map
         * @param sm  the subMap sorted map
         */
        protected ViewMap(final DualTreeBidiMap<K, V> bidi, final SortedMap<K, V> sm) {
            // the implementation is not great here...
            // use the normalMap as the filtered map, but reverseMap as the full map
            // this forces containsValue and clear to be overridden
            super(new DualTreeBidiMap<>(sm, bidi.reverseMap, bidi.inverseBidiMap));
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsValue(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected DualTreeBidiMap<K, V> decorated() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public SortedMap<K, V> headMap(final K toKey) {
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
        public SortedMap<K, V> subMap(final K fromKey, final K toKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public SortedMap<K, V> tailMap(final K fromKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Ensure serialization compatibility
     */
    private static final long serialVersionUID = 721969328361809L;

    /**
     * The key comparator to use
     */
    private final Comparator<? super K> comparator;

    /**
     * The value comparator to use
     */
    private final Comparator<? super V> valueComparator;

    /**
     * Creates an empty {@link DualTreeBidiMap}.
     */
    public DualTreeBidiMap() {
        super(new TreeMap<>(), new TreeMap<>());
        this.comparator = null;
        this.valueComparator = null;
    }

    /**
     * Constructs a {@link DualTreeBidiMap} using the specified {@link Comparator}.
     *
     * @param keyComparator  the comparator
     * @param valueComparator  the values comparator to use
     */
    public DualTreeBidiMap(final Comparator<? super K> keyComparator, final Comparator<? super V> valueComparator) {
        super(new TreeMap<>(keyComparator), new TreeMap<>(valueComparator));
        this.comparator = keyComparator;
        this.valueComparator = valueComparator;
    }

    /**
     * Constructs a {@link DualTreeBidiMap} and copies the mappings from
     * specified {@link Map}.
     *
     * @param map  the map whose mappings are to be placed in this map
     */
    public DualTreeBidiMap(final Map<? extends K, ? extends V> map) {
        super(new TreeMap<>(), new TreeMap<>());
        putAll(map);
        this.comparator = null;
        this.valueComparator = null;
    }

    /**
     * Constructs a {@link DualTreeBidiMap} that decorates the specified maps.
     *
     * @param normalMap  the normal direction map
     * @param reverseMap  the reverse direction map
     * @param inverseBidiMap  the inverse BidiMap
     */
    protected DualTreeBidiMap(final Map<K, V> normalMap, final Map<V, K> reverseMap, final BidiMap<V, K> inverseBidiMap) {
        super(normalMap, reverseMap, inverseBidiMap);
        this.comparator = ((SortedMap<K, V>) normalMap).comparator();
        this.valueComparator = ((SortedMap<V, K>) reverseMap).comparator();
    }

    @Override
    public Comparator<? super K> comparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected DualTreeBidiMap<V, K> createBidiMap(final Map<V, K> normalMap, final Map<K, V> reverseMap, final BidiMap<K, V> inverseMap) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> headMap(final K toKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedBidiMap<V, K> inverseBidiMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SortedBidiMap<V, K> inverseSortedBidiMap() {
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
    public K nextKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K previousKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes an instance from an ObjectInputStream.
     *
     * @param in The source ObjectInputStream.
     * @throws IOException            Any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException A class of a serialized object cannot be found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        normalMap = new TreeMap<>(comparator);
        reverseMap = new TreeMap<>(valueComparator);
        // will fail at runtime if the stream is incorrect
        @SuppressWarnings("unchecked")
        final Map<K, V> map = (Map<K, V>) in.readObject();
        putAll(map);
    }

    @Override
    public SortedMap<K, V> subMap(final K fromKey, final K toKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> tailMap(final K fromKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Comparator<? super V> valueComparator() {
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
        out.writeObject(normalMap);
    }
}
