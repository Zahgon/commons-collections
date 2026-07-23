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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.ResettableIterator;

/**
 * An Iterator that restarts when it reaches the end.
 * <p>
 * The iterator will loop continuously around the provided elements, unless
 * there are no elements in the collection to begin with, or all the elements
 * have been {@link #remove removed}.
 * </p>
 * <p>
 * Concurrent modifications are not directly supported, and for most collection
 * implementations will throw a ConcurrentModificationException.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 3.0
 */
public class LoopingIterator<E> implements ResettableIterator<E> {

    /**
     * The collection to base the iterator on
     */
    private final Collection<? extends E> collection;

    /**
     * The current iterator
     */
    private Iterator<? extends E> iterator;

    /**
     * Constructor that wraps a collection.
     * <p>
     * There is no way to reset an Iterator instance without recreating it from
     * the original source, so the Collection must be passed in.
     * </p>
     *
     * @param collection  the collection to wrap
     * @throws NullPointerException if the collection is null
     */
    public LoopingIterator(final Collection<? extends E> collection) {
        this.collection = Objects.requireNonNull(collection, "collection");
        reset();
    }

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

    @Override
    public void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
