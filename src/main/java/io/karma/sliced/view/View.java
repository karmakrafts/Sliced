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

package io.karma.sliced.view;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.view.impl.ArrayView;
import io.karma.sliced.view.impl.CollectionView;
import io.karma.sliced.view.impl.EmptyView;
import io.karma.sliced.view.impl.ListView;
import org.apiguardian.api.API;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
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
     * Creates an empty view for the given type which has no elements.
     *
     * @return An empty view for the given type which has no elements.
     */
    @SuppressWarnings("unchecked")
    static <T> View<T> empty() {
        return (View<T>) EmptyView.INSTANCE;
    }

    /**
     * Creates a new view instance which references the given array.<br>
     *
     * @param <T>   The element type of the given array, and the newly created view.
     * @param array The array of which to create a view.
     * @return A new view instance, which references the given array.
     */
    @SafeVarargs
    static <T> @NotNull View<T> of(final @NotNull T... array) {
        return new ArrayView<>(array);
    }

    /**
     * Creates a new view instance which references the given {@link Collection}.
     * <p>
     * If your given collection is a {@link List}, {@link Queue},
     * {@link Deque}, {@link Stack} or {@link Map}, you should use the
     * specialized functions provided by this class.<br>
     *
     * @param <T>        The element type of the given array, and the newly created view.
     * @param collection The collection of which to create a view.
     * @return A new view instance, which references the given array.
     */
    static <T> @NotNull View<T> of(final @NotNull Collection<T> collection) {
        return new CollectionView<>(collection);
    }

    /**
     * Creates a new view instance which references the given {@link List}.
     *
     * @param <T>  The element type of the given list, and the newly created view.
     * @param list The list of which to create a view.
     * @return A new view instance, which references the given list.
     */
    static <T> @NotNull View<T> of(final @NotNull List<T> list) {
        return new ListView<>(list);
    }

    /**
     * Retrieves the number of elements contained within the array/collection
     * referenced by this array.
     *
     * @return The number of element contained within the collection/array
     * referenced by this view instance.
     */
    int size();

    /**
     * Creates a new slice instance from this view instance containing
     * the same underlying collection reference, or a newly created wrapper list
     * if there's no specialization available for the type of the underlying collection.
     * <b>Note</b>
     * If you want to create slices directly, see {@link Slice#of(Object[])} and it's overloads.
     *
     * @return A new slice instance with a reference to the same collection/array
     * as this view instance.
     */
    @NotNull Slice<T> asSlice();

    /**
     * Checks if the elements referenced by this view instance
     * are equal to the elements within the given array.
     *
     * @param array The array to check elements from.
     * @return True if the elements in the given array equal the elements
     * referenced by this view instance.
     */
    default boolean contentEquals(final @NotNull T[] array) {
        if (array.length == 0) {
            return false;
        }

        int index = 0;

        for (final T element : this) {
            if (!element.equals(array[index++])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the elements referenced by this view instance
     * are equal to the elements within the given collection instance.
     *
     * @param collection THe collection to check elements from.
     * @return True if the elements in the given collection equal the elements
     * referenced by this view instance.
     */
    default boolean contentEquals(final @NotNull Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        final Iterator<? extends T> itr = collection.iterator();

        for (final T element : this) {
            if (!itr.hasNext() || !element.equals(itr.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the elements references in this view instance
     * are equal to the element references within the given array.
     *
     * @param array The array to check elements from.
     * @return True if the element references in the given array equal the
     * element references in this view instance.
     */
    default boolean referencesEqual(final @NotNull T[] array) {
        if (array.length == 0) {
            return false;
        }

        int index = 0;

        for (final T element : this) {
            if (element != array[index++]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the elements references in this view instance
     * are equal to the element references within the given collection.
     *
     * @param collection The collection to check elements from.
     * @return True if the element references in the given collection equal the
     * element references in this view instance.
     */
    default boolean referencesEqual(final @NotNull Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        final Iterator<? extends T> itr = collection.iterator();

        for (final T element : this) {
            if (!itr.hasNext() || element != itr.next()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates a new array of size n, where n is the number of elements contained
     * within the underlying collection of this view instance, using the given factory function,
     * and copies a reference to all elements contained within the collection
     * this view is referencing into the newly created array.
     *
     * @param factory The function with which to create the new array of size n.
     * @return A new array of size n, containing a reference to all elements contained
     * within this view's underlying collection/array.
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
     * Creates a new collection of type {@link C}, and copies all element references
     * contained within the underlying collection/array of this view into the newly
     * created collection.
     *
     * @param factory The function with which to create the new collection.
     * @param <C>     The type of the collection to create.
     * @return A new collection instance of type {@link C}, containing all elements
     */
    default <C extends Collection<T>> @NotNull C copy(final @NotNull Supplier<C> factory) {
        final C result = factory.get();

        for (final T element : this) {
            result.add(element);
        }

        return result;
    }

    /**
     * Checks if the given value is contained within the collection/array referenced
     * by this view instance.
     *
     * @param value The value to check for.
     * @return True if the given value is contained within the underlying collection/array
     * of this view instance.
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
     * Checks whether all elements from the given collection
     * are contained within the collection/array referenced by this view instance.
     *
     * @param elements A collection of elements to check for.
     * @return True if all elements contained within the given collection
     * are also contained within the collection/array referenced by this view instance.
     */
    default boolean containsAll(final @Nullable Collection<? extends T> elements) {
        if (elements == null) {
            return false;
        }

        int matches = 0;

        for (final T element : this) {
            if (!elements.contains(element)) {
                continue;
            }

            matches++;
        }

        return matches == elements.size();
    }

    /**
     * Checks whether the collection/array referenced by this view instance
     * contains the exact given reference to the given value.
     *
     * @param ref The reference to check for.
     * @return True if the collection/array associated with this view instance
     * contains the exact given reference to an object.
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
     * Checks whether all element references from the given collection
     * are contained within the collection/array referenced by this view instance.
     *
     * @param refs A collection of element references to check for.
     * @return True if all element references contained within the given collection
     * are also contained within the collection/array referenced by this view instance.
     */
    default boolean containsAllRefs(final @Nullable Collection<? extends T> refs) {
        if (refs == null) {
            return false;
        }

        int matches = 0;

        for (final T element : this) {
            for (final T ref : refs) {
                if (element != ref) {
                    continue;
                }

                matches++;
            }
        }

        return matches == refs.size();
    }

    /**
     * Creates a new non-parallelized instance of {@link Stream},
     * with access to all elements contained within the underlying
     * collection/array of this view instance.
     *
     * @return A new, non-parallelized {@link Stream} instance with access to the elements
     * contained within the collection/array of this view instance.
     */
    default @NotNull Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * Creates a new parallelized instance of {@link Stream},
     * with access to all elements contained within the underlying
     * collection/array of this view instance.
     *
     * @return A new, parallelized {@link Stream} instance with access to the elements
     * contained within the collection/array of this view instance.
     */
    default @NotNull Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    /**
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
     * Creates a new {@link List}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @return A new list containing all elements given access to by this view instance.
     */
    default @NotNull ArrayList<T> copyArrayList() {
        return copy(ArrayList::new);
    }

    /**
     * Creates a new {@link Set}, and copies reference to all elements
     * contained within the underlying collection/array of this view instance
     * into the newly created list instance.
     *
     * @return A new set containing all elements given access to by this view instance.
     */
    default @NotNull HashSet<T> copyHashSet() {
        return copy(HashSet::new);
    }
}