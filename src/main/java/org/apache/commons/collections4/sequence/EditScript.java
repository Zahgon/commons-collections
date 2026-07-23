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
package org.apache.commons.collections4.sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * This class gathers all the {@link EditCommand commands} needed to transform
 * one objects sequence into another objects sequence.
 * <p>
 * An edit script is the most general view of the differences between two
 * sequences. It is built as the result of the comparison between two sequences
 * by the {@link SequencesComparator SequencesComparator} class. The user can
 * walk through it using the <em>visitor</em> design pattern.
 * </p>
 * <p>
 * It is guaranteed that the objects embedded in the {@link InsertCommand insert
 * commands} come from the second sequence and that the objects embedded in
 * either the {@link DeleteCommand delete commands} or {@link KeepCommand keep
 * commands} come from the first sequence. This can be important if subclassing
 * is used for some elements in the first sequence and the {@code equals}
 * method is specialized.
 * </p>
 *
 * @param <T> the type of object to apply commands.
 * @see SequencesComparator
 * @see EditCommand
 * @see CommandVisitor
 * @see ReplacementsHandler
 * @since 4.0
 */
public class EditScript<T> {

    /**
     * Container for the commands.
     */
    private final List<EditCommand<T>> commands;

    /**
     * Length of the longest common subsequence.
     */
    private int lcsLength;

    /**
     * Number of modifications.
     */
    private int modifications;

    /**
     * Simple constructor. Creates a new empty script.
     */
    public EditScript() {
        commands = new ArrayList<>();
        lcsLength = 0;
        modifications = 0;
    }

    public void append(final DeleteCommand<T> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void append(final InsertCommand<T> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void append(final KeepCommand<T> command) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getLCSLength() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getModifications() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void visit(final CommandVisitor<T> visitor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
