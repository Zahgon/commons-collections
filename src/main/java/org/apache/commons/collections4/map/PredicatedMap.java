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
import java.util.Map;
import org.apache.commons.collections4.Predicate;

/**
 * Decorates another {@code Map} to validate that additions
 * match a specified predicate.
 * <p>
 * This map exists to provide validation for the decorated map.
 * It is normally created to decorate an empty map.
 * If an object cannot be added to the map, an IllegalArgumentException is thrown.
 * </p>
 * <p>
 * One usage would be to ensure that no null keys are added to the map.
 * </p>
 * <pre>Map map = PredicatedSet.decorate(new HashMap(), NotNullPredicate.INSTANCE, null);</pre>
 * <p>
 * <strong>Note that PredicatedMap is not synchronized and is not thread-safe.</strong>
 * If you wish to use this map from multiple threads concurrently, you must use
 * appropriate synchronization. The simplest approach is to wrap this map
 * using {@link java.util.Collections#synchronizedMap(Map)}. This class may throw
 * exceptions when accessed by concurrent threads without synchronization.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 3.0
 */
public class PredicatedMap<K, V> extends AbstractInputCheckedMapDecorator<K, V> implements Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 7412622456128415156L;

    public static <K, V> PredicatedMap<K, V> predicatedMap(final Map<K, V> map, final Predicate<? super K> keyPredicate, final Predicate<? super V> valuePredicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The key predicate to use
     */
    protected final Predicate<? super K> keyPredicate;

    /**
     * The value predicate to use
     */
    protected final Predicate<? super V> valuePredicate;

    /**
     * Constructor that wraps (not copies).
     *
     * @param map  the map to decorate, must not be null
     * @param keyPredicate  the predicate to validate the keys, null means no check
     * @param valuePredicate  the predicate to validate to values, null means no check
     * @throws NullPointerException if the map is null
     */
    protected PredicatedMap(final Map<K, V> map, final Predicate<? super K> keyPredicate, final Predicate<? super V> valuePredicate) {
        super(map);
        this.keyPredicate = keyPredicate;
        this.valuePredicate = valuePredicate;
        map.forEach(this::validate);
    }

    @Override
    protected V checkSetValue(final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected boolean isSetValueChecking() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     * @since 3.1
     */
    // (1) should only fail if input stream is incorrect
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // (1)
        map = (Map<K, V>) in.readObject();
    }

    protected void validate(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     * @since 3.1
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
}
