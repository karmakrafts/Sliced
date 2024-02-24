/*
 * Copyright 2022 - 2024 Karma Krafts & associates
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.karma.sliced.view.impl;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ListSlice;
import io.karma.sliced.view.MapView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
public final class MapViewImpl<K, V, M extends Map<K, V>> implements MapView<K, V> {
    private final M ref;

    public MapViewImpl(final @NotNull M ref) {
        this.ref = ref;
    }

    @Override
    public boolean containsKey(final @Nullable K key) {
        return ref.containsKey(key);
    }

    @Override
    public boolean containsValue(final @Nullable V value) {
        return ref.containsValue(value);
    }

    @Override
    public V get(final @Nullable K key) {
        return ref.get(key);
    }

    @Override
    public @NotNull Set<K> keys() {
        return ref.keySet();
    }

    @Override
    public @NotNull Collection<V> values() {
        return ref.values();
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
        return new ListSlice<>(list, 0, list.size());
    }

    @Override
    public @NotNull Iterator<Entry<K, V>> iterator() {
        return ref.entrySet().iterator();
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof MapViewImpl) {
            return ((MapViewImpl<?, ?, ?>) obj).ref.equals(ref);
        }
        else if (obj instanceof MapView) {
            final MapView<?, ?> other = (MapView<?, ?>) obj;
            final Iterator<? extends Entry<?, ?>> otherItr = other.iterator();
            final Iterator<? extends Entry<? extends K, ? extends V>> itr = iterator();

            boolean b1 = itr.hasNext();
            boolean b2 = otherItr.hasNext();

            while (b1 || b2) {
                if (b1 != b2 || !itr.next().equals(otherItr.next())) {
                    return false;
                }

                b1 = itr.hasNext();
                b2 = otherItr.hasNext();
            }

            return true;
        }
        else if (obj instanceof Map) {
            final Set<? extends Entry<?, ?>> entries = ((Map<?, ?>) obj).entrySet();
            final Iterator<Entry<K, V>> itr = iterator();

            for (final Entry<?, ?> entry : entries) {
                if (!itr.hasNext()) {
                    return false;
                }

                final Entry<?, ?> otherEntry = itr.next();

                if (!otherEntry.getKey().equals(entry.getKey()) || !otherEntry.getValue().equals(entry.getValue())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
