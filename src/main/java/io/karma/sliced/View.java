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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * <h2>Information</h2>
 * A view describes a read-only window into an existing sequence of elements,
 * like a collection or an array. This is a lot cleaner and more expressive
 * than existing solutions, since most implementations resort to stubbing out
 * standard-functions from a given interface, and make them throw exceptions,
 * which is a hassle to debug most of the time; a redundant waste of time.
 * <p>
 * This allows for the easy and clean separation between actual collections/arrays,
 * and references to them which don't allow write-access.
 *
 * @author Alexander Hinze
 * @since 09/08/2022
 */
@API(status = API.Status.STABLE)
public interface View<T> extends Iterable<T> {
    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given array.
     *
     * @param <TT>  The element type of the given array, and the newly created view.
     * @param array The array of which to create a view.
     * @return A new view instance, which references the given array.
     */
    static <TT> @NotNull View<TT> of(final @NotNull TT[] array) {
        return new ArrayView<>(array);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link Collection}.
     * <h2>Note</h2>
     * That if your given collection is a {@link List}, {@link Queue},
     * {@link Deque}, {@link Stack} or {@link Map}, you should use the
     * specialized functions provided by this class.
     *
     * @param <TT>       The element type of the given array, and the newly created view.
     * @param collection The collection of which to create a view.
     * @return A new view instance, which references the given array.
     */
    static <TT> @NotNull View<TT> of(final @NotNull Collection<TT> collection) {
        return new CollectionView<>(collection);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new view instance which references the given {@link List}.
     *
     * @param <TT> The element type of the given list, and the newly created view.
     * @param list The list of which to create a view.
     * @return A new view instance, which references the given list.
     */
    static <TT> @NotNull View<TT> of(final @NotNull List<TT> list) {
        return new ListView<>(list);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b>
     *
     * @return The number of element contained within the collection/array
     *         referenced by this view instance.
     */
    int size();

    /**
     * <h2>Information</h2>
     * <b>Time Complexity:</b><br>
     * <ul>
     *     <li><b>O(1)</b> if the collection referenced by this view is an array, a {@link java.util.List}, or a {@link java.util.Stack}</li>
     *     <li><b>O(n)</b> if the collection is none of the above types, and a new wrapper list needs to be created without using {@link System#arraycopy(Object, int, Object, int, int)}</li>
     * </ul>
     * Creates a new slice instance from this view instance containing
     * the same underlying collection reference, or a newly created wrapper list
     * if there's no specialization available for the type of the underlying collection.
     * <h2>Note</h2>
     * If you want to create slices directly, see {@link Slice#of(Object[])} and it's overloads.
     *
     * @return A new slice instance with a reference to the same collection/array
     *         as this view instance.
     */
    @NotNull Slice<T> asSlice();

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new array of size n, where n is the number of elements contained
     * within the underlying collection of this view instance, using the given factory function,
     * and copies a reference to all elements contained within the collection
     * this view is referencing into the newly created array.
     *
     * @param factory The function with which to create the new array of size n.
     * @return A new array of size n, containing a reference to all elements contained
     *         within this view's underlying collection/array.
     */
    default @NotNull T[] toArray(final @NotNull IntFunction<T[]> factory) {
        final T[] result = factory.apply(size());
        int index = 0;

        for (final T element : this) {
            result[index++] = element;
        }

        return result;
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new collection of type {@link C}, and copies all element references
     * contained within the underlying collection/array of this view into the newly
     * created collection.
     *
     * @param factory The function with which to create the new collection.
     * @param <C>     The type of the collection to create.
     * @return A new collection instance of type {@link C}, containing all elements
     *         given access to by this view instance.
     */
    default <C extends Collection<T>> @NotNull C copy(final @NotNull Supplier<C> factory) {
        final C result = factory.get();

        for (final T element : this) {
            result.add(element);
        }

        return result;
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity:</b><br>
     * <ul>
     *     <li><b>O(n)</b> if the default implementation provided by this interface is used</li>
     *     <li><b>O(1)</b> if a specialized implementation on a {@link java.util.HashSet} is used</li>
     * </ul>
     * Checks if the given value is contained within the collection/array referenced
     * by this view instance.
     *
     * @param value The value to check for.
     * @return True if the given value is contained within the underlying collection/array
     *         of this view instance.
     */
    default boolean contains(final @Nullable T value) {
        if (value == null) {
            return false;
        }

        for (final T element : this) {
            if (!element.equals(value)) {
                continue;
            }

            return true;
        }

        return false;
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Checks whether or not the collection/array referenced by this view instance
     * contains the exact given reference to the given value.
     *
     * @param ref The reference to check for.
     * @return True if the collection/array associated with this view instance
     *         contains the exact given reference to an object.
     */
    default boolean containsRef(final @Nullable T ref) {
        if (ref == null) {
            return false;
        }

        for (final T element : this) {
            if (element != ref) {
                continue;
            }

            return true;
        }

        return false;
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new non-parallelized instance of {@link Stream},
     * with access to all elements contained within the underlying
     * collection/array of this view instance.
     *
     * @return A new, non-parallelized {@link Stream} instance with access to the elements
     *         contained within the collection/array of this view instance.
     */
    default @NotNull Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(1)</b><br>
     * Creates a new parallelized instance of {@link Stream},
     * with access to all elements contained within the underlying
     * collection/array of this view instance.
     *
     * @return A new, parallelized {@link Stream} instance with access to the elements
     *         contained within the collection/array of this view instance.
     */
    default @NotNull Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Iterates over all elements given access to by this view instance,
     * and provides the current element, as well as it's associated index.
     *
     * @param consumer A function which takes an element of type {@link T},
     *                 as well as an index of type {@code int}.
     */
    default void forEachIndexed(final @NotNull ObjIntConsumer<T> consumer) {
        int index = 0;

        for (final T element : this) {
            consumer.accept(element, index++);
        }
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Iterates over all elements, filtering them using the given predicate,
     * and calls the given callback functions for each retained entry.
     *
     * @param filter   The function with which to filter the entries while iterating over them.
     *                 If the function returns true for a given entry, the entry will be retained
     *                 for callback, otherwise it will be skipped.
     * @param consumer The function which to call for each element being iterated over.
     */
    default void forAll(final @NotNull Predicate<T> filter, final @NotNull Consumer<T> consumer) {
        for (final T element : this) {
            if (!filter.test(element)) {
                continue;
            }

            consumer.accept(element);
        }
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Iterates over all elements, filtering them using the given predicate,
     * and calls the given callback functions for each retained entry. The function
     * also takes care of keeping track of the current element index, and provides that
     * alongside the current element through the given callback function.
     *
     * @param filter   The function with which to filter the entries while iterating over them.
     *                 If the function returns true for a given entry, the entry will be retained
     *                 for callback, otherwise it will be skipped.
     * @param consumer The function which to call for each element being iterated over.
     */
    default void forAllIndexed(final @NotNull Predicate<T> filter, final @NotNull ObjIntConsumer<T> consumer) {
        int index = 0;

        for (final T element : this) {
            if (!filter.test(element)) {
                continue;
            }

            consumer.accept(element, index++);
        }
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new {@link List}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @return A new list containing all elements given access to by this view instance.
     */
    default @NotNull List<T> copyList() {
        return copy(ArrayList::new);
    }

    /**
     * <h2>Information</h2>
     * <b>Time Complexity: O(n)</b><br>
     * Creates a new {@link Set}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @return A new set containing all elements given access to by this view instance.
     */
    default @NotNull Set<T> copySet() {
        return copy(HashSet::new);
    }
}