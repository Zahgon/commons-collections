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
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that returns the same constant each time.
 * <p>
 * No check is made that the object is immutable. In general, only immutable
 * objects should use the constant factory. Mutable objects should
 * use the prototype factory.
 * </p>
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the result of the function.
 * @since 3.0
 */
public class ConstantTransformer<T, R> implements Transformer<T, R>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 6374440726369055124L;

    /**
     * Returns null each time
     */
    @SuppressWarnings("rawtypes")
    public static final Transformer NULL_INSTANCE = new ConstantTransformer<>(null);

    public static <I, O> Transformer<I, O> constantTransformer(final O constantToReturn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> nullTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The closures to call in turn
     */
    private final R iConstant;

    /**
     * Constructor that performs no validation.
     * Use {@code constantTransformer} if you want that.
     *
     * @param constantToReturn  the constant to return each time
     */
    public ConstantTransformer(final R constantToReturn) {
        iConstant = constantToReturn;
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public R getConstant() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public R transform(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
