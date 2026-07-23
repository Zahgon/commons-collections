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
import org.apache.commons.collections4.Predicate;

/**
 * Predicate implementation that returns true if the input is an instanceof
 * the type stored in this predicate.
 *
 * @since 3.0
 */
public final class InstanceofPredicate extends AbstractPredicate<Object> implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = -6682656911025165584L;

    public static Predicate<Object> instanceOfPredicate(final Class<?> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The type to compare to
     */
    private final Class<?> iType;

    /**
     * Constructor that performs no validation.
     * Use {@code instanceOfPredicate} if you want that.
     *
     * @param type  the type to check for
     */
    public InstanceofPredicate(final Class<?> type) {
        iType = type;
    }

    public Class<?> getType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean test(final Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
