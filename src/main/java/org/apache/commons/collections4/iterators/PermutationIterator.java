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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This iterator creates permutations of an input collection, using the
 * Steinhaus-Johnson-Trotter algorithm (also called plain changes).
 * <p>
 * The iterator will return exactly n! permutations of the input collection.
 * The {@code remove()} operation is not supported, and will throw an
 * {@code UnsupportedOperationException}.
 * </p>
 * <p>
 * NOTE: in case an empty collection is provided, the iterator will
 * return exactly one empty list as result, as 0! = 1.
 * </p>
 *
 * @param <E>  the type of the objects being permuted
 * @since 4.0
 */
public class PermutationIterator<E> implements Iterator<List<E>> {

    /**
     * Permutation is done on these keys to handle equal objects.
     */
    private final int[] keys;

    /**
     * Mapping between keys and objects.
     */
    private final Map<Integer, E> objectMap;

    /**
     * Direction table used in the algorithm:
     * <ul>
     *   <li>false is left</li>
     *   <li>true is right</li>
     * </ul>
     */
    private final boolean[] direction;

    /**
     * Next permutation to return. When a permutation is requested
     * this instance is provided and the next one is computed.
     */
    private List<E> nextPermutation;

    /**
     * Standard constructor for this class.
     * @param collection  the collection to generate permutations for
     * @throws NullPointerException if coll is null
     */
    public PermutationIterator(final Collection<? extends E> collection) {
        Objects.requireNonNull(collection, "collection");
        keys = new int[collection.size()];
        direction = new boolean[collection.size()];
        Arrays.fill(direction, false);
        int value = 1;
        objectMap = new HashMap<>();
        for (final E e : collection) {
            objectMap.put(Integer.valueOf(value), e);
            keys[value - 1] = value;
            value++;
        }
        nextPermutation = new ArrayList<>(collection);
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public List<E> next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
