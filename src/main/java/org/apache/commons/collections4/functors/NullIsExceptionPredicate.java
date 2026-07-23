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
import java.util.Objects;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Predicate;

/**
 * Predicate implementation that throws an exception if the input is null.
 *
 * @param <T> the type of the input to the predicate.
 * @since 3.0
 */
public final class NullIsExceptionPredicate<T> extends AbstractPredicate<T> implements PredicateDecorator<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 3243449850504576071L;

    public static <T> Predicate<T> nullIsExceptionPredicate(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The predicate to decorate
     */
    private final Predicate<? super T> iPredicate;

    /**
     * Constructor that performs no validation.
     * Use {@code nullIsExceptionPredicate} if you want that.
     *
     * @param predicate  the predicate to call after the null check
     */
    public NullIsExceptionPredicate(final Predicate<? super T> predicate) {
        iPredicate = predicate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate<? super T>[] getPredicates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean test(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
