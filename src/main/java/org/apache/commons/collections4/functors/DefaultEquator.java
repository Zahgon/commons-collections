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
import java.util.Objects;
import org.apache.commons.collections4.Equator;

/**
 * Default {@link Equator} implementation.
 *
 * @param <T>  the types of object this {@link Equator} can evaluate.
 * @since 4.0
 */
public class DefaultEquator<T> implements Equator<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 825802648423525485L;

    /**
     * Static instance
     */
    // the static instance works for all types
    @SuppressWarnings("rawtypes")
    public static final DefaultEquator INSTANCE = new DefaultEquator<>();

    /**
     * Hashcode used for {@code null} objects.
     */
    public static final int HASHCODE_NULL = -1;

    public static <T> DefaultEquator<T> defaultEquator() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Restricted constructor.
     */
    private DefaultEquator() {
    }

    @Override
    public boolean equate(final T o1, final T o2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hash(final T o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance.
     */
    private Object readResolve() {
        return INSTANCE;
    }
}
