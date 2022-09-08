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

package io.karma.sliced.test.slice;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.test.view.AbstractViewTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
public abstract class AbstractSliceTest<T, S extends Slice<T>> extends AbstractViewTest<T, S> {
    protected final ArraySliceFactory<T, ? extends S> arraySliceFactory;
    protected final ListSliceFactory<T, ? extends S> listSliceFactory;

    protected AbstractSliceTest(final @NotNull Supplier<T[]> randomProvider, final @NotNull ArraySliceFactory<T, ? extends S> arraySliceFactory, final @NotNull ListSliceFactory<T, ? extends S> listSliceFactory) {
        super(randomProvider, a -> arraySliceFactory.create(a, 0, a.length), l -> listSliceFactory.create(l, 0, l.size()));
        this.arraySliceFactory = arraySliceFactory;
        this.listSliceFactory = listSliceFactory;
    }

    @SuppressWarnings("unchecked")
    protected <AS extends S> @NotNull AS createArraySlice(final T[] ref, final int offset, final int size) {
        return (AS) arraySliceFactory.create(ref, offset, size);
    }

    @SuppressWarnings("unchecked")
    protected <LS extends S> @NotNull LS createListSlice(final @NotNull List<T> list, final int offset, final int size) {
        return (LS) listSliceFactory.create(list, offset, size);
    }

    @Test
    void testOffsetEqualsSelf() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();
            final T[] values2 = getRandomValues();

            final int b = values.length >> 4;
            final int o = RANDOM.nextInt(b);
            final int s = RANDOM.nextInt(b);
            final int actualSize = values.length - s;

            final S view1 = createArraySlice(values, o, actualSize);
            Assertions.assertNotNull(view1);
            Assertions.assertEquals(actualSize, view1.size());

            final S view2 = createArraySlice(values, o, actualSize);
            Assertions.assertNotNull(view2);
            Assertions.assertEquals(actualSize, view2.size());

            final S view3 = createArraySlice(values2, 0, values2.length);
            Assertions.assertNotNull(view3);
            Assertions.assertEquals(values2.length, view3.size());

            Assertions.assertEquals(view1, view2);
            Assertions.assertNotEquals(view1, view3);
            Assertions.assertNotEquals(view2, view3);
        }
    }

    @Test
    void testOffsetEqualsOther() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();
            final T[] values2 = getRandomValues();

            final int b = values.length >> 4;
            final int o = RANDOM.nextInt(b);
            final int s = RANDOM.nextInt(b);
            final int actualSize = values.length - (o + s);

            final S view1 = createArraySlice(values, o, actualSize);
            Assertions.assertNotNull(view1);
            Assertions.assertEquals(actualSize, view1.size());

            final S view2 = createListSlice(Arrays.asList(values), o, actualSize);
            Assertions.assertNotNull(view2);
            Assertions.assertEquals(actualSize, view2.size());

            final S view3 = createListSlice(Arrays.asList(values2), 0, values2.length);
            Assertions.assertNotNull(view3);
            Assertions.assertEquals(values2.length, view3.size());

            Assertions.assertEquals(view1, view2);
            Assertions.assertNotEquals(view1, view3);
            Assertions.assertNotEquals(view2, view3);
        }
    }

    @Test
    void testIndexedIteration() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();
            final int numValues = values.length;

            final S slice = createArraySlice(values, 0, numValues);
            Assertions.assertNotNull(slice);
            Assertions.assertEquals(0, slice.offset());
            Assertions.assertEquals(values.length, slice.size());

            final AssertionFunction eaf = getElementEqualityFunction();

            for (int j = 0; j < numValues; j++) {
                eaf.assertThat(values[j], slice.get(j));
            }
        }
    }

    @Test
    void testOffsetIndexedIteration() {
        final T[] values = getRandomValues();
        final int numValues = values.length;
        final int ob = Math.max(1, numValues >> 4);

        final AssertionFunction eaf = getElementEqualityFunction();

        for (int i = 0; i < ITERATIONS; i++) {
            final int o1 = RANDOM.nextInt(ob);
            final int o2 = RANDOM.nextInt(ob);
            final int to = o1 + o2;

            final S slice = createArraySlice(values, o1, numValues - o2);
            Assertions.assertNotNull(slice);
            Assertions.assertEquals(o1, slice.offset());

            final int actualSize = numValues - to;

            for (int j = 0; j < actualSize; j++) {
                eaf.assertThat(values[j + o1], slice.get(j));
            }
        }
    }

    /**
     * @author Alexander Hinze
     * @since 06/09/2022
     */
    @FunctionalInterface
    public interface ArraySliceFactory<T, S extends Slice<T>> {
        @NotNull S create(final T[] ref, final int offset, final int size);
    }

    /**
     * @author Alexander Hinze
     * @since 06/09/2022
     */
    @FunctionalInterface
    public interface ListSliceFactory<T, S extends Slice<T>> {
        @NotNull S create(final @NotNull List<T> list, final int offset, final int size);
    }
}
