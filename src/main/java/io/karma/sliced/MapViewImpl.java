/*
 * Copyright 2022 Karma Krafts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.karma.sliced;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
final class MapViewImpl<K, V, M extends Map<K, V>> implements MapView<K, V> {
    private final M ref;

    MapViewImpl(final @NotNull M ref) {
        this.ref = ref;
    }

    @Override
    public V get(final @Nullable K key) {
        return ref.get(key);
    }

    @Override
    public int size() {
        return ref.size();
    }

    @Override
    public @NotNull Entry<K, V>[] toArray(final @NotNull IntFunction<Entry<K, V>[]> factory) {
        final Entry<K, V>[] result = factory.apply(size());
        final Set<Entry<K, V>> entries = ref.entrySet();
        int index = 0;

        for (final Entry<K, V> entry : entries) {
            result[index++] = entry;
        }

        return result;
    }

    @Override
    public <C extends Collection<Entry<K, V>>> @NotNull C copy(final @NotNull Supplier<C> factory) {
        final C result = factory.get();
        result.addAll(ref.entrySet());
        return result;
    }

    @Override
    public @NotNull Slice<Entry<K, V>> asSlice() {
        final ArrayList<Entry<K, V>> list = new ArrayList<>(ref.entrySet());
        return new ListSlice<>(list, 0, list.size() - 1);
    }

    @Override
    public @NotNull Iterator<Entry<K, V>> iterator() {
        return ref.entrySet().iterator();
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof Map) {
            final Set<? extends Entry<? extends K, ? extends V>> entries = ((Map<? extends K, ? extends V>) obj).entrySet();

            for (final Entry<? extends K, ? extends V> entry : entries) {

            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
