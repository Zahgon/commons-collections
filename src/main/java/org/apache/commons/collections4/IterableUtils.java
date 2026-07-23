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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.commons.collections4.iterators.UniqueFilterIterator;

/**
 * Provides utility methods and decorators for {@link Iterable} instances.
 * <p>
 * <strong>Note</strong>: This utility class has been designed with fail-fast argument checking.
 * </p>
 * <ul>
 * <li>All decorator methods are <em>not</em> null-safe for the provided Iterable argument; for example, they will throw a {@link NullPointerException} if a
 * null Iterable is passed as argument.
 * <li>All other utility methods are null-safe for the provided Iterable argument; for example, they will treat a null Iterable the same way as an empty one.
 * For other arguments which are null, a {@link Predicate} will result in a {@link NullPointerException}. Exception: passing a null {@link Comparator} is
 * equivalent to a Comparator with natural ordering.
 * </ul>
 *
 * @since 4.1
 */
public class IterableUtils {

    /**
     * Inner class to distinguish unmodifiable instances.
     */
    private static final class UnmodifiableIterable<E> extends FluentIterable<E> {

        private final Iterable<E> iterable;

        UnmodifiableIterable(final Iterable<E> iterable) {
            this.iterable = iterable;
        }

        @Override
        public Iterator<E> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * An empty iterable.
     */
    @SuppressWarnings("rawtypes")
    static final FluentIterable EMPTY_ITERABLE = new FluentIterable<Object>() {

        @Override
        public Iterator<Object> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    };

    public static <E> Iterable<E> boundedIterable(final Iterable<E> iterable, final long maxSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E>... iterables) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E> a, final Iterable<? extends E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E> a, final Iterable<? extends E> b, final Iterable<? extends E> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Iterable<E> chainedIterable(final Iterable<? extends E> a, final Iterable<? extends E> b, final Iterable<? extends E> c, final Iterable<? extends E> d) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void checkNotNull(final Iterable<?>... iterables) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> collatedIterable(final Comparator<? super E> comparator, final Iterable<? extends E> a, final Iterable<? extends E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> collatedIterable(final Iterable<? extends E> a, final Iterable<? extends E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean contains(final Iterable<? extends E> iterable, final E object, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean contains(final Iterable<E> iterable, final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> long countMatches(final Iterable<E> input, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> duplicateList(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> duplicateSequencedSet(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> duplicateSet(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static <C extends Collection<E>, E> C duplicateSet(final Iterable<E> iterable, final C duplicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> emptyIfNull(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // OK, empty collection is compatible with any type
    @SuppressWarnings("unchecked")
    public static <E> Iterable<E> emptyIterable() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an empty iterator if the argument is {@code null},
     * or {@code iterable.iterator()} otherwise.
     *
     * @param <E> the element type
     * @param iterable  the iterable, possibly {@code null}
     * @return an empty iterator if the argument is {@code null}
     */
    private static <E> Iterator<E> emptyIteratorIfNull(final Iterable<E> iterable) {
        return iterable != null ? iterable.iterator() : IteratorUtils.<E>emptyIterator();
    }

    public static <E> Iterable<E> filteredIterable(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E find(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T first(final Iterable<T> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> void forEach(final Iterable<E> iterable, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E forEachButLast(final Iterable<E> iterable, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E, T extends E> int frequency(final Iterable<E> iterable, final T obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T get(final Iterable<T> iterable, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int indexOf(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmpty(final Iterable<?> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> loopingIterable(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean matchesAll(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean matchesAny(final Iterable<E> iterable, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O, R extends Collection<O>> List<R> partition(final Iterable<? extends O> iterable, final Factory<R> partitionFactory, final Predicate<? super O>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> List<List<O>> partition(final Iterable<? extends O> iterable, final Predicate<? super O> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> List<List<O>> partition(final Iterable<? extends O> iterable, final Predicate<? super O>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> reversedIterable(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int size(final Iterable<?> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> skippingIterable(final Iterable<E> iterable, final long elementsToSkip) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> toList(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterable<E> iterable, final Transformer<? super E, String> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterable<E> iterable, final Transformer<? super E, String> transformer, final String delimiter, final String prefix, final String suffix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Iterable<O> transformedIterable(final Iterable<I> iterable, final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> uniqueIterable(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> unmodifiableIterable(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> a, final Iterable<? extends E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> zippingIterable(final Iterable<? extends E> first, final Iterable<? extends E>... others) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Make private in 5.0.
     *
     * @deprecated TODO Make private in 5.0.
     */
    @Deprecated
    public IterableUtils() {
        // empty
    }
}
