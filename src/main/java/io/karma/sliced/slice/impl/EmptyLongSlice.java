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

import io.karma.sliced.iterator.LongIterator;
import io.karma.sliced.slice.LongSlice;
import io.karma.sliced.slice.Slice;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Spliterator;
import java.util.Spliterators;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyLongSlice implements LongSlice {
    public static final EmptyLongSlice INSTANCE = new EmptyLongSlice();

    // @formatter:off
    private EmptyLongSlice() {}
    // @formatter:on

    @Override
    public long getLong(final int index) {
        return 0;
    }

    @Override
    public long[] toLongArray(final int offset, final int size) {
        return new long[0];
    }

    @Override
    public int offset() {
        return 0;
    }

    @Override
    public @NotNull Slice<Long> slice(final int offset, final int size) {
        return this;
    }

    @Override
    public void reset() {

    }

    @Override
    public @NotNull LongIterator longIterator() {
        return LongIterator.NOP;
    }

    @Override
    public @NotNull Spliterator.OfLong longSpliterator() {
        return Spliterators.emptyLongSpliterator();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public Long nextElement() {
        return null;
    }
}
