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
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.ConstantFactory;
import org.apache.commons.collections4.functors.ExceptionFactory;
import org.apache.commons.collections4.functors.InstantiateFactory;
import org.apache.commons.collections4.functors.PrototypeFactory;

/**
 * {@code FactoryUtils} provides reference implementations and utilities
 * for the Factory functor interface. The supplied factories are:
 * <ul>
 * <li>Prototype - clones a specified object
 * <li>Instantiate - creates objects using reflection
 * <li>Constant - always returns the same object
 * <li>Null - always returns null
 * <li>Exception - always throws an exception
 * </ul>
 * <p>
 * Since v4.1 only factories which are considered to be safe are
 * Serializable. Factories considered to be unsafe for serialization are:
 * </p>
 * <ul>
 * <li>Prototype
 * <li>Instantiate
 * </ul>
 *
 * @since 3.0
 */
public class FactoryUtils {

    public static <T> Factory<T> constantFactory(final T constantToReturn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Factory<T> exceptionFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Factory<T> instantiateFactory(final Class<T> classToInstantiate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Factory<T> instantiateFactory(final Class<T> classToInstantiate, final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Factory<T> nullFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Factory<T> prototypeFactory(final T prototype) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private FactoryUtils() {
        // empty
    }
}
