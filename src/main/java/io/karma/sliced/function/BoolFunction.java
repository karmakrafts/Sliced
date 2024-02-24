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

package io.karma.sliced.function;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

/**
 * A functional interface which defines a function of type {@link R}({@code boolean}).
 *
 * @author Alexander Hinze
 * @since 25/08/2022
 */
@API(status = Status.STABLE)
@FunctionalInterface
public interface BoolFunction<R> {
    /**
     * Invokes the function with the given boolean value to obtain
     * a result of type {@link R}.
     *
     * @param b The boolean value.
     * @return A result value of type {@link R}.
     */
    R apply(final boolean b);
}
