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
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.CloneTransformer;
import org.apache.commons.collections4.functors.ClosureTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionTransformer;
import org.apache.commons.collections4.functors.FactoryTransformer;
import org.apache.commons.collections4.functors.IfTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.functors.MapTransformer;
import org.apache.commons.collections4.functors.NOPTransformer;
import org.apache.commons.collections4.functors.PredicateTransformer;
import org.apache.commons.collections4.functors.StringValueTransformer;
import org.apache.commons.collections4.functors.SwitchTransformer;

/**
 * {@code TransformerUtils} provides reference implementations and
 * utilities for the Transformer functor interface. The supplied transformers are:
 * <ul>
 * <li>Invoker - returns the result of a method call on the input object
 * <li>Clone - returns a clone of the input object
 * <li>Constant - always returns the same object
 * <li>Closure - performs a Closure and returns the input object
 * <li>Predicate - returns the result of the predicate as a Boolean
 * <li>Factory - returns a new object from a factory
 * <li>Chained - chains two or more transformers together
 * <li>If - calls one transformer or another based on a predicate
 * <li>Switch - calls one transformer based on one or more predicates
 * <li>SwitchMap - calls one transformer looked up from a Map
 * <li>Instantiate - the Class input object is instantiated
 * <li>Map - returns an object from a supplied Map
 * <li>Null - always returns null
 * <li>NOP - returns the input object, which should be immutable
 * <li>Exception - always throws an exception
 * <li>StringValue - returns a {@link String} representation of the input object
 * </ul>
 * <p>
 * Since v4.1 only transformers which are considered to be safe are
 * Serializable. Transformers considered to be unsafe for serialization are:
 * </p>
 * <ul>
 * <li>Invoker
 * <li>Clone
 * <li>Instantiate
 * </ul>
 *
 * @since 3.0
 */
public class TransformerUtils {

    public static <T> Transformer<T, T> asTransformer(final Closure<? super T> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> asTransformer(final Factory<? extends O> factory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, Boolean> asTransformer(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> chainedTransformer(final Collection<? extends Transformer<? super T, ? extends T>> transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> chainedTransformer(final Transformer<? super T, ? extends T>... transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> cloneTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> constantTransformer(final O constantToReturn) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> exceptionTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> ifTransformer(final Predicate<? super I> predicate, final Transformer<? super I, ? extends O> trueTransformer, final Transformer<? super I, ? extends O> falseTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> ifTransformer(final Predicate<? super T> predicate, final Transformer<? super T, ? extends T> trueTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> invokerTransformer(final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> invokerTransformer(final String methodName, final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> mapTransformer(final Map<? super I, ? extends O> map) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, T> nopTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> nullTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> Transformer<T, String> stringValueTransformer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <I, O> Transformer<I, O> switchMapTransformer(final Map<I, Transformer<I, O>> objectsAndTransformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> switchTransformer(final Map<Predicate<I>, Transformer<I, O>> predicatesAndTransformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create a new Transformer that calls one of two transformers depending
     * on the specified predicate.
     *
     * @param <I>  the input type
     * @param <O>  the output type
     * @param predicate  the predicate to switch on
     * @param trueTransformer  the transformer called if the predicate is true
     * @param falseTransformer  the transformer called if the predicate is false
     * @return the transformer
     * @throws NullPointerException if either the predicate or transformer is null
     * @see SwitchTransformer
     * @deprecated as of 4.1, use {@link #ifTransformer(Predicate, Transformer, Transformer)}
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    public static <I, O> Transformer<I, O> switchTransformer(final Predicate<? super I> predicate, final Transformer<? super I, ? extends O> trueTransformer, final Transformer<? super I, ? extends O> falseTransformer) {
        return SwitchTransformer.switchTransformer(new Predicate[] { predicate }, new Transformer[] { trueTransformer }, falseTransformer);
    }

    public static <I, O> Transformer<I, O> switchTransformer(final Predicate<? super I>[] predicates, final Transformer<? super I, ? extends O>[] transformers) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <I, O> Transformer<I, O> switchTransformer(final Predicate<? super I>[] predicates, final Transformer<? super I, ? extends O>[] transformers, final Transformer<? super I, ? extends O> defaultTransformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This class is not normally instantiated.
     */
    private TransformerUtils() {
        // empty
    }
}
