/*
 * Copyright [2022-2023] [Xebisco]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.xebisco.yield;

import org.jbox2d.common.Vec2;

/**
 * Static collection of utils methods.
 */
public final class Global {

    private static Font defaultFont;

    private static Texture defaultTexture;

    public static final String HORIZONTAL = "Horizontal", VERTICAL = "Vertical";

    public static Vec2 toVec2(Vector2D vector2D) {
        return new Vec2((float) vector2D.getX(), (float) vector2D.getY());
    }

    public static Vector2D toVector2D(Vec2 vec2) {
        return new Vector2D(vec2.x, vec2.y);
    }

    /**
     * If value is greater than max, return max, otherwise return the greatest of value and min.
     *
     * @param value The value to clamp.
     * @param min   The minimum value that the returned value can be.
     * @param max   The maximum value that the returned value can be.
     * @return The value of the variable value if it is less than the variable max, otherwise the value of the variable
     * max.
     */
    public static int clamp(int value, int min, int max) {
        return value > max ? max : Math.max(value, min);
    }

    /**
     * If value is greater than max, return max, otherwise return the greatest of value and min.
     *
     * @param value The value to clamp.
     * @param min   The minimum value that the returned value can be.
     * @param max   The maximum value that the returned value can be.
     * @return The value of the variable value if it is less than the variable max, otherwise the value of the variable
     * max.
     */
    public static long clamp(long value, long min, long max) {
        return value > max ? max : Math.max(value, min);
    }

    /**
     * If value is greater than max, return max, otherwise return the greatest of value and min.
     *
     * @param value The value to clamp.
     * @param min   The minimum value that the returned value can be.
     * @param max   The maximum value that the returned value can be.
     * @return The value of the variable value if it is less than the variable max, otherwise the value of the variable
     * max.
     */
    public static double clamp(double value, double min, double max) {
        return value > max ? max : Math.max(value, min);
    }

    /**
     * If value is greater than max, return max, otherwise return the greatest of value and min.
     *
     * @param value The value to clamp.
     * @param min   The minimum value that the returned value can be.
     * @param max   The maximum value that the returned value can be.
     * @return The value of the variable value if it is less than the variable max, otherwise the value of the variable
     * max.
     */
    public static float clamp(float value, float min, float max) {
        return value > max ? max : Math.max(value, min);
    }

    public static Font getDefaultFont() {
        return defaultFont;
    }

    public static void setDefaultFont(Font defaultFont) {
        Global.defaultFont = defaultFont;
    }

    public static Texture getDefaultTexture() {
        return defaultTexture;
    }

    public static void setDefaultTexture(Texture defaultTexture) {
        Global.defaultTexture = defaultTexture;
    }
}
