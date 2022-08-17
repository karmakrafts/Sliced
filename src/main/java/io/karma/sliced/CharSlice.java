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

/**
 * <h2>Information</h2>
 * A char slice is a specialized form of {@link Slice}, which provides
 * primitive specializations and additional functionality through extending {@link CharSequence}.
 * <p>
 * It also extends {@link ResettableCharIterator}, which allows
 * the string slice to be used as a regular {@link java.text.CharacterIterator},
 * with the benefit of being able to reset it's internal iteration index easily.
 *
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.STABLE)
public interface CharSlice extends Slice<Character>, CharSequence, ResettableCharIterator {
    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.
     *
     * @param seq   The character sequence to create a slice of.
     * @param start The start index of the slice to be created.
     * @param end   The end index (inclusive) of the slice to be created.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull CharSlice of(final @NotNull CharSequence seq, final int start, final int end) {
        return new StringCharSliceImpl(seq, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.
     *
     * @param seq The character sequence to create a slice of.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull CharSlice of(final @NotNull CharSequence seq) {
        return new StringCharSliceImpl(seq, 0, seq.length() - 1);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new string slice for the given character array,
     * with the given start- and end index.
     *
     * @param ref   The character array to create a slice of.
     * @param start The start index of the slice to be created.
     * @param end   The end index (inclusive) of the slice to be created.
     * @return A new string slice referencing the given character array.
     */
    static @NotNull CharSlice of(final char[] ref, final int start, final int end) {
        return new ArrayCharSliceImpl(ref, start, end);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new string slice for the given character array,
     * with the given start- and end index.
     *
     * @param ref The character array to create a slice of.
     * @return A new string slice referencing the given character array.
     */
    static @NotNull CharSlice of(final char[] ref) {
        return new ArrayCharSliceImpl(ref, 0, ref.length - 1);
    }

    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final @NotNull CharSequence delimiter, final int start, final int end) {
        final int size = seq.length();
        final int maxIndex = size - 1;

        if (start < 0 || start > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (end < 0 || end > maxIndex || end < start) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        // Find number of delimiters
        final int delimiterLength = delimiter.length();
        int numDelimiters = 0; // # of delimiters total
        int matchingChars = 0; // # of matching characters for the delimiter

        for (int i = start; i <= end; i++) {
            if (seq.charAt(i) != delimiter.charAt(matchingChars)) {
                matchingChars = 0; // Reset number of matching chars
                continue;
            }

            matchingChars++;

            if (matchingChars != delimiterLength) {
                continue;
            }

            matchingChars = 0;
            numDelimiters++;
        }

        // Create sub-views
        final int numSlices = numDelimiters + 1;
        final CharSlice[] slices = new CharSlice[numSlices];
        int index = 0;
        int subEnd = 0;

        matchingChars = 0; // Reset # of matching chars

        for (int i = start; i <= end; i++) {
            if (seq.charAt(i) != delimiter.charAt(matchingChars)) {
                matchingChars = 0; // Reset number of matching chars
                continue;
            }

            matchingChars++;

            if (matchingChars != delimiterLength) {
                continue;
            }

            final int subStart = subEnd == 0 ? 0 : subEnd + delimiterLength;
            subEnd = i - delimiterLength + 1;
            slices[index++] = new StringCharSliceImpl(seq, subStart, subEnd);
            matchingChars = 0;
        }

        final int subStart = subEnd + delimiterLength;
        slices[index] = new StringCharSliceImpl(seq, subStart, end);

        return slices;
    }

    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final @NotNull CharSequence delimiter) {
        return split(seq, delimiter, 0, seq.length() - 1);
    }

    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final char delimiter, final int start, final int end) {
        final int size = seq.length();
        final int maxIndex = size - 1;

        if (start < 0 || start > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (end < 0 || end > maxIndex || end < start) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        // Find # of delimiters
        int numDelimiters = 0;

        for (int i = start; i <= end; i++) {
            if (seq.charAt(i) != delimiter) {
                continue;
            }

            numDelimiters++;
        }

        // Create sub-views
        final int numSlices = numDelimiters + 1;
        final CharSlice[] slices = new CharSlice[numSlices];
        int index = 0;
        int subEnd = 0;

        for (int i = start; i <= end; i++) {
            if (seq.charAt(i) != delimiter) {
                continue;
            }

            final int subStart = subEnd == 0 ? 0 : subEnd + 1;
            slices[index++] = new StringCharSliceImpl(seq, subStart, subEnd = i);
        }

        slices[index] = new StringCharSliceImpl(seq, subEnd + 1, end);
        return slices;
    }

    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final char delimiter) {
        return split(seq, delimiter, 0, seq.length() - 1);
    }

    // Instance functions

    default @NotNull CharSlice[] split(final char delimiter, final int start, final int end) {
        return split(this, delimiter, start, end);
    }

    default @NotNull CharSlice[] split(final char delimiter) {
        return split(delimiter, 0, length() - 1);
    }

    default @NotNull CharSlice[] split(final @NotNull CharSequence delimiter, final int start, final int end) {
        return split(this, delimiter, start, end);
    }

    default @NotNull CharSlice[] split(final @NotNull CharSequence delimiter) {
        return split(delimiter, 0, length() - 1);
    }

    @NotNull CharSlice trimLeading();

    @NotNull CharSlice trimTrailing();

    default @NotNull CharSlice trim() {
        return trimLeading().trimTrailing();
    }

    char[] toCharArray(final int start, final int end);

    default char[] toCharArray() {
        return toCharArray(0, length() - 1);
    }

    @Override
    default @NotNull Character get(final int index) {
        return charAt(index);
    }

    @Override
    default int length() {
        return size();
    }

    @Override
    default @NotNull CharSequence subSequence(final int start, final int end) {
        return (CharSlice) slice(start, end);
    }

    @Override
    default char first() {
        return charAt(0);
    }

    @Override
    default char last() {
        return charAt(length() - 1);
    }

    @Override
    default int getBeginIndex() {
        return start();
    }

    @Override
    default int getEndIndex() {
        return end();
    }
}
