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

package io.karma.sliced.slice.mutable.impl;

import io.karma.sliced.iterator.impl.RangedCharSeqCharIterator;
import io.karma.sliced.slice.CharSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.CharSeqSlice;
import io.karma.sliced.slice.mutable.MutableCharSlice;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 14/08/2022
 */
@API(status = Status.INTERNAL)
public final class MutableCharSeqSlice extends AbstractMutableSlice<Character> implements MutableCharSlice {
    private final CharSequence ref;
    private int iterationIndex = 0;

    public MutableCharSeqSlice(final @NotNull CharSequence ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public @NotNull CharSlice trimLeading() {
        int start = 0;

        while (ref.charAt(start) == ' ') {
            start++;
        }

        return new CharSeqSlice(ref, start, size);
    }

    @Override
    public @NotNull CharSlice trimTrailing() {
        int end = length() - 1;

        while (ref.charAt(end) == ' ') {
            end--;
        }

        return new CharSeqSlice(ref, offset, end);
    }

    @Override
    public char[] toCharArray(final int offset, final int size) {
        if (offset < 0 || size < 0 || size > this.size || offset >= size) {
            throw new ArrayIndexOutOfBoundsException("Invalid index range");
        }

        final char[] result = new char[size];

        for (int i = 0; i < size; i++) {
            result[i] = getChar(i);
        }

        return result;
    }

    @Override
    public @NotNull Slice<Character> slice(final int offset, final int size) {
        return new MutableCharSeqSlice(ref, this.offset + offset, size);
    }

    @Override
    public char getChar(final int index) {
        return ref.charAt(offset + index);
    }

    @Override
    public @NotNull Iterator<Character> iterator() {
        return new RangedCharSeqCharIterator(ref, offset, size);
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public @NotNull Character nextElement() {
        return ref.charAt(offset + iterationIndex++);
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull Object clone() {
        final MutableCharSeqSlice result = new MutableCharSeqSlice(ref, offset, size);
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
        return toString().hashCode(); // TODO: make this more efficient
        // I did not, because i am lazy lol
        // @ the next person who reads this, you need to fix it now.
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof MutableCharSeqSlice) {
            final MutableCharSeqSlice other = (MutableCharSeqSlice) obj;
            return other.ref.equals(ref) && other.offset == offset && other.size == size;
        }
        else if (obj instanceof Slice) {
            final Slice<?> slice = (Slice<?>) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (get(i) != slice.get(i)) {
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
                if (get(i) != itr.next()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return new String(toCharArray());
    }
}
