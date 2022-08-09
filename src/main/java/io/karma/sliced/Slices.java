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

import java.util.List;
import java.util.Stack;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public final class Slices {
    // @formatter:off
    private Slices() {}
    // @formatter:on

    public static <T> @NotNull Slice<T> from(final @NotNull T[] array, final int start, final int end) {
        return new ArraySlice<>(array, start, end);
    }

    public static <T> @NotNull Slice<T> from(final @NotNull T[] array) {
        return new ArraySlice<>(array, 0, array.length - 1);
    }

    public static <T> @NotNull Slice<T> from(final @NotNull List<T> list, final int start, final int end) {
        return new ListSlice<>(list, start, end);
    }

    public static <T> @NotNull Slice<T> from(final @NotNull List<T> list) {
        return new ListSlice<>(list, 0, list.size() - 1);
    }

    public static <T> @NotNull StackSlice<T> fromStack(final @NotNull Stack<T> stack, final int start, final int end) {
        return new StackSliceImpl<>(stack, start, end);
    }

    public static <T> @NotNull StackSlice<T> fromStack(final @NotNull Stack<T> stack) {
        return new StackSliceImpl<>(stack, 0, stack.size() - 1);
    }
}