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

import io.karma.sliced.iterator.impl.ArrayShortIterator;
import io.karma.sliced.iterator.impl.RangedArrayShortIterator;
import io.karma.sliced.util.Resettable;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of {@link Iterator}&lt;{@link Short}&gt;,
 * which provides a non-boxing version of {@link Iterator#next()}.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface ShortIterator extends Iterator<Short>, Resettable {
    ShortIterator NOP = new ShortIterator() {
        @Override
        public short nextShort() {
            return 0;
        }

        @Override
        public void reset() {

        }

        @Override
        public boolean hasNext() {
            return false;
        }
    };

    static @NotNull ShortIterator of(final short[] ref, final int offset, final int size) {
        return new RangedArrayShortIterator(ref, offset, size);
    }

    static @NotNull ShortIterator of(final short... ref) {
        return new ArrayShortIterator(ref);
    }

    /**
     * Retrieves the next {@code short} value in the sequence being iterated over.
     *
     * @return The next {@code short} value in the sequence being iterated over.
     */
    short nextShort();

    @Override
    default @NotNull Short next() {
        return nextShort();
    }
}
