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

import org.apache.commons.collections4.Closure;

/**
 * Closure implementation that calls another closure n times, like a for loop.
 * <p>
 * <strong>WARNING:</strong> from v4.1 onwards this class will <strong>not</strong> be serializable anymore
 * in order to prevent potential remote code execution exploits. Please refer to
 * <a href="https://issues.apache.org/jira/browse/COLLECTIONS-580">COLLECTIONS-580</a>
 * for more details.
 * </p>
 *
 * @param <T> the type of the input to the operation.
 * @since 3.0
 */
public class ForClosure<T> implements Closure<T> {

    @SuppressWarnings("unchecked")
    public static <E> Closure<E> forClosure(final int count, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The number of times to loop
     */
    private final int iCount;

    /**
     * The closure to call
     */
    private final Closure<? super T> iClosure;

    /**
     * Constructor that performs no validation.
     * Use {@code forClosure} if you want that.
     *
     * @param count  the number of times to execute the closure
     * @param closure  the closure to execute, not null
     */
    public ForClosure(final int count, final Closure<? super T> closure) {
        iCount = count;
        iClosure = closure;
    }

    @Override
    public void execute(final T input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Closure<? super T> getClosure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
