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
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 14/08/2022
 */
@API(status = Status.INTERNAL)
final class MutableStringSliceImpl extends AbstractMutableSlice<Character> implements MutableCharSlice {
    private final CharSequence ref;
    private int iterationIndex = 0;

    MutableStringSliceImpl(final @NotNull CharSequence ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public @NotNull CharSlice[] split(final @NotNull CharSequence delimiter, final int start, final int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        // Find number of delimiters
        final int delimiterLength = delimiter.length();
        int numDelimiters = 0; // # of delimiters total
        int matchingChars = 0; // # of matching characters for the delimiter

        for (int i = actualStart; i <= actualEnd; i++) {
            if (ref.charAt(i) != delimiter.charAt(matchingChars)) {
                matchingChars = 0; // Reset number of matching chars
                continue;
            }

            matchingChars++;

            if (matchingChars == delimiterLength) {
                matchingChars = 0;
                numDelimiters++;
            }
        }

        // Create sub-views
        final int numSlices = numDelimiters + 1;
        final CharSlice[] slices = new CharSlice[numSlices];
        int index = 0;
        int subStart = 0;
        int subEnd = 0;

        matchingChars = 0; // Reset # of matching chars

        for (int i = actualStart; i <= actualEnd; i++) {
            if (ref.charAt(i) != delimiter.charAt(matchingChars)) {
                matchingChars = 0; // Reset number of matching chars
                continue;
            }

            matchingChars++;

            if (matchingChars == delimiterLength) {
                matchingChars = 0;
                subStart = subEnd; // Last end is now the start
                subEnd = Math.max(0, i - delimiterLength);
                slices[index++] = new StringCharSliceImpl(ref, subStart, subEnd);
            }
        }

        return slices;
    }

    @Override
    public @NotNull CharSlice[] split(final char delimiter, final int start, final int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        // Find # of delimiters
        int numDelimiters = 0;

        for (int i = actualStart; i <= actualEnd; i++) {
            if (ref.charAt(i) != delimiter) {
                continue;
            }

            numDelimiters++;
        }

        // Create sub-views
        final int numSlices = numDelimiters + 1;
        final CharSlice[] slices = new CharSlice[numSlices];
        int index = 0;
        int subStart = 0;
        int subEnd = 0;

        for (int i = actualStart; i <= actualEnd; i++) {
            if (ref.charAt(i) != delimiter) {
                continue;
            }

            subStart = subEnd;
            subEnd = Math.max(0, i - 1);
            slices[index++] = new StringCharSliceImpl(ref, subStart, subEnd);
        }

        return slices;
    }

    @Override
    public @NotNull CharSlice trimLeading() {
        int start = 0;

        while (ref.charAt(start) == ' ') {
            start++;
        }

        return new StringCharSliceImpl(ref, start, end);
    }

    @Override
    public @NotNull CharSlice trimTrailing() {
        int end = length() - 1;

        while (ref.charAt(end) == ' ') {
            end--;
        }

        return new StringCharSliceImpl(ref, start, end);
    }

    @Override
    public @NotNull Slice<Character> slice(final int start, final int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        return new StringCharSliceImpl(ref, actualStart, actualEnd);
    }

    @Override
    public char[] toCharArray(final int start, final int end) {
        if (ref instanceof String) {
            return ((String) ref).toCharArray();
        }

        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        final int size = actualEnd - actualStart;
        final char[] chars = new char[size];
        int index = 0;

        for (int i = actualStart; i <= actualEnd; i++) {
            chars[index++] = ref.charAt(i);
        }

        return chars;
    }

    @Override
    public @NotNull Character @NotNull [] toArray(final int start, final int end, final @NotNull IntFunction<Character[]> factory) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        final int size = actualEnd - actualStart;
        final Character[] result = factory.apply(size);
        int index = 0;

        for (int i = actualStart; i <= actualEnd; i++) {
            result[index++] = ref.charAt(i);
        }

        return result;
    }

    @Override
    public <C extends Collection<Character>> @NotNull C copy(final int start, final int end, final @NotNull IntFunction<C> factory) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        final int size = actualEnd - actualStart;
        final C result = factory.apply(size);

        for (int i = actualStart; i <= actualEnd; i++) {
            result.add(ref.charAt(i));
        }

        return result;
    }

    @Override
    public char charAt(final int index) {
        return ref.charAt(start + index);
    }

    @Override
    public @NotNull Iterator<Character> iterator() {
        return new RangedStringIterator(ref, start, end);
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public @NotNull Character nextElement() {
        return ref.charAt(start + iterationIndex++);
    }

    @Override
    public char current() {
        return ref.charAt(start + iterationIndex);
    }

    @Override
    public char next() {
        return ref.charAt(start + ++iterationIndex);
    }

    @Override
    public char previous() {
        return ref.charAt(start + --iterationIndex);
    }

    @Override
    public char setIndex(final int position) {
        return ref.charAt(iterationIndex = position);
    }

    @Override
    public int getIndex() {
        return iterationIndex;
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull Object clone() {
        final MutableStringSliceImpl result = new MutableStringSliceImpl(ref, start, end);
        result.iterationIndex = iterationIndex;
        return result;
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final @Nullable Object obj) {
        final boolean isArray = obj instanceof Object[];

        if (!isArray && !(obj instanceof CharSlice)) {
            if (obj instanceof View) {
                int matches = 0;

                for (final char element : (View<? extends Character>) obj) {
                    if (containsRef(element)) {
                        matches++;
                    }
                }

                return matches == size;
            }

            return false;
        }

        // @formatter:off
        final int length = isArray
            ? ((Object[])obj).length
            : ((CharSlice)obj).size();

        final Int2CharFunction getter = isArray
            ? i -> (Character)((Object[])obj)[i]
            : ((CharSlice)obj)::get;
        // @formatter:on

        int matches = 0;

        for (int i = 0; i < length; i++) {
            if (!get(i).equals(getter.apply(i))) {
                break;
            }

            matches++;
        }

        return matches == length;
    }

    @Override
    public @NotNull String toString() {
        return ref.toString();
    }
}
