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

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;

/**
 * An abstract implementation of a hash-based map that links entries to create an
 * ordered map and which provides numerous points for subclasses to override.
 * <p>
 * This class implements all the features necessary for a subclass linked
 * hash-based map. Key-value entries are stored in instances of the
 * {@code LinkEntry} class which can be overridden and replaced.
 * The iterators can similarly be replaced, without the need to replace the KeySet,
 * EntrySet and Values view classes.
 * </p>
 * <p>
 * Overridable methods are provided to change the default hashing behavior, and
 * to change how entries are added to and removed from the map. Hopefully, all you
 * need for unusual subclasses is here.
 * </p>
 * <p>
 * This implementation maintains order by original insertion, but subclasses
 * may work differently. The {@code OrderedMap} interface is implemented
 * to provide access to bidirectional iteration and extra convenience methods.
 * </p>
 * <p>
 * The {@code orderedMapIterator()} method provides direct access to a
 * bidirectional iterator. The iterators from the other views can also be cast
 * to {@code OrderedIterator} if required.
 * </p>
 * <p>
 * All the available iterators can be reset back to the start by casting to
 * {@code ResettableIterator} and calling {@code reset()}.
 * </p>
 * <p>
 * The implementation is also designed to be subclassed, with lots of useful
 * methods exposed.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public abstract class AbstractLinkedMap<K, V> extends AbstractHashedMap<K, V> implements OrderedMap<K, V> {

    /**
     * EntrySet iterator.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     */
    protected static class EntrySetIterator<K, V> extends LinkIterator<K, V> implements OrderedIterator<Map.Entry<K, V>>, ResettableIterator<Map.Entry<K, V>> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedMap.
         */
        protected EntrySetIterator(final AbstractLinkedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Map.Entry<K, V> previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * KeySet iterator.
     *
     * @param <K> the key type.
     */
    protected static class KeySetIterator<K> extends LinkIterator<K, Object> implements OrderedIterator<K>, ResettableIterator<K> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedMap.
         */
        @SuppressWarnings("unchecked")
        protected KeySetIterator(final AbstractLinkedMap<K, ?> parent) {
            super((AbstractLinkedMap<K, Object>) parent);
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * LinkEntry that stores the data.
     * <p>
     * If you subclass {@code AbstractLinkedMap} but not {@code LinkEntry}
     * then you will not be able to access the protected fields.
     * The {@code entryXxx()} methods on {@code AbstractLinkedMap} exist
     * to provide the necessary access.
     * </p>
     *
     * @param <K> the key type.
     * @param <V> the value type.
     */
    protected static class LinkEntry<K, V> extends HashEntry<K, V> {

        /**
         * The entry before this one in the order
         */
        protected LinkEntry<K, V> before;

        /**
         * The entry after this one in the order
         */
        protected LinkEntry<K, V> after;

        /**
         * Constructs a new entry.
         *
         * @param next  the next entry in the hash bucket sequence
         * @param hashCode  the hash code
         * @param key  the key
         * @param value  the value
         */
        protected LinkEntry(final HashEntry<K, V> next, final int hashCode, final Object key, final V value) {
            super(next, hashCode, key, value);
        }
    }

    /**
     * Base Iterator that iterates in link order.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     */
    protected abstract static class LinkIterator<K, V> {

        /**
         * The parent map
         */
        protected final AbstractLinkedMap<K, V> parent;

        /**
         * The current (last returned) entry
         */
        protected LinkEntry<K, V> last;

        /**
         * The next entry
         */
        protected LinkEntry<K, V> next;

        /**
         * The modification count expected
         */
        protected int expectedModCount;

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedMap.
         */
        protected LinkIterator(final AbstractLinkedMap<K, V> parent) {
            this.parent = Objects.requireNonNull(parent);
            this.next = parent.header.after;
            this.expectedModCount = parent.modCount;
        }

        protected LinkEntry<K, V> currentEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean hasPrevious() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected LinkEntry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected LinkEntry<K, V> previousEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void reset() {
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
     * @param <K> the key type.
     * @param <V> the value type.
     */
    protected static class LinkMapIterator<K, V> extends LinkIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedMap.
         */
        protected LinkMapIterator(final AbstractLinkedMap<K, V> parent) {
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
        public K previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Values iterator.
     *
     * @param <V> the value type.
     */
    protected static class ValuesIterator<V> extends LinkIterator<Object, V> implements OrderedIterator<V>, ResettableIterator<V> {

        /**
         * Constructs a new instance.
         *
         * @param parent The parent AbstractLinkedMap.
         */
        @SuppressWarnings("unchecked")
        protected ValuesIterator(final AbstractLinkedMap<?, V> parent) {
            super((AbstractLinkedMap<Object, V>) parent);
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Header in the linked list
     */
    transient LinkEntry<K, V> header;

    /**
     * Constructor only used in deserialization, do not use otherwise.
     */
    protected AbstractLinkedMap() {
    }

    /**
     * Constructs a new, empty map with the specified initial capacity.
     *
     * @param initialCapacity  the initial capacity
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    protected AbstractLinkedMap(final int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a new, empty map with the specified initial capacity and
     * load factor.
     *
     * @param initialCapacity  the initial capacity
     * @param loadFactor  the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     * @throws IllegalArgumentException if the load factor is less than zero
     */
    protected AbstractLinkedMap(final int initialCapacity, final float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Constructor which performs no validation on the passed in parameters.
     *
     * @param initialCapacity  the initial capacity, must be a power of two
     * @param loadFactor  the load factor, must be &gt; 0.0f and generally &lt; 1.0f
     * @param threshold  the threshold, must be sensible
     */
    protected AbstractLinkedMap(final int initialCapacity, final float loadFactor, final int threshold) {
        super(initialCapacity, loadFactor, threshold);
    }

    /**
     * Constructor copying elements from another map.
     *
     * @param map  the map to copy
     * @throws NullPointerException if the map is null
     */
    protected AbstractLinkedMap(final Map<? extends K, ? extends V> map) {
        super(map);
    }

    @Override
    protected void addEntry(final HashEntry<K, V> entry, final int hashIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected LinkEntry<K, V> createEntry(final HashEntry<K, V> next, final int hashCode, final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Iterator<K> createKeySetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Iterator<V> createValuesIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected LinkEntry<K, V> entryAfter(final LinkEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected LinkEntry<K, V> entryBefore(final LinkEntry<K, V> entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected LinkEntry<K, V> getEntry(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected LinkEntry<K, V> getEntry(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void init() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K lastKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K nextKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K previousKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void removeEntry(final HashEntry<K, V> entry, final int hashIndex, final HashEntry<K, V> previous) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
