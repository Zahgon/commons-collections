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
package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Decorates another {@link Collection} to synchronize its behavior
 * for a multithreaded environment.
 * <p>
 * Iterators must be manually synchronized:
 * </p>
 * <pre>
 * synchronized (coll) {
 *   Iterator it = coll.iterator();
 *   // do stuff with iterator
 * }
 * </pre>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in the collection
 * @since 3.0
 */
public class SynchronizedCollection<E> implements Collection<E>, Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 2412805092710877986L;

    public static <T> SynchronizedCollection<T> synchronizedCollection(final Collection<T> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The collection to decorate
     */
    private final Collection<E> collection;

    /**
     * The object to lock on, needed for List/SortedSet views
     */
    protected final Object lock;

    /**
     * Constructor that wraps (not copies).
     *
     * @param collection  the collection to decorate, must not be null
     * @throws NullPointerException if the collection is null
     */
    protected SynchronizedCollection(final Collection<E> collection) {
        this.collection = Objects.requireNonNull(collection, "collection");
        this.lock = this;
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param collection  the collection to decorate, must not be null
     * @param lock  the lock object to use, must not be null
     * @throws NullPointerException if the collection or lock is null
     */
    protected SynchronizedCollection(final Collection<E> collection, final Object lock) {
        this.collection = Objects.requireNonNull(collection, "collection");
        this.lock = Objects.requireNonNull(lock, "lock");
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Collection<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T[] toArray(final T[] object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
