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

package io.karma.sliced.slice.mutable;

import io.karma.sliced.iterator.LongIterator;
import io.karma.sliced.iterator.RangedLongIterator;
import io.karma.sliced.slice.ArrayLongSlice;
import io.karma.sliced.slice.Slice;
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
public final class MutableArrayLongSlice extends AbstractMutableSlice<Long> implements MutableLongSlice {
    private final long[] ref;
    private int iterationIndex;

    public MutableArrayLongSlice(final long[] ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public long getLong(final int index) {
        return ref[index];
    }

    @Override
    public long[] toLongArray(final int start, final int end) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final long[] copy = new long[size];
        System.arraycopy(ref, start, copy, 0, size);
        return copy;
    }

    @Override
    public @NotNull Long get(final int index) {
        return ref[index];
    }

    @Override
    public @NotNull Slice<Long> slice(final int start, final int end) {
        return new ArrayLongSlice(ref, start, end);
    }

    @Override
    public @NotNull Long[] toArray(final int start, final int end, final @NotNull IntFunction<Long[]> factory) {
        final int size = end - start;

        if (size > this.size) {
            throw new IndexOutOfBoundsException();
        }

        final Long[] result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result[i] = ref[i + start];
        }

        return result;
    }

    @Override
    public <C extends Collection<Long>> @NotNull C copy(final int start, final int end, @NotNull IntFunction<C> factory) {
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
    public @NotNull LongIterator longIterator() {
        return new RangedLongIterator(ref, start, end);
    }

    @Override
    public @NotNull Spliterator.OfLong longSpliterator() {
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
    public @NotNull Long nextElement() {
        return ref[iterationIndex++];
    }
}
