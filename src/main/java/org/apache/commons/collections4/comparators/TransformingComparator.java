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
package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;

/**
 * Decorates another Comparator with transformation behavior. That is, the
 * return value from the transform operation will be passed to the decorated
 * {@link Comparator#compare(Object,Object) compare} method.
 * <p>
 * This class is Serializable from Commons Collections 4.0.
 * </p>
 *
 * @param <I> the type of the input to the function
 * @param <O> the type of the result of the function
 * @since 2.1
 * @see org.apache.commons.collections4.Transformer
 * @see org.apache.commons.collections4.comparators.ComparableComparator
 */
public class TransformingComparator<I, O> implements Comparator<I>, Serializable {

    /**
     * Serialization version from Collections 4.0.
     */
    private static final long serialVersionUID = 3456940356043606220L;

    /**
     * The decorated comparator.
     */
    private final Comparator<O> decorated;

    /**
     * The transformer being used.
     */
    private final Transformer<? super I, ? extends O> transformer;

    /**
     * Constructs an instance with the given Transformer and a
     * {@link ComparableComparator ComparableComparator}.
     *
     * @param transformer what will transform the arguments to {@code compare}
     */
    public TransformingComparator(final Transformer<? super I, ? extends O> transformer) {
        this(transformer, ComparatorUtils.NATURAL_COMPARATOR);
    }

    /**
     * Constructs an instance with the given Transformer and Comparator.
     *
     * @param transformer  what will transform the arguments to {@code compare}
     * @param decorated  the decorated Comparator
     */
    public TransformingComparator(final Transformer<? super I, ? extends O> transformer, final Comparator<O> decorated) {
        this.decorated = decorated;
        this.transformer = transformer;
    }

    @Override
    public int compare(final I obj1, final I obj2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
