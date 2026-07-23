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
package org.apache.commons.collections4;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.apache.commons.collections4.queue.TransformedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;

/**
 * Provides utility methods and decorators for {@link Queue} instances.
 *
 * @since 4.0
 */
public class QueueUtils {

    /**
     * An empty unmodifiable queue.
     */
    // OK, empty queue is compatible with any type
    @SuppressWarnings("rawtypes")
    public static final Queue EMPTY_QUEUE = UnmodifiableQueue.unmodifiableQueue(new LinkedList<>());

    // OK, empty queue is compatible with any type
    @SuppressWarnings("unchecked")
    public static <E> Queue<E> emptyQueue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Queue<E> predicatedQueue(final Queue<E> queue, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Queue<E> synchronizedQueue(final Queue<E> queue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Queue<E> transformingQueue(final Queue<E> queue, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Queue<E> unmodifiableQueue(final Queue<? extends E> queue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private QueueUtils() {
        // empty
    }
}
