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
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation calls the transformer whose predicate returns true,
 * like a switch statement.
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the result of the function.
 * @since 3.0
 */
public class SwitchTransformer<T, R> implements Transformer<T, R>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -6404460890903469332L;

    @SuppressWarnings("unchecked")
    public static <I, O> Transformer<I, O> switchTransformer(final Map<? extends Predicate<? super I>, ? extends Transformer<? super I, ? extends O>> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <I, O> Transformer<I, O> switchTransformer(final Predicate<? super I>[] predicates, final Transformer<? super I, ? extends O>[] transformers, final Transformer<? super I, ? extends O> defaultTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The tests to consider
     */
    private final Predicate<? super T>[] iPredicates;

    /**
     * The matching transformers to call
     */
    private final Transformer<? super T, ? extends R>[] iTransformers;

    /**
     * The default transformer to call if no tests match
     */
    private final Transformer<? super T, ? extends R> iDefault;

    /**
     * Hidden constructor for the use by the static factory methods.
     *
     * @param clone  if {@code true} the input arguments will be cloned
     * @param predicates  array of predicates, no nulls
     * @param transformers  matching array of transformers, no nulls
     * @param defaultTransformer  the transformer to use if no match, null means return null
     */
    private SwitchTransformer(final boolean clone, final Predicate<? super T>[] predicates, final Transformer<? super T, ? extends R>[] transformers, final Transformer<? super T, ? extends R> defaultTransformer) {
        iPredicates = clone ? FunctorUtils.copy(predicates) : predicates;
        iTransformers = clone ? FunctorUtils.copy(transformers) : transformers;
        iDefault = defaultTransformer == null ? ConstantTransformer.<T, R>nullTransformer() : defaultTransformer;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code switchTransformer} if you want that.
     *
     * @param predicates  array of predicates, cloned, no nulls
     * @param transformers  matching array of transformers, cloned, no nulls
     * @param defaultTransformer  the transformer to use if no match, null means return null
     */
    public SwitchTransformer(final Predicate<? super T>[] predicates, final Transformer<? super T, ? extends R>[] transformers, final Transformer<? super T, ? extends R> defaultTransformer) {
        this(true, predicates, transformers, defaultTransformer);
    }

    public Transformer<? super T, ? extends R> getDefaultTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Predicate<? super T>[] getPredicates() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Transformer<? super T, ? extends R>[] getTransformers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public R transform(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
