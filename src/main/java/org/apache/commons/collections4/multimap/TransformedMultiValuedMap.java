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

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.FluentIterable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;

/**
 * Decorates another {@code MultiValuedMap} to transform objects that are added.
 * <p>
 * This class affects the MultiValuedMap put methods. Thus objects must be
 * removed or searched for using their transformed form. For example, if the
 * transformation converts Strings to Integers, you must use the Integer form to
 * remove objects.
 * </p>
 * <p>
 * <strong>Note that TransformedMultiValuedMap is not synchronized and is not thread-safe.</strong>
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.1
 */
public class TransformedMultiValuedMap<K, V> extends AbstractMultiValuedMapDecorator<K, V> {

    /**
     * Serialization Version
     */
    private static final long serialVersionUID = 20150612L;

    public static <K, V> TransformedMultiValuedMap<K, V> transformedMap(final MultiValuedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> TransformedMultiValuedMap<K, V> transformingMap(final MultiValuedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The key transformer
     */
    private final Transformer<? super K, ? extends K> keyTransformer;

    /**
     * The value transformer
     */
    private final Transformer<? super V, ? extends V> valueTransformer;

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the collection being decorated, they
     * are NOT transformed.
     * </p>
     *
     * @param map  the MultiValuedMap to decorate, may not be null
     * @param keyTransformer  the transformer to use for key conversion, null means no conversion
     * @param valueTransformer  the transformer to use for value conversion, null means no conversion
     * @throws NullPointerException if map is null
     */
    protected TransformedMultiValuedMap(final MultiValuedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        super(map);
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
    }

    @Override
    public boolean put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final K key, final Iterable<? extends V> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean putAll(final MultiValuedMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected K transformKey(final K object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected V transformValue(final V object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
