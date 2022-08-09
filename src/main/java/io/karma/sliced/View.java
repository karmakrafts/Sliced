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
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public interface View<T> extends Iterable<T> {
    int size();

    @NotNull T[] toArray(final @NotNull IntFunction<T[]> factory);

    <C extends Collection<T>> @NotNull C copy(final @NotNull Supplier<C> factory);

    @NotNull Slice<T> asSlice();

    default boolean contains(final @Nullable T value) {
        if (value == null) {
            return false;
        }

        for (final T element : this) {
            if (!element.equals(value)) {
                continue;
            }

            return true;
        }

        return false;
    }

    default @NotNull Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default @NotNull Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    default void forEachIndexed(final @NotNull ObjIntConsumer<T> consumer) {
        int index = 0;

        for (final T element : this) {
            consumer.accept(element, index++);
        }
    }
}