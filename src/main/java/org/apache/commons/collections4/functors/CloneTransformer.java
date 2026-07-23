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

import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that returns a clone of the input object.
 * <p>
 * Clone is performed using {@code PrototypeFactory.prototypeFactory(input).create()}.
 * </p>
 * <p>
 * <strong>WARNING:</strong> from v4.1 onwards this class will <strong>not</strong> be serializable anymore
 * in order to prevent potential remote code execution exploits. Please refer to
 * <a href="https://issues.apache.org/jira/browse/COLLECTIONS-580">COLLECTIONS-580</a>
 * for more details.
 * </p>
 *
 * @param <T> the type of the input and result to the function.
 * @since 3.0
 */
public class CloneTransformer<T> implements Transformer<T, T> {

    /**
     * Singleton predicate instance
     */
    // the singleton instance works for all types
    @SuppressWarnings("rawtypes")
    public static final Transformer INSTANCE = new CloneTransformer<>();

    public static <T> Transformer<T, T> cloneTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Constructs a new instance.
     */
    private CloneTransformer() {
    }

    @Override
    public T transform(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
