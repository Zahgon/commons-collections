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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * Decorates a collection of other collections to provide a single unified view.
 * <p>
 * Changes made to this collection will actually be made on the decorated collection.
 * Add and remove operations require the use of a pluggable strategy. If no
 * strategy is provided then add and remove are unsupported.
 * </p>
 * @param <E> the type of the elements in the collection
 * @since 3.0
 */
public class CompositeCollection<E> implements Collection<E>, Serializable {

    /**
     * Pluggable strategy to handle changes to the composite.
     *
     * @param <E> the element being held in the collection
     */
    public interface CollectionMutator<E> extends Serializable {

        /**
         * Called when an object is to be added to the composite.
         *
         * @param composite  the CompositeCollection being changed
         * @param collections  all of the Collection instances in this CompositeCollection
         * @param obj  the object being added
         * @return true if the collection is changed
         * @throws UnsupportedOperationException if add is unsupported
         * @throws ClassCastException if the object cannot be added due to its type
         * @throws NullPointerException if the object cannot be added because its null
         * @throws IllegalArgumentException if the object cannot be added
         */
        boolean add(CompositeCollection<E> composite, List<Collection<E>> collections, E obj);

        /**
         * Called when a collection is to be added to the composite.
         *
         * @param composite  the CompositeCollection being changed
         * @param collections  all of the Collection instances in this CompositeCollection
         * @param coll  the collection being added
         * @return true if the collection is changed
         * @throws UnsupportedOperationException if add is unsupported
         * @throws ClassCastException if the object cannot be added due to its type
         * @throws NullPointerException if the object cannot be added because its null
         * @throws IllegalArgumentException if the object cannot be added
         */
        boolean addAll(CompositeCollection<E> composite, List<Collection<E>> collections, Collection<? extends E> coll);

        /**
         * Called when an object is to be removed to the composite.
         *
         * @param composite  the CompositeCollection being changed
         * @param collections  all of the Collection instances in this CompositeCollection
         * @param obj  the object being removed
         * @return true if the collection is changed
         * @throws UnsupportedOperationException if removed is unsupported
         * @throws ClassCastException if the object cannot be removed due to its type
         * @throws NullPointerException if the object cannot be removed because its null
         * @throws IllegalArgumentException if the object cannot be removed
         */
        boolean remove(CompositeCollection<E> composite, List<Collection<E>> collections, Object obj);
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 8417515734108306801L;

    /**
     * CollectionMutator to handle changes to the collection
     */
    private CollectionMutator<E> mutator;

    /**
     * Collections in the composite
     */
    private final List<Collection<E>> all = new ArrayList<>();

    /**
     * Create an empty CompositeCollection.
     */
    public CompositeCollection() {
    }

    /**
     * Create a Composite Collection with one collection.
     *
     * @param compositeCollection  the Collection to be appended to the composite
     */
    public CompositeCollection(final Collection<E> compositeCollection) {
        addComposited(compositeCollection);
    }

    /**
     * Create a Composite Collection with an array of collections.
     *
     * @param compositeCollections  the collections to composite
     */
    public CompositeCollection(final Collection<E>... compositeCollections) {
        addComposited(compositeCollections);
    }

    /**
     * Create a Composite Collection with two collections.
     *
     * @param compositeCollection1  the Collection to be appended to the composite
     * @param compositeCollection2  the Collection to be appended to the composite
     */
    public CompositeCollection(final Collection<E> compositeCollection1, final Collection<E> compositeCollection2) {
        addComposited(compositeCollection1, compositeCollection2);
    }

    @Override
    public boolean add(final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComposited(final Collection<E> compositeCollection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComposited(final Collection<E>... compositeCollections) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComposited(final Collection<E> compositeCollection1, final Collection<E> compositeCollection2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<Collection<E>> getCollections() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected CollectionMutator<E> getMutator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void removeComposited(final Collection<E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMutator(final CollectionMutator<E> mutator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(final T[] array) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Collection<E> toCollection() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
