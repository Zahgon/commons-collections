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
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Extends {@link Iterator} with additional default methods.
 *
 * @param <E> the type of elements returned by this iterator.
 * @since 4.5.0-M3
 */
public interface IteratorOperations<E> extends Iterator<E> {

    default <C extends Collection<E>> C addTo(final C collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default E removeNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default <C extends Collection<E>> C toCollection(final Supplier<C> collectionSupplier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default List<E> toList() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    default Set<E> toSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
