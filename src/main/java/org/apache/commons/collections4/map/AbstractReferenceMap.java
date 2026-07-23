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
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;

/**
 * An abstract implementation of a hash-based map that allows the entries to
 * be removed by the garbage collector.
 * <p>
 * This class implements all the features necessary for a subclass reference
 * hash-based map. Key-value entries are stored in instances of the
 * {@code ReferenceEntry} class which can be overridden and replaced.
 * The iterators can similarly be replaced, without the need to replace the KeySet,
 * EntrySet and Values view classes.
 * </p>
 * <p>
 * Overridable methods are provided to change the default hashing behavior, and
 * to change how entries are added to and removed from the map. Hopefully, all you
 * need for unusual subclasses is here.
 * </p>
 * <p>
 * When you construct an {@code AbstractReferenceMap}, you can specify what
 * kind of references are used to store the map's keys and values.
 * If non-hard references are used, then the garbage collector can remove
 * mappings if a key or value becomes unreachable, or if the JVM's memory is
 * running low. For information on how the different reference types behave,
 * see {@link Reference}.
 * </p>
 * <p>
 * Different types of references can be specified for keys and values.
 * The keys can be configured to be weak but the values hard,
 * in which case this class will behave like a
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/WeakHashMap.html">
 * {@code WeakHashMap}</a>. However, you can also specify hard keys and
 * weak values, or any other combination. The default constructor uses
 * hard keys and soft values, providing a memory-sensitive cache.
 * </p>
 * <p>
 * This {@link Map} implementation does <em>not</em> allow null elements.
 * Attempting to add a null key or value to the map will raise a
 * {@code NullPointerException}.
 * </p>
 * <p>
 * All the available iterators can be reset back to the start by casting to
 * {@code ResettableIterator} and calling {@code reset()}.
 * </p>
 * <p>
 * This implementation is not synchronized.
 * You can use {@link java.util.Collections#synchronizedMap} to
 * provide synchronized access to a {@code ReferenceMap}.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @see java.lang.ref.Reference
 * @since 3.1 (extracted from ReferenceMap in 3.0)
 */
public abstract class AbstractReferenceMap<K, V> extends AbstractHashedMap<K, V> {

    /**
     * Base iterator class.
     */
    static class ReferenceBaseIterator<K, V> {

        /**
         * The parent map
         */
        final AbstractReferenceMap<K, V> parent;

        // These fields keep track of where we are in the table.
        int index;

        ReferenceEntry<K, V> next;

        ReferenceEntry<K, V> current;

        // These Object fields provide hard references to the
        // current and next entry; this assures that if hasNext()
        // returns true, next() will actually return a valid element.
        K currentKey;

        K nextKey;

        V currentValue;

        V nextValue;

        int expectedModCount;

        ReferenceBaseIterator(final AbstractReferenceMap<K, V> parent) {
            this.parent = parent;
            index = !parent.isEmpty() ? parent.data.length : 0;
            // have to do this here!  size() invocation above
            // may have altered the modCount.
            expectedModCount = parent.modCount;
        }

