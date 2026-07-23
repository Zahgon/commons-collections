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
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.collections4.Predicate;

/**
 * Internal utilities for functors.
 *
 * @since 3.0
 */
final class FunctorUtils {

    @SuppressWarnings("unchecked")
    static <R extends java.util.function.Predicate<T>, P extends java.util.function.Predicate<? super T>, T> R coerce(final P predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    static <R extends Function<I, O>, P extends Function<? super I, ? extends O>, I, O> R coerce(final P transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    static <T extends Consumer<?>> T[] copy(final T... consumers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    static <T extends java.util.function.Predicate<?>> T[] copy(final T... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    static <T extends Function<?, ?>> T[] copy(final T... transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static <T> Predicate<? super T>[] validate(final Collection<? extends java.util.function.Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void validate(final Consumer<?>... consumers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void validate(final Function<?, ?>... functions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static void validate(final java.util.function.Predicate<?>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Restricted constructor.
     */
    private FunctorUtils() {
    }
}
