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

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

/**
 * Utilities for working with "split maps:" objects that implement {@link Put}
 * and/or {@link Get} but not {@link Map}.
 *
 * @since 4.0
 * @see Get
 * @see Put
 */
public class SplitMapUtils {

    private static final class WrappedGet<K, V> implements IterableMap<K, V>, Unmodifiable {

        private final Get<K, V> get;

        private WrappedGet(final Get<K, V> get) {
            this.get = get;
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
        public boolean equals(final Object arg0) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V get(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
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
        public MapIterator<K, V> mapIterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V put(final K key, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void putAll(final Map<? extends K, ? extends V> t) {
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

    private static final class WrappedPut<K, V> implements Map<K, V>, Put<K, V> {

        private final Put<K, V> put;

        private WrappedPut(final Put<K, V> put) {
            this.put = put;
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
        @SuppressWarnings("unchecked")
        public V put(final K key, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void putAll(final Map<? extends K, ? extends V> t) {
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

    @SuppressWarnings("unchecked")
    public static <K, V> IterableMap<K, V> readableMap(final Get<K, V> get) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> writableMap(final Put<K, V> put) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private SplitMapUtils() {
        // empty
    }
}
