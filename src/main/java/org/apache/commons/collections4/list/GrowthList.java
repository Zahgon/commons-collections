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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Decorates another {@code List} to make it seamlessly grow when
 * indices larger than the list size are used on add and set,
 * avoiding most IndexOutOfBoundsExceptions.
 * <p>
 * This class avoids errors by growing when a set or add method would
 * normally throw an IndexOutOfBoundsException.
 * Note that IndexOutOfBoundsException IS returned for invalid negative indices.
 * </p>
 * <p>
 * Trying to set or add to an index larger than the size will cause the list
 * to grow (using {@code null} elements). Clearly, care must be taken
 * not to use excessively large indices, as the internal list will grow to
 * match.
 * </p>
 * <p>
 * Trying to use any method other than add or set with an invalid index will
 * call the underlying list and probably result in an IndexOutOfBoundsException.
 * </p>
 * <p>
 * Take care when using this list with {@code null} values, as
 * {@code null} is the value added when growing the list.
 * </p>
 * <p>
 * All sub-lists will access the underlying list directly, and will throw
 * IndexOutOfBoundsExceptions.
 * </p>
 * <p>
 * This class differs from {@link LazyList} because here growth occurs on
 * set and add, where {@code LazyList} grows on get. However, they
 * can be used together by decorating twice.
 * </p>
 *
 * @param <E> the type of the elements in the list.
 * @see LazyList
 * @since 3.2
 */
public class GrowthList<E> extends AbstractSerializableListDecorator<E> {

    /**
     * Serialization version
     */
    private static final long serialVersionUID = -3620001881672L;

    public static <E> GrowthList<E> growthList(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructor that uses an ArrayList internally.
     */
    public GrowthList() {
        super(new ArrayList<>());
    }

    /**
     * Constructor that uses an ArrayList internally.
     *
     * @param initialCapacity  the initial capacity of the ArrayList
     * @throws IllegalArgumentException if initial capacity is invalid
     */
    public GrowthList(final int initialCapacity) {
        super(new ArrayList<>(initialCapacity));
    }

    /**
     * Constructor that wraps (not copies).
     *
     * @param list  the list to decorate, must not be null
     * @throws NullPointerException if list is null
     */
    protected GrowthList(final List<E> list) {
        super(list);
    }

    @Override
    public void add(final int index, final E element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public E set(final int index, final E element) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
