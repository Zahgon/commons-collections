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
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/**
 * A bloom filter using an array of bit maps to track enabled bits. This is a standard implementation and should work well for most Bloom filters.
 *
 * @since 4.5.0-M1
 */
public final class SimpleBloomFilter implements BloomFilter<SimpleBloomFilter> {

    /**
     * The array of bit map longs that defines this Bloom filter. Will be null if the filter is empty.
     */
    private final long[] bitMap;

    /**
     * The Shape of this Bloom filter.
     */
    private final Shape shape;

    /**
     * The cardinality of this Bloom filter.
     */
    private int cardinality;

    /**
     * Creates an empty instance.
     *
     * @param shape The shape for the filter.
     */
    public SimpleBloomFilter(final Shape shape) {
        Objects.requireNonNull(shape, "shape");
        this.shape = shape;
        this.bitMap = BitMaps.newBitMap(shape);
        this.cardinality = 0;
    }

    /**
     * Copy constructor for {@code copy()} use.
     *
     * @param source
     */
    private SimpleBloomFilter(final SimpleBloomFilter source) {
        this.shape = source.shape;
        this.bitMap = source.bitMap.clone();
        this.cardinality = source.cardinality;
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
    public boolean contains(final IndexExtractor indexExtractor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public SimpleBloomFilter copy() {
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
    public boolean processBitMapPairs(final BitMapExtractor other, final LongBiPredicate func) {
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
