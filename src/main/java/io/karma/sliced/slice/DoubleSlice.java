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

import io.karma.sliced.iterator.DoubleIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.DoubleFunction;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization of a regular {@link Slice}&lt;{@link Double}&gt;,
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface DoubleSlice extends Slice<Double> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref   The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull DoubleSlice of(final double[] ref, final int start, final int end) {
        return new ArrayDoubleSlice(ref, start, end);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull DoubleSlice of(final double[] ref) {
        return new ArrayDoubleSlice(ref, 0, ref.length - 1);
    }

    /**
     * Creates a new {@link DoubleIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link DoubleIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull DoubleIterator doubleIterator();

    /**
     * Creates a new {@link Spliterator.OfDouble} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfDouble} from the
     *         elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfDouble doubleSpliterator();

    /**
     * Retrieves a {@code double} value from this
     * slice instance at the given index.
     *
     * @param index The index at which to retrieve the element.
     * @return The {@code double} value at the given index.
     */
    double getDouble(final int index);

    /**
     * Creates a new {@code double} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    double[] toDoubleArray(final int start, final int end);

    /**
     * Creates a new {@code double} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    default double[] toDoubleArray() {
        return toDoubleArray(0, size() - 1);
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

    /**
     * Creates a new non-parallel {@link DoubleStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link DoubleStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull DoubleStream doubleStream() {
        return StreamSupport.doubleStream(doubleSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link DoubleStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link DoubleStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull DoubleStream parallelDoubleStream() {
        return StreamSupport.doubleStream(doubleSpliterator(), true);
    }

    @Override
    default @NotNull Slice<Double> asSlice() {
        return this;
    }

    @Override
    default @NotNull Iterator<Double> iterator() {
        return doubleIterator();
    }
}
