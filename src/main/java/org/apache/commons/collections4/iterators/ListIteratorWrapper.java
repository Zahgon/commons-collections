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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.ResettableListIterator;

/**
 * Converts an {@link Iterator} into a {@link ResettableListIterator}.
 * For plain {@code Iterator}s this is accomplished by caching the returned
 * elements.  This class can also be used to simply add
 * {@link org.apache.commons.collections4.ResettableIterator ResettableIterator}
 * functionality to a given {@link ListIterator}.
 * <p>
 * The {@code ListIterator} interface has additional useful methods
 * for navigation - {@code previous()} and the index methods.
 * This class allows a regular {@code Iterator} to behave as a
 * {@code ListIterator}. It achieves this by building a list internally
 * of as the underlying iterator is traversed.
 * </p>
 * <p>
 * The optional operations of {@code ListIterator} are not supported for plain {@code Iterator}s.
 * </p>
 * <p>
 * This class implements ResettableListIterator from Commons Collections 3.2.
 * </p>
 *
 * @param <E> the type of elements in this iterator.
 * @since 2.1
 */
public class ListIteratorWrapper<E> implements ResettableListIterator<E> {

    /**
     * Message used when set or add are called.
     */
    private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";

    /**
     * Message used when set or add are called.
     */
    private static final String CANNOT_REMOVE_MESSAGE = "Cannot remove element at index {0}.";

    /**
     * The underlying iterator being decorated.
     */
    private final Iterator<? extends E> iterator;

    /**
     * The list being used to cache the iterator.
     */
    private final List<E> list = new ArrayList<>();

    /**
     * The current index of this iterator.
     */
    private int currentIndex;

    /**
     * The current index of the wrapped iterator.
     */
    private int wrappedIteratorIndex;

    /**
     * Recall whether the wrapped iterator's "cursor" is in such a state as to allow remove() to be called
     */
    private boolean removeState;

    /**
     * Constructs a new {@code ListIteratorWrapper} that will wrap
     * the given iterator.
     *
     * @param iterator  the iterator to wrap
     * @throws NullPointerException if the iterator is null
     */
    public ListIteratorWrapper(final Iterator<? extends E> iterator) {
        this.iterator = Objects.requireNonNull(iterator, "iterator");
    }

    @Override
    public void add(final E obj) throws UnsupportedOperationException {
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
    public E next() throws NoSuchElementException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int nextIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E previous() throws NoSuchElementException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int previousIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() throws IllegalStateException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void set(final E obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
