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

package io.karma.sliced.view;

import io.karma.sliced.view.impl.EmptyStackView;
import io.karma.sliced.view.impl.StackViewImpl;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * A specialized {@link View} for {@link java.util.Stack},
 * which provides an additional set of functionality over
 * the regular view access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.STABLE)
public interface StackView<T> extends View<T> {
    /**
     * Creates an empty stack view for the given type which has no elements.
     *
     * @return An empty stack view for the given type which has no elements.
     */
    @SuppressWarnings("unchecked")
    static <T> StackView<T> empty() {
        return (StackView<T>) EmptyStackView.INSTANCE;
    }

    /**
     * Creates a new view instance which references the given {@link Stack}.
     *
     * @param <T>   The element type of the given stack, and the newly created view.
     * @param stack The stack of which to create a view.
     * @return A new view instance, which references the given stack.
     */
    static <T> @NotNull StackView<T> of(final @NotNull Stack<T> stack) {
        return new StackViewImpl<>(stack);
    }

    /**
     * Retrieves the current, topmost element on the stack.
     * Returns null if the stack is empty.
     *
     * @return The current topmost element on the stack. Null if the stack is empty.
     */
    T peek();
}
