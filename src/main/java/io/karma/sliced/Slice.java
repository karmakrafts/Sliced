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
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public interface Slice<T> extends View<T>, ReusableEnumeration<T> {
    @NotNull Slice<T> slice(final int start, final int end);

    int start();

    int end();

    T get(final int index);

    @NotNull T[] toArray(final int start, final int end, final @NotNull IntFunction<T[]> factory);

    <C extends Collection<T>> @NotNull C copy(final int start, final int end, final @NotNull IntFunction<C> factory);

    default <C extends Collection<T>> @NotNull C copy(final @NotNull IntFunction<C> factory) {
        return copy(0, size() - 1, factory);
    }

    @Override
    default int size() {
        return end() - start();
    }
}