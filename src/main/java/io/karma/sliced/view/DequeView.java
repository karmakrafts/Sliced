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

package io.karma.sliced.view;

import io.karma.sliced.view.impl.DequeViewImpl;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

/**
 * A specialized {@link View} for {@link java.util.Deque},
 * which provides an additional set of functionality over
 * the regular view access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.STABLE)
public interface DequeView<T> extends QueueView<T> {
    /**
     * Creates a new view instance which references the given {@link Deque}.
     *
     * @param <T>   The element type of the given deque, and the newly created view.
     * @param deque The deque of which to create a view.
     * @return A new view instance, which references the given deque.
     */
    static <T> @NotNull DequeView<T> of(final @NotNull Deque<T> deque) {
        return new DequeViewImpl<>(deque);
    }

    /**
     * Retrieves the last element from the deque.
     * Returns null if the deque is empty.
     *
     * @return The first element from the deque. Null if the deque is empty.
     */
    T peekLast();
}
