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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

/**
 * Subclasses create and load {@link Properties} and subclasses of {@link Properties} like {@link SortedProperties}.
 *
 * @param <T> {@link Properties} or a subclass like {@link SortedProperties}.
 * @see Properties
 * @since 4.4
 */
public abstract class AbstractPropertiesFactory<T extends Properties> {

    /**
     * Enumerates property formats.
     *
     * @since 4.5.0-M1
     */
    public enum PropertyFormat {

        /**
         * Properties file format.
         */
        PROPERTIES,
        /**
         * XML file format.
         */
        XML;

        static PropertyFormat toPropertyFormat(final String fileName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * Constructs an instance.
     */
    protected AbstractPropertiesFactory() {
        // no init.
    }

    /**
     * Subclasses override to provide customized properties instances.
     *
     * @return a new Properties instance.
     */
    protected abstract T createProperties();

    public T load(final ClassLoader classLoader, final String name) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final File file) throws FileNotFoundException, IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final InputStream inputStream, final PropertyFormat propertyFormat) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final Path path) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final Reader reader) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final String name) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final URI uri) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public T load(final URL url) throws IOException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
