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

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.collections4.IteratorUtils;

/**
 * Extends Iterator functionality to include operations commonly found on streams (for example filtering, concatenating, mapping). It also provides convenience methods
 * for common operations.
 *
 * @param <T> The type of object returned from the iterator.
 * @since 4.5.0-M3
 */
public final class ExtendedIterator<T> implements IteratorOperations<T> {

    public static <T> ExtendedIterator<T> create(final Iterator<T> it) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> ExtendedIterator<T> create(final Stream<T> stream) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> ExtendedIterator<T> createNoRemove(final Iterator<T> it) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static ExtendedIterator<?> emptyIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> ExtendedIterator<T> flatten(final Iterator<Iterator<T>> iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set to <code>true</code> if this wrapping doesn't permit the use of {@link #remove()}, otherwise removal is delegated to the base iterator.
     */
    private final boolean throwOnRemove;

    /**
     * The base iterator that we wrap
     */
    private final Iterator<? extends T> base;

    /**
     * Initialize this wrapping with the given base iterator and remove-control.
     *
     * @param base          the base iterator that this iterator wraps
     * @param throwOnRemove true if .remove() must throw an exception
     */
    private ExtendedIterator(final Iterator<? extends T> base, final boolean throwOnRemove) {
        this.base = base;
        this.throwOnRemove = throwOnRemove;
    }

    public <X extends T> ExtendedIterator<T> andThen(final Iterator<X> other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ExtendedIterator<T> filter(final Predicate<T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void forEachRemaining(final Consumer<? super T> action) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public <U> ExtendedIterator<U> map(final Function<T, U> function) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public T next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
