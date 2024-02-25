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

import io.karma.sliced.iterator.ShortIterator;
import io.karma.sliced.slice.ShortSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.ShortView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyShortView implements ShortView {
    public static final EmptyShortView INSTANCE = new EmptyShortView();

    // @formatter:off
    private EmptyShortView() {}
    // @formatter:on

    @Override
    public @NotNull ShortIterator shortIterator() {
        return ShortIterator.NOP;
    }

    @Override
    public short[] toShortArray() {
        return new short[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Short> asSlice() {
        return ShortSlice.empty();
    }
}
