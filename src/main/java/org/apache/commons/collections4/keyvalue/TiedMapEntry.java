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
package org.apache.commons.collections4.keyvalue;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.KeyValue;

/**
 * A {@link java.util.Map.Entry Map.Entry} tied to a map underneath.
 * <p>
 * This can be used to enable a map entry to make changes on the underlying
 * map, however this will probably mess up any iterators.
 * </p>
 *
 * @param <K> the type of keys
 * @param <V> the type of mapped values
 * @since 3.0
 */
public class TiedMapEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V>, Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -8453869361373831205L;

    /**
     * The map underlying the entry/iterator
     */
    private final Map<K, V> map;

    /**
     * The key
     */
    private final K key;

    /**
     * Constructs a new entry with the given Map and key.
     *
     * @param map  the map
     * @param key  the key
     */
    public TiedMapEntry(final Map<K, V> map, final K key) {
        this.map = map;
        this.key = key;
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

    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
