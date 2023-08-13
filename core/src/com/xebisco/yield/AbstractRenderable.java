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

import com.aparapi.Range;
import com.xebisco.yield.shader.VertexShader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ComponentIcon(iconType = ComponentIconType.GRAPHICAL)
public abstract class AbstractRenderable extends ComponentBehavior {
    private final List<String> vertexShaders = new ArrayList<>();
    public abstract int verticesCount();

    public abstract void setupX(float[] verticesX);
    public abstract void setupY(float[] verticesY);

    private float[] verticesX = new float[verticesCount()], verticesY = new float[verticesCount()];

    private final Range range = Range.create(verticesCount());

    private final DrawInstruction drawInstruction = new DrawInstruction(new float[verticesCount()], new float[verticesCount()]);

    @VisibleOnEditor
    private Size2D size = new Size2D(100, 100);
    @VisibleOnEditor
    private Color color = new Color(Colors.WHITE);
    @VisibleOnEditor
    private double borderThickness;
    @VisibleOnEditor
    private boolean filled = true, absoluteScaled, ignoreOffsetScaling;

    @VisibleOnEditor
    private Vector2D offset = new Vector2D();

    @VisibleOnEditor
    private RectangleAnchor anchor = RectangleAnchor.CENTER;

    private final Vector2D anchorSum = new Vector2D();

    @Override
    public DrawInstruction render() {
        if (filled)
            drawInstruction.setStroke(0);
        else drawInstruction.setStroke(borderThickness);
        drawInstruction.setColor(color);

        anchorSum.reset();
        switch (anchor) {
            case UP:
                anchorSum.setY(-size().getHeight() / 2.);
                break;
            case DOWN:
                anchorSum.setY(size().getHeight() / 2.);
                break;
            case RIGHT:
                anchorSum.setX(-size().getWidth() / 2.);
                break;
            case LEFT:
                anchorSum.setX(size().getWidth() / 2.);
                break;
        }
        setupX(verticesX);
        setupY(verticesY);
        IntStream.range(0, verticesCount()).forEach(i -> {
            for(String shaderName : vertexShaders) {
                VertexShader shader = getApplication().vertexShaderMap().get(shaderName);
                shader.inXPos = verticesX;
                shader.inYPos = verticesY;
                shader.outXPos = drawInstruction.verticesX();
                shader.outYPos = drawInstruction.verticesY();
                shader.putAll();
                shader.execute(range);
                shader.getOut();
                drawInstruction.setVerticesX(shader.outXPos);
                drawInstruction.setVerticesY(shader.outYPos);
            }
        });
        for (int i = 0; i < drawInstruction.verticesX().length; i++) {
            drawInstruction.verticesX()[i] += (float) anchorSum.getX();
            drawInstruction.verticesY()[i] += (float) anchorSum.getY();
        }
        double ox = offset.getX(), oy = offset.getY();
        if(!ignoreOffsetScaling) {
            ox *= getTransform().getScale().getX();
            oy += getTransform().getScale().getY();
        }
        drawInstruction.setX(ox);
        drawInstruction.setY(oy);
        return drawInstruction;
    }

    public List<String> vertexShaders() {
        return vertexShaders;
    }

    public float[] verticesX() {
        return verticesX;
    }

    public AbstractRenderable setVerticesX(float[] verticesX) {
        this.verticesX = verticesX;
        return this;
    }

    public float[] verticesY() {
        return verticesY;
    }

    public AbstractRenderable setVerticesY(float[] verticesY) {
        this.verticesY = verticesY;
        return this;
    }

    public Range range() {
        return range;
    }

    public DrawInstruction drawInstruction() {
        return drawInstruction;
    }

    public Size2D size() {
        return size;
    }

    public AbstractRenderable setSize(Size2D size) {
        this.size = size;
        return this;
    }

    public Color color() {
        return color;
    }

    public AbstractRenderable setColor(Color color) {
        this.color = color;
        return this;
    }

    public double borderThickness() {
        return borderThickness;
    }

    public AbstractRenderable setBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
        return this;
    }

    public boolean filled() {
        return filled;
    }

    public AbstractRenderable setFilled(boolean filled) {
        this.filled = filled;
        return this;
    }

    public boolean absoluteScaled() {
        return absoluteScaled;
    }

    public AbstractRenderable setAbsoluteScaled(boolean absoluteScaled) {
        this.absoluteScaled = absoluteScaled;
        return this;
    }

    public boolean ignoreOffsetScaling() {
        return ignoreOffsetScaling;
    }

    public AbstractRenderable setIgnoreOffsetScaling(boolean ignoreOffsetScaling) {
        this.ignoreOffsetScaling = ignoreOffsetScaling;
        return this;
    }

    public Vector2D offset() {
        return offset;
    }

    public AbstractRenderable setOffset(Vector2D offset) {
        this.offset = offset;
        return this;
    }

    public RectangleAnchor anchor() {
        return anchor;
    }

    public AbstractRenderable setAnchor(RectangleAnchor anchor) {
        this.anchor = anchor;
        return this;
    }

    public Vector2D anchorSum() {
        return anchorSum;
    }
}
