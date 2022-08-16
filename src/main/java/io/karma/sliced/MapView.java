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

import java.util.Map;
import java.util.Map.Entry;

/**
 * <h2>Information</h2>
 * A specialized {@link View} for {@link java.util.Map},
 * which provides an additional set of functionality over
 * the regular view access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.STABLE)
public interface MapView<K, V> extends View<Entry<K, V>> {
    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Map}.
     *
     * @param <TK> The key type of the given map and view to create.
     * @param <TV> The value type of the given map and view to create.
     * @param map  The map of which to create a view.
     * @return A new view instance, which references the given map.
     */
    static <TK, TV> @NotNull MapView<TK, TV> of(final @NotNull Map<TK, TV> map) {
        return new MapViewImpl<>(map);
    }

    /**
     * Retrieves the value associated with the given key.
     * Returns null if the given key doesn't exist in the map
     * instance referenced by this view instance.
     *
     * @param key The key for which to retrieve the value.
     * @return The value associated with the given key. Null if the given key doesn't exist.
     */
    V get(final @Nullable K key);
}
