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

import java.util.function.LongBinaryOperator;

/**
 * Implementations of set operations on BitMapExtractors.
 *
 * @since 4.5.0-M1
 */
public final class SetOperations {

    public static int andCardinality(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int cardinality(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Calculates the cardinality of the result of a LongBinaryOperator using the {@code BitMapExtractor.makePredicate} method.
     *
     * @param first  the first BitMapExtractor
     * @param second the second BitMapExtractor
     * @param op     a long binary operation on where x = {@code first} and y = {@code second} bitmap extractors.
     * @return the calculated cardinality.
     */
    private static int cardinality(final BitMapExtractor first, final BitMapExtractor second, final LongBinaryOperator op) {
        final int[] cardinality = new int[1];
        first.processBitMapPairs(second, (x, y) -> {
            cardinality[0] += Long.bitCount(op.applyAsLong(x, y));
            return true;
        });
        return cardinality[0];
    }

    public static double cosineDistance(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static double cosineSimilarity(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static double cosineSimilarity(final BloomFilter<?> first, final BloomFilter<?> second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int hammingDistance(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static double jaccardDistance(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static double jaccardSimilarity(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int orCardinality(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int xorCardinality(final BitMapExtractor first, final BitMapExtractor second) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Do not instantiate.
     */
    private SetOperations() {
    }
}
