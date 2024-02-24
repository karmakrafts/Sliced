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

import io.karma.sliced.iterator.ShortIterator;
import io.karma.sliced.view.impl.ArrayShortView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization for {@code short} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface ShortView extends View<Short> {
    static @NotNull ShortView of(final short... ref) {
        return new ArrayShortView(ref);
    }

    /**
     * Creates a new {@link ShortIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link ShortIterator} from the
     * elements referenced by this slice instance.
     */
    @NotNull ShortIterator shortIterator();

    /**
     * Creates a new {@code short} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    short[] toShortArray();

    @Override
    default @NotNull Iterator<Short> iterator() {
        return shortIterator();
    }
}
