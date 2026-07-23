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

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

/**
 * Implements an {@link java.util.Iterator Iterator} over any array.
 * <p>
 * The array can be either an array of object or of primitives. If you know
 * that you have an object array, the
 * {@link org.apache.commons.collections4.iterators.ObjectArrayIterator ObjectArrayIterator}
 * class is a better choice, as it will perform better.
 * </p>
 * <p>
 * The iterator implements a {@link #reset} method, allowing the reset of
 * the iterator back to the start if required.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 1.0
 */
public class ArrayIterator<E> implements ResettableIterator<E> {

    /**
     * The array to iterate over
     */
    final Object array;

    /**
     * The start index to loop from
     */
    final int startIndex;

    /**
     * The end index to loop to
     */
    final int endIndex;

    /**
     * The current iterator index
     */
    int index;

    /**
     * Constructs an ArrayIterator that will iterate over the values in the
     * specified array.
     *
     * @param array the array to iterate over.
     * @throws IllegalArgumentException if {@code array} is not an array.
     * @throws NullPointerException if {@code array} is {@code null}
     */
    public ArrayIterator(final Object array) {
        this(array, 0);
    }

    /**
     * Constructs an ArrayIterator that will iterate over the values in the
     * specified array from a specific start index.
     *
     * @param array  the array to iterate over.
     * @param startIndex  the index to start iterating at.
     * @throws IllegalArgumentException if {@code array} is not an array.
     * @throws NullPointerException if {@code array} is {@code null}
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public ArrayIterator(final Object array, final int startIndex) {
        this(array, startIndex, Array.getLength(array));
    }

    /**
     * Constructs an ArrayIterator that will iterate over a range of values
     * in the specified array.
     *
     * @param array  the array to iterate over.
     * @param startIndex  the index to start iterating at.
     * @param endIndex  the index to finish iterating at.
     * @throws IllegalArgumentException if {@code array} is not an array.
     * @throws NullPointerException if {@code array} is {@code null}
     * @throws IndexOutOfBoundsException if either index is invalid
     */
    public ArrayIterator(final Object array, final int startIndex, final int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.index = startIndex;
        final int len = Array.getLength(array);
        checkBound(startIndex, len, "start");
        checkBound(endIndex, len, "end");
        if (endIndex < startIndex) {
            throw new IllegalArgumentException("End index must not be less than start index.");
        }
    }

    protected void checkBound(final int bound, final int len, final String type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Object getArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getEndIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getStartIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
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
}
