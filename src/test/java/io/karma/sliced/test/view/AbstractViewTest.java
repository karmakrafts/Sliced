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

package io.karma.sliced.test.view;

import io.karma.sliced.test.AbstractTest;
import io.karma.sliced.view.View;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
public abstract class AbstractViewTest<T, V extends View<T>> extends AbstractTest {
    protected final Supplier<T[]> randomProvider;
    protected final Function<T[], ? extends V> arrayViewFactory;
    protected final Function<List<T>, ? extends V> listViewFactory;

    protected AbstractViewTest(final @NotNull Supplier<T[]> randomProvider, final @NotNull Function<T[], ? extends V> arrayViewFactory, final @NotNull Function<List<T>, ? extends V> listViewFactory) {
        this.randomProvider = randomProvider;
        this.arrayViewFactory = arrayViewFactory;
        this.listViewFactory = listViewFactory;
    }

    protected boolean isPrimitiveTest() {
        return getClass().isAnnotationPresent(PrimitiveTest.class);
    }

    protected @NotNull AssertionFunction getElementEqualityFunction() {
        return isPrimitiveTest() ? Assertions::assertEquals : Assertions::assertSame;
    }

    protected @NotNull T[] getRandomValues() {
        return randomProvider.get();
    }

    @SuppressWarnings("unchecked")
    protected <AV extends V> @NotNull AV createArrayView(final T[] ref) {
        return (AV) arrayViewFactory.apply(ref);
    }

    @SuppressWarnings("unchecked")
    protected <LV extends V> @NotNull LV createListView(final @NotNull List<T> ref) {
        return (LV) listViewFactory.apply(ref);
    }

    @Test
    void testIteration() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();

            final V view = createArrayView(values);
            Assertions.assertNotNull(view);
            Assertions.assertEquals(values.length, view.size());

            final AssertionFunction eef = getElementEqualityFunction();
            int index = 0;

            for (final T element : view) {
                eef.assertThat(values[index++], element);
            }
        }
    }

    @Test
    void testCopyList() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();

            final V view = createArrayView(values);
            Assertions.assertNotNull(view);
            Assertions.assertEquals(values.length, view.size());

            final ArrayList<T> list = view.copyArrayList();
            Assertions.assertNotNull(list);
            Assertions.assertEquals(values.length, list.size());

            final AssertionFunction eef = getElementEqualityFunction();
            int index = 0;

            for (final T element : list) {
                eef.assertThat(values[index++], element);
            }
        }
    }

    @Test
    void testEqualsSelf() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();
            final T[] values2 = getRandomValues();

            final V view1 = createArrayView(values);
            Assertions.assertNotNull(view1);
            Assertions.assertEquals(values.length, view1.size());

            final V view2 = createArrayView(values);
            Assertions.assertNotNull(view2);
            Assertions.assertEquals(values.length, view2.size());

            final V view3 = createArrayView(values2);
            Assertions.assertNotNull(view3);
            Assertions.assertEquals(values2.length, view3.size());

            Assertions.assertEquals(view1, view2);
            Assertions.assertNotEquals(view1, view3);
            Assertions.assertNotEquals(view2, view3);
        }
    }

    @Test
    void testEqualsOther() {
        for (int i = 0; i < ITERATIONS; i++) {
            final T[] values = getRandomValues();
            final T[] values2 = getRandomValues();

            final V view1 = createArrayView(values);
            Assertions.assertNotNull(view1);
            Assertions.assertEquals(values.length, view1.size());

            final V view2 = createListView(Arrays.asList(values));
            Assertions.assertNotNull(view2);
            Assertions.assertEquals(values.length, view2.size());

            final V view3 = createListView(Arrays.asList(values2));
            Assertions.assertNotNull(view3);
            Assertions.assertEquals(values2.length, view3.size());

            Assertions.assertEquals(view1, view2);
            Assertions.assertNotEquals(view1, view3);
            Assertions.assertNotEquals(view2, view3);
        }
    }

    /**
     * @author Alexander Hinze
     * @since 07/09/2022
     */
    @FunctionalInterface
    protected interface AssertionFunction {
        void assertThat(final @Nullable Object o1, final @Nullable Object o2) throws AssertionError;
    }

    /**
     * @author Alexander Hinze
     * @since 07/09/2022
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface PrimitiveTest {
    }
}
