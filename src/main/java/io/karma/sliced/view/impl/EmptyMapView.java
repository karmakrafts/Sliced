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
import io.karma.sliced.util.NopIterator;
import io.karma.sliced.view.MapView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyMapView<K, V> implements MapView<K, V> {
    public static final EmptyMapView<?, ?> INSTANCE = new EmptyMapView<>();

    // @formatter:off
    private EmptyMapView() {}
    // @formatter:on

    @Override
    public boolean containsKey(@Nullable K key) {
        return false;
    }

    @Override
    public boolean containsValue(@Nullable V value) {
        return false;
    }

    @Override
    public V get(@Nullable K key) {
        return null;
    }

    @Override
    public @NotNull Set<K> keys() {
        return Collections.emptySet();
    }

    @Override
    public @NotNull Collection<V> values() {
        return Collections.emptyList();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Map.Entry<K, V>> asSlice() {
        return Slice.empty();
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return NopIterator.instance();
    }
}
