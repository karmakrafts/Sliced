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

package io.karma.sliced;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@API(status = Status.INTERNAL)
final class MutableStackSliceImpl<T, S extends Stack<T>> extends AbstractMutableSlice<T> implements MutableStackSlice<T> {
    private final S ref;
    private int iterationIndex;

    MutableStackSliceImpl(final @NotNull S ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public @NotNull Slice<T> slice(final int start, final int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;
        final int maxIndex = this.size - 1;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        return new StackSliceImpl<>(ref, actualStart, actualEnd);
    }

    @Override
    public T get(final int index) {
        return ref.get(index);
    }

    @Override
    public @NotNull T[] toArray(final int start, final int end, final @NotNull IntFunction<T[]> factory) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;
        final int maxIndex = this.size - 1;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        final int size = actualEnd - actualStart;
        final T[] result = factory.apply(size);
        int index = 0;

        for (int i = actualStart; i <= actualEnd; i++) {
            result[index++] = ref.get(i);
        }

        return result;
    }

    @Override
    public <C extends Collection<T>> @NotNull C copy(int start, int end, @NotNull IntFunction<C> factory) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;
        final int maxIndex = this.size - 1;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        final int size = actualEnd - actualStart;
        final C result = factory.apply(size);

        for (int i = actualStart; i <= actualEnd; i++) {
            result.add(ref.get(i));
        }

        return result;
    }

    @Override
    public T peek() {
        return ref.peek();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return ref.iterator();
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < ref.size() - 1;
    }

    @Override
    public T nextElement() {
        return ref.get(iterationIndex++);
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }
}
