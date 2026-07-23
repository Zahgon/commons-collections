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

import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

/**
 * Decorates another {@link BidiMap} to ensure it can't be altered.
 * <p>
 * Attempts to modify it will result in an UnsupportedOperationException.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public final class UnmodifiableBidiMap<K, V> extends AbstractBidiMapDecorator<K, V> implements Unmodifiable {

    public static <K, V> BidiMap<K, V> unmodifiableBidiMap(final BidiMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The inverse unmodifiable map
     */
    private UnmodifiableBidiMap<V, K> inverse;

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to decorate, must not be null
     * @throws NullPointerException if map is null
     */
    // safe to upcast
    @SuppressWarnings("unchecked")
    private UnmodifiableBidiMap(final BidiMap<? extends K, ? extends V> map) {
        super((BidiMap<K, V>) map);
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
    public synchronized BidiMap<V, K> inverseBidiMap() {
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
    public void putAll(final Map<? extends K, ? extends V> mapToCopy) {
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
    public Set<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
