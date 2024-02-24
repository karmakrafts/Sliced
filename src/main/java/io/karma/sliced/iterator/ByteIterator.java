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

import io.karma.sliced.iterator.impl.ArrayByteIterator;
import io.karma.sliced.iterator.impl.RangedArrayByteIterator;
import io.karma.sliced.util.Resettable;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of {@link Iterator}&lt;{@link Byte}&gt;,
 * which provides a non-boxing version of {@link Iterator#next()}.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
public interface ByteIterator extends Iterator<Byte>, Resettable {
    static @NotNull ByteIterator of(final byte[] ref, final int offset, final int size) {
        return new RangedArrayByteIterator(ref, offset, size);
    }

    static @NotNull ByteIterator of(final byte... ref) {
        return new ArrayByteIterator(ref);
    }

    /**
     * Retrieves the next {@code byte} value in the sequence being iterated over.
     *
     * @return The next {@code byte} value in the sequence being iterated over.
     */
    byte nextByte();

    @Override
    default @NotNull Byte next() {
        return nextByte();
    }
}
