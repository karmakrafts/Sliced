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

package io.karma.sliced.slice;

import io.karma.sliced.function.BoolFunction;
import io.karma.sliced.view.BoolView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Boolean}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface BoolSlice extends BoolView, Slice<Boolean> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull BoolSlice of(final boolean[] ref, final int start, final int end) {
        return new ArrayBoolSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull BoolSlice of(final boolean[] ref) {
        return new ArrayBoolSlice(ref, 0, ref.length - 1);
    }

    /**
     * Retrieves a {@code boolean} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code boolean} value at the given index.
     */
    boolean getBool(final int index);

    /**
     * Creates a new {@code boolean} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    boolean[] toBoolArray(final int start, final int end);

    @Override
    default boolean[] toBoolArray() {
        return toBoolArray(0, size() - 1);
    }

    /**
     * Maps the {@code boolean} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull BoolFunction<R> function) {
        return function.apply(getBool(index));
    }

    @Override
    default @NotNull Slice<Boolean> asSlice() {
        return this;
    }
}
