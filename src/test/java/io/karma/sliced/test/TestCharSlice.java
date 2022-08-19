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

import io.karma.sliced.CharSlice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
final class TestCharSlice {
    private static final String VALUE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = VALUE.length();
    private static final String VALUE_2 = "A.B.C.D.E.F.G.H.I.J.K.L.M.N.O.P.Q.R.S.T.U.V.W.X.Y.Z";
    private static final char[] VALUE_2_CHARS = VALUE_2.toCharArray();
    private static final String VALUE_3 = "A.-.B.-.C.-.D.-.E.-.F.-.G.-.H.-.I.-.J.-.K.-.L.-.M.-.N.-.O.-.P.-.Q.-.R.-.S.-.T.-.U.-.V.-.W.-.X.-.Y.-.Z";
    private static final char[] VALUE_3_CHARS = VALUE_3.toCharArray();
    private static final String VALUE_4 = "foo.bar.foobar.barfoo.apple.banana.orange.tangerine";
    private static final char[] VALUE_4_CHARS = VALUE_4.toCharArray();
    private static final String[] WORDS = VALUE_4.split("\\.");
    private static final int WORD_COUNT = WORDS.length;

    @Test
    void testSplitStaticSequence() {
        CharSlice[] slices = CharSlice.split(VALUE_3, ".-.");
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }

        slices = CharSlice.split(VALUE_4, ".");
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(WORD_COUNT, slices.length);

        for (int i = 0; i < WORD_COUNT; i++) {
            Assertions.assertEquals(WORDS[i], slices[i].toString());
        }
    }

    @Test
    void testSplitStaticChar() {
        CharSlice[] slices = CharSlice.split(VALUE_2, '.');
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }

        slices = CharSlice.split(VALUE_4, '.');
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(WORD_COUNT, slices.length);

        for (int i = 0; i < WORD_COUNT; i++) {
            Assertions.assertEquals(WORDS[i], slices[i].toString());
        }
    }

    @Test
    void testSplitSequence() {
        final CharSlice[] slices = CharSlice.of(VALUE_3).split(".-.");
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }
    }

    @Test
    void testSplitChar() {
        final CharSlice[] slices = CharSlice.of(VALUE_2).split('.');
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }
    }

    @Test
    void testArraySplitSequence() {
        final CharSlice[] slices = CharSlice.of(VALUE_3_CHARS).split(".-.");
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }
    }

    @Test
    void testArraySplitChar() {
        final CharSlice[] slices = CharSlice.of(VALUE_2_CHARS).split('.');
        Assertions.assertNotNull(slices);
        Assertions.assertEquals(LENGTH, slices.length);

        for (int i = 0; i < LENGTH; i++) {
            Assertions.assertEquals(VALUE.charAt(i), slices[i].charAt(0));
        }
    }
}
