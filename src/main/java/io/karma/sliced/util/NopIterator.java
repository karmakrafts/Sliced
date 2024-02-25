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

package io.karma.sliced.util;

import org.apiguardian.api.API;

import java.util.Iterator;

/**
 * @author Alexander Hinze
 * @since 25/02/2024
 */
@API(status = API.Status.INTERNAL)
public final class NopIterator<T> implements Iterator<T> {
    private static final NopIterator<?> INSTANCE = new NopIterator<>();

    // @formatter:off
    private NopIterator() {}
    // @formatter:on

    @SuppressWarnings("unchecked")
    public static <T> NopIterator<T> instance() {
        return (NopIterator<T>) INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
