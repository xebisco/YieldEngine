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

package com.xebisco.yieldengine.core.rendering;

public final class Render {
    private static Render instance;

    private final IArrayMemory arrayMemory;
    private final IRenderer renderer;

    public Render(IArrayMemory arrayMemory, IRenderer renderer) {
        this.arrayMemory = arrayMemory;
        this.renderer = renderer;
    }

    public static Render getInstance() {
        return instance;
    }

    public static void setInstance(Render instance) {
        Render.instance = instance;
    }

    public IArrayMemory getArrayMemory() {
        return arrayMemory;
    }

    public IRenderer getRenderer() {
        return renderer;
    }
}
