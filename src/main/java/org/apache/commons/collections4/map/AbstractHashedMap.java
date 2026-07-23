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
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;

/**
 * An abstract implementation of a hash-based map which provides numerous points for
 * subclasses to override.
 * <p>
 * This class implements all the features necessary for a subclass hash-based map.
 * Key-value entries are stored in instances of the {@code HashEntry} class,
 * which can be overridden and replaced. The iterators can similarly be replaced,
 * without the need to replace the KeySet, EntrySet and Values view classes.
 * </p>
 * <p>
 * Overridable methods are provided to change the default hashing behavior, and
 * to change how entries are added to and removed from the map. Hopefully, all you
 * need for unusual subclasses is here.
 * </p>
 * <p>
 * NOTE: From Commons Collections 3.1 this class extends AbstractMap.
 * This is to provide backwards compatibility for ReferenceMap between v3.0 and v3.1.
 * This extends clause will be removed in v5.0.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class AbstractHashedMap<K, V> extends AbstractMap<K, V> implements IterableMap<K, V> {

    /**
     * EntrySet implementation.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     */
    protected static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {

        /**
         * The parent map
         */
        private final AbstractHashedMap<K, V> parent;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent map.
         */
        protected EntrySet(final AbstractHashedMap<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object entry) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * EntrySet iterator.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     */
    protected static class EntrySetIterator<K, V> extends HashIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent map.
         */
        protected EntrySetIterator(final AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * HashEntry used to store the data.
     * <p>
     * If you subclass {@code AbstractHashedMap} but not {@code HashEntry}
     * then you will not be able to access the protected fields.
     * The {@code entryXxx()} methods on {@code AbstractHashedMap} exist
     * to provide the necessary access.
     * </p>
     *
     * @param <K> the type of the keys
     * @param <V> the type of the values
     */
    protected static class HashEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {

        /**
         * The next entry in the hash chain
         */
        protected HashEntry<K, V> next;

        /**
         * The hash code of the key
         */
        protected int hashCode;

        /**
         * The key
         */
        protected Object key;

        /**
         * The value
         */
        protected Object value;

        /**
         * Constructs a new instance.
         *
         * @param next next.
         * @param hashCode hash code.
         * @param key key.
         * @param value value.
         */
        protected HashEntry(final HashEntry<K, V> next, final int hashCode, final Object key, final V value) {
            this.next = next;
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public K getKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public V getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Base Iterator.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     */
    protected abstract static class HashIterator<K, V> {

        /**
         * The parent map
         */
        private final AbstractHashedMap<K, V> parent;

        /**
         * The current index into the array of buckets
         */
        private int hashIndex;

        /**
         * The last returned entry
         */
        private HashEntry<K, V> last;

        /**
         * The next entry
         */
        private HashEntry<K, V> next;

        /**
         * The modification count expected
         */
        private int expectedModCount;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        protected HashIterator(final AbstractHashedMap<K, V> parent) {
            this.parent = parent;
            final HashEntry<K, V>[] data = parent.data;
            int i = data.length;
            HashEntry<K, V> next = null;
            while (i > 0 && next == null) {
                next = data[--i];
            }
            this.next = next;
            this.hashIndex = i;
            this.expectedModCount = parent.modCount;
        }

        protected HashEntry<K, V> currentEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected HashEntry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * MapIterator implementation.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     */
    protected static class HashMapIterator<K, V> extends HashIterator<K, V> implements MapIterator<K, V> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        protected HashMapIterator(final AbstractHashedMap<K, V> parent) {
            super(parent);
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
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * KeySet implementation.
     *
     * @param <K> the type of elements maintained by this set
     */
    protected static class KeySet<K> extends AbstractSet<K> {

        /**
         * The parent map
         */
        private final AbstractHashedMap<K, ?> parent;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        protected KeySet(final AbstractHashedMap<K, ?> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * KeySet iterator.
     *
     * @param <K> the type of elements maintained by this set
     */
    protected static class KeySetIterator<K> extends HashIterator<K, Object> implements Iterator<K> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        @SuppressWarnings("unchecked")
        protected KeySetIterator(final AbstractHashedMap<K, ?> parent) {
            super((AbstractHashedMap<K, Object>) parent);
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Values implementation.
     *
     * @param <V> the type of elements maintained by this collection
     */
    protected static class Values<V> extends AbstractCollection<V> {

        /**
         * The parent map
         */
        private final AbstractHashedMap<?, V> parent;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        protected Values(final AbstractHashedMap<?, V> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<V> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Values iterator.
     *
     * @param <V> the type of elements maintained by this collection
     */
    protected static class ValuesIterator<V> extends HashIterator<Object, V> implements Iterator<V> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractHashedMap.
         */
        @SuppressWarnings("unchecked")
        protected ValuesIterator(final AbstractHashedMap<?, V> parent) {
            super((AbstractHashedMap<Object, V>) parent);
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Exception message.
     */
    protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";

    /**
     * Exception message.
     */
    protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";

    /**
     * Exception message.
     */
    protected static final String REMOVE_INVALID = "remove() can only be called once after next()";

    /**
     * Exception message.
     */
    protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";

    /**
     * Exception message.
     */
    protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";

    /**
     * Exception message.
     */
    protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";

    /**
     * The default capacity to use
     */
    protected static final int DEFAULT_CAPACITY = 16;

    /**
     * The default threshold to use
     */
    protected static final int DEFAULT_THRESHOLD = 12;

    /**
     * The default load factor to use
     */
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The maximum capacity allowed
     */
    protected static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * An object for masking null
     */
    protected static final Object NULL = new Object();

    /**
     * Load factor, normally 0.75
     */
    transient float loadFactor;

    /**
     * The size of the map
     */
    transient int size;

    /**
     * Map entries
     */
    transient HashEntry<K, V>[] data;

    /**
     * Size at which to rehash
     */
    transient int threshold;

    /**
     * Modification count for iterators
     */
    transient int modCount;

    /**
     * Entry set
     */
    transient EntrySet<K, V> entrySet;

    /**
     * Key set
     */
    transient KeySet<K> keySet;

    /**
     * Values
     */
    transient Values<V> values;

    /**
     * Constructor only used in deserialization, do not use otherwise.
     */
    protected AbstractHashedMap() {
    }

    /**
     * Constructs a new, empty map with the specified initial capacity and
     * default load factor.
     *
     * @param initialCapacity  the initial capacity
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    protected AbstractHashedMap(final int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs a new, empty map with the specified initial capacity and
     * load factor.
     *
     * @param initialCapacity  the initial capacity
     * @param loadFactor  the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     * @throws IllegalArgumentException if the load factor is less than or equal to zero
     */
    @SuppressWarnings("unchecked")
    protected AbstractHashedMap(int initialCapacity, final float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity must be a non negative number");
        }
        if (loadFactor <= 0.0f || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be greater than 0");
        }
        this.loadFactor = loadFactor;
        initialCapacity = calculateNewCapacity(initialCapacity);
        this.threshold = calculateThreshold(initialCapacity, loadFactor);
        this.data = new HashEntry[initialCapacity];
        init();
    }

    /**
     * Constructor which performs no validation on the passed in parameters.
     *
     * @param initialCapacity  the initial capacity, must be a power of two
     * @param loadFactor  the load factor, must be &gt; 0.0f and generally &lt; 1.0f
     * @param threshold  the threshold, must be sensible
     */
    @SuppressWarnings("unchecked")
    protected AbstractHashedMap(final int initialCapacity, final float loadFactor, final int threshold) {
        this.loadFactor = loadFactor;
        this.data = new HashEntry[initialCapacity];
        this.threshold = threshold;
        init();
    }

    /**
     * Constructor copying elements from another map.
     *
     * @param map  the map to copy
     * @throws NullPointerException if the map is null
     */
    protected AbstractHashedMap(final Map<? extends K, ? extends V> map) {
        this(Math.max(2 * map.size(), DEFAULT_CAPACITY), DEFAULT_LOAD_FACTOR);
        putAll(map);
    }

    protected void addEntry(final HashEntry<K, V> entry, final int hashIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void addMapping(final int hashIndex, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int calculateNewCapacity(final int proposedCapacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int calculateThreshold(final int newCapacity, final float factor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void checkCapacity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected AbstractHashedMap<K, V> clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Object convertKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected HashEntry<K, V> createEntry(final HashEntry<K, V> next, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<K> createKeySetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<V> createValuesIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void destroyEntry(final HashEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    protected void ensureCapacity(final int newCapacity) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int entryHashCode(final HashEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected K entryKey(final HashEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected HashEntry<K, V> entryNext(final HashEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected V entryValue(final HashEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected HashEntry<K, V> getEntry(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hash(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hashIndex(final int hashCode, final int dataSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void init() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualKey(final Object key1, final Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isEqualValue(final Object value1, final Object value2) {
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
    public void putAll(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void removeEntry(final HashEntry<K, V> entry, final int hashIndex, final HashEntry<K, V> previous) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void removeMapping(final HashEntry<K, V> entry, final int hashIndex, final HashEntry<K, V> previous) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void reuseEntry(final HashEntry<K, V> entry, final int hashIndex, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void updateEntry(final HashEntry<K, V> entry, final V newValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
