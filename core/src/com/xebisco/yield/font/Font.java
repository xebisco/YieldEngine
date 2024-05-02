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

package com.xebisco.yield.font;

import com.xebisco.yield.FileInput;
import com.xebisco.yield.manager.FontManager;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code Font} class represents a font file with a specified size and font loader.
 */
public class Font extends FileInput {

    private final Map<Character, FontCharacter> characterMap = new HashMap<>();
    private final double size;

    /**
     * Constructs a new Font object.
     *
     * @param path The path to the font file.
     * @param size The size of the font.
     * @param fontManager The {@link FontManager} instance responsible for loading fonts.
     */
    public Font(String path, double size, FontManager fontManager) {
        super(path);
        this.size = size;
        fontManager.loadFont(this);
    }

    /**
     * Returns the map containing all the characters and their corresponding FontCharacter objects.
     *
     * @return The map of characters and their corresponding {@link FontCharacter} objects.
     */
    public Map<Character, FontCharacter> characterMap() {
        return characterMap;
    }

    /**
     * Returns the size of the font.
     *
     * @return The size of the font.
     */
    public double size() {
        return size;
    }
}
