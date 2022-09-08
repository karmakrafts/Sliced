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

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Random;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
public abstract class AbstractTest {
    protected static final Random RANDOM = new Random(0xDEADBEEF);
    protected static final int ITERATIONS = 100;

    protected static byte[] randomBytes(final int count) {
        final byte[] bytes = new byte[count];

        for (int i = 0; i < count; i++) {
            bytes[i] = (byte) (Byte.MIN_VALUE + RANDOM.nextInt(Byte.MAX_VALUE << 1));
        }

        return bytes;
    }

    protected static short[] randomShorts(final int count) {
        final short[] shorts = new short[count];

        for (int i = 0; i < count; i++) {
            shorts[i] = (short) (Short.MIN_VALUE + RANDOM.nextInt(Short.MAX_VALUE << 1));
        }

        return shorts;
    }

    protected static int[] randomInts(final int count) {
        final int[] ints = new int[count];

        for (int i = 0; i < count; i++) {
            ints[i] = Integer.MIN_VALUE + (RANDOM.nextInt(Integer.MAX_VALUE) << 1);
        }

        return ints;
    }

    protected static long[] randomLongs(final int count) {
        final long[] longs = new long[count];

        for (int i = 0; i < count; i++) {
            longs[i] = Long.MIN_VALUE + (RANDOM.nextLong() << 1);
        }

        return longs;
    }

    protected static float[] randomFloats(final int count) {
        final float[] floats = new float[count];

        for (int i = 0; i < count; i++) {
            floats[i] = Float.MIN_VALUE + (RANDOM.nextFloat() * 2F);
        }

        return floats;
    }

    protected static double[] randomDoubles(final int count) {
        final double[] doubles = new double[count];

        for (int i = 0; i < count; i++) {
            doubles[i] = Double.MIN_VALUE + (RANDOM.nextDouble() * 2.0);
        }

        return doubles;
    }

    protected static char[] randomChars(final int count) {
        final char[] chars = new char[count];

        for (int i = 0; i < count; i++) {
            chars[i] = randomChar();
        }

        return chars;
    }

    protected static boolean randomBool() {
        return RANDOM.nextInt(100) <= 50;
    }

    protected static char randomChar() {
        return (char) (33 + RANDOM.nextInt(93));
    }

    protected static boolean[] randomBools(final int count) {
        final boolean[] result = new boolean[count];

        for (int i = 0; i < count; i++) {
            result[i] = randomBool();
        }

        return result;
    }

    protected static @NotNull String randomString(final int length) {
        final char[] chars = new char[length];

        for (int i = 0; i < length; i++) {
            chars[i] = randomChar();
        }

        return new String(chars);
    }

    protected static @NotNull String[] randomUniqueStrings(final int count, final int length) {
        final String[] result = new String[count];
        final HashSet<String> generated = new HashSet<>();

        for (int i = 0; i < count; i++) {
            String s = null;

            while (s == null || generated.contains(s)) {
                s = randomString(length);
            }

            generated.add(result[i] = s);
        }

        return result;
    }

    protected static @NotNull String[] randomStrings(final int count, final int length) {
        final String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            result[i] = randomString(length);
        }

        return result;
    }
}
