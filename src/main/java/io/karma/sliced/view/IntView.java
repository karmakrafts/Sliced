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

package io.karma.sliced.view;

import io.karma.sliced.iterator.IntIterator;
import io.karma.sliced.view.impl.ArrayIntView;
import io.karma.sliced.view.impl.EmptyIntView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization for {@code int} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface IntView extends View<Integer> {
    /**
     * Creates an empty int view with no elements.
     *
     * @return An empty int view with no elements.
     */
    static IntView empty() {
        return EmptyIntView.INSTANCE;
    }

    static @NotNull IntView of(final int... ref) {
        return new ArrayIntView(ref);
    }

    /**
     * Creates a new {@link IntIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link IntIterator} from the
     * elements referenced by this slice instance.
     */
    @NotNull IntIterator intIterator();

    /**
     * Creates a new {@link Spliterator.OfInt} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfInt} from the
     * elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfInt intSpliterator();

    /**
     * Creates a new {@code int} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    int[] toIntArray();

    /**
     * Creates a new non-parallel {@link IntStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link IntStream} instance of all
     * elements referenced by this slice instance.
     */
    default @NotNull IntStream intStream() {
        return StreamSupport.intStream(intSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link IntStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link IntStream} instance of all
     * elements referenced by this slice instance.
     */
    default @NotNull IntStream parallelIntStream() {
        return StreamSupport.intStream(intSpliterator(), true);
    }

    @Override
    default @NotNull Iterator<Integer> iterator() {
        return intIterator();
    }
}
