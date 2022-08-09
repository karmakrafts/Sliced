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
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 10/08/2022
 */
@API(status = Status.INTERNAL)
final class SliceView<T> implements View<T> {
    private final Slice<T> slice;

    SliceView(final @NotNull Slice<T> slice) {
        this.slice = slice;
    }

    @Override
    public int size() {
        return slice.size();
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return slice;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return slice.iterator();
    }
}
