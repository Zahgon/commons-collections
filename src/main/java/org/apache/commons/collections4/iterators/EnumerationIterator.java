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

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Adapter to make {@link Enumeration Enumeration} instances appear
 * to be {@link Iterator Iterator} instances.
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 1.0
 */
public class EnumerationIterator<E> implements Iterator<E> {

    /**
     * The collection to remove elements from
     */
    private final Collection<? super E> collection;

    /**
     * The enumeration being converted
     */
    private Enumeration<? extends E> enumeration;

    /**
     * The last object retrieved
     */
    private E last;

    // Constructors
    /**
     * Constructs a new {@code EnumerationIterator} that will not
     * function until {@link #setEnumeration(Enumeration)} is called.
     */
    public EnumerationIterator() {
        this(null, null);
    }

    /**
     * Constructs a new {@code EnumerationIterator} that provides
     * an iterator view of the given enumeration.
     *
     * @param enumeration  the enumeration to use
     */
    public EnumerationIterator(final Enumeration<? extends E> enumeration) {
        this(enumeration, null);
    }

    /**
     * Constructs a new {@code EnumerationIterator} that will remove
     * elements from the specified collection.
     *
     * @param enumeration  the enumeration to use
     * @param collection  the collection to remove elements from
     */
    public EnumerationIterator(final Enumeration<? extends E> enumeration, final Collection<? super E> collection) {
        this.enumeration = enumeration;
        this.collection = collection;
        this.last = null;
    }

    public Enumeration<? extends E> getEnumeration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Iterator interface
    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setEnumeration(final Enumeration<? extends E> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
