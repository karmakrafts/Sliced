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

package io.karma.sliced.test.slice;

import io.karma.sliced.slice.Slice;
import io.karma.sliced.slice.impl.ArraySlice;
import io.karma.sliced.slice.impl.ListSlice;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author Alexander Hinze
 * @since 07/09/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
public final class TestSlice extends AbstractSliceTest<String, Slice<String>> {
    public TestSlice() {
        super(() -> randomUniqueStrings(256, 256), ArraySlice::new, ListSlice::new);
    }
}
