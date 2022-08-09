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
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * <h2>Information</h2>
 * Provides static factory functions for creating views of
 * supported collection/array types.
 * <h2>Note</h2>
 * If you want to create a slice, create it using {@link Slices}
 * instead of converting from a {@link View}.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public final class Views {
    // @formatter:off
    private Views() {}
    // @formatter:on

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created view.
     * @param array The array of which to create a view.
     * @return A new view instance, which references the given array.
     */
    public static <T> @NotNull View<T> from(final @NotNull T[] array) {
        return new ArrayView<>(array);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Collection}.
     * <h2>Note</h2>
     * That if your given collection is a {@link List}, {@link Queue},
     * {@link Deque}, {@link Stack} or {@link Map}, you should use the
     * specialized functions provided by this class.
     *
     * @param <T>        The element type of the given array, and the newly created view.
     * @param collection The collection of which to create a view.
     * @return A new view instance, which references the given array.
     */
    public static <T> @NotNull View<T> from(final @NotNull Collection<T> collection) {
        return new CollectionView<>(collection);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created view.
     * @param list The list of which to create a view.
     * @return A new view instance, which references the given list.
     */
    public static <T> @NotNull View<T> fromList(final @NotNull List<T> list) {
        return new ListView<>(list);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Queue}.
     *
     * @param <T>   The element type of the given queue, and the newly created view.
     * @param queue The queue of which to create a view.
     * @return A new view instance, which references the given queue.
     */
    public static <T> @NotNull QueueView<T> fromQueue(final @NotNull Queue<T> queue) {
        return new QueueViewImpl<>(queue);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Deque}.
     *
     * @param <T>   The element type of the given deque, and the newly created view.
     * @param deque The deque of which to create a view.
     * @return A new view instance, which references the given deque.
     */
    public static <T> @NotNull DequeView<T> fromDeque(final @NotNull Deque<T> deque) {
        return new DequeViewImpl<>(deque);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created view.
     * @param stack The stack of which to create a view.
     * @return A new view instance, which references the given stack.
     */
    public static <T> @NotNull StackView<T> fromStack(final @NotNull Stack<T> stack) {
        return new StackViewImpl<>(stack);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Map}.
     *
     * @param <K> The key type of the given map and view to create.
     * @param <V> The value type of the given map and view to create.
     * @param map The map of which to create a view.
     * @return A new view instance, which references the given map.
     */
    public static <K, V> @NotNull MapView<K, V> fromMap(final @NotNull Map<K, V> map) {
        return new MapViewImpl<>(map);
    }
}