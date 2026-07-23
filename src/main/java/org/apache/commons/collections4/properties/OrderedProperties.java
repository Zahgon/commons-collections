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
package org.apache.commons.collections4.properties;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A drop-in replacement for {@link Properties} for ordered keys.
 * <p>
 * Overrides methods to keep keys in insertion order. Allows other methods in the superclass to work with ordered keys.
 * </p>
 *
 * @see OrderedPropertiesFactory#INSTANCE
 * @since 4.5.0-M1
 */
public class OrderedProperties extends Properties {

    private static final long serialVersionUID = 1L;

    /**
     * Preserves the insertion order.
     */
    private final LinkedHashSet<Object> orderedKeys = new LinkedHashSet<>();

    /**
     * Constructs a new instance.
     */
    public OrderedProperties() {
        // empty
    }

    @Override
    public synchronized void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object compute(final Object key, final BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object computeIfAbsent(final Object key, final Function<? super Object, ? extends Object> mappingFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void forEach(final BiConsumer<? super Object, ? super Object> action) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Enumeration<Object> keys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Object> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object merge(final Object key, final Object value, final BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Enumeration<?> propertyNames() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object put(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized void putAll(final Map<? extends Object, ? extends Object> t) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object putIfAbsent(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized Object remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized boolean remove(final Object key, final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public synchronized String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
