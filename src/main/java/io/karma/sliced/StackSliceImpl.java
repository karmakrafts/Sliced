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
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
final class StackSliceImpl<T, S extends Stack<T>> extends AbstractSlice<T> implements StackSlice<T> {
    private final S ref;
    private int iterationIndex;

    StackSliceImpl(final @NotNull S ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public T get(final int index) {
        return ref.get(start + index);
    }

    @Override
    public T peek() {
        return get(size);
    }

    @Override
    public @NotNull Slice<T> slice(final int start, final int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if (actualStart < 0 || actualStart > maxIndex) {
            throw new ArrayIndexOutOfBoundsException("Start index is out of range");
        }

        if (actualEnd < 0 || actualEnd > maxIndex || actualEnd < actualStart) {
            throw new ArrayIndexOutOfBoundsException("End index is out of range");
        }

        return new StackSliceImpl<>(ref, actualStart, actualEnd);
    }

    @Override
    public @NotNull T[] toArray(final int start, final int end, final @NotNull IntFunction<T[]> factory) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

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
    public @NotNull Iterator<T> iterator() {
        return new RangedListIterator<>(ref, start, end);
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public T nextElement() {
        return ref.get(start + iterationIndex++);
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final @Nullable Object obj) {
        final boolean isStack = obj instanceof Stack;

        if (!isStack && !(obj instanceof StackSliceImpl)) {
            return false;
        }

        // @formatter:off
        final int length = isStack
            ? ((Stack<? extends T>)obj).size()
            : ((StackSliceImpl<? extends T, ?>)obj).size;

        final IntFunction<T> getter = isStack
            ? ((Stack<? extends T>)obj)::get
            : ((StackSliceImpl<? extends T, ?>)obj)::get;
        // @formatter:on

        int matches = 0;

        for (int i = 0; i < length; i++) {
            if (!get(i).equals(getter.apply(i))) {
                break;
            }

            matches++;
        }

        return matches == length;
    }

    @Override
    public @NotNull String toString() {
        return super.toString();
    }
}
