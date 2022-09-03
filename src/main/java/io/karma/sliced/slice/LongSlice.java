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

import io.karma.sliced.iterator.LongIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.LongFunction;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Long}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface LongSlice extends Slice<Long> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull LongSlice of(final long[] ref, final int start, final int end) {
        return new ArrayLongSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull LongSlice of(final long... ref) {
        return new ArrayLongSlice(ref, 0, ref.length - 1);
    }

    /**
     * Creates a new {@link LongIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link LongIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull LongIterator longIterator();

    /**
     * Creates a new {@link Spliterator.OfLong} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfLong} from the
     *         elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfLong longSpliterator();

    /**
     * Retrieves a {@code long} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code long} value at the given index.
     */
    long getLong(final int index);

    /**
     * Creates a new {@code long} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    long[] toLongArray(final int start, final int end);

    /**
     * Creates a new {@code long} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    default long[] toLongArray() {
        return toLongArray(0, size() - 1);
    }

    /**
     * Maps the {@code long} value at the given index to
     * a new object of type {@link R}.
     *
     * @param index    The index of the value to map.
     * @param function The function to map the value with.
     * @param <R>      The return value type.
     * @return A new object of type {@link R}.
     */
    default <R> R map(final int index, final @NotNull LongFunction<R> function) {
        return function.apply(getLong(index));
    }

    /**
     * Creates a new non-parallel {@link LongStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link LongStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull LongStream longStream() {
        return StreamSupport.longStream(longSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link LongStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link LongStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull LongStream parallelLongStream() {
        return StreamSupport.longStream(longSpliterator(), true);
    }

    @Override
    default @NotNull Slice<Long> asSlice() {
        return this;
    }

    @Override
    default @NotNull Iterator<Long> iterator() {
        return longIterator();
    }
}
