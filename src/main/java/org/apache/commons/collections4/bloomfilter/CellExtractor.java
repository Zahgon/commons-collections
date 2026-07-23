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

import java.util.TreeMap;
import java.util.function.IntPredicate;

/**
 * Some Bloom filter implementations use a count rather than a bit flag. The term {@code Cell} is used to
 * refer to these counts and their associated index.  This class is the equivalent of the index extractor except
 * that it produces cells.
 *
 * <p>Note that a CellExtractor must not return duplicate indices and must be ordered.</p>
 *
 * <p>Implementations must guarantee that:</p>
 *
 * <ul>
 * <li>The IndexExtractor implementation returns unique ordered indices.</li>
 * <li>The cells are produced in IndexExtractor order.</li>
 * <li>For every value produced by the IndexExtractor there will be only one matching
 * cell produced by the CellExtractor.</li>
 * <li>The CellExtractor will not generate cells with indices that are not output by the IndexExtractor.</li>
 * <li>The IndexExtractor will not generate indices that have a zero count for the cell.</li>
 * </ul>
 *
 * @since 4.5.0-M2
 */
@FunctionalInterface
public interface CellExtractor extends IndexExtractor {

    /**
     * Represents an operation that accepts an {@code <index, count>} pair.
     * Returns {@code true} if processing should continue, {@code false} otherwise.
     *
     * <p>Note: This is a functional interface as a specialization of
     * {@link java.util.function.BiPredicate} for {@code int}.</p>
     */
    @FunctionalInterface
    interface CellPredicate {

        /**
         * Performs an operation on the given {@code <index, count>} pair.
         *
         * @param index the bit index.
         * @param count the cell value at the specified bit index.
         * @return {@code true} if processing should continue, {@code false} if processing should stop.
         */
        boolean test(int index, int count);
    }

    static CellExtractor from(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Performs the given action for each {@code cell}  where the cell count is non-zero.
     *
     * <p>Some Bloom filter implementations use a count rather than a bit flag.  The term {@code Cell} is used to
     * refer to these counts.</p>
     *
     * <p>Any exceptions thrown by the action are relayed to the caller. The consumer is applied to each
     * cell. If the consumer returns {@code false} the execution is stopped, {@code false}
     * is returned, and no further pairs are processed.</p>
     *
     * @param consumer the action to be performed for each non-zero cell.
     * @return {@code true} if all cells return true from consumer, {@code false} otherwise.
     * @throws NullPointerException if the specified consumer is null
     */
    boolean processCells(CellPredicate consumer);

    @Override
    default boolean processIndices(final IntPredicate predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    default IndexExtractor uniqueIndices() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
