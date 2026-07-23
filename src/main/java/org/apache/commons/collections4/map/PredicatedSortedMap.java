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

import java.util.Comparator;
import java.util.SortedMap;
import org.apache.commons.collections4.Predicate;

/**
 * Decorates another {@code SortedMap} to validate that additions
 * match a specified predicate.
 * <p>
 * This map exists to provide validation for the decorated map.
 * It is normally created to decorate an empty map.
 * If an object cannot be added to the map, an IllegalArgumentException is thrown.
 * </p>
 * <p>
 * One usage would be to ensure that no null keys are added to the map.
 * </p>
 * <pre>
 *   SortedMap map =
 *     PredicatedSortedMap.predicatedSortedMap(new TreeMap(),
 *                                             NotNullPredicate.notNullPredicate(),
 *                                             null);
 * </pre>
 * <p>
 * <strong>Note that PredicatedSortedMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedSortedMap}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class PredicatedSortedMap<K, V> extends PredicatedMap<K, V> implements SortedMap<K, V> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 3359846175935304332L;

    public static <K, V> PredicatedSortedMap<K, V> predicatedSortedMap(final SortedMap<K, V> map, final Predicate<? super K> keyPredicate, final Predicate<? super V> valuePredicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to decorate, must not be null
     * @param keyPredicate  the predicate to validate the keys, null means no check
     * @param valuePredicate  the predicate to validate to values, null means no check
     * @throws NullPointerException if the map is null
     */
    protected PredicatedSortedMap(final SortedMap<K, V> map, final Predicate<? super K> keyPredicate, final Predicate<? super V> valuePredicate) {
        super(map, keyPredicate, valuePredicate);
    }

    @Override
    public Comparator<? super K> comparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected SortedMap<K, V> getSortedMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> headMap(final K toKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K lastKey() {
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
