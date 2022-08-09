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
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.INTERNAL)
class ListSlice<T> extends ListView<T> implements Slice<T> {
    private final int start;
    private final int end;
    private int iterationIndex;

    ListSlice(final @NotNull List<T> ref, final int start, final int end) {
        super(ref);
        this.start = start;
        this.end = end;
    }

    @Override
    public @NotNull Slice<T> slice(int start, int end) {
        final int actualStart = this.start + start;
        final int actualEnd = this.start + end;

        if(actualStart > end || actualEnd > end) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return new ListSlice<>(ref, actualStart, actualEnd);
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }

    @Override
    public T get(int index) {
        final int actualIndex = start + index;

        if(actualIndex > end) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return ref.get(actualIndex);
    }

    @Override
    public <C extends Collection<T>> @NotNull C copy(final int start, final int end, @NotNull IntFunction<C> factory) {
        final int size = end - start;
        final C result = factory.apply(size);

        for(int i = start; i <= end; i++) {
            result.add(get(i));
        }

        return result;
    }

    @Override
    public @NotNull T[] toArray(final int start, final int end, @NotNull IntFunction<T[]> factory) {
        final int size = end - start;
        final T[] result = factory.apply(size);
        int index = 0;

        for(int i = start; i <= end; i++) {
            result[index++] = get(i);
        }

        return result;
    }

    @Override
    public boolean hasMoreElements() {
        return iterationIndex < size() - 1;
    }

    @Override
    public T nextElement() {
        return ref.get(iterationIndex++);
    }

    @Override
    public void rewind() {
        iterationIndex = 0;
    }
}