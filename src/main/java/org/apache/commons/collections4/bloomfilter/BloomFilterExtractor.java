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
package org.apache.commons.collections4.bloomfilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Produces Bloom filters from a collection (for example, {@link LayeredBloomFilter}).
 *
 * @since 4.5.0-M2
 */
@FunctionalInterface
public interface BloomFilterExtractor {

    static <T extends BloomFilter<T>> BloomFilterExtractor fromBloomFilterArray(final BloomFilter<?>... filters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default BloomFilter[] asBloomFilterArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default BloomFilter flatten() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean processBloomFilterPair(final BloomFilterExtractor other, final BiPredicate<BloomFilter, BloomFilter> func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Executes a Bloom filter Predicate on each Bloom filter in the collection. The ordering of the Bloom filters is not specified by this interface.
     *
     * @param bloomFilterPredicate the predicate to evaluate each Bloom filter with.
     * @return {@code false} when the first filter fails the predicate test. Returns {@code true} if all filters pass the test.
     */
    boolean processBloomFilters(Predicate<BloomFilter> bloomFilterPredicate);
}
