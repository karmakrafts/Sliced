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

package io.karma.sliced.test;

import io.karma.sliced.slice.mutable.MutableSlice;
import io.karma.sliced.slice.Slice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
final class TestListSlice {
    static final List<String> LIST = Arrays.asList(TestArraySlice.ARRAY);
    static final List<String> LIST_2 = Arrays.asList(TestArraySlice.ARRAY_2);

    @Test
    void test() {
        final Slice<String> slice = Slice.of(LIST);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : LIST) {
            Assertions.assertSame(s, slice.get(index++));
        }
    }

    @Test
    void testMutable() {
        final MutableSlice<String> slice = MutableSlice.of(LIST);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : LIST) {
            Assertions.assertSame(s, slice.get(index++));
        }

        index = 0;
        slice.setRange(3, LIST.size() - 4);

        for (final String s : LIST_2) {
            Assertions.assertEquals(s, slice.get(index++));
        }
    }

    @Test
    void testIteration() {
        final Slice<String> slice = Slice.of(LIST);
        Assertions.assertNotNull(slice);
        int index = 0;

        for (final String s : slice) {
            Assertions.assertSame(LIST.get(index++), s);
        }
    }

    @Test
    void testRangedIteration() {
        final MutableSlice<String> slice = MutableSlice.of(LIST);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : slice) {
            Assertions.assertSame(LIST.get(index++), s);
        }

        index = 0;
        slice.setRange(3, LIST.size() - 4);

        for (final String s : slice) {
            Assertions.assertEquals(LIST_2.get(index++), s);
        }
    }
}
