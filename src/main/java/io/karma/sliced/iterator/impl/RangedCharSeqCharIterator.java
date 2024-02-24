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

import io.karma.sliced.iterator.CharIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.INTERNAL)
public final class RangedCharSeqCharIterator implements CharIterator {
    private final CharSequence seq;
    private final int offset;
    private final int size;
    private int index;

    public RangedCharSeqCharIterator(final @NotNull CharSequence seq, final int offset, final int size) {
        this.seq = seq;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public char nextChar() {
        return seq.charAt(offset + index++);
    }
}
