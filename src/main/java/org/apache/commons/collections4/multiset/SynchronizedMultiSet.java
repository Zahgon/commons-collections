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
package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.SynchronizedCollection;

/**
 * Decorates another {@link MultiSet} to synchronize its behavior
 * for a multithreaded environment.
 * <p>
 * Methods are synchronized, then forwarded to the decorated multiset.
 * Iterators must be separately synchronized around the loop.
 * </p>
 *
 * @param <E> the type held in the multiset.
 * @since 4.1
 */
public class SynchronizedMultiSet<E> extends SynchronizedCollection<E> implements MultiSet<E> {

    /**
     * Synchronized Set for the MultiSet class.
     *
     * @param <T> the type held in this Set.
     */
    static class SynchronizedSet<T> extends SynchronizedCollection<T> implements Set<T> {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 20150629L;

        /**
         * Constructs a new instance.
         * @param set  the set to decorate
         * @param lock  the lock to use, shared with the multiset
         */
        SynchronizedSet(final Set<T> set, final Object lock) {
            super(set, lock);
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 20150629L;

    public static <E> SynchronizedMultiSet<E> synchronizedMultiSet(final MultiSet<E> multiset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param multiset  the multiset to decorate, must not be null
     * @throws NullPointerException if multiset is null
     */
    protected SynchronizedMultiSet(final MultiSet<E> multiset) {
        super(multiset);
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param multiset  the multiset to decorate, must not be null
     * @param lock  the lock to use, must not be null
     * @throws NullPointerException if multiset or lock is null
     */
    protected SynchronizedMultiSet(final MultiSet<E> multiset, final Object lock) {
        super(multiset, lock);
    }

    @Override
    public int add(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected MultiSet<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Entry<E>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getCount(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int remove(final Object object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int setCount(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<E> uniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
