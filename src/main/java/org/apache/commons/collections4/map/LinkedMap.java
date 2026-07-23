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
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * A {@code Map} implementation that maintains the order of the entries.
 * In this implementation order is maintained by original insertion.
 * <p>
 * This implementation improves on the JDK1.4 LinkedHashMap by adding the
 * {@link org.apache.commons.collections4.MapIterator MapIterator}
 * functionality, additional convenience methods and allowing
 * bidirectional iteration. It also implements {@code OrderedMap}.
 * In addition, non-interface methods are provided to access the map by index.
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
 * <p>
 * <strong>Note that LinkedMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class LinkedMap<K, V> extends AbstractLinkedMap<K, V> implements Serializable, Cloneable {

    /**
     * List view of map.
     */
    static class LinkedMapList<K> extends AbstractList<K> {

        private final LinkedMap<K, ?> parent;

        LinkedMapList(final LinkedMap<K, ?> parent) {
            this.parent = parent;
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
        public K get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int indexOf(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int lastIndexOf(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ListIterator<K> listIterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public ListIterator<K> listIterator(final int fromIndex) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public K remove(final int index) {
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
        public boolean removeIf(final Predicate<? super K> filter) {
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
        public List<K> subList(final int fromIndexInclusive, final int toIndexExclusive) {
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

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 9077234323521161066L;

    /**
     * Constructs a new empty map with default size and load factor.
     */
    public LinkedMap() {
        super(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR, DEFAULT_THRESHOLD);
    }

    /**
     * Constructs a new, empty map with the specified initial capacity.
     *
     * @param initialCapacity  the initial capacity
     * @throws IllegalArgumentException if the initial capacity is negative
     */
    public LinkedMap(final int initialCapacity) {
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
    public LinkedMap(final int initialCapacity, final float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Constructor copying elements from another map.
     *
     * @param map  the map to copy
     * @throws NullPointerException if the map is null
     */
    public LinkedMap(final Map<? extends K, ? extends V> map) {
        super(map);
    }

    public List<K> asList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public LinkedMap<K, V> clone() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public K get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public V getValue(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int indexOf(Object key) {
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

    public V remove(final int index) {
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
