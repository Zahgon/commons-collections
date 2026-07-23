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

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.apache.commons.collections4.set.PredicatedNavigableSet;
import org.apache.commons.collections4.set.PredicatedSet;
import org.apache.commons.collections4.set.PredicatedSortedSet;
import org.apache.commons.collections4.set.TransformedNavigableSet;
import org.apache.commons.collections4.set.TransformedSet;
import org.apache.commons.collections4.set.TransformedSortedSet;
import org.apache.commons.collections4.set.UnmodifiableNavigableSet;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.apache.commons.collections4.set.UnmodifiableSortedSet;

/**
 * Provides utility methods and decorators for
 * {@link Set} and {@link SortedSet} instances.
 *
 * @since 2.1
 */
public class SetUtils {

    /**
     * An unmodifiable <strong>view</strong> of a set that may be backed by other sets.
     * <p>
     * If the decorated sets change, this view will change as well. The contents
     * of this view can be transferred to another instance via the {@link #copyInto(Set)}
     * and {@link #toSet()} methods.
     * </p>
     *
     * @param <E> the element type
     * @since 4.1
     */
    public abstract static class SetView<E> extends AbstractSet<E> {

        /**
         * Constructs a new instance.
         */
        public SetView() {
            // empty
        }

        public <S extends Set<E>> void copyInto(final S set) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Return an iterator for this view; the returned iterator is
         * not required to be unmodifiable.
         * @return a new iterator for this view
         */
        protected abstract Iterator<E> createIterator();

        @Override
        public Iterator<E> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Set<E> toSet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * An empty unmodifiable sorted set.
     * This is not provided in the JDK.
     */
    @SuppressWarnings("rawtypes")
    public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.unmodifiableSortedSet(new TreeSet<>());

    public static <E> SetView<E> difference(final Set<? extends E> setA, final Set<? extends E> setB) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SetView<E> disjunction(final Set<? extends E> setA, final Set<? extends E> setB) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Set<T> emptyIfNull(final Set<T> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> emptySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // empty set is OK for any type
    @SuppressWarnings("unchecked")
    public static <E> SortedSet<E> emptySortedSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> int hashCodeForSet(final Collection<T> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> HashSet<E> hashSet(final E... items) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SetView<E> intersection(final Set<? extends E> setA, final Set<? extends E> setB) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEqualSet(final Collection<?> set1, final Collection<?> set2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> newIdentityHashSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> orderedSet(final Set<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> predicatedNavigableSet(final NavigableSet<E> set, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> predicatedSet(final Set<E> set, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> predicatedSortedSet(final SortedSet<E> set, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> synchronizedSet(final Set<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // SortedSet
    public static <E> SortedSet<E> synchronizedSortedSet(final SortedSet<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> transformedNavigableSet(final NavigableSet<E> set, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> transformedSet(final Set<E> set, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> transformedSortedSet(final SortedSet<E> set, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Set operations
    public static <E> SetView<E> union(final Set<? extends E> setA, final Set<? extends E> setB) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> unmodifiableNavigableSet(final NavigableSet<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> unmodifiableSet(final E... items) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> unmodifiableSet(final Set<? extends E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedSet<E> unmodifiableSortedSet(final SortedSet<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private SetUtils() {
        // empty
    }
}
