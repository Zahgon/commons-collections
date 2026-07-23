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

import java.util.Arrays;
import java.util.Objects;
import java.util.function.LongPredicate;

/**
 * Produces bit map longs for a Bloom filter.
 * <p>
 * Each bit map is a little-endian long value representing a block of bits of in a filter.
 * </p>
 * <p>
 * The returned array will have length {@code ceil(m / 64)} where {@code m} is the number of bits in the filter and {@code ceil} is the ceiling function. Bits
 * 0-63 are in the first long. A value of 1 at a bit position indicates the bit index is enabled.
 * </p>
 * <p>
 * <em>The default implementations of the {@code makePredicate()} and {@code asBitMapArray} methods are slow and should be reimplemented in the implementing
 * classes where possible.</em>
 * </p>
 *
 * @since 4.5.0-M2
 */
@FunctionalInterface
public interface BitMapExtractor {

    static BitMapExtractor fromBitMapArray(final long... bitMaps) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static BitMapExtractor fromIndexExtractor(final IndexExtractor extractor, final int numberOfBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default long[] asBitMapArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean processBitMapPairs(final BitMapExtractor other, final LongBiPredicate func) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Each bit map is passed to the predicate in order. The predicate is applied to each
     * bit map value, if the predicate returns {@code false} the execution is stopped, {@code false}
     * is returned, and no further bit maps are processed.
     *
     * <p>If the extractor is empty this method will return true.</p>
     *
     * <p>Any exceptions thrown by the action are relayed to the caller.</p>
     *
     * @param predicate the function to execute
     * @return {@code true} if all bit maps returned {@code true}, {@code false} otherwise.
     * @throws NullPointerException if the specified consumer is null
     */
    boolean processBitMaps(LongPredicate predicate);
}
