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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableBoundedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.PermutationIterator;

/**
 * Provides utility methods and decorators for {@link Collection} instances.
 * <p>
 * Various utility methods might put the input objects into a Set/Map/Bag. In case
 * the input objects override {@link Object#equals(Object)}, it is mandatory that
 * the general contract of the {@link Object#hashCode()} method is maintained.
 * </p>
 * <p>
 * NOTE: From 4.0, method parameters will take {@link Iterable} objects when possible.
 * </p>
 *
 * @since 1.0
 */
public class CollectionUtils {

    /**
     * Helper class to easily access cardinality properties of two collections.
     * @param <O>  the element type
     */
    private static class CardinalityHelper<O> {

        static boolean equals(final Collection<?> a, final Collection<?> b) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Contains the cardinality for each object in collection A.
         */
        final Bag<O> cardinalityA;

        /**
         * Contains the cardinality for each object in collection B.
         */
        final Bag<O> cardinalityB;

        /**
         * Creates a new CardinalityHelper for two collections.
         *
         * @param a  the first collection
         * @param b  the second collection
         */
        CardinalityHelper(final Iterable<? extends O> a, final Iterable<? extends O> b) {
            cardinalityA = new HashBag<>(a);
            cardinalityB = new HashBag<>(b);
        }

        public int freqA(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public int freqB(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private int getFreq(final Object key, final Bag<?> freqMap) {
            return freqMap.getCount(key);
        }

        public final int max(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public final int min(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Wraps another object and uses the provided Equator to implement
     * {@link #equals(Object)} and {@link #hashCode()}.
     * <p>
     * This class can be used to store objects into a Map.
     * </p>
     *
     * @param <O>  the element type
     * @since 4.0
     */
    private static final class EquatorWrapper<O> {

        private final Equator<? super O> equator;

        private final O object;

        EquatorWrapper(final Equator<? super O> equator, final O object) {
            this.equator = equator;
            this.object = object;
        }

        @Override
        public boolean equals(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public O getObject() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Helper class for set-related operations, for example union, subtract, intersection.
     * @param <O>  the element type
     */
    private static final class SetOperationCardinalityHelper<O> extends CardinalityHelper<O> implements Iterable<O> {

        /**
         * Contains the unique elements of the two collections.
         */
        private final Set<O> elements;

        /**
         * Output collection.
         */
        private final List<O> newList;

        /**
         * Create a new set operation helper from the two collections.
         * @param a  the first collection
         * @param b  the second collection
         */
        SetOperationCardinalityHelper(final Iterable<? extends O> a, final Iterable<? extends O> b) {
            super(a, b);
            elements = new HashSet<>();
            addAll(elements, a);
            addAll(elements, b);
            // the resulting list must contain at least each unique element, but may grow
            newList = new ArrayList<>(elements.size());
        }

        @Override
        public Iterator<O> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Collection<O> list() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setCardinality(final O obj, final int count) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The index value when an element is not found in a collection or array: {@code -1}.
     *
     * @since 4.5.0-M1
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * Default prefix used while converting an Iterator to its String representation.
     *
     * @since 4.5.0-M1
     */
    public static final String DEFAULT_TOSTRING_PREFIX = "[";

    /**
     * Default suffix used while converting an Iterator to its String representation.
     *
     * @since 4.5.0-M1
     */
    public static final String DEFAULT_TOSTRING_SUFFIX = "]";

    /**
     * A String for Colon  (":").
     *
     * @since 4.5.0-M1
     */
    public static final String COLON = ":";

    /**
     * A String for Comma (",").
     *
     * @since 4.5.0-M1
     */
    public static final String COMMA = ",";

    /**
     * An empty unmodifiable collection.
     * The JDK provides empty Set and List implementations which could be used for
     * this purpose. However they could be cast to Set or List which might be
     * undesirable. This implementation only implements Collection.
     */
    // we deliberately use the raw type here
    @SuppressWarnings("rawtypes")
    public static final Collection EMPTY_COLLECTION = Collections.emptyList();

    public static <C> boolean addAll(final Collection<C> collection, final C... elements) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <C> boolean addAll(final Collection<C> collection, final Enumeration<? extends C> enumeration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <C> boolean addAll(final Collection<C> collection, final Iterable<? extends C> iterable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <C> boolean addAll(final Collection<C> collection, final Iterator<? extends C> iterator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean addIgnoreNull(final Collection<T> collection, final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of occurrences of <em>obj</em> in <em>coll</em>.
     *
     * @param obj the object to find the cardinality of
     * @param collection the {@link Iterable} to search
     * @param <O> the type of object that the {@link Iterable} may contain.
     * @return the number of occurrences of obj in coll
     * @throws NullPointerException if collection is null
     * @deprecated since 4.1, use {@link IterableUtils#frequency(Iterable, Object)} instead.
     *   Be aware that the order of parameters has changed.
     */
    @Deprecated
    public static <O> int cardinality(final O obj, final Iterable<? super O> collection) {
        return IterableUtils.frequency(Objects.requireNonNull(collection, "collection"), obj);
    }

    static void checkIndexBounds(final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O extends Comparable<? super O>> List<O> collate(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O extends Comparable<? super O>> List<O> collate(final Iterable<? extends O> a, final Iterable<? extends O> b, final boolean includeDuplicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> List<O> collate(final Iterable<? extends O> a, final Iterable<? extends O> b, final Comparator<? super O> c) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> List<O> collate(final Iterable<? extends O> iterableA, final Iterable<? extends O> iterableB, final Comparator<? super O> comparator, final boolean includeDuplicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O, R extends Collection<? super O>> R collect(final Iterable<? extends I> inputCollection, final Transformer<? super I, ? extends O> transformer, final R outputCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Collection<O> collect(final Iterable<I> inputCollection, final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O, R extends Collection<? super O>> R collect(final Iterator<? extends I> inputIterator, final Transformer<? super I, ? extends O> transformer, final R outputCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Collection<O> collect(final Iterator<I> inputIterator, final Transformer<? super I, ? extends O> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean containsAll(final Collection<?> coll1, final Collection<?> coll2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean containsAny(final Collection<?> coll1, final Collection<?> coll2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean containsAny(final Collection<?> coll1, @SuppressWarnings("unchecked") final T... coll2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Counts the number of elements in the input collection that match the
     * predicate.
     * <p>
     * A {@code null} collection or predicate matches no elements.
     * </p>
     *
     * @param <C>  the type of object the {@link Iterable} contains
     * @param input  the {@link Iterable} to get the input from, may be null
     * @param predicate  the predicate to use, may be null
     * @return the number of matches for the predicate in the collection
     * @deprecated since 4.1, use {@link IterableUtils#countMatches(Iterable, Predicate)} instead
     */
    @Deprecated
    public static <C> int countMatches(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate == null ? 0 : (int) IterableUtils.countMatches(input, predicate);
    }

    public static <O> Collection<O> disjunction(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // OK, empty collection is compatible with any type
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> emptyCollection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Answers true if a predicate is true for at least one element of a
     * collection.
     * <p>
     * A {@code null} collection or predicate returns false.
     * </p>
     *
     * @param <C>  the type of object the {@link Iterable} contains
     * @param input  the {@link Iterable} to get the input from, may be null
     * @param predicate  the predicate to use, may be null
     * @return true if at least one element of the collection matches the predicate
     * @deprecated since 4.1, use {@link IterableUtils#matchesAny(Iterable, Predicate)} instead
     */
    @Deprecated
    public static <C> boolean exists(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAny(input, predicate);
    }

    public static <E> E extractSingleton(final Collection<E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean filter(final Iterable<T> collection, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> boolean filterInverse(final Iterable<T> collection, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Finds the first element in the given collection which matches the given predicate.
     * <p>
     * If the input collection or predicate is null, or no element of the collection
     * matches the predicate, null is returned.
     * </p>
     *
     * @param <T>  the type of object the {@link Iterable} contains
     * @param collection  the collection to search, may be null
     * @param predicate  the predicate to use, may be null
     * @return the first element of the collection which matches the predicate or null if none could be found
     * @deprecated since 4.1, use {@link IterableUtils#find(Iterable, Predicate)} instead
     */
    @Deprecated
    public static <T> T find(final Iterable<T> collection, final Predicate<? super T> predicate) {
        return predicate != null ? IterableUtils.find(collection, predicate) : null;
    }

    /**
     * Executes the given closure on each but the last element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * </p>
     *
     * @param <T>  the type of object the {@link Iterable} contains
     * @param <C>  the closure type
     * @param collection  the collection to get the input from, may be null
     * @param closure  the closure to perform, may be null
     * @return the last element in the collection, or null if either collection or closure is null
     * @since 4.0
     * @deprecated since 4.1, use {@link IterableUtils#forEachButLast(Iterable, Closure)} instead
     */
    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(final Iterable<T> collection, final C closure) {
        return closure != null ? IterableUtils.forEachButLast(collection, closure) : null;
    }

    /**
     * Executes the given closure on each but the last element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * </p>
     *
     * @param <T>  the type of object the {@link Collection} contains
     * @param <C>  the closure type
     * @param iterator  the iterator to get the input from, may be null
     * @param closure  the closure to perform, may be null
     * @return the last element in the collection, or null if either iterator or closure is null
     * @since 4.0
     * @deprecated since 4.1, use {@link IteratorUtils#forEachButLast(Iterator, Closure)} instead
     */
    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(final Iterator<T> iterator, final C closure) {
        return closure != null ? IteratorUtils.forEachButLast(iterator, closure) : null;
    }

    /**
     * Executes the given closure on each element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * </p>
     *
     * @param <T>  the type of object the {@link Iterable} contains
     * @param <C>  the closure type
     * @param collection  the collection to get the input from, may be null
     * @param closure  the closure to perform, may be null
     * @return closure
     * @deprecated since 4.1, use {@link IterableUtils#forEach(Iterable, Closure)} instead
     */
    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(final Iterable<T> collection, final C closure) {
        if (closure != null) {
            IterableUtils.forEach(collection, closure);
        }
        return closure;
    }

    /**
     * Executes the given closure on each element in the collection.
     * <p>
     * If the input collection or closure is null, there is no change made.
     * </p>
     *
     * @param <T>  the type of object the {@link Iterator} contains
     * @param <C>  the closure type
     * @param iterator  the iterator to get the input from, may be null
     * @param closure  the closure to perform, may be null
     * @return closure
     * @since 4.0
     * @deprecated since 4.1, use {@link IteratorUtils#forEach(Iterator, Closure)} instead
     */
    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(final Iterator<T> iterator, final C closure) {
        if (closure != null) {
            IteratorUtils.forEach(iterator, closure);
        }
        return closure;
    }

    /**
     * Gets the {@code index}-th value in the {@code iterable}'s {@link Iterator}, throwing
     * {@code IndexOutOfBoundsException} if there is no such element.
     * <p>
     * If the {@link Iterable} is a {@link List}, then it will use {@link List#get(int)}.
     * </p>
     *
     * @param iterable  the {@link Iterable} to get a value from
     * @param index  the index to get
     * @param <T> the type of object in the {@link Iterable}.
     * @return the object at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     * @deprecated since 4.1, use {@code IterableUtils.get(Iterable, int)} instead
     */
    @Deprecated
    public static <T> T get(final Iterable<T> iterable, final int index) {
        Objects.requireNonNull(iterable, "iterable");
        return IterableUtils.get(iterable, index);
    }

    /**
     * Gets the {@code index}-th value in {@link Iterator}, throwing
     * {@code IndexOutOfBoundsException} if there is no such element.
     * <p>
     * The Iterator is advanced to {@code index} (or to the end, if
     * {@code index} exceeds the number of entries) as a side effect of this method.
     * </p>
     *
     * @param iterator  the iterator to get a value from
     * @param index  the index to get
     * @param <T> the type of object in the {@link Iterator}
     * @return the object at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     * @throws IllegalArgumentException if the object type is invalid
     * @throws NullPointerException if iterator is null
     * @deprecated since 4.1, use {@code IteratorUtils.get(Iterator, int)} instead
     */
    @Deprecated
    public static <T> T get(final Iterator<T> iterator, final int index) {
        Objects.requireNonNull(iterator, "iterator");
        return IteratorUtils.get(iterator, index);
    }

    public static <K, V> Map.Entry<K, V> get(final Map<K, V> map, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Object get(final Object object, final int index) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Map<O, Integer> getCardinalityMap(final Iterable<? extends O> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int hashCode(final Collection<? extends E> collection, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> intersection(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEmpty(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> boolean isEqualCollection(final Collection<? extends E> a, final Collection<? extends E> b, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isFull(final Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isProperSubCollection(final Collection<?> a, final Collection<?> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isSubCollection(final Collection<?> a, final Collection<?> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Answers true if a predicate is true for every element of a
     * collection.
     *
     * <p>
     * A {@code null} predicate returns false.
     * </p>
     * <p>
     * A {@code null} or empty collection returns true.
     * </p>
     *
     * @param <C>  the type of object the {@link Iterable} contains
     * @param input  the {@link Iterable} to get the input from, may be null
     * @param predicate  the predicate to use, may be null
     * @return true if every element of the collection matches the predicate or if the
     * collection is empty, false otherwise
     * @since 4.0
     * @deprecated since 4.1, use {@link IterableUtils#matchesAll(Iterable, Predicate)} instead
     */
    @Deprecated
    public static <C> boolean matchesAll(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAll(input, predicate);
    }

    public static int maxSize(final Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<List<E>> permutations(final Collection<E> collection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <C> Collection<C> predicatedCollection(final Collection<C> collection, final Predicate<? super C> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> removeAll(final Collection<E> collection, final Collection<?> remove) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> removeAll(final Iterable<E> collection, final Iterable<? extends E> remove, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> removeCount(final Collection<E> input, int startIndex, int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> removeRange(final Collection<E> input, final int startIndex, final int endIndex) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <C> Collection<C> retainAll(final Collection<C> collection, final Collection<?> retain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> retainAll(final Iterable<E> collection, final Iterable<? extends E> retain, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static void reverseArray(final Object[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> select(final Iterable<? extends O> inputCollection, final Predicate<? super O> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O, R extends Collection<? super O>> R select(final Iterable<? extends O> inputCollection, final Predicate<? super O> predicate, final R outputCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O, R extends Collection<? super O>> R select(final Iterable<? extends O> inputCollection, final Predicate<? super O> predicate, final R outputCollection, final R rejectedCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> selectRejected(final Iterable<? extends O> inputCollection, final Predicate<? super O> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O, R extends Collection<? super O>> R selectRejected(final Iterable<? extends O> inputCollection, final Predicate<? super O> predicate, final R outputCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int size(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean sizeIsEmpty(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> subtract(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> subtract(final Iterable<? extends O> a, final Iterable<? extends O> b, final Predicate<O> p) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a synchronized collection backed by the given collection.
     * <p>
     * You must manually synchronize on the returned buffer's iterator to
     * avoid non-deterministic behavior:
     * </p>
     * <pre>
     * Collection c = CollectionUtils.synchronizedCollection(myCollection);
     * synchronized (c) {
     *     Iterator i = c.iterator();
     *     while (i.hasNext()) {
     *         process (i.next());
     *     }
     * }
     * </pre>
     * <p>
     * This method uses the implementation in the decorators subpackage.
     * </p>
     *
     * @param <C>  the type of object the {@link Collection} contains
     * @param collection  the collection to synchronize, must not be null
     * @return a synchronized collection backed by the given collection
     * @throws NullPointerException if the collection is null
     * @deprecated since 4.1, use {@link java.util.Collections#synchronizedCollection(Collection)} instead
     */
    @Deprecated
    public static <C> Collection<C> synchronizedCollection(final Collection<C> collection) {
        Objects.requireNonNull(collection, "collection");
        return SynchronizedCollection.synchronizedCollection(collection);
    }

    public static <C> void transform(final Collection<C> collection, final Transformer<? super C, ? extends C> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Collection<E> transformingCollection(final Collection<E> collection, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <O> Collection<O> union(final Iterable<? extends O> a, final Iterable<? extends O> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an unmodifiable collection backed by the given collection.
     * <p>
     * This method uses the implementation in the decorators subpackage.
     * </p>
     *
     * @param <C>  the type of object the {@link Collection} contains
     * @param collection  the collection to make unmodifiable, must not be null
     * @return an unmodifiable collection backed by the given collection
     * @throws NullPointerException if the collection is null
     * @deprecated since 4.1, use {@link java.util.Collections#unmodifiableCollection(Collection)} instead
     */
    @Deprecated
    public static <C> Collection<C> unmodifiableCollection(final Collection<? extends C> collection) {
        Objects.requireNonNull(collection, "collection");
        return UnmodifiableCollection.unmodifiableCollection(collection);
    }

    /**
     * Don't allow instances.
     */
    private CollectionUtils() {
        // empty
    }
}
