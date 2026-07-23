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
package org.apache.commons.collections4;

import org.apache.commons.collections4.bag.CollectionBag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.bag.PredicatedSortedBag;
import org.apache.commons.collections4.bag.SynchronizedBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TransformedBag;
import org.apache.commons.collections4.bag.TransformedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.bag.UnmodifiableSortedBag;

/**
 * Provides utility methods and decorators for {@link Bag} and {@link SortedBag} instances.
 *
 * @since 2.1
 */
public class BagUtils {

    /**
     * An empty unmodifiable bag.
     */
    // OK, empty bag is compatible with any type
    @SuppressWarnings("rawtypes")
    public static final Bag EMPTY_BAG = UnmodifiableBag.unmodifiableBag(new HashBag<>());

    /**
     * An empty unmodifiable sorted bag.
     */
    // OK, empty bag is compatible with any type
    @SuppressWarnings("rawtypes")
    public static final Bag EMPTY_SORTED_BAG = UnmodifiableSortedBag.unmodifiableSortedBag(new TreeBag<>());

    public static <E> Bag<E> collectionBag(final Bag<E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // OK, empty bag is compatible with any type
    @SuppressWarnings("unchecked")
    public static <E> Bag<E> emptyBag() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // OK, empty bag is compatible with any type
    @SuppressWarnings("unchecked")
    public static <E> SortedBag<E> emptySortedBag() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Bag<E> predicatedBag(final Bag<E> bag, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedBag<E> predicatedSortedBag(final SortedBag<E> bag, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Bag<E> synchronizedBag(final Bag<E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedBag<E> synchronizedSortedBag(final SortedBag<E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Bag<E> transformingBag(final Bag<E> bag, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedBag<E> transformingSortedBag(final SortedBag<E> bag, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Bag<E> unmodifiableBag(final Bag<? extends E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SortedBag<E> unmodifiableSortedBag(final SortedBag<E> bag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private BagUtils() {
        // empty
    }
}
