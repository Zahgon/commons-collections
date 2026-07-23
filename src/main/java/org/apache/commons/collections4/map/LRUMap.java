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
import org.apache.commons.collections4.BoundedMap;

/**
 * A {@code Map} implementation with a fixed maximum size which removes
 * the least recently used entry if an entry is added when full.
 * <p>
 * The least recently used algorithm works on the get and put operations only.
 * Iteration of any kind, including setting the value by iteration, does not
 * change the order. Queries such as containsKey and containsValue or access
 * via views also do not change the order.
 * </p>
 * <p>
 * A somewhat subtle ramification of the least recently used
 * algorithm is that calls to {@link #get(Object)} stand a very good chance
 * of modifying the map's iteration order and thus invalidating any
 * iterators currently in use.  It is therefore suggested that iterations
 * over an {@link LRUMap} instance access entry values only through a
 * {@link org.apache.commons.collections4.MapIterator MapIterator} or {@link #entrySet()} iterator.
 * </p>
 * <p>
 * The map implements {@code OrderedMap} and entries may be queried using
 * the bidirectional {@code OrderedMapIterator}. The order returned is
 * least recently used to most recently used. Iterators from map views can
 * also be cast to {@code OrderedIterator} if required.
 * </p>
 * <p>
 * All the available iterators can be reset back to the start by casting to
 * {@code ResettableIterator} and calling {@code reset()}.
 * </p>
 * <p>
 * <strong>Note that LRUMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * {@code NullPointerException}'s when accessed by concurrent threads.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0 (previously in main package v1.0)
 */
public class LRUMap<K, V> extends AbstractLinkedMap<K, V> implements BoundedMap<K, V>, Serializable, Cloneable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -612114643488955218L;

    /**
     * Default maximum size
     */
    protected static final int DEFAULT_MAX_SIZE = 100;

    /**
     * Maximum size
     */
    private transient int maxSize;

    /**
     * Scan behavior
     */
    private final boolean scanUntilRemovable;

    /**
     * Constructs a new empty map with a maximum size of 100.
     */
    public LRUMap() {
        this(DEFAULT_MAX_SIZE, DEFAULT_LOAD_FACTOR, false);
    }

    /**
     * Constructs a new, empty map with the specified maximum size.
     *
     * @param maxSize  the maximum size of the map
     * @throws IllegalArgumentException if the maximum size is less than one
     */
    public LRUMap(final int maxSize) {
        this(maxSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty map with the specified maximum size.
     *
     * @param maxSize  the maximum size of the map
     * @param scanUntilRemovable  scan until a removable entry is found, default false
     * @throws IllegalArgumentException if the maximum size is less than one
     * @since 3.1
     */
    public LRUMap(final int maxSize, final boolean scanUntilRemovable) {
        this(maxSize, DEFAULT_LOAD_FACTOR, scanUntilRemovable);
    }

    /**
     * Constructs a new, empty map with the specified max capacity and
     * load factor.
     *
     * @param maxSize  the maximum size of the map
     * @param loadFactor  the load factor
     * @throws IllegalArgumentException if the maximum size is less than one
     * @throws IllegalArgumentException if the load factor is less than zero
     */
    public LRUMap(final int maxSize, final float loadFactor) {
        this(maxSize, loadFactor, false);
    }

    /**
     * Constructs a new, empty map with the specified max capacity and load factor.
     *
     * @param maxSize  the maximum size of the map
     * @param loadFactor  the load factor
     * @param scanUntilRemovable  scan until a removable entry is found, default false
     * @throws IllegalArgumentException if the maximum size is less than one
     * @throws IllegalArgumentException if the load factor is less than zero
     * @since 3.1
     */
    public LRUMap(final int maxSize, final float loadFactor, final boolean scanUntilRemovable) {
        this(maxSize, maxSize, loadFactor, scanUntilRemovable);
    }

    /**
     * Constructs a new, empty map with the specified maximum size.
     *
     * @param maxSize  the maximum size of the map
     * @param initialSize  the initial size of the map
     * @throws IllegalArgumentException if the maximum size is less than one
     * @throws IllegalArgumentException if the initial size is negative or larger than the maximum size
     * @since 4.1
     */
    public LRUMap(final int maxSize, final int initialSize) {
        this(maxSize, initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty map with the specified max / initial capacity and
     * load factor.
     *
     * @param maxSize  the maximum size of the map
     * @param initialSize  the initial size of the map
     * @param loadFactor  the load factor
     * @throws IllegalArgumentException if the maximum size is less than one
     * @throws IllegalArgumentException if the initial size is negative or larger than the maximum size
     * @throws IllegalArgumentException if the load factor is less than zero
     * @since 4.1
     */
    public LRUMap(final int maxSize, final int initialSize, final float loadFactor) {
        this(maxSize, initialSize, loadFactor, false);
    }

    /**
     * Constructs a new, empty map with the specified max / initial capacity and load factor.
     *
     * @param maxSize  the maximum size of the map
     * @param initialSize  the initial size of the map
     * @param loadFactor  the load factor
     * @param scanUntilRemovable  scan until a removable entry is found, default false
     * @throws IllegalArgumentException if the maximum size is less than one
     * @throws IllegalArgumentException if the initial size is negative or larger than the maximum size
     * @throws IllegalArgumentException if the load factor is less than zero
     * @since 4.1
     */
    public LRUMap(final int maxSize, final int initialSize, final float loadFactor, final boolean scanUntilRemovable) {
        super(initialSize, loadFactor);
        if (maxSize < 1) {
            throw new IllegalArgumentException("LRUMap max size must be greater than 0");
        }
        if (initialSize > maxSize) {
            throw new IllegalArgumentException("LRUMap initial size must not be greater than max size");
        }
        this.maxSize = maxSize;
        this.scanUntilRemovable = scanUntilRemovable;
    }

    /**
     * Constructor copying elements from another map.
     * <p>
     * The maximum size is set from the map's size.
     * </p>
     *
     * @param map  the map to copy
     * @throws NullPointerException if the map is null
     * @throws IllegalArgumentException if the map is empty
     */
    public LRUMap(final Map<? extends K, ? extends V> map) {
        this(map, false);
    }

    /**
     * Constructor copying elements from another map.
     *
     * <p>The maximum size is set from the map's size.</p>
     *
     * @param map  the map to copy
     * @param scanUntilRemovable  scan until a removable entry is found, default false
     * @throws NullPointerException if the map is null
     * @throws IllegalArgumentException if the map is empty
     * @since 3.1
     */
    public LRUMap(final Map<? extends K, ? extends V> map, final boolean scanUntilRemovable) {
        this(map.size(), DEFAULT_LOAD_FACTOR, scanUntilRemovable);
        putAll(map);
    }

    @Override
    protected void addMapping(final int hashIndex, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public LRUMap<K, V> clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V get(final Object key, final boolean updateToMRU) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isScanUntilRemovable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int maxSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void moveToMRU(final LinkEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        doReadObject(in);
    }

    protected boolean removeLRU(final LinkEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void reuseMapping(final LinkEntry<K, V> entry, final int hashIndex, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void updateEntry(final HashEntry<K, V> entry, final V newValue) {
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
        doWriteObject(out);
    }
}
