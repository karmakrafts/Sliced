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

package io.karma.sliced.view.impl;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.util.NopIterator;
import io.karma.sliced.view.DequeView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyDequeView<T> implements DequeView<T> {
    public static final EmptyDequeView<?> INSTANCE = new EmptyDequeView<>();

    // @formatter:off
    private EmptyDequeView() {}
    // @formatter:on

    @Override
    public T peekLast() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return Slice.empty();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return NopIterator.instance();
    }
}
