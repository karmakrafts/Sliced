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

import io.karma.sliced.MutableSlice;
import io.karma.sliced.Slice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
final class TestArraySlice {
    static final String[] ARRAY = "a.b.c.d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w.x.y.z".split("\\.");
    static final String[] ARRAY_2 = "d.e.f.g.h.i.j.k.l.m.n.o.p.q.r.s.t.u.v.w".split("\\.");

    @Test
    void test() {
        final Slice<String> slice = Slice.of(ARRAY);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : ARRAY) {
            Assertions.assertSame(s, slice.get(index++));
        }
    }

    @Test
    void testMutable() {
        final MutableSlice<String> slice = MutableSlice.of(ARRAY);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : ARRAY) {
            Assertions.assertSame(s, slice.get(index++));
        }

        index = 0;
        slice.setRange(3, ARRAY.length - 4);

        for (final String s : ARRAY_2) {
            Assertions.assertEquals(s, slice.get(index++));
        }
    }

    @Test
    void testIteration() {
        final Slice<String> slice = Slice.of(ARRAY);
        Assertions.assertNotNull(slice);
        int index = 0;

        for (final String s : slice) {
            Assertions.assertSame(ARRAY[index++], s);
        }
    }

    @Test
    void testRangedIteration() {
        final MutableSlice<String> slice = MutableSlice.of(ARRAY);
        Assertions.assertNotNull(slice);

        int index = 0;

        for (final String s : slice) {
            Assertions.assertSame(ARRAY[index++], s);
        }

        index = 0;
        slice.setRange(3, ARRAY.length - 4);

        for (final String s : slice) {
            Assertions.assertEquals(ARRAY_2[index++], s);
        }
    }
}
