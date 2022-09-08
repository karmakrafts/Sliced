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

package io.karma.sliced.slice.impl;

import io.karma.sliced.iterator.impl.RangedListIterator;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.INTERNAL)
public final class ListSlice<T, L extends List<T>> extends AbstractSlice<T> {
    private final L ref;
    private int iterationIndex;

    public ListSlice(final @NotNull L ref, final int offset, final int size) {
        super(offset, size);
        this.ref = ref;
    }

    @Override
    public T get(final int index) {
        return ref.get(offset + index);
    }

    @Override
    public @NotNull Slice<T> slice(final int offset, final int size) {
        return new ListSlice<>(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return new RangedListIterator<>(ref, offset, size);
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public T nextElement() {
        return ref.get(offset + iterationIndex++);
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    // Object functions

    @Override
    public int hashCode() {
        return Objects.hash(ref, offset, size);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ListSlice) {
            final ListSlice<?, ?> other = (ListSlice<?, ?>) obj;
            return other.ref.equals(ref) && other.offset == offset && other.size == size;
        }
        else if (obj instanceof Slice) {
            final Slice<?> slice = (Slice<?>) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!get(i).equals(slice.get(i))) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof View) {
            final View<?> view = (View<?>) obj;
            final int size = view.size();

            if (size != this.size) {
                return false;
            }

            final Iterator<?> itr = view.iterator();

            for (int i = 0; i < size; i++) {
                if (!get(i).equals(itr.next())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        final int size = size();
        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(get(i));

            if (i < size - 1) {
                builder.append(',');
            }
        }

        return builder.append(']').toString();
    }
}