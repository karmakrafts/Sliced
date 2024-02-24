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
 * @since 03/09/2022
 */
@API(status = Status.INTERNAL)
public final class RangedCharSeqTextIterator implements TextIterator {
    private final CharSequence ref;
    private final int offset;
    private final int size;
    private int index;

    public RangedCharSeqTextIterator(final @NotNull CharSequence ref, final int offset, final int size) {
        this.ref = ref;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public char first() {
        return ref.charAt(offset + (index = 0));
    }

    @Override
    public char last() {
        return ref.charAt(offset + (index = (size - 1)));
    }

    @Override
    public char current() {
        return ref.charAt(offset + index);
    }

    @Override
    public char next() {
        return ref.charAt(offset + ++index);
    }

    @Override
    public char previous() {
        return ref.charAt(offset + --index);
    }

    @Override
    public char setIndex(final int position) {
        return ref.charAt(offset + (index = position));
    }

    @Override
    public int getBeginIndex() {
        return 0;
    }

    @Override
    public int getEndIndex() {
        return size - 1;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @SuppressWarnings("all")
    @Override
    public @NotNull Object clone() {
        final RangedCharSeqTextIterator result = new RangedCharSeqTextIterator(ref, offset, offset + size);
        result.index = index;
        return result;
    }
}
