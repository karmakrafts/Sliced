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

import io.karma.sliced.iterator.LongIterator;
import io.karma.sliced.slice.LongSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.LongView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Spliterator;
import java.util.Spliterators;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyLongView implements LongView {
    public static final EmptyLongView INSTANCE = new EmptyLongView();

    // @formatter:off
    private EmptyLongView() {}
    // @formatter:on

    @Override
    public @NotNull LongIterator longIterator() {
        return LongIterator.NOP;
    }

    @Override
    public @NotNull Spliterator.OfLong longSpliterator() {
        return Spliterators.emptyLongSpliterator();
    }

    @Override
    public long[] toLongArray() {
        return new long[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Long> asSlice() {
        return LongSlice.empty();
    }
}
