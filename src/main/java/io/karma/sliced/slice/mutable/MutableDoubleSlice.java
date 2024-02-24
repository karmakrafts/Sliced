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

package io.karma.sliced.slice.mutable;

import io.karma.sliced.slice.DoubleSlice;
import io.karma.sliced.slice.mutable.impl.MutableArrayDoubleSlice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * A mutable slice is a subtype of a regular {@link DoubleSlice},
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface MutableDoubleSlice extends MutableSlice<Double>, DoubleSlice {
    /**
     * Creates a new mutable slice instance which references the given array.
     *
     * @param ref    The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull MutableDoubleSlice of(final double[] ref, final int offset, final int size) {
        return new MutableArrayDoubleSlice(ref, offset, size);
    }

    /**
     * Creates a new mutable slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull MutableDoubleSlice of(final double... ref) {
        return new MutableArrayDoubleSlice(ref, 0, ref.length);
    }
}
