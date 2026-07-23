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
package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator Comparator} that compares {@link Comparable Comparable}
 * objects.
 * <p>
 * This Comparator is useful, for example, for enforcing the natural order in
 * custom implementations of {@link java.util.SortedSet SortedSet} and
 * {@link java.util.SortedMap SortedMap}.
 * </p>
 * <p>
 * Note: In the 2.0 and 2.1 releases of Commons Collections, this class would
 * throw a {@link ClassCastException} if either of the arguments to
 * {@link #compare(Comparable, Comparable)} compare} were {@code null}, not
 * {@link Comparable Comparable}, or for which
 * {@link Comparable#compareTo(Object) compareTo} gave inconsistent results.
 * This is no longer the case. See {@link #compare(Comparable, Comparable)} compare} for
 * details.
 * </p>
 *
 * @param <E> the type of objects compared by this comparator
 * @since 2.0
 * @see java.util.Collections#reverseOrder()
 */
public class ComparableComparator<E extends Comparable<? super E>> implements Comparator<E>, Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -291439688585137865L;

    /**
     * The singleton instance.
     */
    @SuppressWarnings("rawtypes")
    public static final ComparableComparator INSTANCE = new ComparableComparator();

    public static <E extends Comparable<? super E>> ComparableComparator<E> comparableComparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor whose use should be avoided.
     * <p>
     * Please use the {@link #comparableComparator()} method whenever possible.
     */
    public ComparableComparator() {
    }

    @Override
    public int compare(final E obj1, final E obj2) {
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
}
