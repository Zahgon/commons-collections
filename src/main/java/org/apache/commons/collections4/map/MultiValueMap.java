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
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;

/**
 * A MultiValueMap decorates another map, allowing it to have
 * more than one value for a key.
 * <p>
 * A {@code MultiMap} is a Map with slightly different semantics.
 * Putting a value into the map will add the value to a Collection at that key.
 * Getting a value will return a Collection, holding all the values put to that key.
 * </p>
 * <p>
 * This implementation is a decorator, allowing any Map implementation
 * to be used as the base.
 * </p>
 * <p>
 * In addition, this implementation allows the type of collection used
 * for the values to be controlled. By default, an {@code ArrayList}
 * is used, however a {@code Class} to instantiate may be specified,
 * or a factory that returns a {@code Collection} instance.
 * </p>
 * <p>
 * <strong>Note that MultiValueMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. This class may throw exceptions when accessed
 * by concurrent threads without synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.2
 * @deprecated since 4.1, use {@link org.apache.commons.collections4.MultiValuedMap MultiValuedMap} instead
 */
@Deprecated
public class MultiValueMap<K, V> extends AbstractMapDecorator<K, Object> implements MultiMap<K, V>, Serializable {

    /**
     * Inner class that provides a simple reflection factory.
     *
     * @param <T> the type of results supplied by this supplier.
     */
    private static final class ReflectionFactory<T extends Collection<?>> implements Factory<T>, Serializable {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 2986114157496788874L;

        private final Class<T> clazz;

        ReflectionFactory(final Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T create() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Deserializes an instance from an ObjectInputStream.
         *
         * @param in The source ObjectInputStream.
         * @throws IOException            Any of the usual Input/Output related exceptions.
         * @throws ClassNotFoundException A class of a serialized object cannot be found.
         */
        private void readObject(final ObjectInputStream is) throws IOException, ClassNotFoundException {
            is.defaultReadObject();
            // ensure that the de-serialized class is a Collection, COLLECTIONS-580
            if (clazz != null && !Collection.class.isAssignableFrom(clazz)) {
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Inner class that provides the values view.
     */
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
     * Inner class that provides the values iterator.
     */
    private final class ValuesIterator implements Iterator<V> {

        private final Object key;

        private final Collection<V> values;

        private final Iterator<V> iterator;

        ValuesIterator(final Object key) {
            this.key = key;
            this.values = getCollection(key);
            this.iterator = values.iterator();
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public V next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -2214159910087182007L;

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(final Map<K, ? super C> map, final Class<C> collectionClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V, C extends Collection<V>> MultiValueMap<K, V> multiValueMap(final Map<K, ? super C> map, final Factory<C> collectionFactory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <K, V> MultiValueMap<K, V> multiValueMap(final Map<K, ? super Collection<V>> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The factory for creating value collections.
     */
    private final Factory<? extends Collection<V>> collectionFactory;

    /**
     * The cached values.
     */
    private transient Collection<V> valuesView;

    /**
     * Creates a MultiValueMap based on a {@code HashMap} and
     * storing the multiple values in an {@code ArrayList}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public MultiValueMap() {
        this(new HashMap<>(), new ReflectionFactory(ArrayList.class));
    }

    /**
     * Creates a MultiValueMap which decorates the given {@code map} and
     * creates the value collections using the supplied {@code collectionFactory}.
     *
     * @param <C>  the collection class type
     * @param map  the map to decorate
     * @param collectionFactory  the collection factory which must return a Collection instance
     */
    @SuppressWarnings("unchecked")
    protected <C extends Collection<V>> MultiValueMap(final Map<K, ? super C> map, final Factory<C> collectionFactory) {
        super((Map<K, Object>) map);
        if (collectionFactory == null) {
            throw new IllegalArgumentException("The factory must not be null");
        }
        this.collectionFactory = collectionFactory;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean containsValue(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Collection<V> createCollection(final int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Entry<K, Object>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public Collection<V> getCollection(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Iterator<V> iterator(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object put(final K key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean putAll(final K key, final Collection<V> values) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void putAll(final Map<? extends K, ?> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     * @since 4.0
     */
    // (1) should only fail if input stream is incorrect
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // (1)
        map = (Map<K, Object>) in.readObject();
    }

    @Override
    public boolean removeMapping(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int totalSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Object> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     * @since 4.0
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
}
