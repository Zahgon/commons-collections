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
 * The definition of a Bloom filter shape.
 *
 * <p>This class contains the values for the filter configuration and is used to
 * convert a Hasher into a BloomFilter as well as verify that two Bloom filters are
 * compatible. (i.e. can be compared or merged)</p>
 *
 * <h2>Interrelatedness of values</h2>
 *
 * <dl>
 * <dt>Number of Items ({@code n})</dt>
 * <dd>{@code n = ceil(m / (-k / ln(1 - exp(ln(p) / k))))}</dd>
 * <dt>Probability of False Positives ({@code p})</dt>
 * <dd>{@code p = pow(1 - exp(-k / (m / n)), k)}</dd>
 * <dt>Number of Bits ({@code m})</dt>
 * <dd>{@code m = ceil((n * ln(p)) / ln(1 / pow(2, ln(2))))}</dd>
 * <dt>Number of Functions ({@code k})</dt>
 * <dd>{@code k = round((m / n) * ln(2))}</dd>
 * </dl>
 *
 * <h2>Estimations from cardinality based on shape</h2>
 *
 * <p>Several estimates can be calculated from the Shape and the cardinality of a Bloom filter.</p>
 *
 * <p>In the calculation below the following values are used:</p>
 * <ul>
 * <li>double c = the cardinality of the Bloom filter.</li>
 * <li>double m = numberOfBits as specified in the shape.</li>
 * <li>double k = numberOfHashFunctions as specified in the shape.</li>
 * </ul>
 *
 * <h3>Estimate N - n()</h3>
 *
 * <p>The calculation for the estimate of N is: {@code -(m/k) * ln(1 - (c/m))}.  This is the calculation
 * performed by the {@code Shape.estimateN(cardinality)} method below.  This estimate is roughly equivalent to the
 * number of hashers that have been merged into a filter to create the cardinality specified.</p>
 *
 * <p><em>Note:</em></p>
 * <ul>
 * <li>if cardinality == numberOfBits, then result is infinity.</li>
 * <li>if cardinality &gt; numberOfBits, then result is NaN.</li>
 * </ul>
 *
 * <h3>Estimate N of Union - n(A &cup; B)</h3>
 *
 * <p>To estimate the number of items in the union of two Bloom filters with the same shape, merge them together and
 * calculate the estimated N from the result.</p>
 *
 * <h3>Estimate N of the Intersection - n(A &cap; B)</h3>
 *
 * <p>To estimate the number of items in the intersection of two Bloom filters A and B with the same shape the calculation is:
 * n(A) + n(b) - n(A &cup; B).</p>
 *
 * <p>Care must be taken when any of the n(x) returns infinity.  In general the following assumptions are true:
 *
 * <ul>
 * <li>If n(A) = &infin; and n(B) &lt; &infin; then n(A &cap; B) = n(B)</li>
 * <li>If n(A) &lt; &infin; and n(B) = &infin; then n(A &cap; B) = n(A)</li>
 * <li>If n(A) = &infin; and n(B) = &infin; then n(A &cap; B) = &infin;</li>
 * <li>If n(A) &lt; &infin; and n(B) &lt; &infin; and n(A &cup; B) = &infin; then n(A &cap; B) is undefined.</li>
 * </ul>
 *
 * @see <a href="https://hur.st/bloomfilter">Bloom Filter calculator</a>
 * @see <a href="https://en.wikipedia.org/wiki/Bloom_filter">Bloom filter
 * [Wikipedia]</a>
 * @since 4.5.0-M1
 */
public final class Shape {

    /**
     * The natural logarithm of 2. Used in several calculations. Approximately 0.693147180559945.
     */
    private static final double LN_2 = Math.log(2.0);

    /**
     * ln(1 / 2^ln(2)). Used in calculating the number of bits. Approximately -0.480453013918201.
     *
     * <p>ln(1 / 2^ln(2)) = ln(1) - ln(2^ln(2)) = -ln(2) * ln(2)</p>
     */
    private static final double DENOMINATOR = -LN_2 * LN_2;

    /**
     * Calculates the number of hash functions given numberOfItems and numberOfBits.
     * This is a method so that the calculation is consistent across all constructors.
     *
     * @param numberOfItems the number of items in the filter.
     * @param numberOfBits the number of bits in the filter.
     * @return the optimal number of hash functions.
     * @throws IllegalArgumentException if the calculated number of hash function is {@code < 1}
     */
    private static int calculateNumberOfHashFunctions(final int numberOfItems, final int numberOfBits) {
        // k = round((m / n) * ln(2)) We change order so that we use real math rather
        // than integer math.
        final long k = Math.round(LN_2 * numberOfBits / numberOfItems);
        if (k < 1) {
            throw new IllegalArgumentException(String.format("Filter too small: Calculated number of hash functions (%s) was less than 1", k));
        }
        // Normally we would check that numberOfHashFunctions <= Integer.MAX_VALUE but
        // since numberOfBits is at most Integer.MAX_VALUE the numerator of
        // numberOfHashFunctions is ln(2) * Integer.MAX_VALUE = 646456992.9449 the
        // value of k cannot be above Integer.MAX_VALUE.
        return (int) k;
    }

