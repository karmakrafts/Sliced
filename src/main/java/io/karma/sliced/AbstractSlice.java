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

package io.karma.sliced;

import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.INTERNAL)
abstract class AbstractSlice<T> implements Slice<T> {
    protected final int start;
    protected final int end;
    protected final int size;

    protected AbstractSlice(final int start, final int end) {
        this.start = start;
        this.end = end;
        size = end - start;
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return this;
    }

    @Override
    public int start() {
        return start;
    }

    @Override
    public int end() {
        return end;
    }

    @Override
    public int size() {
        return size;
    }
}