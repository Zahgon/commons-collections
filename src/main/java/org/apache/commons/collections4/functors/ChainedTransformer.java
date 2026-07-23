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
import java.util.Collection;
import java.util.Objects;
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that chains the specified transformers together.
 * <p>
 * The input object is passed to the first transformer. The transformed result
 * is passed to the second transformer and so on.
 * </p>
 *
 * @param <T> the type of the input and result to the function.
 * @since 3.0
 */
public class ChainedTransformer<T> implements Transformer<T, T>, Serializable {

    @SuppressWarnings("rawtypes")
    private static final Transformer[] EMPTY_TRANSFORMER_ARRAY = {};

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 3514945074733160196L;

    public static <T> Transformer<T, T> chainedTransformer(final Collection<? extends Transformer<? super T, ? extends T>> transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> chainedTransformer(final Transformer<? super T, ? extends T>... transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The transformers to call in turn
     */
    private final Transformer<? super T, ? extends T>[] iTransformers;

    /**
     * Hidden constructor for the use by the static factory methods.
     *
     * @param clone  if {@code true} the input argument will be cloned
     * @param transformers  the transformers to chain, no nulls
     */
    private ChainedTransformer(final boolean clone, final Transformer<? super T, ? extends T>[] transformers) {
        iTransformers = clone ? FunctorUtils.copy(transformers) : transformers;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code chainedTransformer} if you want that.
     *
     * @param transformers  the transformers to chain, copied, no nulls
     */
    public ChainedTransformer(final Transformer<? super T, ? extends T>... transformers) {
        this(true, transformers);
    }

    public Transformer<? super T, ? extends T>[] getTransformers() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public T transform(T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
