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

import io.karma.sliced.iterator.DoubleIterator;
import io.karma.sliced.view.impl.ArrayDoubleView;
import io.karma.sliced.view.impl.EmptyDoubleView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

/**
 * A primitive specialization for {@code double} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface DoubleView extends View<Double> {
    /**
     * Creates an empty double view with no elements.
     *
     * @return An empty double view with no elements.
     */
    static DoubleView empty() {
        return EmptyDoubleView.INSTANCE;
    }

    static @NotNull DoubleView of(final double... ref) {
        return new ArrayDoubleView(ref);
    }

    /**
     * Creates a new {@link DoubleIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link DoubleIterator} from the
     * elements referenced by this slice instance.
     */
    @NotNull DoubleIterator doubleIterator();

    /**
     * Creates a new {@link Spliterator.OfDouble} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link Spliterator.OfDouble} from the
     * elements referenced by this slice instance.
     */
    @NotNull Spliterator.OfDouble doubleSpliterator();

    /**
     * Creates a new {@code double} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    double[] toDoubleArray();

    /**
     * Creates a new non-parallel {@link DoubleStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link DoubleStream} instance of all
     * elements referenced by this slice instance.
     */
    default @NotNull DoubleStream doubleStream() {
        return StreamSupport.doubleStream(doubleSpliterator(), false);
    }

    /**
     * Creates a new parallel {@link DoubleStream}
     * for the elements referenced by this slice instance.
     *
     * @return A new {@link DoubleStream} instance of all
     * elements referenced by this slice instance.
     */
    default @NotNull DoubleStream parallelDoubleStream() {
        return StreamSupport.doubleStream(doubleSpliterator(), true);
    }

    @Override
    default @NotNull Iterator<Double> iterator() {
        return doubleIterator();
    }
}
