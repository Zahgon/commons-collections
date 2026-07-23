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
import org.apache.commons.collections4.Predicate;

/**
 * Predicate implementation that returns true if the input is null.
 *
 * @param <T> the type of the input to the predicate.
 * @since 3.0
 */
public final class NullPredicate<T> extends AbstractPredicate<T> implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 7533784454832764388L;

    /**
     * Singleton predicate instance
     */
    @SuppressWarnings("rawtypes")
    public static final Predicate INSTANCE = new NullPredicate<>();

    public static <T> Predicate<T> nullPredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Restricted constructor.
     */
    private NullPredicate() {
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
    public boolean test(final T object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
