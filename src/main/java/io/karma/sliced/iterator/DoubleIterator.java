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

import io.karma.sliced.iterator.impl.ArrayDoubleIterator;
import io.karma.sliced.iterator.impl.RangedArrayDoubleIterator;
import io.karma.sliced.util.Resettable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization of {@link Iterator}&lt;{@link Double}&gt;,
 * which provides a non-boxing version of {@link Iterator#next()}.
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
public interface DoubleIterator extends Iterator<Double>, Resettable {
    static @NotNull DoubleIterator of(final double[] ref, final int offset, final int size) {
        return new RangedArrayDoubleIterator(ref, offset, size);
    }

    static @NotNull DoubleIterator of(final double... ref) {
        return new ArrayDoubleIterator(ref);
    }

    /**
     * Retrieves the next {@code double} value in the sequence being iterated over.
     *
     * @return The next {@code double} value in the sequence being iterated over.
     */
    double nextDouble();

    @Override
    default @NotNull Double next() {
        return nextDouble();
    }
}
