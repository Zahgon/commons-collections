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

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.BoundedIterator;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.IteratorEnumeration;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
import org.apache.commons.collections4.iterators.LoopingIterator;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.apache.commons.collections4.iterators.ObjectArrayListIterator;
import org.apache.commons.collections4.iterators.ObjectGraphIterator;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.apache.commons.collections4.iterators.PushbackIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.iterators.SingletonListIterator;
import org.apache.commons.collections4.iterators.SkippingIterator;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.ZippingIterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Provides static utility methods and decorators for {@link Iterator}
 * instances. The implementations are provided in the iterators subpackage.
 *
 * @since 2.1
 */
public class IteratorUtils {

    // validation is done in this class in certain cases because the
    // public classes allow invalid states
    /**
     * An iterator over no elements.
     */
    @SuppressWarnings("rawtypes")
    public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;

    /**
     * A list iterator over no elements.
     */
    @SuppressWarnings("rawtypes")
    public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;

    /**
     * An ordered iterator over no elements.
     */
    @SuppressWarnings("rawtypes")
    public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;

    /**
     * A map iterator over no elements.
     */
    @SuppressWarnings("rawtypes")
    public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;

    /**
     * An ordered map iterator over no elements.
     */
    @SuppressWarnings("rawtypes")
    public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;

    /**
     * Default delimiter used to delimit elements while converting an Iterator
     * to its String representation.
     */
    private static final String DEFAULT_TOSTRING_DELIMITER = ", ";

    private static <E, C extends Collection<E>> C addAll(final Iterator<? extends E> iterator, final C list) {
        Objects.requireNonNull(iterator, "iterator");
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> ResettableIterator<E> arrayIterator(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> arrayIterator(final E[] array, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> arrayIterator(final E[] array, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> arrayIterator(final Object array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> arrayIterator(final Object array, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> arrayIterator(final Object array, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final E... array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final E[] array, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final E[] array, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final Object array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final Object array, final int start) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> arrayListIterator(final Object array, final int start, final int end) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Enumeration<E> asEnumeration(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> asIterable(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> asIterator(final Enumeration<? extends E> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> asIterator(final Enumeration<? extends E> enumeration, final Collection<? super E> removeCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterable<E> asMultipleUseIterable(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // Bounded
    public static <E> BoundedIterator<E> boundedIterator(final Iterator<? extends E> iterator, final long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> BoundedIterator<E> boundedIterator(final Iterator<? extends E> iterator, final long offset, final long max) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> chainedIterator(final Collection<? extends Iterator<? extends E>> iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> chainedIterator(final Iterator<? extends E>... iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> chainedIterator(final Iterator<? extends E> iterator1, final Iterator<? extends E> iterator2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> chainedIterator(final Iterator<? extends Iterator<? extends E>> iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Collection<Iterator<? extends E>> iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Iterator<? extends E>... iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> collatedIterator(final Comparator<? super E> comparator, final Iterator<? extends E> iterator1, final Iterator<? extends E> iterator2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean contains(final Iterator<E> iterator, final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> emptyIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> emptyListIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> MapIterator<K, V> emptyMapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> OrderedIterator<E> emptyOrderedIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> OrderedMapIterator<K, V> emptyOrderedMapIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> filteredIterator(final Iterator<? extends E> iterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListIterator<E> filteredListIterator(final ListIterator<? extends E> listIterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E find(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Finds the first element in the given iterator which matches the given predicate.
     * <p>
     * A {@code null} or empty iterator returns {@code defaultValue}.
     * </p>
     *
     * @param <E>          the element type.
     * @param iterator     the iterator to search, may be null.
     * @param predicate    the predicate to use, must not be null.
     * @param defaultValue the default value, may be null.
     * @return the first element of the iterator which matches the predicate or null if none could be found.
     * @throws NullPointerException if predicate is null.
     */
    private static <E> E find(final Iterator<E> iterator, final Predicate<? super E> predicate, final E defaultValue) {
        Objects.requireNonNull(predicate, "predicate");
        if (iterator != null) {
            while (iterator.hasNext()) {
                final E element = iterator.next();
                if (predicate.test(element)) {
                    return element;
                }
            }
        }
        return defaultValue;
    }

    public static <E> E first(final Iterator<E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> void forEach(final Iterator<E> iterator, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E forEachButLast(final Iterator<E> iterator, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E get(final Iterator<E> iterator, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static <E> E get(final Iterator<E> iterator, final int index, final IntFunction<E> defaultSupplier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Iterator<?> getIterator(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int indexOf(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmpty(final Iterator<?> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> loopingIterator(final Collection<? extends E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableListIterator<E> loopingListIterator(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean matchesAll(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean matchesAny(final Iterator<E> iterator, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static NodeListIterator nodeListIterator(final Node node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static NodeListIterator nodeListIterator(final NodeList nodeList) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> objectGraphIterator(final E root, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> peekingIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> pushbackIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ResettableIterator<E> singletonIterator(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListIterator<E> singletonListIterator(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int size(final Iterator<?> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> SkippingIterator<E> skippingIterator(final Iterator<E> iterator, final long offset) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Stream<E> stream(final Iterable<E> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Stream<E> stream(final Iterator<E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object[] toArray(final Iterator<?> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> E[] toArray(final Iterator<? extends E> iterator, final Class<E> arrayClass) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> toList(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> toList(final Iterator<? extends E> iterator, final int estimatedSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListIterator<E> toListIterator(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> toSet(final Iterator<? extends E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Set<E> toSet(final Iterator<? extends E> iterator, final int estimatedSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterator<E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterator<E> iterator, final Transformer<? super E, String> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> String toString(final Iterator<E> iterator, final Transformer<? super E, String> transformer, final String delimiter, final String prefix, final String suffix) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Iterator<O> transformedIterator(final Iterator<? extends I> iterator, final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Iterator<E> unmodifiableIterator(final Iterator<E> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ListIterator<E> unmodifiableListIterator(final ListIterator<E> listIterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(final MapIterator<K, V> mapIterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E>... iterators) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> ZippingIterator<E> zippingIterator(final Iterator<? extends E> a, final Iterator<? extends E> b, final Iterator<? extends E> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private IteratorUtils() {
        // empty
    }
}
