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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.map.MultiValueMap;

/**
 * An IndexedCollection is a Map-like view onto a Collection. It accepts a
 * keyTransformer to define how the keys are converted from the values.
 * <p>
 * Modifications made to this decorator modify the index as well as the
 * decorated {@link Collection}. However, modifications to the underlying
 * {@link Collection} will not update the index and it will get out of sync.
 * </p>
 * <p>
 * If modification of the decorated {@link Collection} is unavoidable, then a
 * call to {@link #reindex()} will update the index to the current contents of
 * the {@link Collection}.
 * </p>
 *
 * @param <K> the type of object in the index.
 * @param <C> the type of object in the collection.
 * @since 4.0
 */
public class IndexedCollection<K, C> extends AbstractCollectionDecorator<C> {

    // TODO: replace with MultiValuedMap
    /**
     * Serialization version
     */
    private static final long serialVersionUID = -5512610452568370038L;

    public static <K, C> IndexedCollection<K, C> nonUniqueIndexedCollection(final Collection<C> coll, final Transformer<C, K> keyTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <K, C> IndexedCollection<K, C> uniqueIndexedCollection(final Collection<C> coll, final Transformer<C, K> keyTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The {@link Transformer} for generating index keys.
     */
    private final Transformer<C, K> keyTransformer;

    /**
     * The map of indexes to collected objects.
     */
    private final MultiMap<K, C> index;

    /**
     * The uniqueness constraint for the index.
     */
    private final boolean uniqueIndex;

    /**
     * Create a {@link IndexedCollection}.
     *
     * @param coll  decorated {@link Collection}
     * @param keyTransformer  {@link Transformer} for generating index keys
     * @param map  map to use as index
     * @param uniqueIndex  if the index shall enforce uniqueness of index keys
     */
    public IndexedCollection(final Collection<C> coll, final Transformer<C, K> keyTransformer, final MultiMap<K, C> map, final boolean uniqueIndex) {
        super(coll);
        this.keyTransformer = keyTransformer;
        this.index = map;
        this.uniqueIndex = uniqueIndex;
        reindex();
    }

    @Override
    public boolean add(final C object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final Collection<? extends C> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Provides checking for adding the index.
     *
     * @param object the object to index
     * @throws IllegalArgumentException if the object maps to an existing key and the index
     *   enforces a uniqueness constraint
     */
    private void addToIndex(final C object) {
        final K key = keyTransformer.apply(object);
        if (uniqueIndex && index.containsKey(key)) {
            throw new IllegalArgumentException("Duplicate key in uniquely indexed collection.");
        }
        index.put(key, object);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public C get(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void reindex() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes an object from the index.
     *
     * @param object the object to remove
     */
    private void removeFromIndex(final C object) {
        index.remove(keyTransformer.apply(object));
    }

    @Override
    public boolean removeIf(final Predicate<? super C> filter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // index is a MultiMap which returns a Collection
    @SuppressWarnings("unchecked")
    public Collection<C> values(final K key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
