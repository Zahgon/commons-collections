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
 * A {@link Comparator} for {@link Boolean} objects that can sort either
 * true or false first.
 *
 * @see #getTrueFirstComparator()
 * @see #getFalseFirstComparator()
 * @see #booleanComparator(boolean)
 * @since 3.0
 */
public final class BooleanComparator implements Comparator<Boolean>, Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 1830042991606340609L;

    /**
     * Constant "true first" reference.
     */
    private static final BooleanComparator TRUE_FIRST = new BooleanComparator(true);

    /**
     * Constant "false first" reference.
     */
    private static final BooleanComparator FALSE_FIRST = new BooleanComparator(false);

    public static BooleanComparator booleanComparator(final boolean trueFirst) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static BooleanComparator getFalseFirstComparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static BooleanComparator getTrueFirstComparator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * {@code true} iff {@code true} values sort before {@code false} values.
     */
    private final boolean trueFirst;

    /**
     * Creates a {@code BooleanComparator} that sorts
     * {@code false} values before {@code true} values.
     * <p>
     * Equivalent to {@link #BooleanComparator(boolean) BooleanComparator(false)}.
     * <p>
     * Please use the static factory instead whenever possible.
     */
    public BooleanComparator() {
        this(false);
    }

    /**
     * Creates a {@code BooleanComparator} that sorts
     * {@code <em>trueFirst</em>} values before
     * {@code &#x21;<em>trueFirst</em>} values.
     * <p>
     * Please use the static factories instead whenever possible.
     *
     * @param trueFirst when {@code true}, sort
     *  {@code true} boolean values before {@code false}
     */
    public BooleanComparator(final boolean trueFirst) {
        this.trueFirst = trueFirst;
    }

    @Override
    public int compare(final Boolean b1, final Boolean b2) {
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

    public boolean sortsTrueFirst() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
