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
import org.apache.commons.collections4.Transformer;

/**
 * Predicate implementation that returns the result of a transformer.
 *
 * @param <T> the type of the input to the predicate.
 * @since 3.0
 */
public final class TransformerPredicate<T> extends AbstractPredicate<T> implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -2407966402920578741L;

    public static <T> Predicate<T> transformerPredicate(final Transformer<? super T, Boolean> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The transformer to call
     */
    private final Transformer<? super T, Boolean> iTransformer;

    /**
     * Constructor that performs no validation.
     * Use {@code transformerPredicate} if you want that.
     *
     * @param transformer  the transformer to decorate
     */
    public TransformerPredicate(final Transformer<? super T, Boolean> transformer) {
        iTransformer = transformer;
    }

    public Transformer<? super T, Boolean> getTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean test(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
