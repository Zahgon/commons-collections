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

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.ResettableListIterator;

/**
 * A ListIterator that restarts when it reaches the end or when it
 * reaches the beginning.
 * <p>
 * The iterator will loop continuously around the provided list,
 * unless there are no elements in the collection to begin with, or
 * all of the elements have been {@link #remove removed}.
 * </p>
 * <p>
 * Concurrent modifications are not directly supported, and for most
 * collection implementations will throw a
 * ConcurrentModificationException.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 3.2
 */
public class LoopingListIterator<E> implements ResettableListIterator<E> {

    /**
     * The list to base the iterator on
     */
    private final List<E> list;

    /**
     * The current list iterator
     */
    private ListIterator<E> iterator;

    /**
     * Constructor that wraps a list.
     * <p>
     * There is no way to reset a ListIterator instance without
     * recreating it from the original source, so the List must be
     * passed in and a reference to it held.
     * </p>
     *
     * @param list the list to wrap
     * @throws NullPointerException if the list is null
     */
    public LoopingListIterator(final List<E> list) {
        this.list = Objects.requireNonNull(list, "collection");
        init();
    }

    @Override
    public void add(final E obj) {
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

    private void init() {
        iterator = list.listIterator();
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E previous() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int previousIndex() {
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

    @Override
    public void set(final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
