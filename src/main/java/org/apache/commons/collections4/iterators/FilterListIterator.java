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
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

/**
 * Decorates another {@link ListIterator} using a predicate to filter elements.
 * <p>
 * This iterator decorates the underlying iterator, only allowing through
 * those elements that match the specified {@link Predicate Predicate}.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 2.0
 */
public class FilterListIterator<E> implements ListIterator<E> {

    /**
     * The iterator being used
     */
    private ListIterator<? extends E> iterator;

    /**
     * The predicate being used
     */
    private Predicate<? super E> predicate;

    /**
     * The value of the next (matching) object, when
     * {@link #nextObjectSet} is true.
     */
    private E nextObject;

    /**
     * Whether or not the {@link #nextObject} has been set
     * (possibly to {@code null}).
     */
    private boolean nextObjectSet;

    /**
     * The value of the previous (matching) object, when
     * {@link #previousObjectSet} is true.
     */
    private E previousObject;

    /**
     * Whether or not the {@link #previousObject} has been set
     * (possibly to {@code null}).
     */
    private boolean previousObjectSet;

    /**
     * The index of the element that would be returned by {@link #next}.
     */
    private int nextIndex;

    /**
     * Constructs a new {@code FilterListIterator} that will not function
     * until {@link #setListIterator(ListIterator) setListIterator}
     * and {@link #setPredicate(Predicate) setPredicate} are invoked.
     */
    public FilterListIterator() {
    }

    /**
     * Constructs a new {@code FilterListIterator} that will not
     * function until {@link #setPredicate(Predicate) setPredicate} is invoked.
     *
     * @param iterator  the iterator to use
     */
    public FilterListIterator(final ListIterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    /**
     * Constructs a new {@code FilterListIterator}.
     *
     * @param iterator  the iterator to use
     * @param predicate  the predicate to use
     */
    public FilterListIterator(final ListIterator<? extends E> iterator, final Predicate<? super E> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    /**
     * Constructs a new {@code FilterListIterator} that will not function
     * until {@link #setListIterator(ListIterator) setListIterator} is invoked.
     *
     * @param predicate  the predicate to use.
     */
    public FilterListIterator(final Predicate<? super E> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void add(final E o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void clearNextObject() {
        nextObject = null;
        nextObjectSet = false;
    }

    private void clearPreviousObject() {
        previousObject = null;
        previousObjectSet = false;
    }

    public ListIterator<? extends E> getListIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Predicate<? super E> getPredicate() {
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

    public void setListIterator(final ListIterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean setNextObject() {
        // if previousObjectSet,
        // then we've walked back one step in the
        // underlying list (due to a hasPrevious() call)
        // so skip ahead one matching object
        if (previousObjectSet) {
            clearPreviousObject();
            if (!setNextObject()) {
                return false;
            }
            clearNextObject();
        }
        if (iterator == null) {
            return false;
        }
        while (iterator.hasNext()) {
            final E object = iterator.next();
            if (predicate.test(object)) {
                nextObject = object;
                nextObjectSet = true;
                return true;
            }
        }
        return false;
    }

    public void setPredicate(final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private boolean setPreviousObject() {
        // if nextObjectSet,
        // then we've walked back one step in the
        // underlying list (due to a hasNext() call)
        // so skip ahead one matching object
        if (nextObjectSet) {
            clearNextObject();
            if (!setPreviousObject()) {
                return false;
            }
            clearPreviousObject();
        }
        if (iterator == null) {
            return false;
        }
        while (iterator.hasPrevious()) {
            final E object = iterator.previous();
            if (predicate.test(object)) {
                previousObject = object;
                previousObjectSet = true;
                return true;
            }
        }
        return false;
    }
}
