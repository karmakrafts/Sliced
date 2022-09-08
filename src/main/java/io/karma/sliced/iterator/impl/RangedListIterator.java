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

package io.karma.sliced.iterator.impl;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Hinze
 * @since 12/08/2022
 */
@API(status = Status.INTERNAL)
public final class RangedListIterator<T> implements Iterator<T> {
    private final List<T> ref;
    private final int offset;
    private final int size;
    private int index = 0;

    public RangedListIterator(final @NotNull List<T> ref, final int offset, final int size) {
        this.ref = ref;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public T next() {
        return ref.get(offset + index++);
    }
}
