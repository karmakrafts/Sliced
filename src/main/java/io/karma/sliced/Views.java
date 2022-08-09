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
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public final class Views {
    // @formatter:off
    private Views() {}
    // @formatter:on

    public static <T> @NotNull View<T> from(final @NotNull Collection<T> collection) {
        return new CollectionView<>(collection);
    }

    public static <T> @NotNull View<T> fromList(final @NotNull List<T> list) {
        return new ListView<>(list);
    }

    public static <T> @NotNull QueueView<T> fromQueue(final @NotNull Queue<T> queue) {
        return new QueueViewImpl<>(queue);
    }

    public static <T> @NotNull DequeView<T> fromDeque(final @NotNull Deque<T> deque) {
        return new DequeViewImpl<>(deque);
    }

    public static <T> @NotNull StackView<T> fromStack(final @NotNull Stack<T> stack) {
        return new StackViewImpl<>(stack);
    }

    public static <K, V> @NotNull MapView<K, V> fromMap(final @NotNull Map<K, V> map) {
        return new MapViewImpl<>(map);
    }
}