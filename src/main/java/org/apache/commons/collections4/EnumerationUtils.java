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
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.IteratorIterable;

/**
 * Provides utility methods for {@link Enumeration} instances.
 *
 * @since 3.0
 */
public class EnumerationUtils {

    public static <T> Iterable<T> asIterable(final Enumeration<T> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T get(final Enumeration<T> e, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> toList(final Enumeration<? extends E> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<String> toList(final StringTokenizer stringTokenizer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> toSet(final Enumeration<? extends E> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private EnumerationUtils() {
        // no instances.
    }
}
