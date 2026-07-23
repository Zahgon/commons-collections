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
package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

/**
 * A {@code List} implementation that stores a cache of internal Node objects
 * in an effort to reduce wasteful object creation.
 * <p>
 * A linked list creates one Node for each item of data added. This can result in
 * a lot of object creation and garbage collection. This implementation seeks to
 * avoid that by maintaining a store of cached nodes.
 * </p>
 * <p>
 * This implementation is suitable for long-lived lists where both add and remove
 * are used. Short-lived lists, or lists which only grow will have worse performance
 * using this class.
 * </p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong>
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @since 3.0
 * @deprecated parent {@link AbstractLinkedList} is source incompatible with List methods added in Java 21
 */
@Deprecated
public class NodeCachingLinkedList<E> extends AbstractLinkedList<E> implements Serializable {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 6897789178562232073L;

    /**
     * The default value for {@link #maximumCacheSize}.
     */
    private static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;

    /**
     * The first cached node, or {@code null} if no nodes are cached.
     * Cached nodes are stored in a singly-linked list with
     * {@code next} pointing to the next element.
     */
    private transient Node<E> firstCachedNode;

    /**
     * The size of the cache.
     */
    private transient int cacheSize;

    /**
     * The maximum size of the cache.
     */
    private int maximumCacheSize;

    /**
     * Constructor that creates.
     */
    public NodeCachingLinkedList() {
        this(DEFAULT_MAXIMUM_CACHE_SIZE);
    }

    /**
     * Constructor that copies the specified collection
     *
     * @param coll  the collection to copy
     */
    public NodeCachingLinkedList(final Collection<? extends E> coll) {
        super(coll);
        this.maximumCacheSize = DEFAULT_MAXIMUM_CACHE_SIZE;
    }

    /**
     * Constructor that species the maximum cache size.
     *
     * @param maximumCacheSize  the maximum cache size
     */
    public NodeCachingLinkedList(final int maximumCacheSize) {
        this.maximumCacheSize = maximumCacheSize;
        // must call init() as use super();
        init();
    }

    protected void addNodeToCache(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Node<E> createNode(final E value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected int getMaximumCacheSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected Node<E> getNodeFromCache() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected boolean isCacheFull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the data held in this object to the stream specified.
     *
     * @param in  the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        doReadObject(in);
    }

    @Override
    protected void removeAllNodes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void removeNode(final Node<E> node) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void setMaximumCacheSize(final int maximumCacheSize) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    protected void shrinkCacheToMaximumSize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        doWriteObject(out);
    }
}
