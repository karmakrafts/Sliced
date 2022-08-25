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

package io.karma.sliced.view;

import io.karma.sliced.slice.ArraySlice;
import io.karma.sliced.iterator.ArrayIterator;
import io.karma.sliced.slice.Slice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayView<T> implements View<T> {
    private final T[] ref;

    public ArrayView(final @NotNull T[] ref) {
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

    @Override
    public @NotNull Iterator<T> iterator() {
        return new ArrayIterator<>(ref);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ref);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.equals(ref, (Object[]) obj);
        }
        else if (obj instanceof View) {
            for (final T element : (View<? extends T>) obj) {
                if (!containsRef(element)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
