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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * <h2>Information</h2>
 * Provides static factory functions for creating slices of
 * supported collection/array types.
 * <h2>Note</h2>
 * If you want to create a view, create it using {@link Views}
 * instead of converting from a {@link Slice}.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public final class Slices {
    // @formatter:off
    private Slices() {}
    // @formatter:on

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new slice instance, which references the given array.
     */
    public static <T> @NotNull Slice<T> from(final @NotNull T[] array, final int start, final int end) {
        return new ArraySlice<>(array, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new slice instance, which references the given array.
     */
    public static <T> @NotNull Slice<T> from(final @NotNull T[] array) {
        return new ArraySlice<>(array, 0, array.length - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given array.
     */
    public static <T> @NotNull MutableSlice<T> mutableFrom(final @NotNull T[] array, final int start, final int end) {
        return new MutableArraySlice<>(array, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    public static <T> @NotNull MutableSlice<T> mutableFrom(final @NotNull T[] array) {
        return new MutableArraySlice<>(array, 0, array.length - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @param start      The index at which the newly created slice should begin.
     * @param end        The index at which the newly created slice should end.
     * @return A new slice instance, which references the given list.
     */
    public static <T> @NotNull Slice<T> from(final @NotNull Collection<T> collection, final int start, final int end) {
        return new ListSlice<>(new ArrayList<>(collection), start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @return A new slice instance, which references the given list.
     */
    public static <T> @NotNull Slice<T> from(final @NotNull Collection<T> collection) {
        return new ListSlice<>(new ArrayList<>(collection), 0, collection.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @param start      The index at which the newly created slice should begin.
     * @param end        The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given list.
     */
    public static <T> @NotNull MutableSlice<T> mutableFrom(final @NotNull Collection<T> collection, final int start, final int end) {
        return new MutableListSlice<>(new ArrayList<>(collection), start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new mutable slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    public static <T> @NotNull MutableSlice<T> mutableFrom(final @NotNull Collection<T> collection) {
        return new MutableListSlice<>(new ArrayList<>(collection), 0, collection.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given {@link List}.
     *
     * @param <T>   The element type of the given list, and the newly created slice.
     * @param list  The list of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new slice instance, which references the given list.
     */
    public static <T> @NotNull Slice<T> fromList(final @NotNull List<T> list, final int start, final int end) {
        return new ListSlice<>(list, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created slice.
     * @param list The list of which to create a slice.
     * @return A new slice instance, which references the given list.
     */
    public static <T> @NotNull Slice<T> fromList(final @NotNull List<T> list) {
        return new ListSlice<>(list, 0, list.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <T>   The element type of the given list, and the newly created slice.
     * @param list  The list of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given list.
     */
    public static <T> @NotNull MutableSlice<T> mutableFromList(final @NotNull List<T> list, final int start, final int end) {
        return new MutableListSlice<>(list, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created slice.
     * @param list The list of which to create a slice.
     * @return A new mutable slice instance, which references the given list.
     */
    public static <T> @NotNull MutableSlice<T> mutableFromList(final @NotNull List<T> list) {
        return new MutableListSlice<>(list, 0, list.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new slice instance, which references the given stack.
     */
    public static <T> @NotNull StackSlice<T> fromStack(final @NotNull Stack<T> stack, final int start, final int end) {
        return new StackSliceImpl<>(stack, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @return A new slice instance, which references the given stack.
     */
    public static <T> @NotNull StackSlice<T> fromStack(final @NotNull Stack<T> stack) {
        return new StackSliceImpl<>(stack, 0, stack.size() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given stack.
     */
    public static <T> @NotNull MutableStackSlice<T> mutableFromStack(final @NotNull Stack<T> stack, final int start, final int end) {
        return new MutableStackSliceImpl<>(stack, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new mutable slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @return A new mutable slice instance, which references the given stack.
     */
    public static <T> @NotNull MutableStackSlice<T> mutableFromStack(final @NotNull Stack<T> stack) {
        return new MutableStackSliceImpl<>(stack, 0, stack.size() - 1);
    }
}