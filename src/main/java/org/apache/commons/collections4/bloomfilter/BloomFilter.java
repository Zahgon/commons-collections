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

import java.util.Objects;

/**
 * The interface that describes a Bloom filter.
 * <p>
 * <em>See implementation notes for {@link BitMapExtractor} and {@link IndexExtractor}.</em>
 * </p>
 *
 * @param <T> The BloomFilter type.
 * @see BitMapExtractor
 * @see IndexExtractor
 * @since 4.5.0-M1
 */
public interface BloomFilter<T extends BloomFilter<T>> extends IndexExtractor, BitMapExtractor {

    /**
     * The sparse characteristic used to determine the best method for matching: {@value}.
     * <p>
     * For `sparse` implementations the {@code forEachIndex(IntConsumer consumer)} method is more efficient. For non `sparse` implementations the
     * {@code forEachBitMap(LongConsumer consumer)} is more efficient. Implementers should determine if it is easier.
     * </p>
     */
    int SPARSE = 0x1;

    /**
     * Gets the cardinality (number of enabled bits) of this Bloom filter.
     *
     * <p>This is also known as the Hamming value or Hamming number.</p>
     *
     * @return the cardinality of this filter
     */
    int cardinality();

    // Query Operations
    /**
     * Gets the characteristics of the filter.
     * <p>
     * Characteristics are defined as bits within the characteristics integer.
     * </p>
     *
     * @return the characteristics for this bloom filter.
     */
    int characteristics();

    /**
     * Clears the filter to by resetting it to its initial, unpopulated state.
     */
    void clear();

    default boolean contains(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean contains(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean contains(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if this filter contains the indices specified IndexExtractor.
     *
     * <p>Specifically this returns {@code true} if this filter is enabled for all bit indexes
     * identified by the {@code IndexExtractor}.</p>
     *
     * @param indexExtractor the IndexExtractor to provide the indexes
     * @return {@code true} if this filter is enabled for all bits specified by the IndexExtractor
     */
    boolean contains(IndexExtractor indexExtractor);

    /**
     * Creates a new instance of this {@link BloomFilter} with the same properties as the current one.
     *
     * @return a copy of this {@link BloomFilter}.
     */
    T copy();

    // update operations
    default int estimateIntersection(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default int estimateN() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default int estimateUnion(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the shape that was used when the filter was built.
     * @return The shape the filter was built with.
     */
    Shape getShape();

    // Counting Operations
    default boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean isFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Merges the specified hasher into this Bloom filter. Specifically all
     * bit indexes that are identified by the {@code bitMapExtractor} will be enabled in this filter.
     *
     * <p><em>Note: This method should return {@code true} even if no additional bit indexes were
     * enabled. A {@code false} result indicates that this filter may or may not contain all the indexes
     * enabled in the {@code bitMapExtractor}.</em>  This state may occur in complex Bloom filter implementations like
     * counting Bloom filters.</p>
     *
     * @param bitMapExtractor The BitMapExtractor to merge.
     * @return true if the merge was successful
     * @throws IllegalArgumentException if bitMapExtractor sends illegal value.
     */
    boolean merge(BitMapExtractor bitMapExtractor);

    default boolean merge(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean merge(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Merges the specified IndexExtractor into this Bloom filter. Specifically all
     * bit indexes that are identified by the {@code indexExtractor} will be enabled in this filter.
     *
     * <p><em>Note: This method should return {@code true} even if no additional bit indexes were
     * enabled. A {@code false} result indicates that this filter may or may not contain all the indexes of
     * the {@code indexExtractor}.</em>  This state may occur in complex Bloom filter implementations like
     * counting Bloom filters.</p>
     *
     * @param indexExtractor The IndexExtractor to merge.
     * @return true if the merge was successful
     * @throws IllegalArgumentException if indexExtractor sends illegal value.
     */
    boolean merge(IndexExtractor indexExtractor);

    @Override
    default IndexExtractor uniqueIndices() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
