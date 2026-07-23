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
package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.SynchronizedCollection;

/**
 * Decorates another {@link Queue} to synchronize its behavior for a multithreaded environment.
 * <p>
 * Methods are synchronized, then forwarded to the decorated queue. Iterators must be separately synchronized around the
 * loop.
 * </p>
 *
 * @param <E> the type of the elements in the collection
 * @since 4.2
 */
public class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 1L;

    public static <E> SynchronizedQueue<E> synchronizedQueue(final Queue<E> queue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param queue
     *            the queue to decorate, must not be null
     * @throws NullPointerException
     *             if queue is null
     */
    protected SynchronizedQueue(final Queue<E> queue) {
        super(queue);
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param queue
     *            the queue to decorate, must not be null
     * @param lock
     *            the lock to use, must not be null
     * @throws NullPointerException
     *             if queue or lock is null
     */
    protected SynchronizedQueue(final Queue<E> queue, final Object lock) {
        super(queue, lock);
    }

    @Override
    protected Queue<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E element() {
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
    public boolean offer(final E e) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
