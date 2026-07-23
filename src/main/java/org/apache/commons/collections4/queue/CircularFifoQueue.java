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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;
import org.apache.commons.collections4.BoundedCollection;

/**
 * CircularFifoQueue is a first-in first-out queue with a fixed size that
 * replaces its oldest element if full.
 * <p>
 * The removal order of a {@link CircularFifoQueue} is based on the
 * insertion order; elements are removed in the same order in which they
 * were added.  The iteration order is the same as the removal order.
 * </p>
 * <p>
 * The {@link #add(Object)}, {@link #remove()}, {@link #peek()}, {@link #poll()},
 * {@link #offer(Object)} operations all perform in constant time.
 * All other operations perform in linear time or worse.
 * </p>
 * <p>
 * This queue prevents null objects from being added.
 * </p>
 *
 * @param <E> the type of elements in this collection
 * @since 4.0
 */
public class CircularFifoQueue<E> extends AbstractCollection<E> implements Queue<E>, BoundedCollection<E>, Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -8423413834657610406L;

    /**
     * Underlying storage array.
     */
    private transient E[] elements;

    /**
     * Array index of first (oldest) queue element.
     */
    private transient int start;

    /**
     * Index mod maxElements of the array position following the last queue
     * element.  Queue elements start at elements[start] and "wrap around"
     * elements[maxElements-1], ending at elements[decrement(end)].
     * For example, elements = {c,a,b}, start=1, end=1 corresponds to
     * the queue [a,b,c].
     */
    private transient int end;

    /**
     * Flag to indicate if the queue is currently full.
     */
    private transient boolean full;

    /**
     * Capacity of the queue.
     */
    private final int maxElements;

    /**
     * Constructor that creates a queue with the default size of 32.
     */
    public CircularFifoQueue() {
        this(32);
    }

    /**
     * Constructor that creates a queue from the specified collection.
     * The collection size also sets the queue size.
     *
     * @param coll  the collection to copy into the queue, may not be null
     * @throws NullPointerException if the collection is null
     */
    public CircularFifoQueue(final Collection<? extends E> coll) {
        this(coll.size());
        addAll(coll);
    }

    /**
     * Constructor that creates a queue with the specified size.
     *
     * @param size  the size of the queue (cannot be changed)
     * @throws IllegalArgumentException  if the size is &lt; 1
     */
    @SuppressWarnings("unchecked")
    public CircularFifoQueue(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size must be greater than 0");
        }
        elements = (E[]) new Object[size];
        maxElements = elements.length;
    }

    @Override
    public boolean add(final E element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decrements the internal index.
     *
     * @param index  the index to decrement
     * @return the updated index
     */
    private int decrement(int index) {
        index--;
        if (index < 0) {
            index = maxElements - 1;
        }
        return index;
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E get(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Increments the internal index.
     *
     * @param index  the index to increment
     * @return the updated index
     */
    private int increment(int index) {
        index++;
        if (index >= maxElements) {
            index = 0;
        }
        return index;
    }

    public boolean isAtFullCapacity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int maxSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean offer(final E element) {
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

    /**
     * Deserializes the queue in using a custom routine.
     *
     * @param in  the input stream
     * @throws IOException if an I/O error occurs while writing to the output stream
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        elements = (E[]) new Object[maxElements];
        final int size = in.readInt();
        for (int i = 0; i < size; i++) {
            elements[i] = (E) in.readObject();
        }
        start = 0;
        full = size == maxElements;
        if (full) {
            end = 0;
        } else {
            end = size;
        }
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(size());
        for (final E e : this) {
            out.writeObject(e);
        }
    }
}
