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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.AbstractHashedMap.HashEntry;

/**
 * A {@code Map} implementation that uses multiple keys to map the value.
 * <p>
 * This class is the most efficient way to uses multiple keys to map to a value.
 * The best way to use this class is via the additional map-style methods.
 * These provide {@code get}, {@code containsKey}, {@code put} and
 * {@code remove} for individual keys which operate without extra object creation.
 * </p>
 * <p>
 * The additional methods are the main interface of this map.
 * As such, you will not normally hold this map in a variable of type {@code Map}.
 * </p>
 * <p>
 * The normal map methods take in and return a {@link MultiKey}.
 * If you try to use {@code put()} with any other object type a
 * {@code ClassCastException} is thrown. If you try to use {@code null} as
 * the key in {@code put()} a {@code NullPointerException} is thrown.
 * </p>
 * <p>
 * This map is implemented as a decorator of a {@code AbstractHashedMap} which
 * enables extra behavior to be added easily.
 * </p>
 * <ul>
 * <li>{@code MultiKeyMap.decorate(new LinkedMap())} creates an ordered map.
 * <li>{@code MultiKeyMap.decorate(new LRUMap())} creates an least recently used map.
 * <li>{@code MultiKeyMap.decorate(new ReferenceMap())} creates a garbage collector sensitive map.
 * </ul>
 * <p>
 * Note that {@code IdentityMap} and {@code ReferenceIdentityMap} are unsuitable
 * for use as the key comparison would work on the whole MultiKey, not the elements within.
 * </p>
 * <p>
 * As an example, consider a least recently used cache that uses a String airline code
 * and a Locale to lookup the airline's name:
 * </p>
 * <pre>
 * private MultiKeyMap cache = MultiKeyMap.multiKeyMap(new LRUMap(50));
 *
 * public String getAirlineName(String code, String locale) {
 *   String name = (String) cache.get(code, locale);
 *   if (name == null) {
 *     name = getAirlineNameFromDB(code, locale);
 *     cache.put(code, locale, name);
 *   }
 *   return name;
 * }
 * </pre>
 * <p>
 * <strong>Note that MultiKeyMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. This class may throw exceptions when accessed
 * by concurrent threads without synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.1
 */
public class MultiKeyMap<K, V> extends AbstractMapDecorator<MultiKey<? extends K>, V> implements Serializable, Cloneable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -1788199231038721040L;

    public static <K, V> MultiKeyMap<K, V> multiKeyMap(final AbstractHashedMap<MultiKey<? extends K>, V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructs a new MultiKeyMap that decorates a {@code HashedMap}.
     */
    public MultiKeyMap() {
        this(new HashedMap<>());
    }

    /**
     * Constructor that decorates the specified map and is called from
     * {@link #multiKeyMap(AbstractHashedMap)}.
     * The map must not be null and should be empty or only contain valid keys.
     * This constructor performs no validation.
     *
     * @param map  the map to decorate
     */
    protected MultiKeyMap(final AbstractHashedMap<MultiKey<? extends K>, V> map) {
        super(map);
        this.map = map;
    }

    protected void checkKey(final MultiKey<?> key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    @Override
    public MultiKeyMap<K, V> clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsKey(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsKey(final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsKey(final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsKey(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected AbstractHashedMap<MultiKey<? extends K>, V> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HashEntry<MultiKey<? extends K>, V> decoratedHashEntry(final int hashCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    int decoratedHashIndex(final int hashCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V get(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V get(final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V get(final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V get(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hash(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hash(final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hash(final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hash(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualKey(final AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualKey(final AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualKey(final AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualKey(final AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry, final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public MapIterator<MultiKey<? extends K>, V> mapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V put(final K key1, final K key2, final K key3, final K key4, final K key5, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V put(final K key1, final K key2, final K key3, final K key4, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V put(final K key1, final K key2, final K key3, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V put(final K key1, final K key2, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final MultiKey<? extends K> key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(final Map<? extends MultiKey<? extends K>, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        map = (Map<MultiKey<? extends K>, V>) in.readObject();
    }

    public boolean removeAll(final Object key1) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean removeAll(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean removeAll(final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean removeAll(final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V removeMultiKey(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V removeMultiKey(final Object key1, final Object key2, final Object key3) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V removeMultiKey(final Object key1, final Object key2, final Object key3, final Object key4) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V removeMultiKey(final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
}
