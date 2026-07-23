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

/**
 * <p>
 * Operations on arrays, primitive arrays (like {@code int[]}) and primitive wrapper arrays (like {@code Integer[]}).
 * </p>
 * <p>
 * This class tries to handle {@code null} input gracefully. An exception will not be thrown for a {@code null} array input. However, an Object array that
 * contains a {@code null} element may throw an exception. Each method documents its behavior.
 * </p>
 * <p>
 * Package private, might move to an internal package if this needs to be public.
 * </p>
 * <p>
 * #ThreadSafe#
 * </p>
 *
 * @since 4.2 (Copied from Apache Commons Lang.)
 */
final class ArrayUtils {

    static boolean contains(final Object[] array, final Object objectToFind) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static <T> int indexOf(final T[] array, final Object objectToFind) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private ArrayUtils() {
    }
}
