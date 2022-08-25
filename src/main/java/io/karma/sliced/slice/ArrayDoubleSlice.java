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

import io.karma.sliced.iterator.DoubleIterator;
import io.karma.sliced.iterator.RangedDoubleIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayDoubleSlice extends AbstractSlice<Double> implements DoubleSlice {
    private final double[] ref;
    private int iterationIndex;

    public ArrayDoubleSlice(final double[] ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public double getDouble(final int index) {
        return ref[index];
    }

    @Override
    public double[] toDoubleArray(final int start, final int end) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final double[] copy = new double[size];
        System.arraycopy(ref, start, copy, 0, size);
        return copy;
    }

    @Override
    public @NotNull Double get(final int index) {
        return ref[index];
    }

    @Override
    public @NotNull Slice<Double> slice(final int start, final int end) {
        return new ArrayDoubleSlice(ref, start, end);
    }

    @Override
    public @NotNull Double[] toArray(final int start, final int end, final @NotNull IntFunction<Double[]> factory) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final Double[] result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result[i] = ref[i + start];
        }

        return result;
    }

    @Override
    public <C extends Collection<Double>> @NotNull C copy(final int start, final int end, @NotNull IntFunction<C> factory) {
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
    public @NotNull DoubleIterator doubleIterator() {
        return new RangedDoubleIterator(ref, start, end);
    }

    @Override
    public @NotNull Spliterator.OfDouble doubleSpliterator() {
        return Spliterators.spliterator(ref, start, end, 0);
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
    public @NotNull Double nextElement() {
        return ref[iterationIndex++];
    }
}
