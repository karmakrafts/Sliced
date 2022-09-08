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

package io.karma.sliced.view.impl;

import io.karma.sliced.iterator.LongIterator;
import io.karma.sliced.iterator.impl.ArrayLongIterator;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ArrayLongSlice;
import io.karma.sliced.util.MoreArrays;
import io.karma.sliced.view.LongView;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayLongView implements LongView {
    private final long[] ref;

    public ArrayLongView(final long[] ref) {
        this.ref = ref;
    }

    @Override
    public @NotNull LongIterator longIterator() {
        return new ArrayLongIterator(ref);
    }

    @Override
    public @NotNull Spliterator.OfLong longSpliterator() {
        return Spliterators.spliterator(ref, 0);
    }

    @Override
    public long[] toLongArray() {
        return MoreArrays.copy(ref);
    }

    @Override
    public int size() {
        return ref.length;
    }

    @Override
    public @NotNull Slice<Long> asSlice() {
        return new ArrayLongSlice(ref, 0, ref.length);
    }

    // Object functions

    @Override
    public int hashCode() {
        return Arrays.hashCode(ref);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ArrayLongView) {
            return Arrays.equals(((ArrayLongView) obj).ref, ref);
        }
        else if (obj instanceof LongView) {
            final LongView other = (LongView) obj;
            final int size = other.size();

            if (size != ref.length) {
                return false;
            }

            final LongIterator itr = other.longIterator();

            for (int i = 0; i < size; i++) {
                if (!itr.hasNext() || ref[i] != itr.nextLong()) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof View) {
            final View<?> other = (View<?>) obj;
            final int size = other.size();

            if (size != ref.length) {
                return false;
            }

            final Iterator<?> itr = other.iterator();

            for (int i = 0; i < size; i++) {
                if (!itr.hasNext() || !itr.next().equals(ref[i])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return Arrays.toString(ref);
    }
}
