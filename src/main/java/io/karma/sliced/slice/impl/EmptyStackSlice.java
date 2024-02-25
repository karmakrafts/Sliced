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

package io.karma.sliced.slice.impl;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.StackSlice;
import io.karma.sliced.util.NopIterator;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyStackSlice<T> implements StackSlice<T> {
    public static final EmptyStackSlice<?> INSTANCE = new EmptyStackSlice<>();

    // @formatter:off
    private EmptyStackSlice() {}
    // @formatter:on

    @Override
    public int offset() {
        return 0;
    }

    @Override
    public T get(final int index) {
        return null;
    }

    @Override
    public @NotNull Slice<T> slice(final int offset, final int size) {
        return this;
    }

    @Override
    public void reset() {

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
        return this;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return NopIterator.instance();
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public T nextElement() {
        return null;
    }
}
