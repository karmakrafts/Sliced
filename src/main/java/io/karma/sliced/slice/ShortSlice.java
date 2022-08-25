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

import io.karma.sliced.function.ShortFunction;
import io.karma.sliced.iterator.ShortIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Short}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface ShortSlice extends Slice<Short> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull ShortSlice of(final short[] ref, final int start, final int end) {
        return new ArrayShortSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull ShortSlice of(final short[] ref) {
        return new ArrayShortSlice(ref, 0, ref.length - 1);
    }

    /**
     * Creates a new {@link ShortIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link ShortIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull ShortIterator shortIterator();

    /**
     * Retrieves a {@code short} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code short} value at the given index.
     */
    short getShort(final int index);

    /**
     * Creates a new {@code short} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    short[] toShortArray(final int start, final int end);

    /**
     * Creates a new {@code short} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    default short[] toShortArray() {
        return toShortArray(0, size() - 1);
    }

    /**
     * Maps the {@code short} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull ShortFunction<R> function) {
        return function.apply(getShort(index));
    }

    @Override
    default @NotNull Slice<Short> asSlice() {
        return this;
    }

    @Override
    default @NotNull Iterator<Short> iterator() {
        return shortIterator();
    }
}
