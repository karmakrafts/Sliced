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

import io.karma.sliced.iterator.impl.ArrayBoolIterator;
import io.karma.sliced.iterator.impl.RangedArrayBoolIterator;
import io.karma.sliced.util.Resettable;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of {@link Iterator}&lt;{@link Boolean}&gt;,
 * which provides a non-boxing version of {@link Iterator#next()}.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface BoolIterator extends Iterator<Boolean>, Resettable {
    BoolIterator NOP = new BoolIterator() {
        @Override
        public boolean nextBool() {
            return false;
        }

        @Override
        public void reset() {
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    };

    static @NotNull BoolIterator of(final boolean[] ref, final int offset, final int size) {
        return new RangedArrayBoolIterator(ref, offset, size);
    }

    static @NotNull BoolIterator of(final boolean... ref) {
        return new ArrayBoolIterator(ref);
    }

    /**
     * Retrieves the next {@code boolean} value in the sequence being iterated over.
     *
     * @return The next {@code boolean} value in the sequence being iterated over.
     */
    boolean nextBool();

    @Override
    default @NotNull Boolean next() {
        return nextBool();
    }
}
