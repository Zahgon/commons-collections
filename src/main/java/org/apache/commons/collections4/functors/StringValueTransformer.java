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
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that returns the result of calling
 * {@code String.valueOf} on the input object.
 *
 * @param <T> the type of the input and result to the function.
 * @since 3.0
 */
public final class StringValueTransformer<T> implements Transformer<T, String>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 7511110693171758606L;

    /**
     * Singleton predicate instance
     */
    private static final Transformer<Object, String> INSTANCE = new StringValueTransformer<>();

    @SuppressWarnings("unchecked")
    public static <T> Transformer<T, String> stringValueTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Restricted constructor.
     */
    private StringValueTransformer() {
    }

    /**
     * Returns the singleton instance.
     *
     * @return the singleton instance.
     */
    private Object readResolve() {
        return INSTANCE;
    }

    @Override
    public String transform(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
