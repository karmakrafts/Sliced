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

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.mutable.impl.MutableArraySlice;
import io.karma.sliced.slice.mutable.impl.MutableListSlice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
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
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <T>    The element type of the given array, and the newly created slice.
     * @param array  The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static <T> @NotNull MutableSlice<T> of(final @NotNull T[] array, final int offset, final int size) {
        return new MutableArraySlice<>(array, offset, size);
    }

    /**
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    @SafeVarargs
    static <T> @NotNull MutableSlice<T> of(final @NotNull T... array) {
        return new MutableArraySlice<>(array, 0, array.length);
    }

    /**
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @param offset     The index at which the newly created slice should begin.
     * @param size       The size of the newly created slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <T> @NotNull MutableSlice<T> of(final @NotNull Collection<T> collection, final int offset, final int size) {
        return new MutableListSlice<>(new ArrayList<>(collection), offset, size);
    }

    /**
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <T> @NotNull MutableSlice<T> of(final @NotNull Collection<T> collection) {
        return new MutableListSlice<>(new ArrayList<>(collection), 0, collection.size());
    }

    /**
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <T>    The element type of the given list, and the newly created slice.
     * @param list   The list of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <T> @NotNull MutableSlice<T> of(final @NotNull List<T> list, final int offset, final int size) {
        return new MutableListSlice<>(list, offset, size);
    }

    /**
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created slice.
     * @param list The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    static <T> @NotNull MutableSlice<T> of(final @NotNull List<T> list) {
        return new MutableListSlice<>(list, 0, list.size());
    }

    /**
     * Sets the starting offset of the current slice instance.
     *
     * @param offset The new starting offset of this slice instance.
     */
    void setOffset(final int offset);

    /**
     * Sets the size of the current slice instance.
     *
     * @param size The new size of this slice instance.
     */
    void setSize(final int size);
}
