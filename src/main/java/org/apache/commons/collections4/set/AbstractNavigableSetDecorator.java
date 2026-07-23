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
package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;

/**
 * Decorates another {@code NavigableSet} to provide additional behavior.
 * <p>
 * Methods are forwarded directly to the decorated set.
 * </p>
 *
 * @param <E> the type of the elements in the navigable set
 * @since 4.1
 */
public abstract class AbstractNavigableSetDecorator<E> extends AbstractSortedSetDecorator<E> implements NavigableSet<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 20150528L;

    /**
     * Constructor only used in deserialization, do not use otherwise.
     */
    protected AbstractNavigableSetDecorator() {
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param set  the set to decorate, must not be null
     * @throws NullPointerException if set is null
     */
    protected AbstractNavigableSetDecorator(final NavigableSet<E> set) {
        super(set);
    }

    @Override
    public E ceiling(final E e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected NavigableSet<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NavigableSet<E> descendingSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E floor(final E e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NavigableSet<E> headSet(final E toElement, final boolean inclusive) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E higher(final E e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E lower(final E e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NavigableSet<E> subSet(final E fromElement, final boolean fromInclusive, final E toElement, final boolean toInclusive) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public NavigableSet<E> tailSet(final E fromElement, final boolean inclusive) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
