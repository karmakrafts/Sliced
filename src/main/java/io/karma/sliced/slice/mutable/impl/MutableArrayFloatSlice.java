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

package io.karma.sliced.slice.mutable.impl;

import io.karma.sliced.iterator.FloatIterator;
import io.karma.sliced.iterator.impl.RangedArrayFloatIterator;
import io.karma.sliced.slice.FloatSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ArrayFloatSlice;
import io.karma.sliced.slice.mutable.MutableFloatSlice;
import io.karma.sliced.util.MoreArrays;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.INTERNAL)
public final class MutableArrayFloatSlice extends AbstractMutableSlice<Float> implements MutableFloatSlice {
    private final float[] ref;
    private int iterationIndex;

    public MutableArrayFloatSlice(final float[] ref, final int offset, final int size) {
        super(offset, size);
        this.ref = ref;
    }

    @Override
    public float getFloat(final int index) {
        return ref[offset + index];
    }

    @Override
    public float[] toFloatArray(final int offset, final int size) {
        return MoreArrays.copy(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull Slice<Float> slice(final int offset, final int size) {
        return new ArrayFloatSlice(ref, this.offset + offset, size);
    }

    @Override
    public @NotNull FloatIterator floatIterator() {
        return new RangedArrayFloatIterator(ref, offset, size);
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

    // Object functions

    @Override
    public int hashCode() {
        return MoreArrays.hashCode(ref, offset, size);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof MutableArrayFloatSlice) {
            final MutableArrayFloatSlice other = (MutableArrayFloatSlice) obj;
            return Arrays.equals(other.ref, ref) && other.offset == offset && other.size == size;
        }
        else if (obj instanceof FloatSlice) {
            final FloatSlice slice = (FloatSlice) obj;
            final int size = slice.size();

            if (size != this.size) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (getFloat(i) != slice.getFloat(i)) {
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
                if (!slice.get(i).equals(getFloat(i))) {
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
                if (!itr.next().equals(getFloat(i))) {
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