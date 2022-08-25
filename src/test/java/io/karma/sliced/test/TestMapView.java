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

package io.karma.sliced.test;

import io.karma.sliced.view.MapView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.HashMap;

/**
 * @author Alexander Hinze
 * @since 17/08/2022
 */
@TestInstance(Lifecycle.PER_CLASS)
final class TestMapView {
    private static final HashMap<String, String> MAP = new HashMap<>();

    static {
        MAP.put("foo", "bar");
        MAP.put("bar", "foo");
        MAP.put("foobar", "barfoo");
        MAP.put("barfoo", "foobar");
    }

    @Test
    void testKeyStream() {
        final MapView<String, String> view = MapView.of(MAP);
        view.keyStream().forEach(s -> Assertions.assertTrue(MAP.containsKey(s)));
    }

    @Test
    void testValueStream() {
        final MapView<String, String> view = MapView.of(MAP);
        view.valueStream().forEach(s -> Assertions.assertTrue(MAP.containsValue(s)));
    }
}
