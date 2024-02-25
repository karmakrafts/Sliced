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

package io.karma.sliced.iterator;

import io.karma.sliced.iterator.impl.ArrayCharIterator;
import io.karma.sliced.iterator.impl.CharSeqCharIterator;
import io.karma.sliced.iterator.impl.RangedArrayCharIterator;
import io.karma.sliced.iterator.impl.RangedCharSeqCharIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of {@link Iterator}&lt;{@link Character}&gt;,
 * which provides a non-boxing version of {@link Iterator#next()}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface CharIterator extends Iterator<Character> {
    CharIterator NOP = new CharIterator() {
        @Override
        public char nextChar() {
            return 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    };

    static @NotNull CharIterator of(final @NotNull CharSequence ref, final int offset, final int size) {
        return new RangedCharSeqCharIterator(ref, offset, size);
    }

    static @NotNull CharIterator of(final @NotNull CharSequence ref) {
        return new CharSeqCharIterator(ref);
    }

    static @NotNull CharIterator of(final char[] ref, final int offset, final int size) {
        return new RangedArrayCharIterator(ref, offset, size);
    }

    static @NotNull CharIterator of(final char... ref) {
        return new ArrayCharIterator(ref);
    }

    /**
     * Retrieves the next {@code char} value in the sequence being iterated over.
     *
     * @return The next {@code char} value in the sequence being iterated over.
     */
    char nextChar();

    @Override
    default @NotNull Character next() {
        return nextChar();
    }
}
