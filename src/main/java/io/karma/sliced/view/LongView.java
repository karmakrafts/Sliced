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

package io.karma.sliced.view;

import io.karma.sliced.iterator.LongIterator;
import io.karma.sliced.view.impl.ArrayLongView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization for {@code long} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface LongView extends View<Long> {
    static @NotNull LongView of(final long... ref) {
        return new ArrayLongView(ref);
    }

    /**
     * Creates a new {@link LongIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link LongIterator} from the
     *         elements referenced by this slice instance.
     */
    @NotNull LongIterator longIterator();

    /**
     * Creates a new {@link Spliterator.OfLong} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfLong} from the
     *         elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfLong longSpliterator();

    /**
     * Creates a new {@code long} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    long[] toLongArray();

    /**
     * Creates a new non-parallel {@link LongStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link LongStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull LongStream longStream() {
        return StreamSupport.longStream(longSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link LongStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link LongStream} instance of all
     *         elements referenced by this slice instance.
     */
    default @NotNull LongStream parallelLongStream() {
        return StreamSupport.longStream(longSpliterator(), true);
    }

    @Override
    default @NotNull Iterator<Long> iterator() {
        return longIterator();
    }
}
