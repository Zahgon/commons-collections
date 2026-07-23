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
import org.apache.commons.collections4.functors.ChainedClosure;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionClosure;
import org.apache.commons.collections4.functors.ForClosure;
import org.apache.commons.collections4.functors.IfClosure;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.functors.NOPClosure;
import org.apache.commons.collections4.functors.SwitchClosure;
import org.apache.commons.collections4.functors.TransformerClosure;
import org.apache.commons.collections4.functors.WhileClosure;

/**
 * {@code ClosureUtils} provides reference implementations and utilities
 * for the Closure functor interface. The supplied closures are:
 * <ul>
 * <li>Invoker - invokes a method on the input object
 * <li>For - repeatedly calls a closure for a fixed number of times
 * <li>While - repeatedly calls a closure while a predicate is true
 * <li>Chained - chains two or more closures together
 * <li>If - calls one closure or another based on a predicate
 * <li>Switch - calls one closure based on one or more predicates
 * <li>SwitchMap - calls one closure looked up from a Map
 * <li>Transformer - wraps a Transformer as a Closure
 * <li>NOP - does nothing
 * <li>Exception - always throws an exception
 * </ul>
 * <p>
 * Since v4.1 only closures which are considered to be safe are
 * Serializable. Closures considered to be unsafe for serialization are:
 * </p>
 * <ul>
 * <li>Invoker
 * <li>For
 * <li>While
 * </ul>
 *
 * @since 3.0
 */
public class ClosureUtils {

    public static <E> Closure<E> asClosure(final Transformer<? super E, ?> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> chainedClosure(final Closure<? super E>... closures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> chainedClosure(final Collection<? extends Closure<? super E>> closures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> doWhileClosure(final Closure<? super E> closure, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> exceptionClosure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> forClosure(final int count, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> ifClosure(final Predicate<? super E> predicate, final Closure<? super E> trueClosure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> ifClosure(final Predicate<? super E> predicate, final Closure<? super E> trueClosure, final Closure<? super E> falseClosure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> invokerClosure(final String methodName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> invokerClosure(final String methodName, final Class<?>[] paramTypes, final Object[] args) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> nopClosure() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> switchClosure(final Map<Predicate<E>, Closure<E>> predicatesAndClosures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> switchClosure(final Predicate<? super E>[] predicates, final Closure<? super E>[] closures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> switchClosure(final Predicate<? super E>[] predicates, final Closure<? super E>[] closures, final Closure<? super E> defaultClosure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @SuppressWarnings("unchecked")
    public static <E> Closure<E> switchMapClosure(final Map<? extends E, Closure<E>> objectsAndClosures) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> Closure<E> whileClosure(final Predicate<? super E> predicate, final Closure<? super E> closure) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private ClosureUtils() {
        // empty
    }
}
