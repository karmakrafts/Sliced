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
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
final class ArrayView<T> implements View<T> {
    private final T[] ref;

    ArrayView(final @NotNull T[] ref) {
        this.ref = ref;
    }

    @Override
    public int size() {
        return ref.length;
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return new ArraySlice<>(ref, 0, ref.length - 1);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(ref);
    }
}
