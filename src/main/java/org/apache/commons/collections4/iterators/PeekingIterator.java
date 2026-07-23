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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Decorates an iterator to support one-element lookahead while iterating.
 * <p>
 * The decorator supports the removal operation, but an {@link IllegalStateException} will be thrown if {@link #remove()} is called directly after a call to
 * {@link #peek()} or {@link #element()}.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 4.0
 */
public class PeekingIterator<E> implements Iterator<E> {

    public static <E> PeekingIterator<E> peekingIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The iterator being decorated.
     */
    private final Iterator<? extends E> iterator;

    /**
     * Indicates that the decorated iterator is exhausted.
     */
    private boolean exhausted;

    /**
     * Indicates if the lookahead slot is filled.
     */
    private boolean slotFilled;

    /**
     * The current slot for lookahead.
     */
    private E slot;

    /**
     * Constructs a new instance.
     *
     * @param iterator the iterator to decorate
     */
    public PeekingIterator(final Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public E element() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void fill() {
        if (exhausted || slotFilled) {
            return;
        }
        if (iterator.hasNext()) {
            slot = iterator.next();
            slotFilled = true;
        } else {
            exhausted = true;
            slot = null;
            slotFilled = false;
        }
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E peek() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
