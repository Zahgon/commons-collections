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
import org.apache.commons.collections4.Closure;

/**
 * Closure implementation that chains the specified closures together.
 *
 * @param <T> the type of the input to the operation.
 * @since 3.0
 */
public class ChainedClosure<T> implements Closure<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -3520677225766901240L;

    public static <E> Closure<E> chainedClosure(final Closure<? super E>... closures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Closure<E> chainedClosure(final Collection<? extends Closure<? super E>> closures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The closures to call in turn
     */
    private final Closure<? super T>[] iClosures;

    /**
     * Hidden constructor for the use by the static factory methods.
     *
     * @param clone  if {@code true} the input argument will be cloned
     * @param closures  the closures to chain, no nulls
     */
    private ChainedClosure(final boolean clone, final Closure<? super T>... closures) {
        iClosures = clone ? FunctorUtils.copy(closures) : closures;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code chainedClosure} if you want that.
     *
     * @param closures  the closures to chain, copied, no nulls
     */
    public ChainedClosure(final Closure<? super T>... closures) {
        this(true, closures);
    }

    @Override
    public void execute(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Closure<? super T>[] getClosures() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
