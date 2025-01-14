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

package io.karma.sliced.slice;

import io.karma.sliced.slice.impl.EmptyStackSlice;
import io.karma.sliced.slice.impl.StackSliceImpl;
import io.karma.sliced.view.StackView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
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
     * Creates a new stack slice of the given type which has no elements.
     *
     * @param <T> The type of the slice to be created.
     * @return A new stack slice of the given type which has no elements.
     */
    @SuppressWarnings("unchecked")
    static <T> StackSlice<T> empty() {
        return (StackSlice<T>) EmptyStackSlice.INSTANCE;
    }

    /**
     * Creates a new slice instance which references the given {@link Stack}.
     *
     * @param <T>    The element type of the given stack, and the newly created slice.
     * @param stack  The stack of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new slice instance, which references the given stack.
     */
    static <T> @NotNull StackSlice<T> of(final @NotNull Stack<T> stack, final int offset, final int size) {
        return new StackSliceImpl<>(stack, offset, size);
    }

    /**
     * Creates a new slice instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created slice.
     * @param stack The stack of which to create a slice.
     * @return A new slice instance, which references the given stack.
     */
    static <T> @NotNull StackSlice<T> of(final @NotNull Stack<T> stack) {
        return new StackSliceImpl<>(stack, 0, stack.size());
    }
}
