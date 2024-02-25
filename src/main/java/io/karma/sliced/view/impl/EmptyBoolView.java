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

import io.karma.sliced.iterator.BoolIterator;
import io.karma.sliced.slice.BoolSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.BoolView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyBoolView implements BoolView {
    public static final EmptyBoolView INSTANCE = new EmptyBoolView();

    // @formatter:off
    private EmptyBoolView() {}
    // @formatter:on

    @Override
    public @NotNull BoolIterator boolIterator() {
        return BoolIterator.NOP;
    }

    @Override
    public boolean[] toBoolArray() {
        return new boolean[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Boolean> asSlice() {
        return BoolSlice.empty();
    }
}