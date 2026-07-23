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

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Trie;

/**
 * This class provides some basic {@link Trie} functionality and
 * utility methods for actual bitwise {@link Trie} implementations.
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.0
 */
public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V> implements Trie<K, V>, Serializable {

    /**
     * A basic implementation of {@link Entry}.
     *
     * @param <K> the type of the keys in this entry.
     * @param <V> the type of the values in this entry.
     */
    abstract static class BasicEntry<K, V> implements Map.Entry<K, V>, Serializable {

        private static final long serialVersionUID = -944364551314110330L;

        /**
         * The entry's key.
         */
        protected K key;

        /**
         * The entry's value.
         */
        protected V value;

        BasicEntry(final K key) {
            this.key = key;
        }

        BasicEntry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(final Object o) {
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

        public V setKeyValue(final K key, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V setValue(final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    private static final long serialVersionUID = 5826987063535505652L;

    static boolean compare(final Object a, final Object b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The {@link KeyAnalyzer} that's being used to build the PATRICIA {@link Trie}.
     */
    private final KeyAnalyzer<? super K> keyAnalyzer;

    /**
     * Constructs a new {@link Trie} using the given {@link KeyAnalyzer}.
     *
     * @param keyAnalyzer  the {@link KeyAnalyzer} to use
     */
    protected AbstractBitwiseTrie(final KeyAnalyzer<? super K> keyAnalyzer) {
        this.keyAnalyzer = Objects.requireNonNull(keyAnalyzer, "keyAnalyzer");
    }

    final int bitIndex(final K key, final K foundKey) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final int bitsPerElement() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    final K castKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean compareKeys(final K key, final K other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected KeyAnalyzer<? super K> getKeyAnalyzer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final boolean isBitSet(final K key, final int bitIndex, final int lengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    final int lengthInBits(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
