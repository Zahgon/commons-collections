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

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.iterators.SingletonIterator;

/**
 * A FluentIterable provides a powerful yet simple API for manipulating
 * Iterable instances in a fluent manner.
 * <p>
 * A FluentIterable can be created either from an Iterable or from a set
 * of elements. The following types of methods are provided:
 * </p>
 * <ul>
 *   <li>fluent methods which return a new {@code FluentIterable} instance,
 *       providing a view of the original iterable (for example filter(Predicate));
 *   <li>conversion methods which copy the FluentIterable's contents into a
 *       new collection or array (for example toList());
 *   <li>utility methods which answer questions about the FluentIterable's
 *       contents (for example size(), anyMatch(Predicate)).
 *   <li>
 * </ul>
 * <p>
 * The following example outputs the first 3 even numbers in the range [1, 10]
 * into a list:
 * </p>
 * <pre>
 * List&lt;String&gt; result =
 *   FluentIterable
 *       .of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
 *       .filter(new Predicate&lt;Integer&gt;() {
 *                   public boolean evaluate(Integer number) {
 *                        return number % 2 == 0;
 *                   }
 *              )
 *       .transform(TransformerUtils.stringValueTransformer())
 *       .limit(3)
 *       .toList();
 * </pre>
 * The resulting list will contain the following elements:
 * <pre>[2, 4, 6]</pre>
 *
 * @param <E>  the element type
 * @since 4.1
 */
public class FluentIterable<E> implements Iterable<E> {

    public static <T> FluentIterable<T> empty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> FluentIterable<T> of(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> FluentIterable<T> of(final T singleton) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> FluentIterable<T> of(final T... elements) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A reference to the wrapped iterable.
     */
    private final Iterable<E> iterable;

    /**
     * Don't allow instances.
     */
    FluentIterable() {
        iterable = this;
    }

    /**
     * Create a new FluentIterable by wrapping the provided iterable.
     * @param iterable  the iterable to wrap
     */
    private FluentIterable(final Iterable<E> iterable) {
        this.iterable = iterable;
    }

    public boolean allMatch(final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean anyMatch(final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> append(final E... elements) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> append(final Iterable<? extends E> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Enumeration<E> asEnumeration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> collate(final Iterable<? extends E> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> collate(final Iterable<? extends E> other, final Comparator<? super E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean contains(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void copyInto(final Collection<? super E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> eval() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> filter(final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void forEach(final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E get(final int position) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> limit(final long maxSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> loop() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> reverse() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> skip(final long elementsToSkip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public E[] toArray(final Class<E> arrayClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<E> toList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <O> FluentIterable<O> transform(final Transformer<? super E, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> unique() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> unmodifiable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> zip(final Iterable<? extends E> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public FluentIterable<E> zip(final Iterable<? extends E>... others) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
