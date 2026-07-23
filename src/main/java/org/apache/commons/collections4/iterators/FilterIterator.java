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
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.TruePredicate;

/**
 * Decorates an {@link Iterator} using an optional predicate to filter elements.
 * <p>
 * This iterator decorates the underlying iterator, only allowing through
 * those elements that match the specified {@link Predicate Predicate}.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 1.0
 */
public class FilterIterator<E> implements IteratorOperations<E> {

    /**
     * The iterator to be filtered.
     */
    private Iterator<? extends E> iterator;

    /**
     * The predicate to filter elements.
     */
    private Predicate<? super E> predicate = TruePredicate.truePredicate();

    /**
     * The next object in the iteration.
     */
    private E nextObject;

    /**
     * Whether the next object has been calculated yet.
     */
    private boolean nextObjectSet;

    /**
     * Constructs a new {@code FilterIterator} that will not function
     * until {@link #setIterator(Iterator) setIterator} is invoked.
     */
    public FilterIterator() {
    }

    /**
     * Constructs a new {@code FilterIterator} that will not function
     * until {@link #setPredicate(Predicate) setPredicate} is invoked.
     *
     * @param iterator  the iterator to use
     */
    public FilterIterator(final Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    /**
     * Constructs a new {@code FilterIterator} that will use the
     * given iterator and predicate.
     *
     * @param iterator  the iterator to use
     * @param predicate  the predicate to use, null accepts all values.
     */
    public FilterIterator(final Iterator<? extends E> iterator, final Predicate<? super E> predicate) {
        this.iterator = iterator;
        this.predicate = safePredicate(predicate);
    }

    public Iterator<? extends E> getIterator() {
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
    public E next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Predicate<? super E> safePredicate(final Predicate<? super E> predicate) {
        return predicate != null ? predicate : TruePredicate.truePredicate();
    }

    public void setIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets nextObject to the next object. If there are no more
     * objects, then return false. Otherwise, return true.
     */
    private boolean setNextObject() {
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
}
