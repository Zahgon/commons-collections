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

import java.io.PrintStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.apache.commons.collections4.map.AbstractMapDecorator;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
import org.apache.commons.collections4.map.FixedSizeMap;
import org.apache.commons.collections4.map.FixedSizeSortedMap;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.commons.collections4.map.LazySortedMap;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.collections4.map.PredicatedSortedMap;
import org.apache.commons.collections4.map.TransformedMap;
import org.apache.commons.collections4.map.TransformedSortedMap;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;

/**
 * Provides utility methods and decorators for {@link Map} and {@link SortedMap} instances.
 * <p>
 * It contains various type safe methods as well as other useful features like deep copying.
 * </p>
 * <p>
 * It also provides the following decorators:
 * </p>
 *
 * <ul>
 * <li>{@link #fixedSizeMap(Map)}
 * <li>{@link #fixedSizeSortedMap(SortedMap)}
 * <li>{@link #lazyMap(Map,Factory)}
 * <li>{@link #lazyMap(Map,Transformer)}
 * <li>{@link #lazySortedMap(SortedMap,Factory)}
 * <li>{@link #lazySortedMap(SortedMap,Transformer)}
 * <li>{@link #predicatedMap(Map,Predicate,Predicate)}
 * <li>{@link #predicatedSortedMap(SortedMap,Predicate,Predicate)}
 * <li>{@link #transformedMap(Map, Transformer, Transformer)}
 * <li>{@link #transformedSortedMap(SortedMap, Transformer, Transformer)}
 * <li>{@link #multiValueMap(Map)}
 * <li>{@link #multiValueMap(Map, Class)}
 * <li>{@link #multiValueMap(Map, Factory)}
 * </ul>
 *
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class MapUtils {

    /**
     * An empty unmodifiable sorted map. This is not provided in the JDK.
     */
    @SuppressWarnings("rawtypes")
    public static final SortedMap EMPTY_SORTED_MAP = UnmodifiableSortedMap.unmodifiableSortedMap(new TreeMap<>());

    /**
     * String used to indent the verbose and debug Map prints.
     */
    private static final String INDENT_STRING = "    ";

    /**
     * Applies the {@code getFunction} and returns its result if non-null, if null returns the result of applying the
     * default function.
     *
     * @param <K> The key type.
     * @param <R> The result type.
     * @param map The map to query.
     * @param key The key into the map.
     * @param getFunction The get function.
     * @param defaultFunction The function to provide a default value.
     * @return The result of applying a function.
     */
    private static <K, R> R applyDefaultFunction(final Map<? super K, ?> map, final K key, final BiFunction<Map<? super K, ?>, K, R> getFunction, final Function<K, R> defaultFunction) {
        return applyDefaultFunction(map, key, getFunction, defaultFunction, null);
    }

    /**
     * Applies the {@code getFunction} and returns its result if non-null, if null returns the result of applying the
     * default function.
     *
     * @param <K> The key type.
     * @param <R> The result type.
     * @param map The map to query.
     * @param key The key into the map.
     * @param getFunction The get function.
     * @param defaultFunction The function to provide a default value.
     * @param defaultValue The default value.
     * @return The result of applying a function.
     */
    private static <K, R> R applyDefaultFunction(final Map<? super K, ?> map, final K key, final BiFunction<Map<? super K, ?>, K, R> getFunction, final Function<K, R> defaultFunction, final R defaultValue) {
        R value = map != null && getFunction != null ? getFunction.apply(map, key) : null;
        if (value == null) {
            value = defaultFunction != null ? defaultFunction.apply(key) : null;
        }
        return value != null ? value : defaultValue;
    }

    /**
     * Applies the {@code getFunction} and returns its result if non-null, if null returns the {@code defaultValue}.
     *
     * @param <K> The key type.
     * @param <R> The result type.
     * @param map The map to query.
     * @param key The key into the map.
     * @param getFunction The get function.
     * @param defaultValue The default value.
     * @return The result of applying a function.
     */
    private static <K, R> R applyDefaultValue(final Map<? super K, ?> map, final K key, final BiFunction<Map<? super K, ?>, K, R> getFunction, final R defaultValue) {
        final R value = map != null && getFunction != null ? getFunction.apply(map, key) : null;
        return value == null ? defaultValue : value;
    }

    public static void debugPrint(final PrintStream out, final Object label, final Map<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Map<K, V> emptyIfNull(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> fixedSizeMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> fixedSizeSortedMap(final SortedMap<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Boolean getBoolean(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Boolean getBoolean(final Map<? super K, ?> map, final K key, final Boolean defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Boolean getBoolean(final Map<? super K, ?> map, final K key, final Function<K, Boolean> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> boolean getBooleanValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> boolean getBooleanValue(final Map<? super K, ?> map, final K key, final boolean defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> boolean getBooleanValue(final Map<? super K, ?> map, final K key, final Function<K, Boolean> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Byte getByte(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Byte getByte(final Map<? super K, ?> map, final K key, final Byte defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Byte getByte(final Map<? super K, ?> map, final K key, final Function<K, Byte> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> byte getByteValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> byte getByteValue(final Map<? super K, ?> map, final K key, final byte defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> byte getByteValue(final Map<? super K, ?> map, final K key, final Function<K, Byte> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Double getDouble(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Double getDouble(final Map<? super K, ?> map, final K key, final Double defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Double getDouble(final Map<? super K, ?> map, final K key, final Function<K, Double> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key, final double defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key, final Function<K, Double> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Float getFloat(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Float getFloat(final Map<? super K, ?> map, final K key, final Float defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Float getFloat(final Map<? super K, ?> map, final K key, final Function<K, Float> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> float getFloatValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> float getFloatValue(final Map<? super K, ?> map, final K key, final float defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> float getFloatValue(final Map<? super K, ?> map, final K key, final Function<K, Float> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Integer getInteger(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Integer getInteger(final Map<? super K, ?> map, final K key, final Function<K, Integer> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Integer getInteger(final Map<? super K, ?> map, final K key, final Integer defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> int getIntValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> int getIntValue(final Map<? super K, ?> map, final K key, final Function<K, Integer> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> int getIntValue(final Map<? super K, ?> map, final K key, final int defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Long getLong(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Long getLong(final Map<? super K, ?> map, final K key, final Function<K, Long> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Long getLong(final Map<? super K, ?> map, final K key, final Long defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> long getLongValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> long getLongValue(final Map<? super K, ?> map, final K key, final Function<K, Long> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> long getLongValue(final Map<? super K, ?> map, final K key, final long defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Map<?, ?> getMap(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Map<?, ?> getMap(final Map<? super K, ?> map, final K key, final Function<K, Map<?, ?>> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Map<?, ?> getMap(final Map<? super K, ?> map, final K key, final Map<?, ?> defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Number getNumber(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Number getNumber(final Map<? super K, ?> map, final K key, final Function<K, Number> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Number getNumber(final Map<? super K, ?> map, final K key, final Number defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> V getObject(final Map<? super K, V> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> V getObject(final Map<K, V> map, final K key, final V defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Short getShort(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Short getShort(final Map<? super K, ?> map, final K key, final Function<K, Short> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> Short getShort(final Map<? super K, ?> map, final K key, final Short defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> short getShortValue(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> short getShortValue(final Map<? super K, ?> map, final K key, final Function<K, Short> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> short getShortValue(final Map<? super K, ?> map, final K key, final short defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> String getString(final Map<? super K, ?> map, final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> String getString(final Map<? super K, ?> map, final K key, final Function<K, String> defaultFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> String getString(final Map<? super K, ?> map, final K key, final String defaultValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Map<V, K> invertMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> iterableMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableSortedMap<K, V> iterableSortedMap(final SortedMap<K, V> sortedMap) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> lazyMap(final Map<K, V> map, final Factory<? extends V> factory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> lazyMap(final Map<K, V> map, final Transformer<? super K, ? extends V> transformerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> lazySortedMap(final SortedMap<K, V> map, final Factory<? extends V> factory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> lazySortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends V> transformerFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a multi-value map backed by the given map which returns collections of type ArrayList.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map to decorate
     * @return a multi-value map backed by the given map which returns ArrayLists of values.
     * @see MultiValueMap
     * @since 3.2
     * @deprecated since 4.1, use {@link MultiValuedMap} instead
     */
    @Deprecated
    public static <K, V> MultiValueMap<K, V> multiValueMap(final Map<K, ? super Collection<V>> map) {
        return MultiValueMap.<K, V>multiValueMap(map);
    }

    /**
     * Creates a multi-value map backed by the given map which returns collections of the specified type.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <C> the collection class type
     * @param map the map to decorate
     * @param collectionClass the type of collections to return from the map (must contain public no-arg constructor and
     *        extend Collection)
     * @return a multi-value map backed by the given map which returns collections of the specified type
     * @see MultiValueMap
     * @since 3.2
     * @deprecated since 4.1, use {@link MultiValuedMap} instead
     */
    @Deprecated
    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(final Map<K, C> map, final Class<C> collectionClass) {
        return MultiValueMap.multiValueMap(map, collectionClass);
    }

    /**
     * Creates a multi-value map backed by the given map which returns collections created by the specified collection
     * factory.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param <C> the collection class type
     * @param map the map to decorate
     * @param collectionFactory a factor which creates collection objects
     * @return a multi-value map backed by the given map which returns collections created by the specified collection
     *         factory
     * @see MultiValueMap
     * @since 3.2
     * @deprecated since 4.1, use {@link MultiValuedMap} instead
     */
    @Deprecated
    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(final Map<K, C> map, final Factory<C> collectionFactory) {
        return MultiValueMap.multiValueMap(map, collectionFactory);
    }

    public static <K, V> OrderedMap<K, V> orderedMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V, E> void populateMap(final Map<K, V> map, final Iterable<? extends E> elements, final Transformer<E, K> keyTransformer, final Transformer<E, V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> void populateMap(final Map<K, V> map, final Iterable<? extends V> elements, final Transformer<V, K> keyTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V, E> void populateMap(final MultiMap<K, V> map, final Iterable<? extends E> elements, final Transformer<E, K> keyTransformer, final Transformer<E, V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> void populateMap(final MultiMap<K, V> map, final Iterable<? extends V> elements, final Transformer<V, K> keyTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> predicatedMap(final Map<K, V> map, final Predicate<? super K> keyPred, final Predicate<? super V> valuePred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> predicatedSortedMap(final SortedMap<K, V> map, final Predicate<? super K> keyPred, final Predicate<? super V> valuePred) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Writes indentation to the given stream.
     *
     * @param out the stream to indent
     * @param indent the index of the indentation
     */
    private static void printIndent(final PrintStream out, final int indent) {
        for (int i = 0; i < indent; i++) {
            out.print(INDENT_STRING);
        }
    }

    // As per Javadoc throws CCE for invalid array contents
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> putAll(final Map<K, V> map, final Object[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K> void safeAddToMap(final Map<? super K, Object> map, final K key, final Object value) throws NullPointerException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int size(final Map<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Map<K, V> synchronizedMap(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> synchronizedSortedMap(final SortedMap<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Map<String, Object> toMap(final ResourceBundle resourceBundle) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Properties toProperties(final Map<K, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> IterableMap<K, V> transformedMap(final Map<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> transformedSortedMap(final SortedMap<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> Map<K, V> unmodifiableMap(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(final SortedMap<K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void verbosePrint(final PrintStream out, final Object label, final Map<?, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Implementation providing functionality for {@link #debugPrint} and for {@link #verbosePrint}. This prints the
     * given map with nice line breaks. If the debug flag is true, it additionally prints the type of the object value.
     * If the contents of a map include the map itself, then the text <em>(this Map)</em> is printed out. If the
     * contents include a parent container of the map, the text <em>(ancestor[i] Map)</em> is printed, where it actually
     * indicates the number of levels which must be traversed in the sequential list of ancestors (for example father,
     * grandfather, great-grandfather, etc.).
     *
     * @param out the stream to print to
     * @param label the label to be used, may be {@code null}. If {@code null}, the label is not output. It
     *        typically represents the name of the property in a bean or similar.
     * @param map the map to print, may be {@code null}. If {@code null}, the text 'null' is output
     * @param lineage a stack consisting of any maps in which the previous argument is contained. This is checked to
     *        avoid infinite recursion when printing the output
     * @param debug flag indicating whether type names should be output.
     * @throws NullPointerException if the stream is {@code null}
     */
    private static void verbosePrintInternal(final PrintStream out, final Object label, final Map<?, ?> map, final Deque<Map<?, ?>> lineage, final boolean debug) {
        printIndent(out, lineage.size());
        if (map == null) {
            if (label != null) {
                out.print(label);
                out.print(" = ");
            }
            out.println("null");
            return;
        }
        if (label != null) {
            out.print(label);
            out.println(" = ");
        }
        printIndent(out, lineage.size());
        out.println("{");
        lineage.addLast(map);
        for (final Map.Entry<?, ?> entry : map.entrySet()) {
            final Object childKey = entry.getKey();
            final Object childValue = entry.getValue();
            if (childValue instanceof Map && !lineage.contains(childValue)) {
                verbosePrintInternal(out, childKey == null ? "null" : childKey, (Map<?, ?>) childValue, lineage, debug);
            } else {
                printIndent(out, lineage.size());
                out.print(childKey);
                out.print(" = ");
                final int lineageIndex = IterableUtils.indexOf(lineage, PredicateUtils.equalPredicate(childValue));
                if (lineageIndex == -1) {
                    out.print(childValue);
                } else if (lineage.size() - 1 == lineageIndex) {
                    out.print("(this Map)");
                } else {
                    out.print("(ancestor[" + (lineage.size() - 1 - lineageIndex - 1) + "] Map)");
                }
                if (debug && childValue != null) {
                    out.print(' ');
                    out.println(childValue.getClass().getName());
                } else {
                    out.println();
                }
            }
        }
        lineage.removeLast();
        printIndent(out, lineage.size());
        out.println(debug ? "} " + map.getClass().getName() : "}");
    }

    /**
     * Don't allow instances.
     */
    private MapUtils() {
    }
}
