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
 * {@code SingletonIterator} is an {@link java.util.ListIterator} over a single
 * object instance.
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 2.1
 */
public class SingletonListIterator<E> implements ResettableListIterator<E> {

    private boolean beforeFirst = true;

    private boolean nextCalled;

    private boolean removed;

    private E object;

    /**
     * Constructs a new {@code SingletonListIterator}.
     *
     * @param object  the single object to return from the iterator
     */
    public SingletonListIterator(final E object) {
        this.object = object;
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
    public void set(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
