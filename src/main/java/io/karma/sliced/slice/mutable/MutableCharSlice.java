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

package io.karma.sliced.slice.mutable;

import io.karma.sliced.slice.CharSlice;
import io.karma.sliced.slice.mutable.impl.MutableArrayCharSlice;
import io.karma.sliced.slice.mutable.impl.MutableCharSeqSlice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * A mutable slice is a subtype of a regular {@link CharSlice},
 * which allows mutating the start- and end-index of the slice.
 * This allows the reduction of allocation overhead in loops for example.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface MutableCharSlice extends CharSlice, MutableSlice<Character> {
    /**
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.
     *
     * @param seq    The character sequence to create a slice of.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull MutableCharSlice of(final @NotNull CharSequence seq, final int offset, final int size) {
        return new MutableCharSeqSlice(seq, offset, size);
    }

    /**
     * Creates a new string slice for the given character sequence,
     * with the given start- and end index.
     *
     * @param seq The character sequence to create a slice of.
     * @return A new string slice referencing the given character sequence.
     */
    static @NotNull MutableCharSlice of(final @NotNull CharSequence seq) {
        return new MutableCharSeqSlice(seq, 0, seq.length());
    }

    /**
     * Creates a new mutable slice instance which references the given array.
     *
     * @param ref    The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull MutableCharSlice of(final char[] ref, final int offset, final int size) {
        return new MutableArrayCharSlice(ref, offset, size);
    }

    /**
     * Creates a new mutable slice instance which references the given array.
     *
     * @param ref The array of which to create a slice.
     * @return A new mutable slice instance, which references the given array.
     */
    static @NotNull MutableCharSlice of(final char... ref) {
        return new MutableArrayCharSlice(ref, 0, ref.length);
    }
}
