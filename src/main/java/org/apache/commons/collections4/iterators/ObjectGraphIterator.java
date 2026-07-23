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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;

/**
 * An Iterator that can traverse multiple iterators down an object graph.
 * <p>
 * This iterator can extract multiple objects from a complex tree-like object graph.
 * The iteration starts from a single root object.
 * It uses a {@code Transformer} to extract the iterators and elements.
 * Its main benefit is that no intermediate {@code List} is created.
 * </p>
 * <p>
 * For example, consider an object graph:
 * </p>
 * <pre>
 *                 |- Branch -- Leaf
 *                 |         \- Leaf
 *         |- Tree |         /- Leaf
 *         |       |- Branch -- Leaf
 *  Forest |                 \- Leaf
 *         |       |- Branch -- Leaf
 *         |       |         \- Leaf
 *         |- Tree |         /- Leaf
 *                 |- Branch -- Leaf
 *                 |- Branch -- Leaf</pre>
 * <p>
 * The following {@code Transformer}, used in this class, will extract all
 * the Leaf objects without creating a combined intermediate list:
 * </p>
 * <pre>
 * public Object transform(Object input) {
 *   if (input instanceof Forest) {
 *     return ((Forest) input).treeIterator();
 *   }
 *   if (input instanceof Tree) {
 *     return ((Tree) input).branchIterator();
 *   }
 *   if (input instanceof Branch) {
 *     return ((Branch) input).leafIterator();
 *   }
 *   if (input instanceof Leaf) {
 *     return input;
 *   }
 *   throw new ClassCastException();
 * }</pre>
 * <p>
 * Internally, iteration starts from the root object. When next is called,
 * the transformer is called to examine the object. The transformer will return
 * either an iterator or an object. If the object is an Iterator, the next element
 * from that iterator is obtained and the process repeats. If the element is an object
 * it is returned.
 * </p>
 * <p>
 * Under many circumstances, linking Iterators together in this manner is
 * more efficient (and convenient) than using nested for loops to extract a list.
 * </p>
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 3.1
 */
public class ObjectGraphIterator<E> implements Iterator<E> {

    /**
     * The stack of iterators
     */
    private final Deque<Iterator<? extends E>> stack = new ArrayDeque<>(8);

    /**
     * The root object in the tree
     */
    private E root;

    /**
     * The transformer to use
     */
    private final Transformer<? super E, ? extends E> transformer;

    /**
     * Whether there is another element in the iteration
     */
    private boolean hasNext;

    /**
     * The current iterator
     */
    private Iterator<? extends E> currentIterator;

    /**
     * The current value
     */
    private E currentValue;

    /**
     * The last used iterator, needed for remove()
     */
    private Iterator<? extends E> lastUsedIterator;

    /**
     * Constructs an ObjectGraphIterator using a root object and transformer.
     * <p>
     * The root object can be an iterator, in which case it will be immediately
     * looped around.
     *
     * @param root  the root object, null will result in an empty iterator
     * @param transformer  the transformer to use, null will use a no effect transformer
     */
    @SuppressWarnings("unchecked")
    public ObjectGraphIterator(final E root, final Transformer<? super E, ? extends E> transformer) {
        if (root instanceof Iterator) {
            this.currentIterator = (Iterator<? extends E>) root;
        } else {
            this.root = root;
        }
        this.transformer = transformer;
    }

    /**
     * Constructs a ObjectGraphIterator that will handle an iterator of iterators.
     * <p>
     * This constructor exists for convenience to emphasise that this class can
     * be used to iterate over nested iterators. That is to say that the iterator
     * passed in here contains other iterators, which may in turn contain further
     * iterators.
     * </p>
     *
     * @param rootIterator  the root iterator, null will result in an empty iterator
     */
    public ObjectGraphIterator(final Iterator<? extends E> rootIterator) {
        this.currentIterator = rootIterator;
        this.transformer = null;
    }

    @SuppressWarnings("unchecked")
    protected void findNext(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void findNextByIterator(final Iterator<? extends E> iterator) {
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

    protected void updateCurrentIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
