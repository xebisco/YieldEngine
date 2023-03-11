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

package com.xebisco.yield;

/**
 * A Size2D is a TwoAnchorRepresentation that represents a width and a height.
 */
public class Size2D extends TwoAnchorRepresentation {

    public Size2D(double width, double height) {
        super(width, height);
    }

    public double getWidth() {
        return getX();
    }

    public double getHeight() {
        return getY();
    }
}
