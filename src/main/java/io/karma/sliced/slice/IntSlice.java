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

package io.karma.sliced.slice;

import io.karma.sliced.slice.impl.ArrayIntSlice;
import io.karma.sliced.slice.impl.EmptyIntSlice;
import io.karma.sliced.view.IntView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Integer}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface IntSlice extends IntView, Slice<Integer> {
    /**
     * Creates a new int slice which has no elements.
     *
     * @return A new int slice which has no elements.
     */
    static IntSlice empty() {
        return EmptyIntSlice.INSTANCE;
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref    The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull IntSlice of(final int[] ref, final int offset, final int size) {
        return new ArrayIntSlice(ref, offset, size);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull IntSlice of(final int... ref) {
        return new ArrayIntSlice(ref, 0, ref.length);
    }

    /**
     * Retrieves a {@code int} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code int} value at the given index.
     */
    int getInt(final int index);

    @Override
    default @NotNull Integer get(final int index) {
        return getInt(index);
    }

    /**
     * Creates a new {@code int} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param offset The index at which the newly created array should begin
     *               (relative to the offset of this slice).
     * @param size   The size of the newly created array.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    int[] toIntArray(final int offset, final int size);

    @Override
    default int[] toIntArray() {
        return toIntArray(0, size());
    }

    /**
     * Maps the {@code int} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull IntFunction<R> function) {
        return function.apply(getInt(index));
    }

    @Override
    default @NotNull Slice<Integer> asSlice() {
        return this;
    }
}
