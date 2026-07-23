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
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;

/**
 * Factory implementation that always throws an exception.
 *
 * @param <T> the type of results supplied by this supplier.
 * @since 3.0
 */
public final class ExceptionFactory<T> implements Factory<T>, Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 7179106032121985545L;

    /**
     * Singleton predicate instance
     */
    // the static instance works for all types
    @SuppressWarnings("rawtypes")
    public static final Factory INSTANCE = new ExceptionFactory<>();

    public static <T> Factory<T> exceptionFactory() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Restricted constructor.
     */
    private ExceptionFactory() {
    }

    @Override
    public T create() {
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
