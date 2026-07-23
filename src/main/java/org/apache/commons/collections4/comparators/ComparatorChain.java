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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A ComparatorChain is a Comparator that wraps one or more Comparators in
 * sequence. The ComparatorChain calls each Comparator in sequence until either
 * 1) any single Comparator returns a non-zero result (and that result is then
 * returned), or 2) the ComparatorChain is exhausted (and zero is returned).
 * This type of sorting is very similar to multi-column sorting in SQL, and this
 * class allows Java classes to emulate that kind of behavior when sorting a
 * List.
 * <p>
 * To further facilitate SQL-like sorting, the order of any single Comparator in
 * the list can be reversed.
 * </p>
 * <p>
 * Calling a method that adds new Comparators or changes the ascend/descend sort
 * <em>after compare(Object, Object) has been called</em> will result in an
 * UnsupportedOperationException. However, <em>take care</em> to not alter the
 * underlying List of Comparators or the BitSet that defines the sort order.
 * </p>
 * <p>
 * Instances of ComparatorChain are not synchronized. The class is not
 * thread-safe at construction time, but it <em>is</em> thread-safe to perform
 * multiple comparisons after all the setup operations are complete.
 * </p>
 *
 * @param <E> the type of objects compared by this comparator
 * @since 2.0
 */
public class ComparatorChain<E> implements Comparator<E>, Serializable {

    /**
     * Serialization version from Collections 2.0.
     */
    private static final long serialVersionUID = -721644942746081630L;

    /**
     * The list of comparators in the chain.
     */
    private final List<Comparator<E>> comparatorChain;

    /**
     * Order - false (clear) = ascend; true (set) = descend.
     */
    private final BitSet orderingBits;

    /**
     * Whether the chain has been "locked".
     */
    private boolean isLocked;

    /**
     * Constructs a ComparatorChain with no Comparators.
     * You must add at least one Comparator before calling
     * the compare(Object,Object) method, or an
     * UnsupportedOperationException is thrown
     */
    public ComparatorChain() {
        this(new ArrayList<>(), new BitSet());
    }

    /**
     * Constructs a ComparatorChain with a single Comparator,
     * sorting in the forward order
     *
     * @param comparator First comparator in the Comparator chain
     */
    public ComparatorChain(final Comparator<E> comparator) {
        this(comparator, false);
    }

    /**
     * Constructs a Comparator chain with a single Comparator,
     * sorting in the given order
     *
     * @param comparator First Comparator in the ComparatorChain
     * @param reverse    false = forward sort; true = reverse sort
     */
    public ComparatorChain(final Comparator<E> comparator, final boolean reverse) {
        comparatorChain = new ArrayList<>(1);
        comparatorChain.add(comparator);
        orderingBits = new BitSet(1);
        if (reverse) {
            orderingBits.set(0);
        }
    }

    /**
     * Constructs a ComparatorChain from the Comparators in the
     * List.  All Comparators will default to the forward
     * sort order.
     *
     * @param list   List of Comparators
     * @see #ComparatorChain(List,BitSet)
     */
    public ComparatorChain(final List<Comparator<E>> list) {
        this(list, new BitSet(list.size()));
    }

    /**
     * Constructs a ComparatorChain from the Comparators in the
     * given List.  The sort order of each column will be
     * drawn from the given BitSet.  When determining the sort
     * order for Comparator at index <em>i</em> in the List,
     * the ComparatorChain will call BitSet.get(<em>i</em>).
     * If that method returns <em>false</em>, the forward
     * sort order is used; a return value of <em>true</em>
     * indicates reverse sort order.
     *
     * @param list   List of Comparators.  NOTE: This constructor does not perform a
     *               defensive copy of the list
     * @param bits   Sort order for each Comparator.  Extra bits are ignored,
     *               unless extra Comparators are added by another method.
     */
    public ComparatorChain(final List<Comparator<E>> list, final BitSet bits) {
        comparatorChain = list;
        orderingBits = bits;
    }

    public void addComparator(final Comparator<E> comparator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComparator(final Comparator<E> comparator, final boolean reverse) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Throws an exception if the {@link ComparatorChain} is empty.
     *
     * @throws UnsupportedOperationException if the {@link ComparatorChain} is empty
     */
    private void checkChainIntegrity() {
        if (comparatorChain.isEmpty()) {
            throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
        }
    }

    /**
     * Throws an exception if the {@link ComparatorChain} is locked.
     *
     * @throws UnsupportedOperationException if the {@link ComparatorChain} is locked
     */
    private void checkLocked() {
        if (isLocked) {
            throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
        }
    }

    @Override
    public int compare(final E o1, final E o2) throws UnsupportedOperationException {
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

    public boolean isLocked() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setComparator(final int index, final Comparator<E> comparator) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setComparator(final int index, final Comparator<E> comparator, final boolean reverse) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setForwardSort(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setReverseSort(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
