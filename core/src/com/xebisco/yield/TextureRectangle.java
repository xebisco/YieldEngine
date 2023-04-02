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

public class TextureRectangle extends Rectangle {
    @VisibleOnInspector
    private Texture texture;

    @VisibleOnInspector
    private PixelProcessor pixelProcessor;

    @VisibleOnInspector
    private boolean textureSize;

    @Override
    public void onStart() {
        super.onStart();
        if(texture == null) texture = getApplication().getDefaultTexture();
        getDrawInstruction().setType(DrawInstruction.Type.IMAGE);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(pixelProcessor != null) {
            texture.process(pixelProcessor);
        }
    }

    @Override
    public void render(PlatformGraphics graphics) {
        getDrawInstruction().setRenderRef(texture.getImageRef());
        if(textureSize) {
            getSize().set(texture.getSize());
        }
        super.render(graphics);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public PixelProcessor getPixelProcessor() {
        return pixelProcessor;
    }

    public void setPixelProcessor(PixelProcessor pixelProcessor) {
        this.pixelProcessor = pixelProcessor;
    }

    public boolean isTextureSize() {
        return textureSize;
    }

    public void setTextureSize(boolean textureSize) {
        this.textureSize = textureSize;
    }
}
