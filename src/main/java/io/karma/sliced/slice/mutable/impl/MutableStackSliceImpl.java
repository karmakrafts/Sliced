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

package io.karma.sliced.slice.mutable.impl;

import io.karma.sliced.iterator.impl.RangedListIterator;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.mutable.MutableStackSlice;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@API(status = Status.INTERNAL)
public final class MutableStackSliceImpl<T, S extends Stack<T>> extends AbstractMutableSlice<T>
    implements MutableStackSlice<T> {
    private final S ref;
    private int iterationIndex;

    public MutableStackSliceImpl(final @NotNull S ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public T get(final int index) {
        return ref.get(offset + index);
    }

    @Override
    public T peek() {
        return get(size);
    }

    @Override
    public @NotNull Slice<T> slice(final int offset, final int size) {
        return new MutableStackSliceImpl<>(ref, this.offset + offset, size);
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
        if (obj instanceof MutableStackSliceImpl) {
            final MutableStackSliceImpl<?, ?> other = (MutableStackSliceImpl<?, ?>) obj;
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
