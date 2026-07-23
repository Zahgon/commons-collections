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
package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Decorates a {@code Map} to obtain {@code Set} behavior.
 * <p>
 * This class is used to create a {@code Set} with the same properties as
 * the key set of any map. Thus, a ReferenceSet can be created by wrapping a
 * {@code ReferenceMap} in an instance of this class.
 * </p>
 * <p>
 * Most map implementation can be used to create a set by passing in dummy values.
 * Exceptions include {@code BidiMap} implementations, as they require unique values.
 * </p>
 *
 * @param <E> the type of the elements in this set
 * @param <V> the dummy value type in this map
 * @since 3.1
 */
public final class MapBackedSet<E, V> implements Set<E>, Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 6723912213766056587L;

    public static <E, V> MapBackedSet<E, V> mapBackedSet(final Map<E, ? super V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E, V> MapBackedSet<E, V> mapBackedSet(final Map<E, ? super V> map, final V dummyValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The map being used as the backing store
     */
    private final Map<E, ? super V> map;

    /**
     * The dummyValue to use
     */
    private final V dummyValue;

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to decorate, must not be null
     * @param dummyValue  the dummy value to use
     * @throws NullPointerException if map is null
     */
    private MapBackedSet(final Map<E, ? super V> map, final V dummyValue) {
        this.map = Objects.requireNonNull(map, "map");
        this.dummyValue = dummyValue;
    }

    @Override
    public boolean add(final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
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
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
