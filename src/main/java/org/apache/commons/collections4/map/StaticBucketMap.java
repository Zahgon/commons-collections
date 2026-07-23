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

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;

/**
 * A StaticBucketMap is an efficient, thread-safe implementation of
 * {@link java.util.Map} that performs well in a highly
 * thread-contentious environment.
 * <p>
 * The map supports very efficient
 * {@link #get(Object) get}, {@link #put(Object,Object) put},
 * {@link #remove(Object) remove} and {@link #containsKey(Object) containsKey}
 * operations, assuming (approximate) uniform hashing and
 * that the number of entries does not exceed the number of buckets.  If the
 * number of entries exceeds the number of buckets or if the hash codes of the
 * objects are not uniformly distributed, these operations have a worst case
 * scenario that is proportional to the number of elements in the map
 * (<em>O(n)</em>).
 * </p>
 * <p>
 * Each bucket in the hash table has its own monitor, so two threads can
 * safely operate on the map at the same time, often without incurring any
 * monitor contention.  This means that you don't have to wrap instances
 * of this class with {@link java.util.Collections#synchronizedMap(Map)};
 * instances are already thread-safe.  Unfortunately, however, this means
 * that this map implementation behaves in ways you may find disconcerting.
 * Bulk operations, such as {@link #putAll(Map) putAll} or the
 * {@link Collection#retainAll(Collection) retainAll} operation in collection
 * views, are <em>not</em> atomic.  If two threads are simultaneously
 * executing
 * </p>
 *
 * <pre>
 *   staticBucketMapInstance.putAll(map);
 * </pre>
 *
 * and
 *
 * <pre>
 *   staticBucketMapInstance.entrySet().removeAll(map.entrySet());
 * </pre>
 *
 * <p>
 * then the results are generally random.  Those two statement could cancel
 * each other out, leaving {@code staticBucketMapInstance} essentially
 * unchanged, or they could leave some random subset of {@code map} in
 * {@code staticBucketMapInstance}.
 * </p>
 * <p>
 * Also, much like an encyclopedia, the results of {@link #size()} and
 * {@link #isEmpty()} are out-of-date as soon as they are produced.
 * </p>
 * <p>
 * The iterators returned by the collection views of this class are <em>not</em>
 * fail-fast.  They will <em>never</em> raise a
 * {@link java.util.ConcurrentModificationException}.  Keys and values
 * added to the map after the iterator is created do not necessarily appear
 * during iteration.  Similarly, the iterator does not necessarily fail to
 * return keys and values that were removed after the iterator was created.
 * </p>
 * <p>
 * Finally, unlike {@link java.util.HashMap}-style implementations, this
 * class <em>never</em> rehashes the map.  The number of buckets is fixed
 * at construction time and never altered.  Performance may degrade if
 * you do not allocate enough buckets upfront.
 * </p>
 * <p>
 * The {@link #atomic(Runnable)} method is provided to allow atomic iterations
 * and bulk operations; however, overuse of {@link #atomic(Runnable) atomic}
 * will basically result in a map that's slower than an ordinary synchronized
 * {@link java.util.HashMap}.
 * </p>
 * <p>
 * Use this class if you do not require reliable bulk operations and
 * iterations, or if you can make your own guarantees about how bulk
 * operations will affect the map.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0 (previously in main package v2.1)
 */
public final class StaticBucketMap<K, V> extends AbstractIterableMap<K, V> {

    class BaseIterator {

        private final ArrayList<Map.Entry<K, V>> current = new ArrayList<>();

        private int bucket;

        private Map.Entry<K, V> last;

        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected Map.Entry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private final class EntryIterator extends BaseIterator implements Iterator<Map.Entry<K, V>> {

        @Override
        public Map.Entry<K, V> next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object obj) {
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

    private final class KeyIterator extends BaseIterator implements Iterator<K> {

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private final class KeySet extends AbstractSet<K> {

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<K> iterator() {
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
     * The lock object, which also includes a count of the nodes in this lock.
     */
    private static final class Lock {

        public int size;
    }

    /**
     * The Map.Entry for the StaticBucketMap.
     */
    private static final class Node<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {

        protected K key;

        protected V value;

        protected Node<K, V> next;

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
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
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private final class ValueIterator extends BaseIterator implements Iterator<V> {

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private final class Values extends AbstractCollection<V> {

        @Override
        public void clear() {
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
     * The default number of buckets to use
     */
    private static final int DEFAULT_BUCKETS = 255;

    /**
     * The array of buckets, where the actual data is held
     */
    private final Node<K, V>[] buckets;

    /**
     * The matching array of locks
     */
    private final Lock[] locks;

    /**
     * Initializes the map with the default number of buckets (255).
     */
    public StaticBucketMap() {
        this(DEFAULT_BUCKETS);
    }

    /**
     * Initializes the map with a specified number of buckets.  The number
     * of buckets is never below 17, and is always an odd number (StaticBucketMap
     * ensures this). The number of buckets is inversely proportional to the
     * chances for thread contention.  The fewer buckets, the more chances for
     * thread contention.  The more buckets the fewer chances for thread
     * contention.
     *
     * @param numBuckets  the number of buckets for this map
     */
    @SuppressWarnings("unchecked")
    public StaticBucketMap(final int numBuckets) {
        int size = Math.max(17, numBuckets);
        // Ensure that bucketSize is never a power of 2 (to ensure maximal distribution)
        if (size % 2 == 0) {
            size--;
        }
        buckets = new Node[size];
        locks = new Lock[size];
        for (int i = 0; i < size; i++) {
            locks[i] = new Lock();
        }
    }

    public void atomic(final Runnable runnable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void atomic(final Runnable r, final int bucket) {
        if (bucket >= buckets.length) {
            r.run();
            return;
        }
        synchronized (locks[bucket]) {
            atomic(r, bucket + 1);
        }
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
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Determine the exact hash entry for the key.  The hash algorithm
     * is rather simplistic, but it does the job:
     *
     * <pre>
     *   He = |Hk mod n|
     * </pre>
     *
     * <p>
     *   He is the entry's hashCode, Hk is the key's hashCode, and n is
     *   the number of buckets.
     * </p>
     */
    private int getHash(final Object key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        hash += ~(hash << 15);
        hash ^= hash >>> 10;
        hash += hash << 3;
        hash ^= hash >>> 6;
        hash += ~(hash << 11);
        hash ^= hash >>> 16;
        hash %= buckets.length;
        return hash < 0 ? hash * -1 : hash;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<K> keySet() {
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
