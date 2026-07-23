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
package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;

/**
 * Decorates another {@link MultiSet} to validate that additions
 * match a specified predicate.
 * <p>
 * This multiset exists to provide validation for the decorated multiset.
 * It is normally created to decorate an empty multiset.
 * If an object cannot be added to the multiset, an {@link IllegalArgumentException}
 * is thrown.
 * </p>
 * <p>
 * One usage would be to ensure that no null entries are added to the multiset.
 * </p>
 * <pre>
 * MultiSet&lt;E&gt; set =
 *      PredicatedMultiSet.predicatedMultiSet(new HashMultiSet&lt;E&gt;(),
 *                                            NotNullPredicate.notNullPredicate());
 * </pre>
 *
 * @param <E> the type held in the multiset
 * @since 4.1
 */
public class PredicatedMultiSet<E> extends PredicatedCollection<E> implements MultiSet<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 20150629L;

    public static <E> PredicatedMultiSet<E> predicatedMultiSet(final MultiSet<E> multiset, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the multiset being decorated, they
     * are validated.
     * </p>
     *
     * @param multiset  the multiset to decorate, must not be null
     * @param predicate  the predicate to use for validation, must not be null
     * @throws NullPointerException if multiset or predicate is null
     * @throws IllegalArgumentException if the multiset contains invalid elements
     */
    protected PredicatedMultiSet(final MultiSet<E> multiset, final Predicate<? super E> predicate) {
        super(multiset, predicate);
    }

    @Override
    public int add(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected MultiSet<E> decorated() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<MultiSet.Entry<E>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getCount(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int remove(final Object object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int setCount(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<E> uniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
