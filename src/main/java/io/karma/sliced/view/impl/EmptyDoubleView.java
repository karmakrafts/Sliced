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

package io.karma.sliced.view.impl;

import io.karma.sliced.iterator.DoubleIterator;
import io.karma.sliced.slice.DoubleSlice;
import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.DoubleView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.Spliterator;
import java.util.Spliterators;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyDoubleView implements DoubleView {
    public static final EmptyDoubleView INSTANCE = new EmptyDoubleView();

    // @formatter:off
    private EmptyDoubleView() {}
    // @formatter:on

    @Override
    public @NotNull DoubleIterator doubleIterator() {
        return DoubleIterator.NOP;
    }

    @Override
    public @NotNull Spliterator.OfDouble doubleSpliterator() {
        return Spliterators.emptyDoubleSpliterator();
    }

    @Override
    public double[] toDoubleArray() {
        return new double[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull Slice<Double> asSlice() {
        return DoubleSlice.empty();
    }
}
