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
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.collections4.multimap.TransformedMultiValuedMap;
import org.apache.commons.collections4.multimap.UnmodifiableMultiValuedMap;

/**
 * Provides utility methods and decorators for {@link MultiValuedMap} instances.
 * <p>
 * It contains various type safe and null safe methods. Additionally, it provides
 * the following decorators:
 * </p>
 * <ul>
 *   <li>{@link #unmodifiableMultiValuedMap(MultiValuedMap)}</li>
 *   <li>{@link #transformedMultiValuedMap(MultiValuedMap, Transformer, Transformer)}</li>
 * </ul>
 *
 * @since 4.1
 */
public class MultiMapUtils {

    /**
     * An empty {@link UnmodifiableMultiValuedMap}.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final MultiValuedMap EMPTY_MULTI_VALUED_MAP = UnmodifiableMultiValuedMap.unmodifiableMultiValuedMap(new ArrayListValuedHashMap(0, 0));

    @SuppressWarnings("unchecked")
    public static <K, V> MultiValuedMap<K, V> emptyIfNull(final MultiValuedMap<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, V> MultiValuedMap<K, V> emptyMultiValuedMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Null safe methods
    public static <K, V> Collection<V> getCollection(final MultiValuedMap<K, V> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Bag<V> getValuesAsBag(final MultiValuedMap<K, V> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> List<V> getValuesAsList(final MultiValuedMap<K, V> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // TODO: review the getValuesAsXXX methods - depending on the actual MultiValuedMap type, changes
    // to the returned collection might update the backing map. This should be clarified and/or prevented.
    public static <K, V> Set<V> getValuesAsSet(final MultiValuedMap<K, V> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmpty(final MultiValuedMap<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> ListValuedMap<K, V> newListValuedHashMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SetValuedMap<K, V> newSetValuedHashMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> MultiValuedMap<K, V> transformedMultiValuedMap(final MultiValuedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> MultiValuedMap<K, V> unmodifiableMultiValuedMap(final MultiValuedMap<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private MultiMapUtils() {
        // empty
    }
}
