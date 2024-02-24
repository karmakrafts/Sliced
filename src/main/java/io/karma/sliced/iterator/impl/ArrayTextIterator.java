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

package io.karma.sliced.iterator.impl;

import io.karma.sliced.iterator.TextIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 06/09/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayTextIterator implements TextIterator {
    private final char[] ref;
    private int index;

    public ArrayTextIterator(final char[] ref) {
        this.ref = ref;
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public char first() {
        return ref[index = 0];
    }

    @Override
    public char last() {
        return ref[index = (ref.length - 1)];
    }

    @Override
    public char current() {
        return ref[index];
    }

    @Override
    public char next() {
        return ref[++index];
    }

    @Override
    public char previous() {
        return ref[--index];
    }

    @Override
    public char setIndex(final int position) {
        return ref[index = position];
    }

    @Override
    public int getBeginIndex() {
        return 0;
    }

    @Override
    public int getEndIndex() {
        return ref.length - 1;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull Object clone() {
        final ArrayTextIterator result = new ArrayTextIterator(ref);
        result.index = index;
        return result;
    }
}
