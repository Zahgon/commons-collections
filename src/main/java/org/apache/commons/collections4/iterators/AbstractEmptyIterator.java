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
import org.apache.commons.collections4.ResettableIterator;

/**
 * Provides an abstract implementation of an empty iterator.
 *
 * @since 3.1
 */
abstract class AbstractEmptyIterator<E> implements ResettableIterator<E> {

    /**
     * Constructs a new instance.
     */
    protected AbstractEmptyIterator() {
    }

    /**
     * Always throws UnsupportedOperationException.
     *
     * @param ignored ignore.
     * @throws UnsupportedOperationException Always thrown.
     * @deprecated Will be removed in 5.0 without replacement.
     */
    @Deprecated
    public void add(final E ignored) {
        throw new UnsupportedOperationException("add() not supported for empty Iterator");
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean hasPrevious() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int nextIndex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E previous() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

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

    public void set(final E ignored) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
