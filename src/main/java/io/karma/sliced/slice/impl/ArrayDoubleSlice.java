/*
 * Copyright 2022 - 2024 Karma Krafts & associates
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.karma.sliced.slice.impl;

import io.karma.sliced.iterator.DoubleIterator;
import io.karma.sliced.iterator.impl.RangedArrayDoubleIterator;
import io.karma.sliced.slice.DoubleSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.util.MoreArrays;
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
 * @since 25/08/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayDoubleSlice extends AbstractSlice<Double> implements DoubleSlice {
    private final double[] ref;
    private int iterationIndex;

    public ArrayDoubleSlice(final double[] ref, final int offset, final int size) {
        super(offset, size);
        this.ref = ref;
    }

    @Override
    public double getDouble(final int index) {
        return ref[offset + index];
    }

    @Override
    public double[] toDoubleArray(final int offset, final int size) {
        return MoreArrays.copy(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull Slice<Double> slice(final int offset, final int size) {
        return new ArrayDoubleSlice(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull DoubleIterator doubleIterator() {
        return new RangedArrayDoubleIterator(ref, offset, size);
    }

    @Override
    public @NotNull Spliterator.OfDouble doubleSpliterator() {
        return Spliterators.spliterator(ref, offset, size, 0);
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

    // Object functions

    @Override
    public int hashCode() {
        return MoreArrays.hashCode(ref, offset, size);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ArrayDoubleSlice) {
            final ArrayDoubleSlice other = (ArrayDoubleSlice) obj;
            return Arrays.equals(other.ref, ref) && other.offset == offset && other.size == size;
        }
        else if (obj instanceof DoubleSlice) {
            final DoubleSlice slice = (DoubleSlice) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (getDouble(i) != slice.getDouble(i)) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof Slice) {
            final Slice<?> slice = (Slice<?>) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!slice.get(i).equals(getDouble(i))) {
                    return false;
                }
            }

            return true;
        }
        else if (obj instanceof View) {
            final View<?> view = (View<?>) obj;
            final int size = view.size();

            if (size != this.size) {
                return false;
            }

            final Iterator<?> itr = view.iterator();

            for (int i = 0; i < size; i++) {
                if (!itr.next().equals(getDouble(i))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return MoreArrays.toString(ref, offset, size);
    }
}
