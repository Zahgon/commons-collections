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
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

/**
 * Decorates another {@link List} to provide additional behavior.
 * <p>
 * Methods are forwarded directly to the decorated list.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.0
 */
public abstract class AbstractListDecorator<E> extends AbstractCollectionDecorator<E> implements List<E> {

    /**
     * Serialization version--necessary in an abstract class?
     */
    private static final long serialVersionUID = 4500739654952315623L;

    /**
     * Constructor only used in deserialization, do not use otherwise.
     * @since 3.1
     */
    protected AbstractListDecorator() {
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param list  the list to decorate, must not be null
     * @throws NullPointerException if list is null
     */
    protected AbstractListDecorator(final List<E> list) {
        super(list);
    }

    @Override
    public void add(final int index, final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected List<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int indexOf(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int lastIndexOf(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E remove(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E set(final int index, final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
