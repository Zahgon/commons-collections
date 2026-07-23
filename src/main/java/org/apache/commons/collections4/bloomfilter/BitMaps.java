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

/**
 * Contains functions to convert {@code int} indices into Bloom filter bit positions and visa versa.
 *
 * <p>The functions view an array of longs as a collection of bit maps each containing 64 bits. The bits are arranged
 * in memory as a little-endian long value. This matches the requirements of the BitMapExtractor interface.</p>
 *
 * @since 4.5.0-M2
 */
public class BitMaps {

    /**
     * A bit shift to apply to an integer to divided by 64 (2^6).
     */
    private static final int DIVIDE_BY_64 = 6;

    public static boolean contains(final long[] bitMaps, final int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static long getLongBit(final int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int getLongIndex(final int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int mod(final long dividend, final int divisor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static long[] newBitMap(final int numberOfBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static long[] newBitMap(final Shape shape) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int numberOfBitMaps(final int numberOfBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static int numberOfBitMaps(final Shape shape) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void set(final long[] bitMaps, final int bitIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Do not instantiate.
     */
    private BitMaps() {
    }
}
