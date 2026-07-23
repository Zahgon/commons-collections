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
import java.util.TreeSet;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/**
 * A bloom filter using a TreeSet of integers to track enabled bits. This is a standard
 * implementation and should work well for most low cardinality Bloom filters.
 *
 * @since 4.5.0-M1
 */
public final class SparseBloomFilter implements BloomFilter<SparseBloomFilter> {

    /**
     * The bitSet that defines this BloomFilter.
     */
    private final TreeSet<Integer> indices;

    /**
     * The shape of this BloomFilter.
     */
    private final Shape shape;

    /**
     * Constructs an empty BitSetBloomFilter.
     *
     * @param shape The shape of the filter.
     */
    public SparseBloomFilter(final Shape shape) {
        Objects.requireNonNull(shape, "shape");
        this.shape = shape;
        this.indices = new TreeSet<>();
    }

    private SparseBloomFilter(final SparseBloomFilter source) {
        shape = source.shape;
        indices = new TreeSet<>(source.indices);
    }

    /**
     * Adds the index to the indices.
     *
     * @param idx the index to add.
     * @return {@code true} always
     */
    private boolean add(final int idx) {
        indices.add(idx);
        return true;
    }

    @Override
    public long[] asBitMapArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int cardinality() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int characteristics() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SparseBloomFilter copy() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Shape getShape() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean merge(final BitMapExtractor bitMapExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean merge(final BloomFilter<?> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean merge(final Hasher hasher) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean merge(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean processBitMaps(final LongPredicate consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean processIndices(final IntPredicate consumer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
