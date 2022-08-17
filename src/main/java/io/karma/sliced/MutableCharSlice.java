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
 * A mutable version of {@link CharSlice}, which allows
 * settings the start and end indices.
 *
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.STABLE)
public interface MutableCharSlice extends CharSlice, MutableSlice<Character> {
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
    static @NotNull MutableCharSlice of(final @NotNull CharSequence seq, final int start, final int end) {
        return new MutableStringSliceImpl(seq, start, end);
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
    static @NotNull MutableCharSlice of(final @NotNull CharSequence seq) {
        return new MutableStringSliceImpl(seq, 0, seq.length() - 1);
    }

    static @NotNull MutableCharSlice of(final char[] ref, final int start, final int end) {
        return new MutableArrayCharSlice(ref, start, end);
    }

    static @NotNull MutableCharSlice of(final char[] ref) {
        return new MutableArrayCharSlice(ref, 0, ref.length - 1);
    }
}
