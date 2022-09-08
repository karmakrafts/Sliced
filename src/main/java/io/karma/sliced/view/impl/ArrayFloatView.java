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

import io.karma.sliced.iterator.FloatIterator;
import io.karma.sliced.iterator.impl.ArrayFloatIterator;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ArrayFloatSlice;
import io.karma.sliced.util.MoreArrays;
import io.karma.sliced.view.FloatView;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 04/09/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayFloatView implements FloatView {
    private final float[] ref;

    public ArrayFloatView(final float[] ref) {
        this.ref = ref;
    }

    @Override
    public @NotNull FloatIterator floatIterator() {
        return new ArrayFloatIterator(ref);
    }

    @Override
    public float[] toFloatArray() {
        return MoreArrays.copy(ref);
    }

    @Override
    public int size() {
        return ref.length;
    }

    @Override
    public @NotNull Slice<Float> asSlice() {
        return new ArrayFloatSlice(ref, 0, ref.length);
    }

    // Object functions

    @Override
    public int hashCode() {
        return Arrays.hashCode(ref);
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof ArrayFloatView) {
            return Arrays.equals(((ArrayFloatView) obj).ref, ref);
        }
        else if (obj instanceof FloatView) {
            final FloatView other = (FloatView) obj;
            final int size = other.size();

            if (size != ref.length) {
                return false;
            }

            final FloatIterator itr = other.floatIterator();

            for (int i = 0; i < size; i++) {
                if (!itr.hasNext() || ref[i] != itr.nextFloat()) {
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
