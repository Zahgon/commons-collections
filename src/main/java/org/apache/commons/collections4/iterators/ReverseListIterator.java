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
import java.util.Objects;
import org.apache.commons.collections4.ResettableListIterator;

/**
 * Iterates backwards through a List, starting with the last element
 * and continuing to the first. This is useful for looping around
 * a list in reverse order without needing to actually reverse the list.
 * <p>
 * The first call to {@code next()} will return the last element
 * from the list, and so on. The {@code hasNext()} method works
 * in concert with the {@code next()} method as expected.
 * However, the {@code nextIndex()} method returns the correct
 * index in the list, thus it starts high and reduces as the iteration
 * continues. The previous methods work similarly.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 3.2
 */
public class ReverseListIterator<E> implements ResettableListIterator<E> {

    /**
     * The list being wrapped.
     */
    private final List<E> list;

    /**
     * The list iterator being wrapped.
     */
    private ListIterator<E> iterator;

    /**
     * Flag to indicate if updating is possible at the moment.
     */
    private boolean validForUpdate = true;

    /**
     * Constructor that wraps a list.
     *
     * @param list  the list to create a reversed iterator for
     * @throws NullPointerException if the list is null
     */
    public ReverseListIterator(final List<E> list) {
        this.list = Objects.requireNonNull(list, "list");
        iterator = list.listIterator(list.size());
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
}
