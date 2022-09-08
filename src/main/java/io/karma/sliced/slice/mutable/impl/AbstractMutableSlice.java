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

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.mutable.MutableSlice;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 11/08/2022
 */
@API(status = Status.INTERNAL)
public abstract class AbstractMutableSlice<T> implements MutableSlice<T> {
    protected int offset;
    protected int size;

    protected AbstractMutableSlice(final int offset, final int size) {
        this.offset = offset;
        this.size = size;
    }

    @Override
    public void setOffset(final int offset) {
        this.offset = offset;
    }

    @Override
    public void setSize(final int size) {
        this.size = size;
    }

    @Override
    public @NotNull Slice<T> asSlice() {
        return this;
    }

    @Override
    public int offset() {
        return offset;
    }

    @Override
    public int size() {
        return size;
    }
}
