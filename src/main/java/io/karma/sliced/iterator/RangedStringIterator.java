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

package io.karma.sliced.iterator;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 13/08/2022
 */
@API(status = Status.INTERNAL)
public final class RangedStringIterator implements Iterator<Character> {
    private final CharSequence seq;
    private final int start;
    private final int size;
    private int index;

    public RangedStringIterator(final @NotNull CharSequence seq, final int start, final int end) {
        this.seq = seq;
        this.start = start;
        size = end - start;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public @NotNull Character next() {
        return seq.charAt(start + index++);
    }
}
