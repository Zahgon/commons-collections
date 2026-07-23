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
package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that returns the value held in a specified map
 * using the input parameter as a key.
 *
 * @param <T> the type of the input to the function.
 * @param <R> the type of the result of the function.
 * @since 3.0
 */
public final class MapTransformer<T, R> implements Transformer<T, R>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 862391807045468939L;

    public static <I, O> Transformer<I, O> mapTransformer(final Map<? super I, ? extends O> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The map of data to lookup in
     */
    private final Map<? super T, ? extends R> iMap;

    /**
     * Constructor that performs no validation.
     * Use {@code mapTransformer} if you want that.
     *
     * @param map  the map to use for lookup, not cloned
     */
    private MapTransformer(final Map<? super T, ? extends R> map) {
        iMap = map;
    }

    public Map<? super T, ? extends R> getMap() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public R transform(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
