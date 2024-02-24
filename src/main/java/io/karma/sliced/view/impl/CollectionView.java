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

package io.karma.sliced.view.impl;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ListSlice;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = Status.INTERNAL)
public class CollectionView<T, C extends Collection<T>> implements View<T> {
    protected final C ref;

    public CollectionView(final @NotNull C ref) {
        this.ref = ref;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @NotNull Slice<T> asSlice() { // @formatter:off
        return new ListSlice<>(ref instanceof List
            ? (List<T>)ref
            : new ArrayList<>(ref), 0, ref.size());
    } // @formatter:on

    @Override
    public int size() {
        return ref.size();
    }

    @Override
    public boolean contains(final @Nullable T value) {
        return ref.contains(value);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return ref.iterator();
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj instanceof CollectionView) { // Take a shortcut if we can..
            return ((CollectionView<?, ?>) obj).ref.equals(ref);
        }
        else if (obj instanceof View) { // ..otherwise try the slow path.
            final View<?> other = (View<?>) obj;

            if (other.size() != ref.size()) {
                return false;
            }

            final Iterator<?> itr = other.iterator();

            for (final T element : this) {
                if (element != itr.next()) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public @NotNull String toString() {
        return ref.toString();
    }
}
