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
 * A mutable slice is a subtype of a regular {@link Slice},
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@API(status = Status.STABLE)
public interface MutableSlice<T> extends Slice<T> {
    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Sets the start- and end-index of this slice instance.
     *
     * @param start The index at which this slice begins.
     * @param end   The index at which this slice ends (inclusive).
     */
    void setRange(final int start, final int end);
}
