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
import java.util.Map;
import java.util.SortedMap;
import org.apache.commons.collections4.Transformer;

/**
 * Decorates another {@code SortedMap } to transform objects that are added.
 * <p>
 * The Map put methods and Map.Entry setValue method are affected by this class.
 * Thus objects must be removed or searched for using their transformed form.
 * For example, if the transformation converts Strings to Integers, you must
 * use the Integer form to remove objects.
 * </p>
 * <p>
 * <strong>Note that TransformedSortedMap is not synchronized and is not thread-safe.</strong>
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
public class TransformedSortedMap<K, V> extends TransformedMap<K, V> implements SortedMap<K, V> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -8751771676410385778L;

    public static <K, V> TransformedSortedMap<K, V> transformedSortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> TransformedSortedMap<K, V> transformingSortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the collection being decorated, they
     * are NOT transformed.
     * </p>
     *
     * @param map  the map to decorate, must not be null
     * @param keyTransformer  the predicate to validate the keys, null means no transformation
     * @param valueTransformer  the predicate to validate to values, null means no transformation
     * @throws NullPointerException if the map is null
     */
    protected TransformedSortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        super(map, keyTransformer, valueTransformer);
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
