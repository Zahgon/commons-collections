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
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/**
 * Predicate implementation that transforms the given object before invoking
 * another {@code Predicate}.
 *
 * @param <T> the type of the input to the predicate.
 * @since 3.1
 */
public final class TransformedPredicate<T> extends AbstractPredicate<T> implements PredicateDecorator<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -5596090919668315834L;

    public static <T> Predicate<T> transformedPredicate(final Transformer<? super T, ? extends T> transformer, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The transformer to call
     */
    private final Transformer<? super T, ? extends T> iTransformer;

    /**
     * The predicate to call
     */
    private final Predicate<? super T> iPredicate;

    /**
     * Constructor that performs no validation.
     * Use {@code transformedPredicate} if you want that.
     *
     * @param transformer  the transformer to use
     * @param predicate  the predicate to decorate
     */
    public TransformedPredicate(final Transformer<? super T, ? extends T> transformer, final Predicate<? super T> predicate) {
        iTransformer = transformer;
        iPredicate = predicate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Predicate<? super T>[] getPredicates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Transformer<? super T, ? extends T> getTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean test(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
