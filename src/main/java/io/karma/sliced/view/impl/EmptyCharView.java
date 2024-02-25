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

import io.karma.sliced.slice.CharSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.CharView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyCharView implements CharView {
    public static final EmptyCharView INSTANCE = new EmptyCharView();

    // @formatter:off
    private EmptyCharView() {}
    // @formatter:on

    @Override
    public char[] toCharArray() {
        return new char[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Character> asSlice() {
        return CharSlice.empty();
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(final int i) {
        return 0;
    }

    @NotNull
    @Override
    public CharSequence subSequence(final int i, final int i1) {
        return "";
    }
}
