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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

/**
 * Decorates an iterator to support pushback of elements.
 * <p>
 * The decorator stores the pushed back elements in a LIFO manner: the last element
 * that has been pushed back, will be returned as the next element in a call to {@link #next()}.
 * </p>
 * <p>
 * The decorator does not support the removal operation. Any call to {@link #remove()} will
 * result in an {@link UnsupportedOperationException}.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 4.0
 */
public class PushbackIterator<E> implements Iterator<E> {

    public static <E> PushbackIterator<E> pushbackIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The iterator being decorated.
     */
    private final Iterator<? extends E> iterator;

    /**
     * The LIFO queue containing the pushed back items.
     */
    private final Deque<E> items = new ArrayDeque<>();

    /**
     * Constructs a new instance.
     *
     * @param iterator  the iterator to decorate
     */
    public PushbackIterator(final Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void pushback(final E item) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
