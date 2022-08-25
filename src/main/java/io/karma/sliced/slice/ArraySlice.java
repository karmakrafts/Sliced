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

import io.karma.sliced.iterator.RangedArrayIterator;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.INTERNAL)
public final class ArraySlice<T> extends AbstractSlice<T> {
    private final T[] ref;
    private int iterationIndex;

    public ArraySlice(final @NotNull T[] ref, final int start, final int end) {
        super(start, end);
        this.ref = ref;
    }

    @Override
    public T get(final int index) {
        return ref[start + index];
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

        return new ArraySlice<>(ref, actualStart, actualEnd);
    }

    @SuppressWarnings("all")
    @Override
    public <C extends Collection<T>> @NotNull C copy(final int start, final int end, final @NotNull IntFunction<C> factory) {
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
            result.add(ref[i]);
        }

        return result;
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
        System.arraycopy(ref, start, result, 0, size);

        return result;
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size;
    }

    @Override
    public T nextElement() {
        return ref[start + iterationIndex++];
    }

    @Override
    public void reset() {
        iterationIndex = 0;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new RangedArrayIterator<>(ref, start, end);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ref);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final @Nullable Object obj) {
        final boolean isArray = obj instanceof Object[];

        if (!isArray && !(obj instanceof Slice)) {
            if (obj instanceof View) {
                int matches = 0;

                for (final T element : (View<? extends T>) obj) {
                    if (containsRef(element)) {
                        matches++;
                    }
                }

                return matches == size;
            }

            return false;
        }

        // @formatter:off
        final int length = isArray
            ? ((Object[])obj).length
            : ((Slice<? extends T>)obj).size();

        final IntFunction<? extends T> getter = isArray
            ? i -> (T)((Object[])obj)[i]
            : ((Slice<? extends T>)obj)::get;
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
        return Arrays.toString(ref);
    }
}