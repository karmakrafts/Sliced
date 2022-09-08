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

import io.karma.sliced.iterator.BoolIterator;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

/**
 * @author Alexander Hinze
 * @since 06/09/2022
 */
@API(status = Status.INTERNAL)
public final class ArrayBoolIterator implements BoolIterator {
    private final boolean[] ref;
    private int index;

    public ArrayBoolIterator(final boolean[] ref) {
        this.ref = ref;
    }

    @Override
    public boolean nextBool() {
        return ref[index++];
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < ref.length;
    }
}
