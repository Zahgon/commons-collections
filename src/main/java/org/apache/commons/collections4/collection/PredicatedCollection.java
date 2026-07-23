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
package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.apache.commons.collections4.multiset.PredicatedMultiSet;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.set.PredicatedSet;

/**
 * Decorates another {@link Collection} to validate that additions
 * match a specified predicate.
 * <p>
 * This collection exists to provide validation for the decorated collection.
 * It is normally created to decorate an empty collection.
 * If an object cannot be added to the collection, an IllegalArgumentException is thrown.
 * </p>
 * <p>
 * One usage would be to ensure that no null entries are added to the collection:
 * </p>
 * <pre>
 * Collection coll = PredicatedCollection.predicatedCollection(new ArrayList(), NotNullPredicate.INSTANCE);
 * </pre>
 * <p>
 * This class is Serializable from Commons Collections 3.1.
 * </p>
 *
 * @param <E> the type of the elements in the collection
 * @since 3.0
 */
public class PredicatedCollection<E> extends AbstractCollectionDecorator<E> {

    /**
     * Builder for creating predicated collections.
     * <p>
     * Create a Builder with a predicate to validate elements against, then add any elements
     * to the builder. Elements that fail the predicate will be added to a rejected list.
     * Finally, create or decorate a collection using the createPredicated[List,Set,Bag,Queue] methods.
     * <p>
     * An example:
     * <pre>
     *   Predicate&lt;String&gt; predicate = NotNullPredicate.notNullPredicate();
     *   PredicatedCollectionBuilder&lt;String&gt; builder = PredicatedCollection.builder(predicate);
     *   builder.add("item1");
     *   builder.add(null);
     *   builder.add("item2");
     *   List&lt;String&gt; predicatedList = builder.createPredicatedList();
     * </pre>
     * <p>
     * At the end of the code fragment above predicatedList is protected by the predicate supplied
     * to the builder, and it contains item1 and item2.
     * <p>
     * More elements can be added to the builder once a predicated collection has been created,
     * but these elements will not be reflected in already created collections.
     *
     * @param <E>  the element type
     * @since 4.1
     */
    public static class Builder<E> {

        /**
         * The predicate to use.
         */
        private final Predicate<? super E> predicate;

        /**
         * The buffer containing valid elements.
         */
        private final List<E> accepted = new ArrayList<>();

        /**
         * The buffer containing rejected elements.
         */
        private final List<E> rejected = new ArrayList<>();

        /**
         * Constructs a PredicatedCollectionBuilder with the specified Predicate.
         *
         * @param predicate  the predicate to use
         * @throws NullPointerException if predicate is null
         */
        public Builder(final Predicate<? super E> predicate) {
            this.predicate = Objects.requireNonNull(predicate, "predicate");
        }

        public Builder<E> add(final E item) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder<E> addAll(final Collection<? extends E> items) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Bag<E> createPredicatedBag() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Bag<E> createPredicatedBag(final Bag<E> bag) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public List<E> createPredicatedList() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public List<E> createPredicatedList(final List<E> list) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public MultiSet<E> createPredicatedMultiSet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public MultiSet<E> createPredicatedMultiSet(final MultiSet<E> multiset) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Queue<E> createPredicatedQueue() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Queue<E> createPredicatedQueue(final Queue<E> queue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Set<E> createPredicatedSet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Set<E> createPredicatedSet(final Set<E> set) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Collection<E> rejectedElements() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -5259182142076705162L;

    public static <E> Builder<E> builder(final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Builder<E> notNullBuilder() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> PredicatedCollection<T> predicatedCollection(final Collection<T> coll, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The predicate to use
     */
    protected final Predicate<? super E> predicate;

    /**
     * Constructor that wraps (not copies).
     * <p>
     * If there are any elements already in the collection being decorated, they
     * are validated.
     *
     * @param collection  the collection to decorate, must not be null
     * @param predicate  the predicate to use for validation, must not be null
     * @throws NullPointerException if collection or predicate is null
     * @throws IllegalArgumentException if the collection contains invalid elements
     */
    protected PredicatedCollection(final Collection<E> collection, final Predicate<? super E> predicate) {
        super(collection);
        this.predicate = Objects.requireNonNull(predicate, "predicate");
        for (final E item : collection) {
            validate(item);
        }
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void validate(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
