/*
 * Copyright [2022-2023] [Xebisco]
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

package com.xebisco.yield.script;

import java.util.Objects;

public class ScriptObject {
    private final Class<?> cast;
    private final ValueProcess getter;
    private final ValueProcess setter;
    private Object value;

    public ScriptObject(final Object value, final Class<?> cast, final ValueProcess getter, final ValueProcess setter) {
        this.value = value;
        this.cast = cast;
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScriptObject that = (ScriptObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Object value() {
        return this.value;
    }

    public ScriptObject setValue(final Object value) {
        this.value = value;
        return this;
    }

    public Class<?> cast() {
        return this.cast;
    }

    public ValueProcess getter() {
        return this.getter;
    }

    public ValueProcess setter() {
        return this.setter;
    }
}