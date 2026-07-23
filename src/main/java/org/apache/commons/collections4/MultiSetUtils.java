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

import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.collections4.multiset.PredicatedMultiSet;
import org.apache.commons.collections4.multiset.SynchronizedMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;

/**
 * Provides utility methods and decorators for {@link MultiSet} instances.
 *
 * @since 4.1
 */
public class MultiSetUtils {

    /**
     * An empty unmodifiable multiset.
     */
    // OK, empty multiset is compatible with any type
    @SuppressWarnings("rawtypes")
    public static final MultiSet EMPTY_MULTISET = UnmodifiableMultiSet.unmodifiableMultiSet(new HashMultiSet<>());

    // OK, empty multiset is compatible with any type
    @SuppressWarnings("unchecked")
    public static <E> MultiSet<E> emptyMultiSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> MultiSet<E> predicatedMultiSet(final MultiSet<E> multiset, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> MultiSet<E> synchronizedMultiSet(final MultiSet<E> multiset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> MultiSet<E> unmodifiableMultiSet(final MultiSet<? extends E> multiset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private MultiSetUtils() {
        // empty
    }
}
