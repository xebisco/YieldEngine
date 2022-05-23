/*
 * Copyright [2022] [Xebisco]
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

package com.xebisco.yield.config;

import com.xebisco.yield.Yld;

public class WindowConfiguration
{
    public int width = 1280, height = 720;
    public WindowPos position = WindowPos.CENTER;
    public String title = "Yield " + Yld.VERSION, internalIconPath = "/com/xebisco/yield/assets/icon";
    public boolean doubleBuffered = true, resizable = false, undecorated = false, fullscreen = false, alwaysOnTop = false, hideMouse, sync = true;

    public enum WindowPos
    {
        CENTER, CUSTOM
    }
}
