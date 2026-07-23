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
import org.apache.commons.collections4.Transformer;

/**
 * Decorates another {@code Map} to transform objects that are added.
 * <p>
 * The Map put methods and Map.Entry setValue method are affected by this class.
 * Thus objects must be removed or searched for using their transformed form.
 * For example, if the transformation converts Strings to Integers, you must
 * use the Integer form to remove objects.
 * </p>
 * <p>
 * <strong>Note that TransformedMap is not synchronized and is not thread-safe.</strong>
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
 * @see org.apache.commons.collections4.splitmap.TransformedSplitMap
 * @since 3.0
 */
public class TransformedMap<K, V> extends AbstractInputCheckedMapDecorator<K, V> implements Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 7023152376788900464L;

    public static <K, V> TransformedMap<K, V> transformedMap(final Map<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> TransformedMap<K, V> transformingMap(final Map<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The transformer to use for the key
     */
    protected final Transformer<? super K, ? extends K> keyTransformer;

    /**
     * The transformer to use for the value
     */
    protected final Transformer<? super V, ? extends V> valueTransformer;

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the collection being decorated, they
     * are NOT transformed.
     * </p>
     *
     * @param map  the map to decorate, must not be null
     * @param keyTransformer  the transformer to use for key conversion, null means no conversion
     * @param valueTransformer  the transformer to use for value conversion, null means no conversion
     * @throws NullPointerException if map is null
     */
    protected TransformedMap(final Map<K, V> map, final Transformer<? super K, ? extends K> keyTransformer, final Transformer<? super V, ? extends V> valueTransformer) {
        super(map);
        this.keyTransformer = keyTransformer;
        this.valueTransformer = valueTransformer;
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
    public V put(K key, V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
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

    protected K transformKey(final K object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    protected Map<K, V> transformMap(final Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected V transformValue(final V object) {
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
