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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A Comparator which imposes a specific order on a specific set of Objects.
 * Objects are presented to the FixedOrderComparator in a specified order and
 * subsequent calls to {@link #compare(Object, Object) compare} yield that order.
 * For example:
 * <pre>
 * String[] planets = {"Mercury", "Venus", "Earth", "Mars"};
 * FixedOrderComparator distanceFromSun = new FixedOrderComparator(planets);
 * Arrays.sort(planets);                     // Sort to alphabetical order
 * Arrays.sort(planets, distanceFromSun);    // Back to original order
 * </pre>
 * <p>
 * Once {@code compare} has been called, the FixedOrderComparator is locked
 * and attempts to modify it yield an UnsupportedOperationException.
 * </p>
 * <p>
 * Instances of FixedOrderComparator are not synchronized.  The class is not
 * thread-safe at construction time, but it is thread-safe to perform
 * multiple comparisons  after all the setup operations are complete.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 4.0.
 * </p>
 *
 * @param <T> the type of objects compared by this comparator
 * @since 3.0
 */
public class FixedOrderComparator<T> implements Comparator<T>, Serializable {

    /**
     * Enumerates the unknown object behaviors.
     *
     * @since 4.0
     */
    public enum UnknownObjectBehavior {

        /**
         * Before unknown object behaviors.
         */
        BEFORE,
        /**
         * After unknown object behaviors.
         */
        AFTER,
        /**
         * Exception unknown object behaviors.
         */
        EXCEPTION
    }

    /**
     * Serialization version from Collections 4.0.
     */
    private static final long serialVersionUID = 82794675842863201L;

    /**
     * Internal map of object to position
     */
    private final Map<T, Integer> map = new HashMap<>();

    /**
     * Counter used in determining the position in the map
     */
    private int counter;

    /**
     * Is the comparator locked against further change
     */
    private boolean isLocked;

    /**
     * The behavior in the case of an unknown object
     */
    private UnknownObjectBehavior unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;

    // Constructors
    /**
     * Constructs an empty FixedOrderComparator.
     */
    public FixedOrderComparator() {
    }

    /**
     * Constructs a FixedOrderComparator which uses the order of the given list
     * to compare the objects.
     * <p>
     * The list is copied, so later changes will not affect the comparator.
     *
     * @param items  the items that the comparator can compare in order
     * @throws NullPointerException if the list is null
     */
    public FixedOrderComparator(final List<T> items) {
        for (final T t : Objects.requireNonNull(items, "items")) {
            add(t);
        }
    }

    /**
     * Constructs a FixedOrderComparator which uses the order of the given array
     * to compare the objects.
     * <p>
     * The array is copied, so later changes will not affect the comparator.
     *
     * @param items  the items that the comparator can compare in order
     * @throws NullPointerException if the array is null
     */
    public FixedOrderComparator(final T... items) {
        for (final T item : Objects.requireNonNull(items, "items")) {
            add(item);
        }
    }

    // Methods for adding items
    public boolean add(final T obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean addAsEqual(final T existingObj, final T newObj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void checkLocked() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Comparator methods
    @Override
    public int compare(final T obj1, final T obj2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Bean methods / state querying methods
    public boolean isLocked() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setUnknownObjectBehavior(final UnknownObjectBehavior unknownObjectBehavior) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
