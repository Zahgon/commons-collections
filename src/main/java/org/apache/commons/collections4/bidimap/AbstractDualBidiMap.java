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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;

/**
 * Abstract {@link BidiMap} implemented using two maps.
 * <p>
 * An implementation can be written simply by implementing the
 * {@link #createBidiMap(Map, Map, BidiMap)} method.
 * </p>
 *
 * @param <K> the type of the keys in the map
 * @param <V> the type of the values in the map
 * @see DualHashBidiMap
 * @see DualTreeBidiMap
 * @since 3.0
 */
public abstract class AbstractDualBidiMap<K, V> implements BidiMap<K, V> {

    /**
     * Inner class MapIterator.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class BidiMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<K, V> parent;

        /**
         * The iterator being wrapped
         */
        protected Iterator<Map.Entry<K, V>> iterator;

        /**
         * The last returned entry
         */
        protected Map.Entry<K, V> last;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param parent  the parent map
         */
        protected BidiMapIterator(final AbstractDualBidiMap<K, V> parent) {
            this.parent = parent;
            this.iterator = parent.normalMap.entrySet().iterator();
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
     * Inner class EntrySet.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class EntrySet<K, V> extends View<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 4040410962603292348L;

        /**
         * Constructs a new instance.
         *
         * @param parent  the parent BidiMap
         */
        protected EntrySet(final AbstractDualBidiMap<K, V> parent) {
            super(parent.normalMap.entrySet(), parent);
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class EntrySetIterator.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class EntrySetIterator<K, V> extends AbstractIteratorDecorator<Map.Entry<K, V>> {

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<K, V> parent;

        /**
         * The last returned entry
         */
        protected Map.Entry<K, V> last;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param iterator  the iterator to decorate
         * @param parent  the parent map
         */
        protected EntrySetIterator(final Iterator<Map.Entry<K, V>> iterator, final AbstractDualBidiMap<K, V> parent) {
            super(iterator);
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

    /**
     * Inner class KeySet.
     *
     * @param <K> the type of elements maintained by this set
     */
    protected static class KeySet<K> extends View<K, Object, K> implements Set<K> {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = -7107935777385040694L;

        /**
         * Constructs a new instance.
         *
         * @param parent  the parent BidiMap
         */
        @SuppressWarnings("unchecked")
        protected KeySet(final AbstractDualBidiMap<K, ?> parent) {
            super(parent.normalMap.keySet(), (AbstractDualBidiMap<K, Object>) parent);
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
    }

    /**
     * Inner class KeySetIterator.
     *
     * @param <K> the key type.
     */
    protected static class KeySetIterator<K> extends AbstractIteratorDecorator<K> {

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<K, ?> parent;

        /**
         * The last returned key
         */
        protected K lastKey;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param iterator  the iterator to decorate
         * @param parent  the parent map
         */
        protected KeySetIterator(final Iterator<K> iterator, final AbstractDualBidiMap<K, ?> parent) {
            super(iterator);
            this.parent = parent;
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class MapEntry.
     *
     * @param <K> the type of the keys.
     * @param <V> the type of the values.
     */
    protected static class MapEntry<K, V> extends AbstractMapEntryDecorator<K, V> {

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<K, V> parent;

        /**
         * Constructs a new instance.
         * @param entry  the entry to decorate
         * @param parent  the parent map
         */
        protected MapEntry(final Map.Entry<K, V> entry, final AbstractDualBidiMap<K, V> parent) {
            super(entry);
            this.parent = parent;
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class Values.
     *
     * @param <V> the type of the values.
     */
    protected static class Values<V> extends View<Object, V, V> implements Set<V> {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 4023777119829639864L;

        /**
         * Constructs a new instance.
         *
         * @param parent  the parent BidiMap
         */
        @SuppressWarnings("unchecked")
        protected Values(final AbstractDualBidiMap<?, V> parent) {
            super(parent.normalMap.values(), (AbstractDualBidiMap<Object, V>) parent);
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
        public boolean remove(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class ValuesIterator.
     *
     * @param <V> the value type.
     */
    protected static class ValuesIterator<V> extends AbstractIteratorDecorator<V> {

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<Object, V> parent;

        /**
         * The last returned value
         */
        protected V lastValue;

        /**
         * Whether remove is allowed at present
         */
        protected boolean canRemove;

        /**
         * Constructs a new instance.
         * @param iterator  the iterator to decorate
         * @param parent  the parent map
         */
        @SuppressWarnings("unchecked")
        protected ValuesIterator(final Iterator<V> iterator, final AbstractDualBidiMap<?, V> parent) {
            super(iterator);
            this.parent = (AbstractDualBidiMap<Object, V>) parent;
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
     * Inner class View.
     *
     * @param <K> the type of the keys in the map.
     * @param <V> the type of the values in the map.
     * @param <E> the type of the elements in the collection.
     */
    protected abstract static class View<K, V, E> extends AbstractCollectionDecorator<E> {

        /**
         * Generated serial version ID.
         */
        private static final long serialVersionUID = 4621510560119690639L;

        /**
         * The parent map
         */
        protected final AbstractDualBidiMap<K, V> parent;

        /**
         * Constructs a new instance.
         *
         * @param coll  the collection view being decorated
         * @param parent  the parent BidiMap
         */
        protected View(final Collection<E> coll, final AbstractDualBidiMap<K, V> parent) {
            super(coll);
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean equals(final Object object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
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
    }

    /**
     * Normal delegate map.
     */
    transient Map<K, V> normalMap;

    // Map delegation
    /**
     * Reverse delegate map.
     */
    transient Map<V, K> reverseMap;

    /**
     * Inverse view of this map.
     */
    transient BidiMap<V, K> inverseBidiMap;

    /**
     * View of the keys.
     */
    transient Set<K> keySet;

    /**
     * View of the values.
     */
    transient Set<V> values;

    /**
     * View of the entries.
     */
    transient Set<Map.Entry<K, V>> entrySet;

    /**
     * Creates an empty map, initialized by {@code createMap}.
     * <p>
     * This constructor remains in place for deserialization.
     * All other usage is deprecated in favor of
     * {@link #AbstractDualBidiMap(Map, Map)}.
     */
    protected AbstractDualBidiMap() {
    }

    /**
     * Creates an empty map using the two maps specified as storage.
     * <p>
     * The two maps must be a matching pair, normal and reverse.
     * They will typically both be empty.
     * <p>
     * Neither map is validated, so nulls may be passed in.
     * If you choose to do this then the subclass constructor must populate
     * the {@code maps[]} instance variable itself.
     *
     * @param normalMap  the normal direction map
     * @param reverseMap  the reverse direction map
     * @since 3.1
     */
    protected AbstractDualBidiMap(final Map<K, V> normalMap, final Map<V, K> reverseMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
    }

    // BidiMap changes
    /**
     * Constructs a map that decorates the specified maps,
     * used by the subclass {@code createBidiMap} implementation.
     *
     * @param normalMap  the normal direction map
     * @param reverseMap  the reverse direction map
     * @param inverseBidiMap  the inverse BidiMap
     */
    protected AbstractDualBidiMap(final Map<K, V> normalMap, final Map<V, K> reverseMap, final BidiMap<V, K> inverseBidiMap) {
        this.normalMap = normalMap;
        this.reverseMap = reverseMap;
        this.inverseBidiMap = inverseBidiMap;
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
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new instance of the subclass.
     *
     * @param normalMap  the normal direction map
     * @param reverseMap  the reverse direction map
     * @param inverseMap  this map, which is the inverse in the new map
     * @return the bidi map
     */
    protected abstract BidiMap<V, K> createBidiMap(Map<V, K> normalMap, Map<K, V> reverseMap, BidiMap<K, V> inverseMap);

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator(final Iterator<Map.Entry<K, V>> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<K> createKeySetIterator(final Iterator<K> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<V> createValuesIterator(final Iterator<V> iterator) {
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
    public K getKey(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public BidiMap<V, K> inverseBidiMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Map views
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // BidiMap
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

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K removeValue(final Object value) {
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
    public Set<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
