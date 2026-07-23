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
package org.apache.commons.collections4.trie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Trie;

/**
 * This class implements the base PATRICIA algorithm and everything that
 * is related to the {@link Map} interface.
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.0
 */
public abstract class AbstractPatriciaTrie<K, V> extends AbstractBitwiseTrie<K, V> {

    /**
     * A range view of the {@link org.apache.commons.collections4.Trie}.
     */
    private abstract class AbstractRangeMap extends AbstractMap<K, V> implements SortedMap<K, V> {

        /**
         * The {@link #entrySet()} view.
         */
        private transient volatile Set<Map.Entry<K, V>> entrySet;

        @Override
        public Comparator<? super K> comparator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsKey(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates and returns an {@link #entrySet()} view of the {@link AbstractRangeMap}.
         */
        protected abstract Set<Map.Entry<K, V>> createEntrySet();

        /**
         * Creates and returns a sub-range view of the current {@link AbstractRangeMap}.
         */
        protected abstract SortedMap<K, V> createRangeMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive);

        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V get(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Gets the FROM Key.
         */
        protected abstract K getFromKey();

        /**
         * Gets the TO Key.
         */
        protected abstract K getToKey();

        @Override
        public SortedMap<K, V> headMap(final K toKey) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected boolean inFromRange(final K key, final boolean forceInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected boolean inRange(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected boolean inRange2(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected boolean inToRange(final K key, final boolean forceInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Tests whether or not the {@link #getFromKey()} is in the range.
         *
         * @return whether or not the {@link #getFromKey()} is in the range.
         */
        protected abstract boolean isFromInclusive();

        /**
         * Tests whether or not the {@link #getToKey()} is in the range.
         *
         * @return whether or not the {@link #getToKey()} is in the range.
         */
        protected abstract boolean isToInclusive();

        @Override
        public V put(final K key, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V remove(final Object key) {
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

    /**
     * An iterator for the entries.
     */
    abstract class AbstractTrieIterator<E> implements Iterator<E> {

        /**
         * For fast-fail.
         */
        protected int expectedModCount = AbstractPatriciaTrie.this.modCount;

        // the next node to return
        protected TrieEntry<K, V> next;

        // the current entry we're on
        protected TrieEntry<K, V> current;

        /**
         * Starts iteration from the root.
         */
        protected AbstractTrieIterator() {
            next = AbstractPatriciaTrie.this.nextEntry(null);
        }

        /**
         * Starts iteration at the given entry.
         */
        protected AbstractTrieIterator(final TrieEntry<K, V> firstEntry) {
            next = firstEntry;
        }

        protected TrieEntry<K, V> findNext(final TrieEntry<K, V> prior) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected TrieEntry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * This is an entry set view of the {@link org.apache.commons.collections4.Trie} as returned by {@link Map#entrySet()}.
     */
    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        /**
         * An {@link Iterator} that returns {@link Entry} Objects.
         */
        private final class EntryIterator extends AbstractTrieIterator<Map.Entry<K, V>> {

            @Override
            public Map.Entry<K, V> next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object o) {
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
     * This is a key set view of the {@link org.apache.commons.collections4.Trie} as returned by {@link Map#keySet()}.
     */
    private final class KeySet extends AbstractSet<K> {

        /**
         * An {@link Iterator} that returns Key Objects.
         */
        private final class KeyIterator extends AbstractTrieIterator<K> {

            @Override
            public K next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A prefix {@link RangeEntrySet} view of the {@link org.apache.commons.collections4.Trie}.
     */
    private final class PrefixRangeEntrySet extends RangeEntrySet {

        /**
         * An {@link Iterator} for iterating over a prefix search.
         */
        private final class EntryIterator extends AbstractTrieIterator<Map.Entry<K, V>> {

            // values to reset the subtree if we remove it.
            private final K prefix;

            private final int offset;

            private final int lengthInBits;

            private boolean lastOne;

            // the subtree to search within
            private TrieEntry<K, V> subtree;

            /**
             * Starts iteration at the given entry &amp; search only
             * within the given subtree.
             */
            EntryIterator(final TrieEntry<K, V> startScan, final K prefix, final int offset, final int lengthInBits) {
                subtree = startScan;
                next = AbstractPatriciaTrie.this.followLeft(startScan);
                this.prefix = prefix;
                this.offset = offset;
                this.lengthInBits = lengthInBits;
            }

            @Override
            protected TrieEntry<K, V> findNext(final TrieEntry<K, V> prior) {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public Map.Entry<K, V> next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        /**
         * An {@link Iterator} that holds a single {@link TrieEntry}.
         */
        private final class SingletonIterator implements Iterator<Map.Entry<K, V>> {

            private final TrieEntry<K, V> entry;

            private int hit;

            SingletonIterator(final TrieEntry<K, V> entry) {
                this.entry = entry;
            }

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public Map.Entry<K, V> next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        private final PrefixRangeMap delegate;

        private TrieEntry<K, V> prefixStart;

        private int expectedModCount;

        /**
         * Creates a {@link PrefixRangeEntrySet}.
         */
        PrefixRangeEntrySet(final PrefixRangeMap delegate) {
            super(delegate);
            this.delegate = delegate;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A submap used for prefix views over the {@link org.apache.commons.collections4.Trie}.
     */
    private final class PrefixRangeMap extends AbstractRangeMap {

        private final K prefix;

        private final int offsetInBits;

        private final int lengthInBits;

        private K fromKey;

        private K toKey;

        private transient int expectedModCount;

        private int size = -1;

        /**
         * Creates a {@link PrefixRangeMap}.
         */
        private PrefixRangeMap(final K prefix, final int offsetInBits, final int lengthInBits) {
            this.prefix = prefix;
            this.offsetInBits = offsetInBits;
            this.lengthInBits = lengthInBits;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected Set<Map.Entry<K, V>> createEntrySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected SortedMap<K, V> createRangeMap(final K fromKey, final boolean fromInclusive, final K toKey, final boolean toInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K firstKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * This method does two things. It determines the FROM
         * and TO range of the {@link PrefixRangeMap} and the number
         * of elements in the range. This method must be called every
         * time the {@link org.apache.commons.collections4.Trie} has changed.
         */
        private int fixup() {
            // The trie has changed since we last found our toKey / fromKey
            if (size == -1 || AbstractPatriciaTrie.this.modCount != expectedModCount) {
                final Iterator<Map.Entry<K, V>> it = super.entrySet().iterator();
                size = 0;
                Map.Entry<K, V> entry = null;
                if (it.hasNext()) {
                    entry = it.next();
                    size = 1;
                }
                fromKey = entry == null ? null : entry.getKey();
                if (fromKey != null) {
                    final TrieEntry<K, V> prior = previousEntry((TrieEntry<K, V>) entry);
                    fromKey = prior == null ? null : prior.getKey();
                }
                toKey = fromKey;
                while (it.hasNext()) {
                    ++size;
                    entry = it.next();
                }
                toKey = entry == null ? null : entry.getKey();
                if (toKey != null) {
                    entry = nextEntry((TrieEntry<K, V>) entry);
                    toKey = entry == null ? null : entry.getKey();
                }
                expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return size;
        }

        @Override
        public K getFromKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K getToKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean inFromRange(final K key, final boolean forceInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean inRange(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean inRange2(final K key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected boolean inToRange(final K key, final boolean forceInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isFromInclusive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isToInclusive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K lastKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A {@link AbstractRangeMap} that deals with {@link Entry}s.
     */
    private final class RangeEntryMap extends AbstractRangeMap {

        /**
         * The key to start from, null if the beginning.
         */
        private final K fromKey;

        /**
         * The key to end at, null if till the end.
         */
        private final K toKey;

        /**
         * Whether or not the 'from' is inclusive.
         */
        private final boolean fromInclusive;

        /**
         * Whether or not the 'to' is inclusive.
         */
        private final boolean toInclusive;

        /**
         * Creates a {@link RangeEntryMap}.
         */
        protected RangeEntryMap(final K fromKey, final boolean fromInclusive, final K toKey, final boolean toInclusive) {
            if (fromKey == null && toKey == null) {
                throw new IllegalArgumentException("must have a from or to!");
            }
            if (fromKey != null && toKey != null && getKeyAnalyzer().compare(fromKey, toKey) > 0) {
                throw new IllegalArgumentException("fromKey > toKey");
            }
            this.fromKey = fromKey;
            this.fromInclusive = fromInclusive;
            this.toKey = toKey;
            this.toInclusive = toInclusive;
        }

        /**
         * Creates a {@link RangeEntryMap} with the fromKey included and
         * the toKey excluded from the range.
         */
        protected RangeEntryMap(final K fromKey, final K toKey) {
            this(fromKey, true, toKey, false);
        }

        @Override
        protected Set<Entry<K, V>> createEntrySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected SortedMap<K, V> createRangeMap(final K fromKey, final boolean fromInclusive, final K toKey, final boolean toInclusive) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K firstKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K getFromKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K getToKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isFromInclusive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isToInclusive() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K lastKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A {@link Set} view of a {@link AbstractRangeMap}.
     */
    private class RangeEntrySet extends AbstractSet<Map.Entry<K, V>> {

        /**
         * An {@link Iterator} for {@link RangeEntrySet}s.
         */
        private final class EntryIterator extends AbstractTrieIterator<Map.Entry<K, V>> {

            private final K excludedKey;

            /**
             * Creates a {@link EntryIterator}.
             */
            private EntryIterator(final TrieEntry<K, V> first, final TrieEntry<K, V> last) {
                super(first);
                this.excludedKey = last != null ? last.getKey() : null;
            }

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }

            @Override
            public Map.Entry<K, V> next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        private final AbstractRangeMap delegate;

        private transient int size = -1;

        private transient int expectedModCount;

        /**
         * Creates a {@link RangeEntrySet}.
         */
        RangeEntrySet(final AbstractRangeMap delegate) {
            this.delegate = Objects.requireNonNull(delegate, "delegate");
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean contains(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A {@link Reference} allows us to return something through a Method's
     * argument list. An alternative would be to an Array with a length of
     * one (1) but that leads to compiler warnings. Computationally and memory
     * wise there's no difference (except for the need to load the
     * {@link Reference} Class but that happens only once).
     */
    private static final class Reference<E> {

        private E item;

        public E get() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void set(final E item) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A {@link org.apache.commons.collections4.Trie} is a set of {@link TrieEntry} nodes.
     *
     * @param <K> the key type.
     * @param <V> the value type.
     */
    protected static class TrieEntry<K, V> extends BasicEntry<K, V> {

        private static final long serialVersionUID = 4596023148184140013L;

        /**
         * The index this entry is comparing.
         */
        protected int bitIndex;

        /**
         * The parent of this entry.
         */
        protected TrieEntry<K, V> parent;

        /**
         * The left child of this entry.
         */
        protected TrieEntry<K, V> left;

        /**
         * The right child of this entry.
         */
        protected TrieEntry<K, V> right;

        /**
         * The entry who uplinks to this entry.
         */
        protected TrieEntry<K, V> predecessor;

        /**
         * Constructs a new instance.
         *
         * @param key The entry's key.
         * @param value The entry's value.
         * @param bitIndex The entry's bitIndex.
         */
        public TrieEntry(final K key, final V value, final int bitIndex) {
            super(key, value);
            this.bitIndex = bitIndex;
            this.parent = null;
            this.left = this;
            this.right = null;
            this.predecessor = this;
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isExternalNode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public boolean isInternalNode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * An {@link OrderedMapIterator} for a {@link org.apache.commons.collections4.Trie}.
     */
    private final class TrieMapIterator extends AbstractTrieIterator<K> implements OrderedMapIterator<K, V> {

        // the previous node to return
        protected TrieEntry<K, V> previous;

        @Override
        public K getKey() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V getValue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        protected TrieEntry<K, V> nextEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K previous() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        protected TrieEntry<K, V> previousEntry() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * This is a value view of the {@link org.apache.commons.collections4.Trie} as returned by {@link Map#values()}.
     */
    private final class Values extends AbstractCollection<V> {

        /**
         * An {@link Iterator} that returns Value Objects.
         */
        private final class ValueIterator extends AbstractTrieIterator<V> {

            @Override
            public V next() {
                throw new UnsupportedOperationException("STUB: not implemented");
            }
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<V> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static final long serialVersionUID = 5155253417231339498L;

    static boolean isValidUplink(final TrieEntry<?, ?> next, final TrieEntry<?, ?> from) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The root node of the {@link org.apache.commons.collections4.Trie}.
     */
    private transient TrieEntry<K, V> root = new TrieEntry<>(null, null, -1);

    /**
     * Each of these fields are initialized to contain an instance of the
     * appropriate view the first time this view is requested. The views are
     * stateless, so there's no reason to create more than one of each.
     */
    private transient volatile Set<K> keySet;

    private transient volatile Collection<V> values;

    private transient volatile Set<Map.Entry<K, V>> entrySet;

    /**
     * The current size of the {@link org.apache.commons.collections4.Trie}.
     */
    private transient int size;

    /**
     * The number of times this {@link org.apache.commons.collections4.Trie} has been modified.
     * It's used to detect concurrent modifications and fail-fast the {@link Iterator}s.
     */
    protected transient int modCount;

    /**
     * Constructs a new {@link Trie} using the given {@link KeyAnalyzer}.
     *
     * @param keyAnalyzer  the {@link KeyAnalyzer}.
     */
    protected AbstractPatriciaTrie(final KeyAnalyzer<? super K> keyAnalyzer) {
        super(keyAnalyzer);
    }

    /**
     * Constructs a new {@link org.apache.commons.collections4.Trie} using the given {@link KeyAnalyzer} and initializes the
     * {@link org.apache.commons.collections4.Trie} with the values from the provided {@link Map}.
     *
     * @param keyAnalyzer  the {@link KeyAnalyzer}.
     * @param map The source map.
     */
    protected AbstractPatriciaTrie(final KeyAnalyzer<? super K> keyAnalyzer, final Map<? extends K, ? extends V> map) {
        super(keyAnalyzer);
        putAll(map);
    }

    TrieEntry<K, V> addEntry(final TrieEntry<K, V> entry, final int lengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> ceilingEntry(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Comparator<? super K> comparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(final Object k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void decrementSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> firstEntry() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K firstKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> floorEntry(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> followLeft(TrieEntry<K, V> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> followRight(TrieEntry<K, V> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(final Object k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> getEntry(final Object k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> getNearestEntryForKey(final K key, final int lengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets a view of this {@link org.apache.commons.collections4.Trie} of all elements that are prefixed
     * by the number of bits in the given Key.
     * <p>
     * The view that this returns is optimized to have a very efficient
     * {@link Iterator}. The {@link SortedMap#firstKey()},
     * {@link SortedMap#lastKey()} &amp; {@link Map#size()} methods must
     * iterate over all possible values in order to determine the results.
     * This information is cached until the PATRICIA {@link org.apache.commons.collections4.Trie} changes.
     * All other methods (except {@link Iterator}) must compare the given
     * key to the prefix to ensure that it is within the range of the view.
     * The {@link Iterator}'s remove method must also relocate the subtree
     * that contains the prefixes if the entry holding the subtree is
     * removed or changes. Changing the subtree takes O(K) time.
     *
     * @param key  the key to use in the search
     * @param offsetInBits  the prefix offset
     * @param lengthInBits  the number of significant prefix bits
     * @return a {@link SortedMap} view of this {@link org.apache.commons.collections4.Trie} with all elements whose
     *   key is prefixed by the search key
     */
    private SortedMap<K, V> getPrefixMapByBits(final K key, final int offsetInBits, final int lengthInBits) {
        final int offsetLength = offsetInBits + lengthInBits;
        if (offsetLength > lengthInBits(key)) {
            throw new IllegalArgumentException(offsetInBits + " + " + lengthInBits + " > " + lengthInBits(key));
        }
        if (offsetLength == 0) {
            return this;
        }
        return new PrefixRangeMap(key, offsetInBits, lengthInBits);
    }

    @Override
    public SortedMap<K, V> headMap(final K toKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> higherEntry(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A helper method to increment the modification counter.
     */
    private void incrementModCount() {
        ++modCount;
    }

    void incrementSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> lastEntry() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K lastKey() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> lowerEntry(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> nextEntry(final TrieEntry<K, V> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> nextEntryImpl(final TrieEntry<K, V> start, final TrieEntry<K, V> previous, final TrieEntry<K, V> tree) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> nextEntryInSubtree(final TrieEntry<K, V> node, final TrieEntry<K, V> parentOfSubtree) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K nextKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> prefixMap(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> previousEntry(final TrieEntry<K, V> start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public K previousKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes an instance from an ObjectInputStream.
     *
     * @param in The source ObjectInputStream.
     * @throws IOException            Any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException A class of a serialized object cannot be found.
     */
    // This will fail at runtime if the stream is incorrect
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        root = new TrieEntry<>(null, null, -1);
        final int size = in.readInt();
        for (int i = 0; i < size; i++) {
            final K k = (K) in.readObject();
            final V v = (V) in.readObject();
            put(k, v);
        }
    }

    @Override
    public V remove(final Object k) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    V removeEntry(final TrieEntry<K, V> h) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes an external entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an external Entry then just remove it.
     * This is very easy and straight forward.
     */
    private void removeExternalEntry(final TrieEntry<K, V> h) {
        if (h == root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        }
        if (!h.isExternalNode()) {
            throw new IllegalArgumentException(h + " is not an external Entry!");
        }
        final TrieEntry<K, V> parent = h.parent;
        final TrieEntry<K, V> child = h.left == h ? h.right : h.left;
        if (parent.left == h) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        // either the parent is changing, or the predecessor is changing.
        if (child.bitIndex > parent.bitIndex) {
            child.parent = parent;
        } else {
            child.predecessor = parent;
        }
    }

    /**
     * Removes an internal entry from the {@link org.apache.commons.collections4.Trie}.
     *
     * If it's an internal Entry then "good luck" with understanding
     * this code. The Idea is essentially that Entry p takes Entry h's
     * place in the trie which requires some re-wiring.
     */
    private void removeInternalEntry(final TrieEntry<K, V> h) {
        if (h == root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        }
        if (!h.isInternalNode()) {
            throw new IllegalArgumentException(h + " is not an internal Entry!");
        }
        final TrieEntry<K, V> p = h.predecessor;
        // Set P's bitIndex
        p.bitIndex = h.bitIndex;
        // Fix P's parent, predecessor and child Nodes
        {
            final TrieEntry<K, V> parent = p.parent;
            final TrieEntry<K, V> child = p.left == h ? p.right : p.left;
            // if it was looping to itself previously,
            // it will now be pointed from its parent
            // (if we aren't removing its parent --
            //  in that case, it remains looping to itself).
            // otherwise, it will continue to have the same
            // predecessor.
            if (p.predecessor == p && p.parent != h) {
                p.predecessor = p.parent;
            }
            if (parent.left == p) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            if (child.bitIndex > parent.bitIndex) {
                child.parent = parent;
            }
        }
        // Fix H's parent and child Nodes
        {
            // If H is a parent of its left and right child
            // then change them to P
            if (h.left.parent == h) {
                h.left.parent = p;
            }
            if (h.right.parent == h) {
                h.right.parent = p;
            }
            // Change H's parent
            if (h.parent.left == h) {
                h.parent.left = p;
            } else {
                h.parent.right = p;
            }
        }
        // Copy the remaining fields from H to P
        //p.bitIndex = h.bitIndex;
        p.parent = h.parent;
        p.left = h.left;
        p.right = h.right;
        // Make sure that if h was pointing to any uplinks,
        // p now points to them.
        if (isValidUplink(p.left, p)) {
            p.left.predecessor = p;
        }
        if (isValidUplink(p.right, p)) {
            p.right.predecessor = p;
        }
    }

    public Map.Entry<K, V> select(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public K selectKey(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean selectR(final TrieEntry<K, V> h, final int bitIndex, final K key, final int lengthInBits, final Reference<Map.Entry<K, V>> reference) {
        if (h.bitIndex <= bitIndex) {
            // If we hit the root Node and it is empty
            // we have to look for an alternative best
            // matching node.
            if (!h.isEmpty()) {
                reference.set(h);
                return false;
            }
            return true;
        }
        if (!isBitSet(key, h.bitIndex, lengthInBits)) {
            if (selectR(h.left, h.bitIndex, key, lengthInBits, reference)) {
                return selectR(h.right, h.bitIndex, key, lengthInBits, reference);
            }
        } else if (selectR(h.right, h.bitIndex, key, lengthInBits, reference)) {
            return selectR(h.left, h.bitIndex, key, lengthInBits, reference);
        }
        return false;
    }

    public V selectValue(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> subMap(final K fromKey, final K toKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    TrieEntry<K, V> subtree(final K prefix, final int offsetInBits, final int lengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SortedMap<K, V> tailMap(final K fromKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
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
        out.writeInt(this.size());
        for (final Entry<K, V> entry : entrySet()) {
            out.writeObject(entry.getKey());
            out.writeObject(entry.getValue());
        }
    }
}
