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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Creates and loads {@link Properties}.
 *
 * @see Properties
 * @since 4.4
 */
public class PropertiesFactory extends AbstractPropertiesFactory<Properties> {

    private static final class EmptyProperties extends Properties {

        private static final long serialVersionUID = 1L;

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
        public synchronized Object computeIfPresent(final Object key, final BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean contains(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean containsKey(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public boolean containsValue(final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized Enumeration<Object> elements() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Set<Entry<Object, Object>> entrySet() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean equals(final Object o) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized void forEach(final BiConsumer<? super Object, ? super Object> action) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized Object get(final Object key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized Object getOrDefault(final Object key, final Object defaultValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String getProperty(final String key) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public String getProperty(final String key, final String defaultValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized int hashCode() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean isEmpty() {
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

        @SuppressWarnings("resource")
        @Override
        public synchronized void load(final InputStream inStream) throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("resource")
        @Override
        public synchronized void load(final Reader reader) throws IOException {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("resource")
        @Override
        public synchronized void loadFromXML(final InputStream in) throws IOException, InvalidPropertiesFormatException {
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
        protected void rehash() {
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
        public synchronized Object replace(final Object key, final Object value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean replace(final Object key, final Object oldValue, final Object newValue) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized void replaceAll(final BiFunction<? super Object, ? super Object, ? extends Object> function) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @SuppressWarnings("deprecation")
        @Override
        public void save(final OutputStream out, final String comments) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized Object setProperty(final String key, final String value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized int size() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Set<String> stringPropertyNames() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized String toString() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public Collection<Object> values() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * The empty map (immutable). This map is serializable.
     *
     * @since 4.5.0-M1
     */
    public static final Properties EMPTY_PROPERTIES = new EmptyProperties();

    /**
     * The singleton instance.
     */
    public static final PropertiesFactory INSTANCE = new PropertiesFactory();

    /**
     * Constructs an instance.
     */
    private PropertiesFactory() {
        // There is only one instance.
    }

    @Override
    protected Properties createProperties() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
