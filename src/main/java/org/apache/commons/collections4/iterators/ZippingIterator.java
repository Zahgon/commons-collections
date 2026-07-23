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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.apache.commons.collections4.FluentIterable;

/**
 * Provides an interleaved iteration over the elements contained in a
 * collection of Iterators.
 * <p>
 * Given two {@link Iterator} instances {@code A} and {@code B}, the
 * {@link #next} method on this iterator will switch between {@code A.next()}
 * and {@code B.next()} until both iterators are exhausted.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 4.1
 */
public class ZippingIterator<E> implements Iterator<E> {

    /**
     * The {@link Iterator}s to evaluate.
     */
    private final Iterator<Iterator<? extends E>> iterators;

    /**
     * The next iterator to use for next().
     */
    private Iterator<? extends E> nextIterator;

    /**
     * The last iterator which was used for next().
     */
    private Iterator<? extends E> lastReturned;

    /**
     * Constructs a new {@code ZippingIterator} that will provide
     * interleaved iteration of the specified iterators.
     *
     * @param iterators  the array of iterators
     * @throws NullPointerException if any iterator is null
     */
    public ZippingIterator(final Iterator<? extends E>... iterators) {
        // create a mutable list to be able to remove exhausted iterators
        final List<Iterator<? extends E>> list = new ArrayList<>();
        for (final Iterator<? extends E> iterator : iterators) {
            Objects.requireNonNull(iterator, "iterator");
            list.add(iterator);
        }
        this.iterators = FluentIterable.of(list).loop().iterator();
    }

    /**
     * Constructs a new {@code ZippingIterator} that will provide
     * interleaved iteration over the two given iterators.
     *
     * @param a  the first child iterator
     * @param b  the second child iterator
     * @throws NullPointerException if either iterator is null
     */
    @SuppressWarnings("unchecked")
    public ZippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b) {
        this(new Iterator[] { a, b });
    }

    /**
     * Constructs a new {@code ZippingIterator} that will provide
     * interleaved iteration over the three given iterators.
     *
     * @param a  the first child iterator
     * @param b  the second child iterator
     * @param c  the third child iterator
     * @throws NullPointerException if either iterator is null
     */
    @SuppressWarnings("unchecked")
    public ZippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b, final Iterator<? extends E> c) {
        this(new Iterator[] { a, b, c });
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E next() throws NoSuchElementException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
