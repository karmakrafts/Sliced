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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <TT>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull TT[] array, final int start, final int end) {
        return new MutableArraySlice<>(array, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <TT>  The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull TT[] array) {
        return new MutableArraySlice<>(array, 0, array.length - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <TT>       The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @param start      The index at which the newly created slice should begin.
     * @param end        The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given list.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull Collection<TT> collection, final int start, final int end) {
        return new MutableListSlice<>(new ArrayList<>(collection), start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <TT>       The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull Collection<TT> collection) {
        return new MutableListSlice<>(new ArrayList<>(collection), 0, collection.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <TT>  The element type of the given list, and the newly created slice.
     * @param list  The list of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given list.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull List<TT> list, final int start, final int end) {
        return new MutableListSlice<>(list, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <TT> The element type of the given list, and the newly created slice.
     * @param list The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <TT> @NotNull MutableSlice<TT> of(final @NotNull List<TT> list) {
        return new MutableListSlice<>(list, 0, list.size() - 1);
    }

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
