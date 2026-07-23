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
package org.apache.commons.collections4.trie.analyzer;

import org.apache.commons.collections4.trie.KeyAnalyzer;

/**
 * An {@link KeyAnalyzer} for {@link String}s.
 * <p>
 * This class is stateless.
 * </p>
 * @since 4.0
 */
public class StringKeyAnalyzer extends KeyAnalyzer<String> {

    private static final long serialVersionUID = -7032449491269434877L;

    /**
     * A singleton instance of {@link StringKeyAnalyzer}.
     */
    public static final StringKeyAnalyzer INSTANCE = new StringKeyAnalyzer();

    /**
     * The number of bits per {@link Character}.
     */
    public static final int LENGTH = Character.SIZE;

    /**
     * A bit mask where the first bit is 1 and the others are zero.
     */
    private static final int MSB = 0x8000;

    /**
     * Returns a bit mask where the given bit is set.
     */
    private static int mask(final int bit) {
        return MSB >>> bit;
    }

    /**
     * Constructs a new instance.
     *
     * @deprecated Use {@link #INSTANCE}.
     */
    @Deprecated
    public StringKeyAnalyzer() {
        // empty
    }

    @Override
    public int bitIndex(final String key, final int offsetInBits, final int lengthInBits, final String other, final int otherOffsetInBits, final int otherLengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int bitsPerElement() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isBitSet(final String key, final int bitIndex, final int lengthInBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isPrefix(final String prefix, final int offsetInBits, final int lengthInBits, final String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int lengthInBits(final String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
