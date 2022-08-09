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

/**
 * <h2>Information</h2>
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
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Retrieves the first element from the queue.
     * Returns null if the queue is empty.
     *
     * @return The first element from the queue. Null if the queue is empty.
     */
    T peek();
}
