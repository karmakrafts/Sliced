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

import io.karma.sliced.function.FloatFunction;
import io.karma.sliced.iterator.FloatIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Float}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface FloatSlice extends Slice<Float> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull FloatSlice of(final float[] ref, final int start, final int end) {
        return new ArrayFloatSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull FloatSlice of(final float... ref) {
        return new ArrayFloatSlice(ref, 0, ref.length - 1);
    }

    /**
     * Creates a new {@link FloatIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link FloatIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull FloatIterator floatIterator();

    /**
     * Retrieves a {@code float} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code float} value at the given index.
     */
    float getFloat(final int index);

    /**
     * Creates a new {@code float} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    float[] toFloatArray(final int start, final int end);

    /**
     * Creates a new {@code float} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    default float[] toFloatArray() {
        return toFloatArray(0, size() - 1);
    }

    /**
     * Maps the {@code float} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull FloatFunction<R> function) {
        return function.apply(getFloat(index));
    }

    @Override
    default @NotNull Slice<Float> asSlice() {
        return this;
    }

    @Override
    default @NotNull Iterator<Float> iterator() {
        return floatIterator();
    }
}
