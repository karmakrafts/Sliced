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

import io.karma.sliced.slice.CharSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.util.MoreArrays;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 14/08/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayCharSlice extends AbstractSlice<Character> implements CharSlice {
    private final char[] ref;
    private int iterationIndex;

    public ArrayCharSlice(final char[] ref, final int offset, final int size) {
        super(offset, size);
        this.ref = ref;
    }

    @Override
    public @NotNull CharSlice trimLeading() {
        int start = 0;

        while (ref[start] == ' ') {
            start++;
        }

        return new ArrayCharSlice(ref, start, size);
    }

    @Override
    public @NotNull CharSlice trimTrailing() {
        int end = length() - 1;

        while (ref[end] == ' ') {
            end--;
        }

        return new ArrayCharSlice(ref, offset, end);
    }

    @Override
    public char[] toCharArray(final int offset, final int size) {
        return MoreArrays.copy(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull Slice<Character> slice(final int start, final int end) {
        final int actualStart = this.offset + start;
        final int actualEnd = this.offset + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        return new ArrayCharSlice(ref, actualStart, actualEnd);
    }

    @Override
    public char getChar(final int index) {
        return ref[offset + index];
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public @NotNull Character nextElement() {
        return ref[offset + iterationIndex++];
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull Object clone() {
        final ArrayCharSlice result = new ArrayCharSlice(ref, offset, size);
        result.iterationIndex = iterationIndex;
        return result;
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    // Object functions

    @Override
    public int hashCode() {
        return MoreArrays.hashCode(ref, offset, size);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ArrayCharSlice) {
            final ArrayCharSlice other = (ArrayCharSlice) obj;
            return Arrays.equals(other.ref, ref) && other.offset == offset && other.size == size;
        }
        else if (obj instanceof CharSlice) {
            final CharSlice slice = (CharSlice) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (getChar(i) != slice.getChar(i)) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof Slice) {
            final Slice<?> slice = (Slice<?>) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!slice.get(i).equals(getChar(i))) {
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
                if (!itr.next().equals(getChar(i))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return MoreArrays.toString(ref, offset, size);
    }
}
