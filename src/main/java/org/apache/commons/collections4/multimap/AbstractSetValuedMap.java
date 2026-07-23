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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.SetValuedMap;

/**
 * Abstract implementation of the {@link SetValuedMap} interface to simplify the
 * creation of subclass implementations.
 * <p>
 * Subclasses specify a Map implementation to use as the internal storage and
 * the Set implementation to use as values.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.1
 */
public abstract class AbstractSetValuedMap<K, V> extends AbstractMultiValuedMap<K, V> implements SetValuedMap<K, V> {

    /**
     * Wrapped set to handle add and remove on the collection returned by
     * {@code get(Object)}.
     */
    private final class WrappedSet extends WrappedCollection implements Set<V> {

        WrappedSet(final K key) {
            super(key);
        }

        @Override
        public boolean equals(final Object other) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Constructor needed for subclass serialization.
     */
    protected AbstractSetValuedMap() {
    }

    /**
     * A constructor that wraps, not copies
     *
     * @param map  the map to wrap, must not be null
     * @throws NullPointerException if the map is null
     */
    protected AbstractSetValuedMap(final Map<K, ? extends Set<V>> map) {
        super(map);
    }

    /**
     * Creates a new value collection using the provided factory.
     * @return a new set
     */
    @Override
    protected abstract Set<V> createCollection();

    @Override
    public Set<V> get(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map<K, Set<V>> getMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<V> remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    Set<V> wrappedCollection(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
