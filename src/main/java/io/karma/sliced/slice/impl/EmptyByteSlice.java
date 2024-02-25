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

package io.karma.sliced.slice.impl;

import io.karma.sliced.iterator.ByteIterator;
import io.karma.sliced.slice.ByteSlice;
import io.karma.sliced.slice.Slice;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class EmptyByteSlice implements ByteSlice {
    public static final EmptyByteSlice INSTANCE = new EmptyByteSlice();

    // @formatter:off
    private EmptyByteSlice() {}
    // @formatter:on

    @Override
    public byte getByte(final int index) {
        return 0;
    }

    @Override
    public byte[] toByteArray(final int offset, final int size) {
        return new byte[0];
    }

    @Override
    public int offset() {
        return 0;
    }

    @Override
    public @NotNull Slice<Byte> slice(final int offset, final int size) {
        return this;
    }

    @Override
    public void reset() {

    }

    @Override
    public @NotNull ByteIterator byteIterator() {
        return ByteIterator.NOP;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean hasMoreElements() {
        return false;
    }

    @Override
    public Byte nextElement() {
        return null;
    }
}
