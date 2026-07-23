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

import java.util.ListIterator;
import java.util.Objects;
import org.apache.commons.collections4.Unmodifiable;

/**
 * Decorates a list iterator such that it cannot be modified.
 * <p>
 * Attempts to modify it will result in an UnsupportedOperationException.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 3.0
 */
public final class UnmodifiableListIterator<E> implements ListIterator<E>, Unmodifiable {

    /**
     * Decorates the specified iterator such that it cannot be modified.
     *
     * @param <E>  the element type
     * @param iterator  the iterator to decorate
     * @return a new unmodifiable list iterator
     * @throws NullPointerException if the iterator is null
     * @deprecated method name has typo in it. Use {@link org.apache.commons.collections4.iterators.UnmodifiableListIterator#unmodifiableListIterator(ListIterator)} instead.
     */
    @Deprecated
    public static <E> ListIterator<E> umodifiableListIterator(final ListIterator<? extends E> iterator) {
        return unmodifiableListIterator(iterator);
    }

    public static <E> ListIterator<E> unmodifiableListIterator(final ListIterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The iterator being decorated
     */
    private final ListIterator<? extends E> iterator;

    /**
     * Constructs a new instance.
     *
     * @param iterator  the iterator to decorate
     */
    private UnmodifiableListIterator(final ListIterator<? extends E> iterator) {
        this.iterator = iterator;
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
    public void set(final E ignored) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
