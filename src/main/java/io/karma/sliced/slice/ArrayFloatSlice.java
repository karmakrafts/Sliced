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

package io.karma.sliced.slice;

import io.karma.sliced.iterator.FloatIterator;
import io.karma.sliced.iterator.RangedFloatIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayFloatSlice extends AbstractSlice<Float> implements FloatSlice {
    private final float[] ref;
    private int iterationIndex;

    public ArrayFloatSlice(final float[] ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public float getFloat(final int index) {
        return ref[index];
    }

    @Override
    public float[] toFloatArray(final int start, final int end) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final float[] copy = new float[size];
        System.arraycopy(ref, start, copy, 0, size);
        return copy;
    }

    @Override
    public @NotNull Float get(final int index) {
        return ref[index];
    }

    @Override
    public @NotNull Slice<Float> slice(final int start, final int end) {
        return new ArrayFloatSlice(ref, start, end);
    }

    @Override
    public @NotNull Float[] toArray(final int start, final int end, final @NotNull IntFunction<Float[]> factory) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final Float[] result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result[i] = ref[i + start];
        }

        return result;
    }

    @Override
    public <C extends Collection<Float>> @NotNull C copy(final int start, final int end, @NotNull IntFunction<C> factory) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final C result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result.add(ref[i + start]);
        }

        return result;
    }

    @Override
    public @NotNull FloatIterator floatIterator() {
        return new RangedFloatIterator(ref, start, end);
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public @NotNull Float nextElement() {
        return ref[iterationIndex++];
    }
}
