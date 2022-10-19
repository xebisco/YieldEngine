/*
 * Copyright [2022] [Xebisco]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xebisco.yield.ux;

import com.xebisco.yield.SampleGraphics;
import com.xebisco.yield.Vector2;

public abstract class UXComponent {
    private UXPanel panel;
    private Vector2 position, size;
    private UXMain uxMain;

    public UXComponent(UXMain uxMain) {
        this.uxMain = uxMain;
    }

    public UXAction rightClickActions() {
        return null;
    }

    public abstract void render(SampleGraphics graphics, float delta);

    public UXPanel getPanel() {
        return panel;
    }

    public void setPanel(UXPanel panel) {
        this.panel = panel;
    }

    public UXMain getUxMain() {
        return uxMain;
    }

    public void setUxMain(UXMain uxMain) {
        this.uxMain = uxMain;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }
}
