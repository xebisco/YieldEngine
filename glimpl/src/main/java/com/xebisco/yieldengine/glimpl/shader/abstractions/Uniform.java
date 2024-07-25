/*
 * Copyright [2022-2024] [Xebisco]
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

package com.xebisco.yieldengine.glimpl.shader.abstractions;

public final class Uniform {
    private final DataType type;
    private final String name;
    private final Object value;

    private Uniform(DataType type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public static Uniform create(String name, Object value) throws InvalidShaderTypeException {
        return new Uniform(DataType.getDataType(value.getClass()), name, value);
    }

    public DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
