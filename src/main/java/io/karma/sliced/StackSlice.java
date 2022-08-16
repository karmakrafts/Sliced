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

import java.util.Stack;

/**
 * <h2>Information</h2>
 * A specialized {@link Slice} for {@link java.util.Stack},
 * which provides an additional set of functionality over
 * the regular slice access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public interface StackSlice<T> extends Slice<T>, StackView<T> {
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
    static <T> @NotNull StackSlice<T> of(final @NotNull Stack<T> stack, final int start, final int end) {
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
    static <T> @NotNull StackSlice<T> of(final @NotNull Stack<T> stack) {
        return new StackSliceImpl<>(stack, 0, stack.size() - 1);
    }
}
