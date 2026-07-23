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
 * The interface that describes a Bloom filter that associates a count with each
 * bit index rather than a bit.  This allows reversal of merge operations with
 * remove operations.
 *
 * <p>A counting Bloom filter is expected to function identically to a standard
 * Bloom filter that is the merge of all the Bloom filters that have been added
 * to and not later subtracted from the counting Bloom filter. The functional
 * state of a CountingBloomFilter at the start and end of a series of merge and
 * subsequent remove operations of the same Bloom filters, irrespective of
 * remove order, is expected to be the same.</p>
 *
 * <p>Removal of a filter that has not previously been merged results in an
 * invalid state where the cells no longer represent a sum of merged Bloom
 * filters. It is impossible to validate merge and remove exactly without
 * explicitly storing all filters. Consequently such an operation may go
 * undetected. The CountingBloomFilter maintains a state flag that is used as a
 * warning that an operation was performed that resulted in invalid cells and
 * thus an invalid state. For example this may occur if a cell for an index was
 * set to negative following a remove operation.</p>
 *
 * <p>Implementations should document the expected state of the filter after an
 * operation that generates invalid cells, and any potential recovery options.
 * An implementation may support a reversal of the operation to restore the
 * state to that prior to the operation. In the event that invalid cells are
 * adjusted to a valid range then it should be documented if there has been
 * irreversible information loss.</p>
 *
 * <p>Implementations may choose to throw an exception during an operation that
 * generates invalid cells. Implementations should document the expected state
 * of the filter after such an operation. For example are the cells not updated,
 * partially updated or updated entirely before the exception is raised.</p>
 *
 * @see CellExtractor
 * @since 4.5.0-M1
 */
public interface CountingBloomFilter extends BloomFilter<CountingBloomFilter>, CellExtractor {

    // Query Operations
    /**
     * Adds the specified CellExtractor to this Bloom filter.
     *
     * <p>Specifically
     * all cells for the indexes identified by the {@code other} will be incremented
     * by their corresponding values in the {@code other}.</p>
     *
     * <p>This method will return {@code true} if the filter is valid after the operation.</p>
     *
     * @param other the CellExtractor to add.
     * @return {@code true} if the addition was successful and the state is valid
     * @see #isValid()
     * @see #subtract(CellExtractor)
     */
    boolean add(CellExtractor other);

    /**
     * Gets the maximum allowable value for a cell count in this Counting filter.
     *
     * @return the maximum allowable value for a cell count in this Counting filter.
     */
    int getMaxCell();

    default int getMaxInsert(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default int getMaxInsert(final BloomFilter<?> bloomFilter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Determines the maximum number of times the Cell Extractor could have been added.
     *
     * @param cellExtractor the extractor of cells.
     * @return the maximum number of times the CellExtractor could have been inserted.
     */
    int getMaxInsert(CellExtractor cellExtractor);

    default int getMaxInsert(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default int getMaxInsert(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if the internal state is valid.
     *
     * <p>This flag is a warning that an addition or
     * subtraction of cells from this filter resulted in an invalid cell for one or more
     * indexes. For example this may occur if a cell for an index was
     * set to negative following a subtraction operation, or overflows the value specified by {@code getMaxCell()} following an
     * addition operation.</p>
     *
     * <p>A counting Bloom filter that has an invalid state is no longer ensured to function
     * identically to a standard Bloom filter instance that is the merge of all the Bloom filters
     * that have been added to and not later subtracted from this counting Bloom filter.</p>
     *
     * <p>Note: The change to an invalid state may or may not be reversible. Implementations
     * are expected to document their policy on recovery from an addition or removal operation
     * that generated an invalid state.</p>
     *
     * @return {@code true} if the state is valid
     */
    boolean isValid();

    @Override
    default boolean merge(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    default boolean merge(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    default boolean merge(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    default boolean merge(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean remove(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean remove(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean remove(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default boolean remove(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the specified CellExtractor to this Bloom filter.
     *
     * <p>Specifically
     * all cells for the indexes identified by the {@code other} will be decremented
     * by their corresponding values in the {@code other}.</p>
     *
     * <p>This method will return true if the filter is valid after the operation.</p>
     *
     * @param other the CellExtractor to subtract.
     * @return {@code true} if the subtraction was successful and the state is valid
     * @see #isValid()
     * @see #add(CellExtractor)
     */
    boolean subtract(CellExtractor other);

    @Override
    default IndexExtractor uniqueIndices() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
