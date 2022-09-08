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

import io.karma.sliced.view.impl.QueueViewImpl;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * A specialized {@link View} for {@link java.util.Queue},
 * which provides an additional set of functionality over
 * the regular view access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.STABLE)
public interface QueueView<T> extends View<T> {
    /**
     * Creates a new view instance which references the given {@link Queue}.
     *
     * @param <T>   The element type of the given queue, and the newly created view.
     * @param queue The queue of which to create a view.
     * @return A new view instance, which references the given queue.
     */
    static <T> @NotNull QueueView<T> of(final @NotNull Queue<T> queue) {
        return new QueueViewImpl<>(queue);
    }

    /**
     * Retrieves the first element from the queue.
     * Returns null if the queue is empty.
     *
     * @return The first element from the queue. Null if the queue is empty.
     */
    T peek();
}
