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

import io.karma.sliced.iterator.IntIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Integer}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface IntSlice extends Slice<Integer> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull IntSlice of(final int[] ref, final int start, final int end) {
        return new ArrayIntSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull IntSlice of(final int... ref) {
        return new ArrayIntSlice(ref, 0, ref.length - 1);
    }

    /**
     * Creates a new {@link IntIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link IntIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull IntIterator intIterator();

    /**
     * Creates a new {@link Spliterator.OfInt} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfInt} from the
     *         elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfInt intSpliterator();

    /**
     * Retrieves a {@code int} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code int} value at the given index.
     */
    int getInt(final int index);

    /**
     * Creates a new {@code int} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    int[] toIntArray(final int start, final int end);

    /**
     * Creates a new {@code int} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    default int[] toIntArray() {
        return toIntArray(0, size() - 1);
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

    /**
     * Creates a new non-parallel {@link IntStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link IntStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull IntStream intStream() {
        return StreamSupport.intStream(intSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link IntStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link IntStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull IntStream parallelIntStream() {
        return StreamSupport.intStream(intSpliterator(), true);
    }

    @Override
    default @NotNull Slice<Integer> asSlice() {
        return this;
    }

    @Override
    default @NotNull Iterator<Integer> iterator() {
        return intIterator();
    }
}
