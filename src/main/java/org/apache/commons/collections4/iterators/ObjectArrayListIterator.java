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

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

/**
 * Implements a {@link java.util.ListIterator} over an array of objects.
 * <p>
 * This iterator does not support {@link #add} or {@link #remove}, as the object array
 * cannot be structurally modified. The {@link #set} method is supported however.
 * </p>
 * <p>
 * The iterator implements a {@link #reset} method, allowing the reset of the iterator
 * back to the start if required.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @see org.apache.commons.collections4.iterators.ObjectArrayIterator
 * @see java.util.Iterator
 * @see java.util.ListIterator
 * @since 3.0
 */
public class ObjectArrayListIterator<E> extends ObjectArrayIterator<E> implements ResettableListIterator<E> {

    /**
     * Holds the index of the last item returned by a call to {@code next()}
     * or {@code previous()}. This is set to {@code -1} if neither method
     * has yet been invoked. {@code lastItemIndex} is used to implement the
     * {@link #set} method.
     */
    private int lastItemIndex = -1;

    /**
     * Constructs an ObjectArrayListIterator that will iterate over the values in the
     * specified array.
     *
     * @param array the array to iterate over
     * @throws NullPointerException if {@code array} is {@code null}
     */
    public ObjectArrayListIterator(final E... array) {
        super(array);
    }

    /**
     * Constructs an ObjectArrayListIterator that will iterate over the values in the
     * specified array from a specific start index.
     *
     * @param array  the array to iterate over
     * @param start  the index to start iterating at
     * @throws NullPointerException if {@code array} is {@code null}
     * @throws IndexOutOfBoundsException if the start index is out of bounds
     */
    public ObjectArrayListIterator(final E[] array, final int start) {
        super(array, start);
    }

    /**
     * Constructs an ObjectArrayListIterator that will iterate over a range of values
     * in the specified array.
     *
     * @param array  the array to iterate over
     * @param start  the index to start iterating at
     * @param end  the index (exclusive) to finish iterating at
     * @throws IndexOutOfBoundsException if the start or end index is out of bounds
     * @throws IllegalArgumentException if end index is before the start
     * @throws NullPointerException if {@code array} is {@code null}
     */
    public ObjectArrayListIterator(final E[] array, final int start, final int end) {
        super(array, start, end);
    }

    @Override
    public void add(final E obj) {
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
    public void reset() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void set(final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
