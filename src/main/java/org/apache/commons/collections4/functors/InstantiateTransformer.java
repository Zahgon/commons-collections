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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

/**
 * Transformer implementation that creates a new object instance by reflection.
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
public class InstantiateTransformer<T> implements Transformer<Class<? extends T>, T> {

    /**
     * Singleton instance that uses the no arg constructor
     */
    @SuppressWarnings("rawtypes")
    private static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer<>();

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The constructor parameter types
     */
    private final Class<?>[] iParamTypes;

    /**
     * The constructor arguments
     */
    private final Object[] iArgs;

    /**
     * Constructor for no arg instance.
     */
    private InstantiateTransformer() {
        iParamTypes = null;
        iArgs = null;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code instantiateTransformer} if you want that.
     * <p>
     * Note: from 4.0, the input parameters will be cloned
     *
     * @param paramTypes  the constructor parameter types
     * @param args  the constructor arguments
     */
    public InstantiateTransformer(final Class<?>[] paramTypes, final Object[] args) {
        iParamTypes = paramTypes != null ? paramTypes.clone() : null;
        iArgs = args != null ? args.clone() : null;
    }

    @Override
    public T transform(final Class<? extends T> input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
