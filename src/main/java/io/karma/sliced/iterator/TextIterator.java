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

package io.karma.sliced.iterator;

import io.karma.sliced.iterator.impl.ArrayTextIterator;
import io.karma.sliced.iterator.impl.CharSeqTextIterator;
import io.karma.sliced.iterator.impl.RangedArrayTextIterator;
import io.karma.sliced.iterator.impl.RangedCharSeqTextIterator;
import io.karma.sliced.util.Resettable;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.text.CharacterIterator;

/**
 * Extends both {@link CharacterIterator} as well as {@link Resettable},
 * to allow for the easy polymorphic implementation of multipurpose
 * resettable iterators/enumerations.
 *
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.STABLE)
public interface TextIterator extends CharacterIterator, Resettable {
    static @NotNull TextIterator of(final @NotNull CharSequence ref, final int offset, final int size) {
        return new RangedCharSeqTextIterator(ref, offset, size);
    }

    static @NotNull TextIterator of(final @NotNull CharSequence ref) {
        return new CharSeqTextIterator(ref);
    }

    static @NotNull TextIterator of(final char[] ref, final int offset, final int size) {
        return new RangedArrayTextIterator(ref, offset, size);
    }

    static @NotNull TextIterator of(final char[] ref) {
        return new ArrayTextIterator(ref);
    }
}
