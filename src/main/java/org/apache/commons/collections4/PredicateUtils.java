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

import java.util.Collection;
import org.apache.commons.collections4.functors.AllPredicate;
import org.apache.commons.collections4.functors.AndPredicate;
import org.apache.commons.collections4.functors.AnyPredicate;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.IdentityPredicate;
import org.apache.commons.collections4.functors.InstanceofPredicate;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.functors.NonePredicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.NotPredicate;
import org.apache.commons.collections4.functors.NullIsExceptionPredicate;
import org.apache.commons.collections4.functors.NullIsFalsePredicate;
import org.apache.commons.collections4.functors.NullIsTruePredicate;
import org.apache.commons.collections4.functors.NullPredicate;
import org.apache.commons.collections4.functors.OnePredicate;
import org.apache.commons.collections4.functors.OrPredicate;
import org.apache.commons.collections4.functors.TransformedPredicate;
import org.apache.commons.collections4.functors.TransformerPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.functors.UniquePredicate;

/**
 * {@code PredicateUtils} provides reference implementations and utilities
 * for the Predicate functor interface. The supplied predicates are:
 * <ul>
 * <li>Invoker - returns the result of a method call on the input object
 * <li>InstanceOf - true if the object is an instanceof a class
 * <li>Equal - true if the object equals() a specified object
 * <li>Identity - true if the object == a specified object
 * <li>Null - true if the object is null
 * <li>NotNull - true if the object is not null
 * <li>Unique - true if the object has not already been evaluated
 * <li>And/All - true if all of the predicates are true
 * <li>Or/Any - true if any of the predicates is true
 * <li>Either/One - true if only one of the predicate is true
 * <li>Neither/None - true if none of the predicates are true
 * <li>Not - true if the predicate is false, and vice versa
 * <li>Transformer - wraps a Transformer as a Predicate
 * <li>True - always return true
 * <li>False - always return false
 * <li>Exception - always throws an exception
 * <li>NullIsException/NullIsFalse/NullIsTrue - check for null input
 * <li>Transformed - transforms the input before calling the predicate
 * </ul>
 * <p>
 * All the supplied predicates are Serializable.
 * </p>
 *
 * @since 3.0
 */
public class PredicateUtils {

    public static <T> Predicate<T> allPredicate(final Collection<? extends Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> allPredicate(final Predicate<? super T>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> andPredicate(final Predicate<? super T> predicate1, final Predicate<? super T> predicate2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> anyPredicate(final Collection<? extends Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> anyPredicate(final Predicate<? super T>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> asPredicate(final Transformer<? super T, Boolean> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> eitherPredicate(final Predicate<? super T> predicate1, final Predicate<? super T> predicate2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> equalPredicate(final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> exceptionPredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> falsePredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> identityPredicate(final T value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Predicate<Object> instanceofPredicate(final Class<?> type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> invokerPredicate(final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> invokerPredicate(final String methodName, final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> neitherPredicate(final Predicate<? super T> predicate1, final Predicate<? super T> predicate2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nonePredicate(final Collection<? extends Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nonePredicate(final Predicate<? super T>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> notNullPredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> notPredicate(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nullIsExceptionPredicate(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nullIsFalsePredicate(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nullIsTruePredicate(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> nullPredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> onePredicate(final Collection<? extends Predicate<? super T>> predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> onePredicate(final Predicate<? super T>... predicates) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> orPredicate(final Predicate<? super T> predicate1, final Predicate<? super T> predicate2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> transformedPredicate(final Transformer<? super T, ? extends T> transformer, final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> truePredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Predicate<T> uniquePredicate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private PredicateUtils() {
        // empty
    }
}
