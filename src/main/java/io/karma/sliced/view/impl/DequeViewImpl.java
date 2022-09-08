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

package io.karma.sliced.view.impl;

import io.karma.sliced.view.DequeView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Deque;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
public final class DequeViewImpl<T, Q extends Deque<T>> extends CollectionView<T, Q> implements DequeView<T> {
    public DequeViewImpl(final @NotNull Q ref) {
        super(ref);
    }

    @Override
    public T peek() {
        return ref.peek();
    }

    @Override
    public T peekLast() {
        return ref.peekLast();
    }
}