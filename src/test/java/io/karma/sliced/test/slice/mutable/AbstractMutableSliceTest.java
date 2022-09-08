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

package io.karma.sliced.test.slice.mutable;

import io.karma.sliced.slice.mutable.MutableSlice;
import io.karma.sliced.test.slice.AbstractSliceTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
public abstract class AbstractMutableSliceTest<T, S extends MutableSlice<T>> extends AbstractSliceTest<T, S> {
    protected AbstractMutableSliceTest(@NotNull Supplier<T[]> randomProvider, @NotNull ArraySliceFactory<T, ? extends S> arraySliceFactory, @NotNull ListSliceFactory<T, ? extends S> listSliceFactory) {
        super(randomProvider, arraySliceFactory, listSliceFactory);
    }

    @Test
    void testSetOffsetAndSize() {
        final T[] values = getRandomValues();
        final int numValues = values.length;
        final int ob = Math.max(1, numValues >> 4);

        final S slice = createArraySlice(values, 0, numValues);
        Assertions.assertNotNull(slice);

        final AssertionFunction eaf = getElementEqualityFunction();

        for (int i = 0; i < 100; i++) {
            final int o1 = RANDOM.nextInt(ob);
            final int o2 = RANDOM.nextInt(ob);
            final int to = o1 + o2;
            final int actualSize = numValues - to;

            slice.setOffset(o1);
            slice.setSize(actualSize);
            Assertions.assertEquals(o1, slice.offset());

            for (int j = 0; j < actualSize; j++) {
                eaf.assertThat(values[j + o1], slice.get(j));
            }
        }
    }
}
