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

package com.xebisco.yield;

/**
 * Simulation of a Pixel.
 * @since 4_1.1.2
 * @author Xebisco
 */
public class Pixel
{
    private String cache;
    private Vector2 textureLocation, location;
    private Color textureColor, color;

    /**
     * Getter of the location variable.
     * @return The location variable.
     */
    public Vector2 getLocation()
    {
        return location;
    }

    /**
     * Setter of the location variable.
     */
    public void setLocation(Vector2 location)
    {
        this.location = location;
    }

    /**
     * Getter of the textureColor variable.
     * @return The textureColor variable.
     */
    public Color getTextureColor()
    {
        return textureColor;
    }

    /**
     * Setter of the textureColor variable.
     */
    public void setTextureColor(Color textureColor)
    {
        this.textureColor = textureColor;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2 getTextureLocation() {
        return textureLocation;
    }

    public void setTextureLocation(Vector2 textureLocation) {
        this.textureLocation = textureLocation;
    }
}
