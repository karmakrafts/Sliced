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

import io.karma.sliced.iterator.ByteIterator;
import io.karma.sliced.view.impl.ArrayByteView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * A primitive specialization for {@code byte} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface ByteView extends View<Byte> {
    static @NotNull ByteView of(final byte... ref) {
        return new ArrayByteView(ref);
    }

    /**
     * Creates a new {@link ByteIterator} from the
     * elements referenced by this slice instance.
     *
     * @return A new {@link ByteIterator} from the
     * elements referenced by this slice instance.
     */
    @NotNull ByteIterator byteIterator();

    /**
     * Creates a new {@code byte} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements referenced by this slice instance.
     */
    byte[] toByteArray();

    @Override
    default @NotNull Iterator<Byte> iterator() {
        return byteIterator();
    }
}
