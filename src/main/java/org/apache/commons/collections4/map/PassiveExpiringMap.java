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
package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Decorates a {@code Map} to evict expired entries once their expiration
 * time has been reached.
 * <p>
 * When putting a key-value pair in the map this decorator uses a
 * {@link ExpirationPolicy} to determine how long the entry should remain alive
 * as defined by an expiration time value.
 * </p>
 * <p>
 * When accessing the mapped value for a key, its expiration time is checked,
 * and if it is a negative value or if it is greater than the current time, the
 * mapped value is returned. Otherwise, the key is removed from the decorated
 * map, and {@code null} is returned.
 * </p>
 * <p>
 * When invoking methods that involve accessing the entire map contents (i.e
 * {@link #containsValue(Object)}, {@link #entrySet()}, etc.) this decorator
 * removes all expired entries prior to actually completing the invocation.
 * </p>
 * <p>
 * <strong>Note that {@link PassiveExpiringMap} is not synchronized and is not
 * thread-safe.</strong> If you wish to use this map from multiple threads
 * concurrently, you must use appropriate synchronization. The simplest approach
 * is to wrap this map using {@link java.util.Collections#synchronizedMap(Map)}.
 * This class may throw exceptions when accessed by concurrent threads without
 * synchronization.
 * </p>
 *
 * @param <K> the type of the keys in this map
 * @param <V> the type of the values in this map
 * @since 4.0
 */
public class PassiveExpiringMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {

    /**
     * A {@link org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy ExpirationPolicy}
     * that returns an expiration time that is a
     * constant about of time in the future from the current time.
     *
     * @param <K> the type of the keys in the map
     * @param <V> the type of the values in the map
     * @since 4.0
     */
    public static class ConstantTimeToLiveExpirationPolicy<K, V> implements ExpirationPolicy<K, V> {

        /**
         * Serialization version
         */
        private static final long serialVersionUID = 1L;

        /**
         * The constant time-to-live value measured in milliseconds.
         */
        private final long timeToLiveMillis;

        /**
         * Default constructor. Constructs a policy using a negative
         * time-to-live value that results in entries never expiring.
         */
        public ConstantTimeToLiveExpirationPolicy() {
            this(-1L);
        }

        /**
         * Constructs a policy with the given time-to-live constant measured in
         * milliseconds. A negative time-to-live value indicates entries never
         * expire. A zero time-to-live value indicates entries expire (nearly)
         * immediately.
         *
         * @param timeToLiveMillis the constant amount of time (in milliseconds)
         *        an entry is available before it expires. A negative value
         *        results in entries that NEVER expire. A zero value results in
         *        entries that ALWAYS expire.
         */
        public ConstantTimeToLiveExpirationPolicy(final long timeToLiveMillis) {
            this.timeToLiveMillis = timeToLiveMillis;
        }

        /**
         * Constructs a policy with the given time-to-live constant measured in
         * the given time unit of measure.
         *
         * @param timeToLive the constant amount of time an entry is available
         *        before it expires. A negative value results in entries that
         *        NEVER expire. A zero value results in entries that ALWAYS
         *        expire.
         * @param timeUnit the unit of time for the {@code timeToLive}
         *        parameter, must not be null.
         * @throws NullPointerException if the time unit is null.
         */
        public ConstantTimeToLiveExpirationPolicy(final long timeToLive, final TimeUnit timeUnit) {
            this(validateAndConvertToMillis(timeToLive, timeUnit));
        }

        @Override
        public long expirationTime(final K key, final V value) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }

    /**
     * A policy to determine the expiration time for key-value entries.
     *
     * @param <K> the key object type.
     * @param <V> the value object type
     * @since 4.0
     */
    @FunctionalInterface
    public interface ExpirationPolicy<K, V> extends Serializable {

        /**
         * Determine the expiration time for the given key-value entry.
         *
         * @param key the key for the entry.
         * @param value the value for the entry.
         * @return the expiration time value measured in milliseconds. A
         *         negative return value indicates the entry never expires.
         */
        long expirationTime(K key, V value);
    }

    /**
     * Serialization version
     */
    private static final long serialVersionUID = 1L;

    /**
     * First validate the input parameters. If the parameters are valid, convert
     * the given time measured in the given units to the same time measured in
     * milliseconds.
     *
     * @param timeToLive the constant amount of time an entry is available
     *        before it expires. A negative value results in entries that NEVER
     *        expire. A zero value results in entries that ALWAYS expire.
     * @param timeUnit the unit of time for the {@code timeToLive}
     *        parameter, must not be null.
     * @throws NullPointerException if the time unit is null.
     */
    private static long validateAndConvertToMillis(final long timeToLive, final TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "timeUnit");
        return TimeUnit.MILLISECONDS.convert(timeToLive, timeUnit);
    }

    /**
     * Map used to manage expiration times for the actual map entries.
     */
    private final Map<Object, Long> expirationMap = new HashMap<>();

    /**
     * The policy used to determine time-to-live values for map entries.
     */
    private final ExpirationPolicy<K, V> expiringPolicy;

    /**
     * Default constructor. Constructs a map decorator that results in entries
     * NEVER expiring.
     */
    public PassiveExpiringMap() {
        this(-1L);
    }

    /**
     * Constructs a map decorator using the given expiration policy to determine
     * expiration times.
     *
     * @param expiringPolicy the policy used to determine expiration times of
     *        entries as they are added.
     * @throws NullPointerException if expiringPolicy is null
     */
    public PassiveExpiringMap(final ExpirationPolicy<K, V> expiringPolicy) {
        this(expiringPolicy, new HashMap<>());
    }

    /**
     * Constructs a map decorator that decorates the given map and uses the given
     * expiration policy to determine expiration times. If there are any
     * elements already in the map being decorated, they will NEVER expire
     * unless they are replaced.
     *
     * @param expiringPolicy the policy used to determine expiration times of
     *        entries as they are added.
     * @param map the map to decorate, must not be null.
     * @throws NullPointerException if the map or expiringPolicy is null.
     */
    public PassiveExpiringMap(final ExpirationPolicy<K, V> expiringPolicy, final Map<K, V> map) {
        super(map);
        this.expiringPolicy = Objects.requireNonNull(expiringPolicy, "expiringPolicy");
    }

    /**
     * Constructs a map decorator that decorates the given map using the given
     * time-to-live value measured in milliseconds to create and use a
     * {@link ConstantTimeToLiveExpirationPolicy} expiration policy.
     *
     * @param timeToLiveMillis the constant amount of time (in milliseconds) an
     *        entry is available before it expires. A negative value results in
     *        entries that NEVER expire. A zero value results in entries that
     *        ALWAYS expire.
     */
    public PassiveExpiringMap(final long timeToLiveMillis) {
        this(new ConstantTimeToLiveExpirationPolicy<>(timeToLiveMillis), new HashMap<>());
    }

    /**
     * Constructs a map decorator using the given time-to-live value measured in
     * milliseconds to create and use a
     * {@link ConstantTimeToLiveExpirationPolicy} expiration policy. If there
     * are any elements already in the map being decorated, they will NEVER
     * expire unless they are replaced.
     *
     * @param timeToLiveMillis the constant amount of time (in milliseconds) an
     *        entry is available before it expires. A negative value results in
     *        entries that NEVER expire. A zero value results in entries that
     *        ALWAYS expire.
     * @param map the map to decorate, must not be null.
     * @throws NullPointerException if the map is null.
     */
    public PassiveExpiringMap(final long timeToLiveMillis, final Map<K, V> map) {
        this(new ConstantTimeToLiveExpirationPolicy<>(timeToLiveMillis), map);
    }

    /**
     * Constructs a map decorator using the given time-to-live value measured in
     * the given time units of measure to create and use a
     * {@link ConstantTimeToLiveExpirationPolicy} expiration policy.
     *
     * @param timeToLive the constant amount of time an entry is available
     *        before it expires. A negative value results in entries that NEVER
     *        expire. A zero value results in entries that ALWAYS expire.
     * @param timeUnit the unit of time for the {@code timeToLive}
     *        parameter, must not be null.
     * @throws NullPointerException if the time unit is null.
     */
    public PassiveExpiringMap(final long timeToLive, final TimeUnit timeUnit) {
        this(validateAndConvertToMillis(timeToLive, timeUnit));
    }

    /**
     * Constructs a map decorator that decorates the given map using the given
     * time-to-live value measured in the given time units of measure to create
     * {@link ConstantTimeToLiveExpirationPolicy} expiration policy. This policy
     * is used to determine expiration times. If there are any elements already
     * in the map being decorated, they will NEVER expire unless they are
     * replaced.
     *
     * @param timeToLive the constant amount of time an entry is available
     *        before it expires. A negative value results in entries that NEVER
     *        expire. A zero value results in entries that ALWAYS expire.
     * @param timeUnit the unit of time for the {@code timeToLive}
     *        parameter, must not be null.
     * @param map the map to decorate, must not be null.
     * @throws NullPointerException if the map or time unit is null.
     */
    public PassiveExpiringMap(final long timeToLive, final TimeUnit timeUnit, final Map<K, V> map) {
        this(validateAndConvertToMillis(timeToLive, timeUnit), map);
    }

    /**
     * Constructs a map decorator that decorates the given map and results in
     * entries NEVER expiring. If there are any elements already in the map
     * being decorated, they also will NEVER expire.
     *
     * @param map the map to decorate, must not be null.
     * @throws NullPointerException if the map is null.
     */
    public PassiveExpiringMap(final Map<K, V> map) {
        this(-1L, map);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsKey(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean containsValue(final Object value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public V get(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Determines if the given expiration time is less than {@code now}.
     *
     * @param now the time in milliseconds used to compare against the
     *        expiration time.
     * @param expirationTimeObject the expiration time value retrieved from
     *        {@link #expirationMap}, can be null.
     * @return {@code true} if {@code expirationTimeObject} is &ge; 0
     *         and {@code expirationTimeObject} &lt; {@code now}.
     *         {@code false} otherwise.
     */
    private boolean isExpired(final long now, final Long expirationTimeObject) {
        if (expirationTimeObject != null) {
            final long expirationTime = expirationTimeObject.longValue();
            return expirationTime >= 0 && now >= expirationTime;
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The current time in milliseconds.
     */
    private long now() {
        return System.currentTimeMillis();
    }

    @Override
    public V put(final K key, final V value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void putAll(final Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Deserializes the map in using a custom routine.
     *
     * @param in the input stream
     * @throws IOException if an error occurs while reading from the stream
     * @throws ClassNotFoundException if an object read from the stream cannot be loaded
     */
    @SuppressWarnings("unchecked")
    private // (1) should only fail if input stream is incorrect
    void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // (1)
        map = (Map<K, V>) in.readObject();
    }

    @Override
    public V remove(final Object key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes all entries in the map whose expiration time is less than
     * {@code now}. The exceptions are entries with negative expiration
     * times; those entries are never removed.
     *
     * @see #isExpired(long, Long)
     */
    private void removeAllExpired(final long nowMillis) {
        final Iterator<Map.Entry<Object, Long>> iter = expirationMap.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<Object, Long> expirationEntry = iter.next();
            if (isExpired(nowMillis, expirationEntry.getValue())) {
                // remove entry from collection
                super.remove(expirationEntry.getKey());
                // remove entry from expiration map
                iter.remove();
            }
        }
    }

    /**
     * Removes the entry with the given key if the entry's expiration time is
     * less than {@code now}. If the entry has a negative expiration time,
     * the entry is never removed.
     */
    private void removeIfExpired(final Object key, final long nowMillis) {
        final Long expirationTimeObject = expirationMap.get(key);
        if (isExpired(nowMillis, expirationTimeObject)) {
            remove(key);
        }
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Serializes this object to an ObjectOutputStream.
     *
     * @param out the target ObjectOutputStream.
     * @throws IOException thrown when an I/O errors occur writing to the target stream.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(map);
    }
}
