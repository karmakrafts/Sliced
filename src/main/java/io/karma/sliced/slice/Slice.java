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

package io.karma.sliced.slice;

import io.karma.sliced.slice.impl.ArraySlice;
import io.karma.sliced.slice.impl.ListSlice;
import io.karma.sliced.util.ResettableEnumeration;
import io.karma.sliced.view.View;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;

/**
 * A slice is a {@link View}, which provides the ability
 * to retrieve singular elements by their index, as well
 * as creating sub-slices from a given index range.
 * <p>
 * A slice is also a {@link ResettableEnumeration}, which means
 * it can be used like a regular {@link java.util.Enumeration}
 * with the benefit of being able to reset the internal iterator
 * of the enumeration instance.
 * <b>Note</b>
 * If you don't depend on the ability to index into
 * the underlying collection/array, use {@link View}
 * instead. See {@link View#of(Object[])} and it's overloads.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public interface Slice<T> extends View<T>, ResettableEnumeration<T> {
    /**
     * Creates a new slice instance which references the given array.
     *
     * @param <T>    The element type of the given array, and the newly created slice.
     * @param array  The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new slice instance, which references the given array.
     */
    static <T> @NotNull Slice<T> of(final @NotNull T[] array, final int offset, final int size) {
        return new ArraySlice<>(array, offset, size);
    }

    /**
     * Creates a new slice instance which references the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new slice instance, which references the given array.
     */
    @SafeVarargs
    static <T> @NotNull Slice<T> of(final @NotNull T... array) {
        return new ArraySlice<>(array, 0, array.length);
    }

    /**
     * Creates a new slice instance which references the given {@link List}.
     *
     * @param <T>    The element type of the given list, and the newly created slice.
     * @param list   The list of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new slice instance, which references the given list.
     */
    static <T> @NotNull Slice<T> of(final @NotNull List<T> list, final int offset, final int size) {
        return new ListSlice<>(list, offset, size);
    }

    /**
     * Creates a new slice instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created slice.
     * @param list The list of which to create a slice.
     * @return A new slice instance, which references the given list.
     */
    static <T> @NotNull Slice<T> of(final @NotNull List<T> list) {
        return new ListSlice<>(list, 0, list.size());
    }

    /**
     * Creates a new slice instance which references a copy of the given array.
     *
     * @param <T>    The element type of the given array, and the newly created slice.
     * @param array  The array of which to create a slice.
     * @param offset The index at which the newly created slice should begin.
     * @param size   The size of the newly created slice.
     * @return A new slice instance, which references the given array.
     */
    static <T> @NotNull Slice<T> copyOf(final @NotNull T[] array, final int offset, final int size, final @NotNull IntFunction<T[]> factory) {
        final int arraySize = array.length;

        if (offset < 0 || size < 0 || size > arraySize) {
            throw new ArrayIndexOutOfBoundsException("Invalid slice range");
        }

        final T[] copy = factory.apply(size);
        System.arraycopy(array, offset, copy, 0, size);
        return new ArraySlice<>(copy, offset, size);
    }

    /**
     * Creates a new slice instance which references a copy of the given array.
     *
     * @param <T>   The element type of the given array, and the newly created slice.
     * @param array The array of which to create a slice.
     * @return A new slice instance, which references the given array.
     */
    @SafeVarargs
    static <T> @NotNull Slice<T> copyOf(final @NotNull T... array) {
        return new ArraySlice<>(array, 0, array.length);
    }

    /**
     * Creates a new slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @param offset     The index at which the newly created slice should begin.
     * @param size       The size of the newly created slice.
     * @return A new slice instance, which references the given list.
     */
    static <T> @NotNull Slice<T> copyOf(final @NotNull Collection<T> collection, final int offset, final int size) {
        return new ListSlice<>(new ArrayList<>(collection), offset, size);
    }

    /**
     * Creates a new slice instance which references the given {@link Collection}.
     * Creates a new wrapper list for the slice to index into.
     *
     * @param <T>        The element type of the given list, and the newly created slice.
     * @param collection The list of which to create a slice.
     * @return A new slice instance, which references the given list.
     */
    static <T> @NotNull Slice<T> copyOf(final @NotNull Collection<T> collection) {
        return new ListSlice<>(new ArrayList<>(collection), 0, collection.size());
    }

    /**
     * Retrieves the start index of this slice instance.
     *
     * @return The start index of this slice instance.
     */
    int offset();

    /**
     * Retrieves an element of type {@link T} at the given index.
     *
     * @param index The index of the element which to retrieve.
     * @return The element at the given index.
     * @throws ArrayIndexOutOfBoundsException if the given index is out of range.
     */
    T get(final int index);

    /**
     * Creates a new sub-slice from this slice instance,
     * with the given index range relative to the start index
     * of this slice instance.
     *
     * @param offset The index at which the newly created slice should begin
     *               (relative to the offset of this slice).
     * @param size   The size of the newly created slice.
     * @return A new slice instance containing the same collection/array reference
     *         as this slice instance, but with the given relative index range.
     */
    @NotNull Slice<T> slice(final int offset, final int size);

    /**
     * Creates a new array of size n, where n is the number of elements contained
     * within the underlying collection of this view instance, using the given factory function,
     * and copies a reference to all elements contained within the collection
     * this view is referencing into the newly created array.
     *
     * @param offset  The index at which the newly created array should begin
     *                (relative to the offset of this slice).
     * @param size    The size of the newly created array.
     * @param factory The function with which to create the new array of size n.
     * @return A new array of size n, containing a reference to all elements contained
     *         within this view's underlying collection/array.
     */
    default @NotNull T[] toArray(final int offset, final int size, final @NotNull IntFunction<T[]> factory) {
        if (offset < 0 || offset > size() || size <= 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid slice range");
        }

        final T[] result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result[i] = get(offset + i);
        }

        return result;
    }

    /**
     * Creates a new collection of type {@link C}, and copies all element references
     * contained within the underlying collection/array of this view into the newly
     * created collection.
     *
     * @param offset  The index at which the newly created collection should begin
     *                (relative to the offset of this slice).
     * @param size    The size of the newly created collection.
     * @param factory The function with which to create the new collection.
     * @param <C>     The type of the collection to create.
     * @return A new collection instance of type {@link C}, containing all elements
     *         given access to by this view instance.
     */
    default <C extends Collection<T>> @NotNull C copy(final int offset, final int size, final @NotNull IntFunction<C> factory) {
        if (offset < 0 || offset > size() || size <= 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid slice range");
        }

        final C result = factory.apply(size);

        for (int i = 0; i < size; i++) {
            result.add(get(offset + i));
        }

        return result;
    }

    /**
     * Creates a new collection of type {@link C}, and copies all element references
     * contained within the underlying collection/array of this view into the newly
     * created collection.
     *
     * @param factory The function with which to create the new collection,
     *                pre-allocated with the given size.
     * @param <C>     The type of the collection to create.
     * @return A new collection instance of type {@link C}, containing all elements
     *         given access to by this view instance.
     */
    default <C extends Collection<T>> @NotNull C copy(final @NotNull IntFunction<C> factory) {
        return copy(0, size(), factory);
    }

    /**
     * Creates a new {@link List}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @param offset The index at which the newly created list should begin
     *               (relative to the offset of this slice).
     * @param size   The size of the newly created list.
     * @return A new list containing all elements given access to by this view instance.
     */
    default @NotNull ArrayList<T> copyArrayList(final int offset, final int size) {
        return copy(offset, size, ArrayList::new);
    }

    /**
     * Creates a new {@link Set}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @param offset The index at which the newly created set should begin
     *               (relative to the offset of this slice).
     * @param size   The size of the newly created set.
     * @return A new set containing all elements given access to by this view instance.
     */
    default @NotNull HashSet<T> copyHashSet(final int offset, final int size) {
        return copy(offset, size, HashSet::new);
    }

    /**
     * Maps the element of type {@link T} to a new element of type {@link R}.
     *
     * @param index    The index of the element to map.
     * @param function The function to map the element with.
     * @param <R>      The return value type.
     * @return A new element of type {@link T}.
     */
    default <R> R map(final int index, final @NotNull Function<T, R> function) {
        return function.apply(get(index));
    }

    /**
     * Retrieves the element at the given index if it is
     * not null, otherwise it returns the given default value.
     *
     * @param index The index of the element to retrieve.
     * @param def   A not-null default value of type {@link T}.
     * @return The element at the given index if not null, otherwise the default value.
     */
    default @NotNull T getOrElse(final int index, final @NotNull T def) {
        final T value = get(index);
        return value != null ? value : def;
    }

    /**
     * Retrieves the element at the given index if it is
     * not null, otherwise throws an exception of type {@link X}.
     *
     * @param index    The index of the element to retrieve.
     * @param supplier The exception supplier.
     * @param <X>      The exception type.
     * @return The element at the given index.
     * @throws X If the value at the given index was null.
     */
    default <X extends Throwable> @NotNull T getOrThrow(final int index, final @NotNull Supplier<X> supplier) throws X {
        final T value = get(index);

        if (value == null) {
            throw supplier.get();
        }

        return value;
    }

    /**
     * Retrieves an {@link Optional} of the element at the given index.
     * If the element at the given index is null, {@link Optional#empty()} is returned.
     *
     * @param index The index of the element to retrieve.
     * @return An instance of {@link Optional}, containing the requested element if present.
     */
    default @NotNull Optional<T> getOptional(final int index) {
        return Optional.ofNullable(get(index));
    }

    @Override
    default void forEachIndexed(final @NotNull ObjIntConsumer<T> consumer) {
        final int size = size();

        for (int i = 0; i < size; i++) {
            consumer.accept(get(i), i);
        }
    }

    @Override
    default @NotNull T[] toArray(final @NotNull IntFunction<T[]> factory) {
        return toArray(0, size(), factory);
    }

    @Override
    default <C extends Collection<T>> @NotNull C copy(final @NotNull Supplier<C> factory) {
        return copy(0, size(), i -> factory.get());
    }
}