    /**
     * Checks the calculated probability is {@code < 1.0}.
     *
     * <p>
     * This function is used to verify that the dynamically calculated probability for the Shape is in the valid range 0 to 1 exclusive. This need only be
     * performed once upon construction.
     * </p>
     *
     * @param probability the probability
     * @throws IllegalArgumentException if the probability is {@code >= 1.0}.
     */
    private static void checkCalculatedProbability(final double probability) {
        // We do not need to check for p <= 0.0 since we only allow positive values for
        // parameters and the closest we can come to exp(-kn/m) == 1 is
        // exp(-1/Integer.MAX_INT) approx 0.9999999995343387 so Math.pow(x, y) will
        // always be 0<x<1 and y>0
        if (probability >= 1.0) {
            throw new IllegalArgumentException("Calculated probability is greater than or equal to 1: " + probability);
        }
    }

    /**
     * Checks number of bits is strictly positive.
     *
     * @param numberOfBits the number of bits
     * @return the number of bits
     * @throws IllegalArgumentException if the number of bits is {@code < 1}.
     */
    private static int checkNumberOfBits(final int numberOfBits) {
        if (numberOfBits < 1) {
            throw new IllegalArgumentException("Number of bits must be greater than 0: " + numberOfBits);
        }
        return numberOfBits;
    }

    /**
     * Checks number of hash functions is strictly positive.
     *
     * @param numberOfHashFunctions the number of hash functions
     * @return the number of hash functions
     * @throws IllegalArgumentException if the number of hash functions is {@code < 1}.
     */
    private static int checkNumberOfHashFunctions(final int numberOfHashFunctions) {
        if (numberOfHashFunctions < 1) {
            throw new IllegalArgumentException("Number of hash functions must be greater than 0: " + numberOfHashFunctions);
        }
        return numberOfHashFunctions;
    }

    /**
     * Checks number of items is strictly positive.
     *
     * @param numberOfItems the number of items
     * @return the number of items
     * @throws IllegalArgumentException if the number of items is {@code < 1}.
     */
    private static int checkNumberOfItems(final int numberOfItems) {
        if (numberOfItems < 1) {
            throw new IllegalArgumentException("Number of items must be greater than 0: " + numberOfItems);
        }
        return numberOfItems;
    }

    /**
     * Checks the probability is in the range 0.0, exclusive, to 1.0, exclusive.
     *
     * @param probability the probability
     * @throws IllegalArgumentException if the probability is not in the range {@code (0, 1)}
     */
    private static void checkProbability(final double probability) {
        // Using the negation of within the desired range will catch NaN
        if (!(probability > 0.0 && probability < 1.0)) {
            throw new IllegalArgumentException("Probability must be greater than 0 and less than 1: " + probability);
        }
    }

    public static Shape fromKM(final int numberOfHashFunctions, final int numberOfBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Shape fromNM(final int numberOfItems, final int numberOfBits) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Shape fromNMK(final int numberOfItems, final int numberOfBits, final int numberOfHashFunctions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Shape fromNP(final int numberOfItems, final double probability) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Shape fromPMK(final double probability, final int numberOfBits, final int numberOfHashFunctions) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Number of hash functions to create a filter ({@code k}).
     */
    private final int numberOfHashFunctions;

    /**
     * Number of bits in the filter ({@code m}).
     */
    private final int numberOfBits;

    /**
     * Constructs a filter configuration with the specified number of hashFunctions ({@code k}) and
     * bits ({@code m}).
     *
     * @param numberOfHashFunctions Number of hash functions to use for each item placed in the filter.
     * @param numberOfBits The number of bits in the filter
     * @throws IllegalArgumentException if {@code numberOfHashFunctions < 1} or {@code numberOfBits < 1}
     */
    private Shape(final int numberOfHashFunctions, final int numberOfBits) {
        this.numberOfHashFunctions = checkNumberOfHashFunctions(numberOfHashFunctions);
        this.numberOfBits = checkNumberOfBits(numberOfBits);
    }

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public double estimateMaxN() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public double estimateN(final int cardinality) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getNumberOfBits() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getNumberOfHashFunctions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public double getProbability(final int numberOfItems) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isSparse(final int cardinality) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
