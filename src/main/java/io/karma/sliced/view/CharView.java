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

import io.karma.sliced.iterator.CharIterator;
import io.karma.sliced.iterator.TextIterator;
import io.karma.sliced.iterator.impl.CharSeqCharIterator;
import io.karma.sliced.iterator.impl.CharSeqTextIterator;
import io.karma.sliced.view.impl.ArrayCharView;
import io.karma.sliced.view.impl.EmptyCharView;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A primitive specialization for {@code char} of {@link View}.
 *
 * @author Alexander Hinze
 * @since 03/09/2022
 */
@API(status = Status.STABLE)
public interface CharView extends View<Character>, CharSequence {
    /**
     * Creates an empty char view with no elements.
     *
     * @return An empty char view with no elements.
     */
    static CharView empty() {
        return EmptyCharView.INSTANCE;
    }

    static @NotNull CharView of(final char... ref) {
        return new ArrayCharView(ref);
    }

    default @NotNull TextIterator textIterator() {
        return new CharSeqTextIterator(this);
    }

    default @NotNull CharIterator charIterator() {
        return new CharSeqCharIterator(this);
    }

    /**
     * Compiles this character sequence as a new {@link Pattern} instance.
     *
     * @return A new {@link Pattern} instance, containing this char slice
     * as its regular expression pattern.
     */
    default @NotNull Pattern compilePattern() {
        return Pattern.compile(new String(toCharArray())); // why no CharSequence brian? WHY BRIAN?!
    }

    /**
     * @param pattern
     * @return
     */
    default @NotNull Matcher matcher(final @NotNull Pattern pattern) {
        return pattern.matcher(this);
    }

    /**
     * Creates a new {@code char} array with the appropriate size,
     * and copies all values into new newly created array using {@link System#arraycopy(Object, int, Object, int, int)}.
     *
     * @return A new array containing all elements.
     */
    char[] toCharArray();

    @Override
    default @NotNull Iterator<Character> iterator() {
        return charIterator();
    }
}
