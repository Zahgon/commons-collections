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
package org.apache.commons.collections4.iterators;

import java.util.Objects;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;

/**
 * Decorates an ordered map iterator such that it cannot be modified.
 * <p>
 * Attempts to modify it will result in an UnsupportedOperationException.
 * </p>
 *
 * @param <K> the type of keys
 * @param <V> the type of mapped values
 * @since 3.0
 */
public final class UnmodifiableOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, Unmodifiable {

    public static <K, V> OrderedMapIterator<K, V> unmodifiableOrderedMapIterator(final OrderedMapIterator<K, ? extends V> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The iterator being decorated
     */
    private final OrderedMapIterator<? extends K, ? extends V> iterator;

    /**
     * Constructs a new instance.
     *
     * @param iterator  the iterator to decorate
     */
    private UnmodifiableOrderedMapIterator(final OrderedMapIterator<K, ? extends V> iterator) {
        this.iterator = iterator;
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
    public boolean hasNext() {
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
    public K previous() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V setValue(final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
