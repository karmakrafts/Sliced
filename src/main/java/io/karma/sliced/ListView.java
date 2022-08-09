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
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.INTERNAL)
class ListView<T> implements View<T> {
    protected final List<T> ref;

    ListView(final @NotNull List<T> ref) {
        this.ref = ref;
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return new ListSlice<>(ref, 0, ref.size() - 1);
    }

    @Override
    public int size() {
        return ref.size();
    }

    @Override
    public <C extends Collection<T>> @NotNull C copy(@NotNull Supplier<C> factory) {
        final C result = factory.get();

        for(final T element : this) {
            result.add(element);
        }

        return result;
    }

    @Override
    public @NotNull T[] toArray(@NotNull IntFunction<T[]> factory) {
        final int size = size();
        final T[] result = factory.apply(size);
        int index = 0;

        for(final T element : this) {
            result[index++] = element;
        }

        return result;
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return ref.iterator();
    }
}