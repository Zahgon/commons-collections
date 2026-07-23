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

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.comparators.BooleanComparator;
import org.apache.commons.collections4.comparators.ComparableComparator;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.commons.collections4.comparators.NullComparator;
import org.apache.commons.collections4.comparators.ReverseComparator;
import org.apache.commons.collections4.comparators.TransformingComparator;

/**
 * Provides convenient static utility methods for <Code>Comparator</Code>
 * objects.
 * <p>
 * Most of the functionality in this class can also be found in the
 * {@code comparators} package. This class merely provides a
 * convenient central place if you have use for more than one class
 * in the {@code comparators} subpackage.
 * </p>
 *
 * @since 2.1
 */
public class ComparatorUtils {

    @SuppressWarnings("rawtypes")
    private static final Comparator[] EMPTY_COMPARATOR_ARRAY = {};

    /**
     * Comparator for natural sort order.
     *
     * @see ComparableComparator#comparableComparator()
     */
    // explicit type needed for Java 1.5 compilation
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final Comparator NATURAL_COMPARATOR = ComparableComparator.<Comparable>comparableComparator();

    public static Comparator<Boolean> booleanComparator(final boolean trueFirst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Comparator<E> chainedComparator(final Collection<Comparator<E>> comparators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Comparator<E> chainedComparator(final Comparator<E>... comparators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E max(final E o1, final E o2, Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> E min(final E o1, final E o2, Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<? super E>> Comparator<E> naturalComparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Comparator<E> nullHighComparator(Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Comparator<E> nullLowComparator(Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Comparator<E> reversedComparator(final Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <I, O> Comparator<I> transformedComparator(Comparator<O> comparator, final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private ComparatorUtils() {
        // empty
    }
}