        private void checkMod() {
            if (parent.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        protected ReferenceEntry<K, V> currentEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected ReferenceEntry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private boolean nextNull() {
            return nextKey == null || nextValue == null;
        }

        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A MapEntry implementation for the map.
     * <p>
     * If getKey() or getValue() returns null, it means
     * the mapping is stale and should be removed.
     * </p>
     *
     * @param <K> the type of the keys
     * @param <V> the type of the values
     * @since 3.1
     */
    protected static class ReferenceEntry<K, V> extends HashEntry<K, V> {

        /**
         * The parent map
         */
        private final AbstractReferenceMap<K, V> parent;

        /**
         * Creates a new entry object for the ReferenceMap.
         *
         * @param parent  the parent map
         * @param next  the next entry in the hash bucket
         * @param hashCode  the hash code of the key
         * @param key  the key
         * @param value  the value
         */
        public ReferenceEntry(final AbstractReferenceMap<K, V> parent, final HashEntry<K, V> next, final int hashCode, final K key, final V value) {
            super(next, hashCode, null, null);
            this.parent = parent;
            this.key = toReference(parent.keyType, key, hashCode);
            // the key hashCode is passed in deliberately
            this.value = toReference(parent.valueType, value, hashCode);
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

        protected ReferenceEntry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void nullValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected void onPurge() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected boolean purge(final Reference<?> ref) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        @SuppressWarnings("unchecked")
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected <T> Object toReference(final ReferenceStrength type, final T referent, final int hash) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * EntrySet implementation.
     */
    static class ReferenceEntrySet<K, V> extends EntrySet<K, V> {

        protected ReferenceEntrySet(final AbstractHashedMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <T> T[] toArray(final T[] arr) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The EntrySet iterator.
     */
    static class ReferenceEntrySetIterator<K, V> extends ReferenceBaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {

        ReferenceEntrySetIterator(final AbstractReferenceMap<K, V> parent) {
            super(parent);
        }

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * KeySet implementation.
     */
    static class ReferenceKeySet<K> extends KeySet<K> {

        protected ReferenceKeySet(final AbstractHashedMap<K, ?> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <T> T[] toArray(final T[] arr) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The keySet iterator.
     */
    static class ReferenceKeySetIterator<K> extends ReferenceBaseIterator<K, Object> implements Iterator<K> {

        @SuppressWarnings("unchecked")
        ReferenceKeySetIterator(final AbstractReferenceMap<K, ?> parent) {
            super((AbstractReferenceMap<K, Object>) parent);
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The MapIterator implementation.
     */
    static class ReferenceMapIterator<K, V> extends ReferenceBaseIterator<K, V> implements MapIterator<K, V> {

        protected ReferenceMapIterator(final AbstractReferenceMap<K, V> parent) {
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
     * Enumerates reference types.
     */
    public enum ReferenceStrength {

        /**
         * Hard reference type.
         */
        HARD(0),
        /**
         * Soft reference type.
         */
        SOFT(1),
        /**
         * Weak reference type.
         */
        WEAK(2);

        public static ReferenceStrength resolve(final int value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Value
         */
        public final int value;

        ReferenceStrength(final int value) {
            this.value = value;
        }
    }

    /**
     * Values implementation.
     */
    static class ReferenceValues<V> extends Values<V> {

        protected ReferenceValues(final AbstractHashedMap<?, V> parent) {
            super(parent);
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public <T> T[] toArray(final T[] arr) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The values iterator.
     */
    static class ReferenceValuesIterator<V> extends ReferenceBaseIterator<Object, V> implements Iterator<V> {

        @SuppressWarnings("unchecked")
        ReferenceValuesIterator(final AbstractReferenceMap<?, V> parent) {
            super((AbstractReferenceMap<Object, V>) parent);
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A soft reference holder.
     */
    static class SoftRef<T> extends SoftReference<T> {

        /**
         * The hashCode of the key (even if the reference points to a value)
         */
        private final int hash;

        SoftRef(final int hash, final T r, final ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A weak reference holder.
     */
    static class WeakRef<T> extends WeakReference<T> {

        /**
         * The hashCode of the key (even if the reference points to a value)
         */
        private final int hash;

        WeakRef(final int hash, final T r, final ReferenceQueue<? super T> q) {
            super(r, q);
            this.hash = hash;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The reference type for keys.
     */
    private ReferenceStrength keyType;

    /**
     * The reference type for values.
     */
    private ReferenceStrength valueType;

    /**
     * Should the value be automatically purged when the associated key has been collected?
     */
    private boolean purgeValues;

    /**
     * ReferenceQueue used to eliminate stale mappings.
     * See purge.
     */
    private transient ReferenceQueue<Object> queue;

    /**
     * Constructor used during deserialization.
     */
    protected AbstractReferenceMap() {
    }

    /**
     * Constructs a new empty map with the specified reference types,
     * load factor and initial capacity.
     *
     * @param keyType  the type of reference to use for keys;
     *   must be {@link ReferenceStrength#HARD HARD},
     *   {@link ReferenceStrength#SOFT SOFT},
     *   {@link ReferenceStrength#WEAK WEAK}
     * @param valueType  the type of reference to use for values;
     *   must be {@link ReferenceStrength#HARD},
     *   {@link ReferenceStrength#SOFT SOFT},
     *   {@link ReferenceStrength#WEAK WEAK}
     * @param capacity  the initial capacity for the map
     * @param loadFactor  the load factor for the map
     * @param purgeValues  should the value be automatically purged when the
     *   key is garbage collected
     */
    protected AbstractReferenceMap(final ReferenceStrength keyType, final ReferenceStrength valueType, final int capacity, final float loadFactor, final boolean purgeValues) {
        super(capacity, loadFactor);
        this.keyType = keyType;
        this.valueType = valueType;
        this.purgeValues = purgeValues;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected ReferenceEntry<K, V> createEntry(final HashEntry<K, V> next, final int hashCode, final K key, final V value) {
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

    @Override
    @SuppressWarnings("unchecked")
    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected HashEntry<K, V> getEntry(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int hashEntry(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void init() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean isEqualKey(final Object key1, Object key2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isKeyType(final ReferenceStrength type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isValueType(final ReferenceStrength type) {
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

    protected void purge() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void purge(final Reference<?> ref) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // These two classes store the hashCode of the key of
    // the mapping, so that after they're dequeued a quick
    // lookup of the bucket in the table can occur.
    protected void purgeBeforeRead() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void purgeBeforeWrite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
