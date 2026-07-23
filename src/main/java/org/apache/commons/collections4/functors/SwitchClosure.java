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
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;

/**
 * Closure implementation calls the closure whose predicate returns true,
 * like a switch statement.
 *
 * @param <T> the type of the input to the operation.
 * @since 3.0
 */
public class SwitchClosure<T> implements Closure<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 3518477308466486130L;

    @SuppressWarnings("unchecked")
    public static <E> Closure<E> switchClosure(final Map<Predicate<E>, Closure<E>> predicatesAndClosures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Closure<E> switchClosure(final Predicate<? super E>[] predicates, final Closure<? super E>[] closures, final Closure<? super E> defaultClosure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The tests to consider
     */
    private final Predicate<? super T>[] iPredicates;

    /**
     * The matching closures to call
     */
    private final Closure<? super T>[] iClosures;

    /**
     * The default closure to call if no tests match
     */
    private final Closure<? super T> iDefault;

    /**
     * Hidden constructor for the use by the static factory methods.
     *
     * @param clone  if {@code true} the input arguments will be cloned
     * @param predicates  array of predicates, no nulls
     * @param closures  matching array of closures, no nulls
     * @param defaultClosure  the closure to use if no match, null means nop
     */
    private SwitchClosure(final boolean clone, final Predicate<? super T>[] predicates, final Closure<? super T>[] closures, final Closure<? super T> defaultClosure) {
        iPredicates = clone ? FunctorUtils.copy(predicates) : predicates;
        iClosures = clone ? FunctorUtils.copy(closures) : closures;
        iDefault = defaultClosure == null ? NOPClosure.<T>nopClosure() : defaultClosure;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code switchClosure} if you want that.
     *
     * @param predicates  array of predicates, cloned, no nulls
     * @param closures  matching array of closures, cloned, no nulls
     * @param defaultClosure  the closure to use if no match, null means nop
     */
    public SwitchClosure(final Predicate<? super T>[] predicates, final Closure<? super T>[] closures, final Closure<? super T> defaultClosure) {
        this(true, predicates, closures, defaultClosure);
    }

    @Override
    public void execute(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Closure<? super T>[] getClosures() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Closure<? super T> getDefaultClosure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Predicate<? super T>[] getPredicates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
