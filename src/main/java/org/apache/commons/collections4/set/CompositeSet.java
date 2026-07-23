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
package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;

/**
 * Decorates a set of other sets to provide a single unified view.
 * <p>
 * Changes made to this set will actually be made on the decorated set.
 * Add operations require the use of a pluggable strategy.
 * If no strategy is provided then add is unsupported.
 * </p>
 * <p>
 * From version 4.0, this class does not extend
 * {@link org.apache.commons.collections4.collection.CompositeCollection CompositeCollection}
 * anymore due to its input restrictions (only accepts Sets).
 * See <a href="https://issues.apache.org/jira/browse/COLLECTIONS-424">COLLECTIONS-424</a>
 * for more details.
 * </p>
 *
 * @param <E> the type of the elements in this set
 * @since 3.0
 */
public class CompositeSet<E> implements Set<E>, Serializable {

    /**
     * Defines callbacks for mutation operations.
     *
     * @param <E> the type of the elements in this instance.
     */
    public interface SetMutator<E> extends Serializable {

        /**
         * Called when an object is to be added to the composite.
         *
         * @param composite  the CompositeSet being changed
         * @param sets  all of the Set instances in this CompositeSet
         * @param obj  the object being added
         * @return true if the collection is changed
         * @throws UnsupportedOperationException if add is unsupported
         * @throws ClassCastException if the object cannot be added due to its type
         * @throws NullPointerException if the object cannot be added because its null
         * @throws IllegalArgumentException if the object cannot be added
         */
        boolean add(CompositeSet<E> composite, List<Set<E>> sets, E obj);

        /**
         * Called when a collection is to be added to the composite.
         *
         * @param composite  the CompositeSet being changed
         * @param sets  all of the Set instances in this CompositeSet
         * @param coll  the collection being added
         * @return true if the collection is changed
         * @throws UnsupportedOperationException if add is unsupported
         * @throws ClassCastException if the object cannot be added due to its type
         * @throws NullPointerException if the object cannot be added because its null
         * @throws IllegalArgumentException if the object cannot be added
         */
        boolean addAll(CompositeSet<E> composite, List<Set<E>> sets, Collection<? extends E> coll);

        /**
         * Called when a Set is added to the CompositeSet and there is a
         * collision between existing and added sets.
         * <p>
         * If {@code added} and {@code existing} still have any intersects
         * after this method returns an IllegalArgumentException will be thrown.
         *
         * @param comp  the CompositeSet being modified
         * @param existing  the Set already existing in the composite
         * @param added  the Set being added to the composite
         * @param intersects  the intersection of the existing and added sets
         */
        void resolveCollision(CompositeSet<E> comp, Set<E> existing, Set<E> added, Collection<E> intersects);
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 5185069727540378940L;

    /**
     * SetMutator to handle changes to the collection
     */
    private SetMutator<E> mutator;

    /**
     * Sets in the composite
     */
    private final List<Set<E>> all = new ArrayList<>();

    /**
     * Creates an empty CompositeSet.
     */
    public CompositeSet() {
    }

    /**
     * Creates a CompositeSet with just {@code set} composited.
     *
     * @param set  the initial set in the composite
     */
    public CompositeSet(final Set<E> set) {
        addComposited(set);
    }

    /**
     * Creates a composite set with sets as the initial set of composited Sets.
     *
     * @param sets  the initial sets in the composite
     */
    public CompositeSet(final Set<E>... sets) {
        addComposited(sets);
    }

    @Override
    public boolean add(final E obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public synchronized void addComposited(final Set<E> set) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComposited(final Set<E>... sets) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void addComposited(final Set<E> set1, final Set<E> set2) {
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

    @Override
    public boolean equals(final Object obj) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected SetMutator<E> getMutator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public List<Set<E>> getSets() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
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

    public void removeComposited(final Set<E> set) {
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

    public void setMutator(final SetMutator<E> mutator) {
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

    public Set<E> toSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
