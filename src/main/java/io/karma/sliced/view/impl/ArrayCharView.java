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

package io.karma.sliced.view.impl;

import io.karma.sliced.iterator.CharIterator;
import io.karma.sliced.iterator.TextIterator;
import io.karma.sliced.iterator.impl.ArrayCharIterator;
import io.karma.sliced.iterator.impl.ArrayTextIterator;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ArrayCharSlice;
import io.karma.sliced.util.MoreArrays;
import io.karma.sliced.view.CharView;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayCharView implements CharView {
    private final char[] ref;

    public ArrayCharView(final char[] ref) {
        this.ref = ref;
    }

    @Override
    public int size() {
        return ref.length;
    }

    @Override
    public @NotNull Slice<Character> asSlice() {
        return new ArrayCharSlice(ref, 0, ref.length);
    }

    @Override
    public char[] toCharArray() {
        return MoreArrays.copy(ref);
    }

    @Override
    public @NotNull CharIterator charIterator() {
        return new ArrayCharIterator(ref);
    }

    @Override
    public @NotNull TextIterator textIterator() {
        return new ArrayTextIterator(ref);
    }

    @Override
    public int length() {
        return ref.length;
    }

    @Override
    public char charAt(final int index) {
        return ref[index];
    }

    @Override
    public @NotNull CharSequence subSequence(final int start, final int end) {
        final int size = end - start;

        if (size <= 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid subSequence indices");
        }

        final char[] chars = new char[size];
        System.arraycopy(ref, start, chars, 0, size);
        return new String(chars);
    }

    // Object functions

    @Override
    public int hashCode() {
        return Arrays.hashCode(ref);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ArrayCharView) {
            return Arrays.equals(((ArrayCharView) obj).ref, ref);
        }
        else if (obj instanceof CharView) {
            final CharView other = (CharView) obj;
            final int size = other.size();

            if (size != ref.length) {
                return false;
            }

            final CharIterator itr = other.charIterator();

            for (int i = 0; i < size; i++) {
                if (!itr.hasNext() || ref[i] != itr.nextChar()) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof View) {
            final View<?> other = (View<?>) obj;
            final int size = other.size();

            if (size != ref.length) {
                return false;
            }

            final Iterator<?> itr = other.iterator();

            for (int i = 0; i < size; i++) {
                if (!itr.hasNext() || !itr.next().equals(ref[i])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return new String(ref);
    }
}
