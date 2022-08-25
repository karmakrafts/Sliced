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

package io.karma.sliced.slice.mutable;

import io.karma.sliced.slice.StackSlice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * A mutable stack slice is a subtype of a regular {@link StackSlice},
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@API(status = Status.STABLE)
public interface MutableStackSlice<T> extends MutableSlice<T>, StackSlice<T> {
    /**
     * Creates a new mutable slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @param start The index at which the newly created slice should begin.
     * @param end   The index at which the newly created slice should end.
     * @return A new mutable slice instance, which references the given stack.
     */
    static <T> @NotNull MutableStackSlice<T> of(final @NotNull Stack<T> stack, final int start, final int end) {
        return new MutableStackSliceImpl<>(stack, start, end);
    }

    /**
     * Creates a new mutable slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @return A new mutable slice instance, which references the given stack.
     */
    static <T> @NotNull MutableStackSlice<T> of(final @NotNull Stack<T> stack) {
        return new MutableStackSliceImpl<>(stack, 0, stack.size() - 1);
    }
}
