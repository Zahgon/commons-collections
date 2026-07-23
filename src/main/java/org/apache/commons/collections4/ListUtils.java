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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.functors.DefaultEquator;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.list.LazyList;
import org.apache.commons.collections4.list.PredicatedList;
import org.apache.commons.collections4.list.TransformedList;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.EditScript;
import org.apache.commons.collections4.sequence.SequencesComparator;

/**
 * Provides utility methods and decorators for {@link List} instances.
 *
 * @since 1.0
 */
public class ListUtils {

    /**
     * A simple wrapper to use a CharSequence as List.
     */
    private static final class CharSequenceAsList extends AbstractList<Character> {

        private final CharSequence sequence;

        CharSequenceAsList(final CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override
        public Character get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A helper class used to construct the longest common subsequence.
     */
    private static final class LcsVisitor<E> implements CommandVisitor<E> {

        private final ArrayList<E> sequence;

        LcsVisitor() {
            sequence = new ArrayList<>();
        }

        public List<E> getSubSequence() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void visitDeleteCommand(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void visitInsertCommand(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public void visitKeepCommand(final E object) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Provides a partition view on a {@link List}.
     * @since 4.0
     */
    private static final class Partition<T> extends AbstractList<List<T>> {

        private final List<T> list;

        private final int size;

        private Partition(final List<T> list, final int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(final int index) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    public static <T> List<T> defaultIfNull(final List<T> list, final List<T> defaultList) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<T> emptyIfNull(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> fixedSizeList(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T getFirst(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> T getLast(final List<T> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static int hashCodeForList(final Collection<?> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> int indexOf(final List<E> list, final Predicate<E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> intersection(final List<? extends E> list1, final List<? extends E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static boolean isEqualList(final Collection<?> list1, final Collection<?> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> lazyList(final List<E> list, final Factory<? extends E> factory) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> lazyList(final List<E> list, final Transformer<Integer, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String longestCommonSubsequence(final CharSequence charSequenceA, final CharSequence charSequenceB) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> longestCommonSubsequence(final List<E> a, final List<E> b) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> longestCommonSubsequence(final List<E> listA, final List<E> listB, final Equator<? super E> equator) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <T> List<List<T>> partition(final List<T> list, final int size) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> predicatedList(final List<E> list, final Predicate<E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> removeAll(final Collection<E> collection, final Collection<?> remove) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> retainAll(final Collection<E> collection, final Collection<?> retain) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> select(final Collection<? extends E> inputCollection, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> selectRejected(final Collection<? extends E> inputCollection, final Predicate<? super E> predicate) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> subtract(final List<E> list1, final List<? extends E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> sum(final List<? extends E> list1, final List<? extends E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> synchronizedList(final List<E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> transformedList(final List<E> list, final Transformer<? super E, ? extends E> transformer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> union(final List<? extends E> list1, final List<? extends E> list2) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static <E> List<E> unmodifiableList(final List<? extends E> list) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Don't allow instances.
     */
    private ListUtils() {
        // empty
    }
}
