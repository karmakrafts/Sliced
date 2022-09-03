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

package io.karma.sliced.slice;

import io.karma.sliced.view.CharView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * A char slice is a specialized form of {@link Slice}, which provides
 * primitive specializations and additional functionality through extending {@link CharSequence}.
 *
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.STABLE)
public interface CharSlice extends CharView, Slice<Character> {
    /**
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.<br>
     *
     * @param seq   The character sequence to create a slice of.
     * @param start The start index of the slice to be created.
     * @param end   The end index (inclusive) of the slice to be created.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull CharSlice of(final @NotNull CharSequence seq, final int start, final int end) {
        return new CharSeqSliceImpl(seq, start, end);
    }

    /**
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.<br>
     *
     * @param seq The character sequence to create a slice of.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull CharSlice of(final @NotNull CharSequence seq) {
        return new CharSeqSliceImpl(seq, 0, seq.length() - 1);
    }

    /**
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
     * Creates a new string slice for the given character array,
     * with the given start- and end index.
     *
     * @param ref The character array to create a slice of.
     * @return A new string slice referencing the given character array.
     */
    static @NotNull CharSlice of(final char[] ref) {
        return new ArrayCharSliceImpl(ref, 0, ref.length - 1);
    }

    /**
     * Splits the given {@link CharSequence} {code seq} at each given delimiter {@code delimiter},
     * between the given start and end index.
     *
     * @param seq       The character sequence to split.
     * @param delimiter The delimiter to split with.
     * @param start     The index at which to start splitting.
     * @param end       The index at which to end splitting.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
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
        int lastEnd = start;

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

            slices[index++] = new CharSeqSliceImpl(seq, lastEnd, i - (delimiterLength - 1));
            lastEnd = i + 1;
            matchingChars = 0;
        }

        slices[index] = new CharSeqSliceImpl(seq, lastEnd, end + 1);
        return slices;
    }

    /**
     * Splits the given {@link CharSequence} {code seq} at each given delimiter {@code delimiter}.
     *
     * @param seq       The character sequence to split.
     * @param delimiter The delimiter to split with.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final @NotNull CharSequence delimiter) {
        return split(seq, delimiter, 0, seq.length() - 1);
    }

    /**
     * Splits the given {@link CharSequence} {code seq} at each given delimiter {@code delimiter},
     * between the given start and end index.
     *
     * @param seq       The character sequence to split.
     * @param delimiter The delimiter to split with.
     * @param start     The index at which to start splitting.
     * @param end       The index at which to end splitting.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
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
        int lastEnd = 0;

        for (int i = start; i <= end; i++) {
            if (seq.charAt(i) != delimiter) {
                continue;
            }

            slices[index++] = new CharSeqSliceImpl(seq, lastEnd, i);
            lastEnd = i + 1;
        }

        slices[index] = new CharSeqSliceImpl(seq, lastEnd, end + 1);
        return slices;
    }

    /**
     * Splits the given {@link CharSequence} {code seq} at each given delimiter {@code delimiter}.
     *
     * @param seq       The character sequence to split.
     * @param delimiter The delimiter to split with.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    static @NotNull CharSlice[] split(final @NotNull CharSequence seq, final char delimiter) {
        return split(seq, delimiter, 0, seq.length() - 1);
    }

    // Instance functions

    /**
     * Retrieves the character at the given index.
     *
     * @param index The index of the character to retrieve.
     * @return The character at the given index.
     */
    char getChar(final int index);

    /**
     * Splits this {@link CharSlice} at each given delimiter {@code delimiter},
     * between the given start and end index.
     *
     * @param delimiter The delimiter to split with.
     * @param start     The index at which to start splitting.
     * @param end       The index at which to end splitting.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    default @NotNull CharSlice[] split(final char delimiter, final int start, final int end) {
        return split(this, delimiter, start, end);
    }

    /**
     * Splits this {@link CharSlice} at each given delimiter {@code delimiter}.
     *
     * @param delimiter The delimiter to split with.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    default @NotNull CharSlice[] split(final char delimiter) {
        return split(delimiter, 0, length() - 1);
    }

    /**
     * Splits this {@link CharSlice} at each given delimiter {@code delimiter},
     * between the given start and end index.
     *
     * @param delimiter The delimiter to split with.
     * @param start     The index at which to start splitting.
     * @param end       The index at which to end splitting.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    default @NotNull CharSlice[] split(final @NotNull CharSequence delimiter, final int start, final int end) {
        return split(this, delimiter, start, end);
    }

    /**
     * Splits this {@link CharSlice} at each given delimiter {@code delimiter}.
     *
     * @param delimiter The delimiter to split with.
     * @return A new array of {@link CharSlice} instances, referencing the relevant
     *         sections of the original char sequence.
     */
    default @NotNull CharSlice[] split(final @NotNull CharSequence delimiter) {
        return split(delimiter, 0, length() - 1);
    }

    /**
     * Trims all leading whitespace (space, tab, newline etc.)
     * off the original char sequence and returns a new {@link CharSlice},
     * which only references the non-leading-WS part of the original
     * underlying character sequence.
     *
     * @return A new {@link CharSlice} referencing all non-leading-WS
     *         characters of the original slice instance's character sequence.
     */
    @NotNull CharSlice trimLeading();

    /**
     * Trims all trailing whitespace (space, tab, newline etc.)
     * off the original char sequence and returns a new {@link CharSlice},
     * which only references the non-trailing-WS part of the original
     * underlying character sequence.
     *
     * @return A new {@link CharSlice} referencing all non-trailing-WS
     *         characters of the original slice instance's character sequence.
     */
    @NotNull CharSlice trimTrailing();

    /**
     * Trims both leading and trailing whitespace off the original
     * char sequence and returns a new {@link CharSlice}, which only
     * references the non-WS center part of the original underlying
     * character sequence.
     * <p>
     * This function is a combination of {@link #trimLeading()} and {@link #trimTrailing()}.
     *
     * @return A new {@link CharSlice} referencing all non-WS
     *         characters of the original slice instance's character sequence.
     */
    default @NotNull CharSlice trim() {
        return trimLeading().trimTrailing();
    }

    /**
     * Creates a new {@code char} array with the appropriate size,
     * and copies all values from {@code start} to {@code end} into
     * new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @param start The index at which to start copying elements.
     * @param end   The index at which to end copying elements.
     * @return A new array containing all elements from {@code start} to {@code end}.
     */
    char[] toCharArray(final int start, final int end);

    @Override
    default char[] toCharArray() {
        return toCharArray(0, length() - 1);
    }

    // Slice functions

    @Override
    default @NotNull Character get(final int index) {
        return charAt(index);
    }

    // CharSequence functions

    @Override
    default char charAt(final int index) {
        return getChar(index);
    }

    @Override
    default int length() {
        return size();
    }

    @Override
    default @NotNull CharSequence subSequence(final int start, final int end) {
        return (CharSlice) slice(start, end);
    }
}
