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

import io.karma.sliced.MutableStackSlice;
import io.karma.sliced.Slices;
import io.karma.sliced.StackSlice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Stack;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
final class TestStackSlice {
    private static final Stack<String> STACK = new Stack<>();
    private static final Stack<String> STACK_2 = new Stack<>();

    static {
        TestListSlice.LIST.forEach(STACK::push);
        TestListSlice.LIST_2.forEach(STACK_2::push);
    }

    @Test
    void test() {
        final StackSlice<String> slice = Slices.fromStack(STACK);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : STACK) {
            Assertions.assertEquals(s, slice.get(index++));
        }

        Assertions.assertSame(TestListSlice.LIST.get(TestListSlice.LIST.size() - 1), slice.peek());
    }

    @Test
    void testMutable() {
        final MutableStackSlice<String> slice = Slices.mutableFromStack(STACK);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : STACK) {
            Assertions.assertEquals(s, slice.get(index++));
        }

        Assertions.assertSame(TestListSlice.LIST.get(TestListSlice.LIST.size() - 1), slice.peek());
        index = 0;
        slice.setRange(3, STACK.size() - 4);

        for (final String s : STACK_2) {
            Assertions.assertEquals(s, slice.get(index++));
        }

        Assertions.assertSame(TestListSlice.LIST.get(TestListSlice.LIST.size() - 4), slice.peek());
    }

    @Test
    void testIteration() {
        final StackSlice<String> slice = Slices.fromStack(STACK);
        Assertions.assertNotNull(slice);
        int index = 0;

        for (final String s : slice) {
            Assertions.assertSame(STACK.get(index++), s);
        }
    }

    @Test
    void testRangedIteration() {

    }
}
