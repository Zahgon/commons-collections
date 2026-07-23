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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
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
 * @param <T> the type of the input to the function.
 * @param <R> the type of the result of the function.
 * @since 3.0
 */
public class InvokerTransformer<T, R> implements Transformer<T, R> {

    public static <I, O> Transformer<I, O> invokerTransformer(final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> invokerTransformer(final String methodName, final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The method name to call
     */
    private final String iMethodName;

    /**
     * The array of reflection parameter types
     */
    private final Class<?>[] iParamTypes;

    /**
     * The array of reflection arguments
     */
    private final Object[] iArgs;

    /**
     * Constructor for no arg instance.
     *
     * @param methodName  the method to call
     */
    private InvokerTransformer(final String methodName) {
        iMethodName = methodName;
        iParamTypes = null;
        iArgs = null;
    }

    /**
     * Constructor that performs no validation.
     * Use {@code invokerTransformer} if you want that.
     * <p>
     * Note: from 4.0, the input parameters will be cloned
     *
     * @param methodName  the method to call
     * @param paramTypes  the constructor parameter types
     * @param args  the constructor arguments
     */
    public InvokerTransformer(final String methodName, final Class<?>[] paramTypes, final Object[] args) {
        iMethodName = methodName;
        iParamTypes = paramTypes != null ? paramTypes.clone() : null;
        iArgs = args != null ? args.clone() : null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public R transform(final Object input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
