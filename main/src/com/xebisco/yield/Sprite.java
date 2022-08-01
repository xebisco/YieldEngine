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

public class Sprite extends NonFillShape {

    private boolean smartRender = true;

    public Sprite() {
    }

    public Sprite(Vector2 size) {
        setSize(size);
    }

    public Sprite(Texture size) {
        setSizeAsTexture(size);
    }

    @Override
    public void render(SampleGraphics graphics) {
        super.render(graphics);
        if (smartRender) {
            if (drawPosition.x + drawSize.x / 2f >= 0 && drawPosition.x - drawSize.x / 2f <= scene.getView().getWidth() && drawPosition.y + drawSize.y / 2f >= 0 && drawPosition.y - drawSize.y / 2f <= scene.getView().getHeight()) {
                Texture tex = getMaterial().getTexture();
                if (tex == null)
                    tex = game.getYieldLogo();
                graphics.drawTexture(tex, drawPosition, drawSize);
            }
        } else {
            Texture tex = getMaterial().getTexture();
            if (tex == null)
                tex = game.getYieldLogo();
            graphics.drawTexture(tex, drawPosition, drawSize);
        }
    }

    @Override
    public boolean colliding(float x, float y) {
        Transform t = getTransform();
        return x >= t.position.x - getSize().x * t.scale.x / 2f && x <= t.position.x + getSize().x * t.scale.y / 2f &&
                y >= t.position.y - getSize().y * t.scale.y / 2f && y <= t.position.y + getSize().y * t.scale.y / 2f;
    }

    public boolean isSmartRender() {
        return smartRender;
    }

    public void setSmartRender(boolean smartRender) {
        this.smartRender = smartRender;
    }
}
