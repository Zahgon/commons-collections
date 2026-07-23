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
package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.set.TransformedSet;

/**
 * Decorates another {@link Bag} to transform objects that are added.
 * <p>
 * The add methods are affected by this class.
 * Thus objects must be removed or searched for using their transformed form.
 * For example, if the transformation converts Strings to Integers, you must
 * use the Integer form to remove objects.
 * </p>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of elements in this bag
 * @since 3.0
 */
public class TransformedBag<E> extends TransformedCollection<E> implements Bag<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 5421170911299074185L;

    public static <E> Bag<E> transformedBag(final Bag<E> bag, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Bag<E> transformingBag(final Bag<E> bag, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the bag being decorated, they
     * are NOT transformed.
     *
     * @param bag  the bag to decorate, must not be null
     * @param transformer  the transformer to use for conversion, must not be null
     * @throws NullPointerException if bag or transformer is null
     */
    protected TransformedBag(final Bag<E> bag, final Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    @Override
    public boolean add(final E object, final int nCopies) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Bag<E> getBag() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getCount(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object, final int nCopies) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<E> uniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
