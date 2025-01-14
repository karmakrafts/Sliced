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

import io.karma.sliced.slice.impl.ArrayDoubleSlice;
import io.karma.sliced.slice.impl.EmptyDoubleSlice;
import io.karma.sliced.view.DoubleView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleFunction;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Double}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface DoubleSlice extends DoubleView, Slice<Double> {
    /**
     * Creates a new double slice which has no elements.
     *
     * @return A new double slice which has no elements.
     */
    static DoubleSlice empty() {
        return EmptyDoubleSlice.INSTANCE;
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref    The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull DoubleSlice of(final double[] ref, final int offset, final int size) {
        return new ArrayDoubleSlice(ref, offset, size);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull DoubleSlice of(final double... ref) {
        return new ArrayDoubleSlice(ref, 0, ref.length);
    }

    /**
     * Retrieves a {@code double} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code double} value at the given index.
     */
    double getDouble(final int index);

    @Override
    default @NotNull Double get(final int index) {
        return getDouble(index);
    }

    /**
     * Creates a new {@code double} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param offset The index at which the newly created array should begin
     *               (relative to the offset of this slice).
     * @param size   The size of the newly created array.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    double[] toDoubleArray(final int offset, final int size);

    @Override
    default double[] toDoubleArray() {
        return toDoubleArray(0, size());
    }

    /**
     * Maps the {@code double} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull DoubleFunction<R> function) {
        return function.apply(getDouble(index));
    }

    @Override
    default @NotNull Slice<Double> asSlice() {
        return this;
    }
}
