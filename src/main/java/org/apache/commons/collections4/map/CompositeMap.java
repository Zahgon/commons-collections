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
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.apache.commons.collections4.set.CompositeSet;

/**
 * Decorates a map of other maps to provide a single unified view.
 * <p>
 * Changes made to this map will actually be made on the decorated map.
 * Add and remove operations require the use of a pluggable strategy. If no
 * strategy is provided then add and remove are unsupported.
 * </p>
 * <p>
 * <strong>Note that CompositeMap is not synchronized and is not thread-safe.</strong>
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
public class CompositeMap<K, V> extends AbstractIterableMap<K, V> implements Serializable {

    /**
     * This interface allows definition for all of the indeterminate
     * mutators in a CompositeMap, as well as providing a hook for
     * callbacks on key collisions.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     */
    public interface MapMutator<K, V> extends Serializable {

        /**
         * Called when the CompositeMap.put() method is invoked.
         *
         * @param map  the CompositeMap which is being modified
         * @param composited  array of Maps in the CompositeMap being modified
         * @param key  key with which the specified value is to be associated.
         * @param value  value to be associated with the specified key.
         * @return previous value associated with specified key, or {@code null}
         *         if there was no mapping for key.  A {@code null} return can
         *         also indicate that the map previously associated {@code null}
         *         with the specified key, if the implementation supports
         *         {@code null} values.
         *
         * @throws UnsupportedOperationException if not defined
         * @throws ClassCastException if the class of the specified key or value
         *            prevents it from being stored in this map.
         * @throws IllegalArgumentException if some aspect of this key or value
         *            prevents it from being stored in this map.
         * @throws NullPointerException this map does not permit {@code null}
         *            keys or values, and the specified key or value is
         *            {@code null}.
         */
        V put(CompositeMap<K, V> map, Map<K, V>[] composited, K key, V value);

        /**
         * Called when the CompositeMap.putAll() method is invoked.
         *
         * @param map  the CompositeMap which is being modified
         * @param composited  array of Maps in the CompositeMap being modified
         * @param mapToAdd  Mappings to be stored in this CompositeMap
         * @throws UnsupportedOperationException if not defined
         * @throws ClassCastException if the class of the specified key or value
         *            prevents it from being stored in this map.
         * @throws IllegalArgumentException if some aspect of this key or value
         *            prevents it from being stored in this map.
         * @throws NullPointerException this map does not permit {@code null}
         *            keys or values, and the specified key or value is
         *            {@code null}.
         */
        void putAll(CompositeMap<K, V> map, Map<K, V>[] composited, Map<? extends K, ? extends V> mapToAdd);

        /**
         * Called when adding a new Composited Map results in a
         * key collision.
         *
         * @param composite  the CompositeMap with the collision
         * @param existing  the Map already in the composite which contains the
         *        offending key
         * @param added  the Map being added
         * @param intersect  the intersection of the keysets of the existing and added maps
         */
        void resolveCollision(CompositeMap<K, V> composite, Map<K, V> existing, Map<K, V> added, Collection<K> intersect);
    }

    @SuppressWarnings("rawtypes")
    private static final Map[] EMPTY_MAP_ARRAY = {};

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -6096931280583808322L;

    /**
     * Array of all maps in the composite
     */
    private Map<K, V>[] composite;

    /**
     * Handle mutation operations
     */
    private MapMutator<K, V> mutator;

    /**
     * Create a new, empty, CompositeMap.
     */
    @SuppressWarnings("unchecked")
    public CompositeMap() {
        this(new Map[] {}, null);
    }

    /**
     * Create a new CompositeMap which composites all of the Map instances in the
     * argument. It copies the argument array, it does not use it directly.
     *
     * @param composite  the Maps to be composited
     * @throws IllegalArgumentException if there is a key collision
     */
    public CompositeMap(final Map<K, V>... composite) {
        this(composite, null);
    }

    /**
     * Create a new CompositeMap with two composited Map instances.
     *
     * @param one  the first Map to be composited
     * @param two  the second Map to be composited
     * @throws IllegalArgumentException if there is a key collision
     */
    @SuppressWarnings("unchecked")
    public CompositeMap(final Map<K, V> one, final Map<K, V> two) {
        this(new Map[] { one, two }, null);
    }

    /**
     * Create a new CompositeMap with two composited Map instances.
     *
     * @param one  the first Map to be composited
     * @param two  the second Map to be composited
     * @param mutator  MapMutator to be used for mutation operations
     */
    @SuppressWarnings("unchecked")
    public CompositeMap(final Map<K, V> one, final Map<K, V> two, final MapMutator<K, V> mutator) {
        this(new Map[] { one, two }, mutator);
    }

    /**
     * Create a new CompositeMap which composites all of the Map instances in the
     * argument. It copies the argument array, it does not use it directly.
     *
     * @param composite  Maps to be composited
     * @param mutator  MapMutator to be used for mutation operations
     */
    @SuppressWarnings("unchecked")
    public CompositeMap(final Map<K, V>[] composite, final MapMutator<K, V> mutator) {
        this.mutator = mutator;
        this.composite = EMPTY_MAP_ARRAY;
        for (int i = composite.length - 1; i >= 0; --i) {
            this.addComposited(composite[i]);
        }
    }

    public synchronized void addComposited(final Map<K, V> map) throws IllegalArgumentException {
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

    @SuppressWarnings("unchecked")
    public synchronized Map<K, V> removeComposited(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMutator(final MapMutator<K, V> mutator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
