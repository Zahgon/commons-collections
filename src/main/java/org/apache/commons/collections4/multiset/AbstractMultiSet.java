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
package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Transformer;

/**
 * Abstract implementation of the {@link MultiSet} interface to simplify the
 * creation of subclass implementations.
 *
 * @param <E> the type held in the multiset
 * @since 4.1
 */
public abstract class AbstractMultiSet<E> extends AbstractCollection<E> implements MultiSet<E> {

    /**
     * Inner class AbstractEntry.
     *
     * @param <E> the element type.
     */
    protected abstract static class AbstractEntry<E> implements Entry<E> {

        /**
         * Constructs a new instance.
         */
        public AbstractEntry() {
            // empty
        }

        @Override
        public boolean equals(final Object object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class EntrySet.
     *
     * @param <E> the element type.
     */
    protected static class EntrySet<E> extends AbstractSet<Entry<E>> {

        private final AbstractMultiSet<E> parent;

        /**
         * Constructs a new view of the MultiSet.
         *
         * @param parent  the parent MultiSet
         */
        protected EntrySet(final AbstractMultiSet<E> parent) {
            this.parent = parent;
        }

        @Override
        public boolean contains(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<Entry<E>> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object obj) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class iterator for the MultiSet.
     */
    private static final class MultiSetIterator<E> implements Iterator<E> {

        private final AbstractMultiSet<E> parent;

        private final Iterator<Entry<E>> entryIterator;

        private Entry<E> current;

        private int itemCount;

        private boolean canRemove;

        /**
         * Constructs a new instance.
         *
         * @param parent the parent multiset
         */
        MultiSetIterator(final AbstractMultiSet<E> parent) {
            this.parent = parent;
            this.entryIterator = parent.entrySet().iterator();
            this.current = null;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public E next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Inner class UniqueSet.
     *
     * @param <E> the element type.
     */
    protected static class UniqueSet<E> extends AbstractSet<E> {

        /**
         * The parent multiset
         */
        protected final AbstractMultiSet<E> parent;

        /**
         * Constructs a new unique element view of the MultiSet.
         *
         * @param parent  the parent MultiSet
         */
        protected UniqueSet(final AbstractMultiSet<E> parent) {
            this.parent = parent;
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean contains(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsAll(final Collection<?> coll) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Iterator<E> iterator() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean remove(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * View of the elements
     */
    private transient Set<E> uniqueSet;

    /**
     * View of the entries
     */
    private transient Set<Entry<E>> entrySet;

    /**
     * Constructs a new instance subclasses.
     */
    protected AbstractMultiSet() {
    }

    @Override
    public boolean add(final E object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int add(final E object, final int occurrences) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean contains(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Set<Entry<E>> createEntrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates an entry set iterator.
     * Subclasses can override this to return iterators with different properties.
     *
     * @return the entrySet iterator
     */
    protected abstract Iterator<Entry<E>> createEntrySetIterator();

    protected Set<E> createUniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Iterator<E> createUniqueSetIterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doReadObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void doWriteObject(final ObjectOutputStream out) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Entry<E>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getCount(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int remove(final Object object, final int occurrences) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int setCount(final E object, final int count) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of unique elements in this multiset.
     *
     * @return the number of unique elements
     */
    protected abstract int uniqueElements();

    @Override
    public Set<E> uniqueSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
