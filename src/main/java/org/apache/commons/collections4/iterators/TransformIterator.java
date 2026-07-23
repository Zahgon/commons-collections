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
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Transformer;

/**
 * Decorates an iterator such that each element returned is transformed.
 *
 * @param <I> the type of the input to the function.
 * @param <O> the type of the result of the function.
 * @since 1.0
 */
public class TransformIterator<I, O> implements Iterator<O> {

    /**
     * The iterator being used
     */
    private Iterator<? extends I> iterator;

    /**
     * The transformer being used
     */
    private Transformer<? super I, ? extends O> transformer;

    /**
     * Constructs a new {@code TransformIterator} that will not function
     * until the {@link #setIterator(Iterator) setIterator} and
     * {@link #setTransformer(Transformer)} methods are invoked.
     */
    public TransformIterator() {
    }

    /**
     * Constructs a new {@code TransformIterator} that won't transform
     * elements from the given iterator.
     *
     * @param iterator  the iterator to use
     */
    public TransformIterator(final Iterator<? extends I> iterator) {
        this.iterator = iterator;
    }

    /**
     * Constructs a new {@code TransformIterator} that will use the
     * given iterator and transformer.  If the given transformer is null,
     * then objects will not be transformed.
     *
     * @param iterator  the iterator to use
     * @param transformer  the transformer to use
     */
    public TransformIterator(final Iterator<? extends I> iterator, final Transformer<? super I, ? extends O> transformer) {
        this.iterator = iterator;
        this.transformer = transformer;
    }

    public Iterator<? extends I> getIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Transformer<? super I, ? extends O> getTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public O next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setIterator(final Iterator<? extends I> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setTransformer(final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected O transform(final I source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
