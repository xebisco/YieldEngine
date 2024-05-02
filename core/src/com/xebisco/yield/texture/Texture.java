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

package com.xebisco.yield.texture;

import com.xebisco.yield.AbstractTexture;
import com.xebisco.yield.FileInput;
import com.xebisco.yield.ImmutableVector2D;
import com.xebisco.yield.Vector2D;
import com.xebisco.yield.manager.TextureManager;

import java.io.Closeable;
import java.io.IOException;

/**
 * The {@code Texture} class represents an image texture and provides methods for processing and manipulating it.
 */
public class Texture extends AbstractTexture {
    private final ImmutableVector2D size;
    private final TextureManager textureManager;

    /**
     * Constructs a new {@link Texture} object from a file path and texture filter.
     *
     * @param path The path to the image file.
     * @param filter The texture filter to apply.
     * @param textureManager The texture manager to use for loading and unloading textures.
     * @throws IOException If an error occurs while loading the texture from the file.
     */
    public Texture(String path, TextureFilter filter, TextureManager textureManager) throws IOException {
        super(path, filter);
        this.textureManager = textureManager;
        Vector2D size = new Vector2D();
        setImageRef(textureManager.loadTexture(this, size));
        this.size = new ImmutableVector2D(size.width(), size.height());
    }

    /**
     * Constructs a new {@link Texture} object from an image reference and size.
     *
     * @param imageRef The image reference to use for the texture.
     * @param size The size of the texture.
     * @param path The path to the image file (ignored if an image reference is provided).
     * @param filter The texture filter to apply.
     * @param textureManager The texture manager to use for loading and unloading textures.
     */
    public Texture(Object imageRef, Vector2D size, String path, TextureFilter filter, TextureManager textureManager) {
        super(path, filter);
        this.textureManager = textureManager;
        setImageRef(imageRef);
        this.size = new ImmutableVector2D(size.width(), size.height());
    }

    /**
     * Returns an array of string extensions that are supported by the {@link Texture} class.
     *
     * @return an array of string extensions.
     */
    public static String[] extensions() {
        return new String[] {"png", "jpg", "jpeg"};
    }

    @Override
    public void close() {
        if (textureManager != null)
            textureManager().unloadTexture(this);
    }


    /**
     * Returns the size of the spritesheet texture.
     *
     * @return The size of the spritesheet texture.
     */
    public ImmutableVector2D size() {
        return size;
    }

    /**
     * Returns the {@link TextureManager} responsible for loading and unloading textures.
     *
     * @return The {@link TextureManager}.
     */
    public TextureManager textureManager() {
        return textureManager;
    }
}
