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
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Bag;

/**
 * Decorates another {@link Bag} to comply with the Collection contract.
 * <p>
 * By decorating an existing {@link Bag} instance with a {@link CollectionBag},
 * it can be safely passed on to methods that require Collection types that
 * are fully compliant with the Collection contract.
 * </p>
 * <p>
 * The method Javadoc highlights the differences compared to the original Bag interface.
 * </p>
 *
 * @see Bag
 * @param <E> the type of elements in this bag
 * @since 4.0
 */
public final class CollectionBag<E> extends AbstractBagDecorator<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -2560033712679053143L;

    public static <E> Bag<E> collectionBag(final Bag<E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param bag  the bag to decorate, must not be null
     * @throws NullPointerException if bag is null
     */
    public CollectionBag(final Bag<E> bag) {
        super(bag);
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean add(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Reads the collection in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     * @throws ClassCastException if deserialized object has wrong type
     */
    // will throw CCE, see Javadoc
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setCollection((Collection<E>) in.readObject());
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Writes the collection out using a custom routine.
     *
     * @param out  the output stream
     * @throws IOException if an error occurs while writing to the stream
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }
}
