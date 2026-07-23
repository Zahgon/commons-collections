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

import java.util.Collection;
import org.apache.commons.collections4.Predicate;

/**
 * Predicate implementation that returns true if only one of the
 * predicates return true.
 * If the array of predicates is empty, then this predicate returns false.
 * <p>
 * NOTE: In versions prior to 3.2 an array size of zero or one
 * threw an exception.
 * </p>
 *
 * @param <T> the type of the input to the predicate.
 * @since 3.0
 */
public final class OnePredicate<T> extends AbstractQuantifierPredicate<T> {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -8125389089924745785L;

    public static <T> Predicate<T> onePredicate(final Collection<? extends Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> onePredicate(final Predicate<? super T>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that performs no validation.
     * Use {@code onePredicate} if you want that.
     *
     * @param predicates  the predicates to check, not cloned, not null
     */
    public OnePredicate(final Predicate<? super T>... predicates) {
        super(predicates);
    }

    @Override
    public boolean test(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
