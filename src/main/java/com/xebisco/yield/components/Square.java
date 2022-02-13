package com.xebisco.yield.components;

import com.xebisco.yield.Obj;
import com.xebisco.yield.YldGraphics;

public class Square extends Shape {
    private boolean filled = true;
    private float width = 64, height = 64;

    @Override
    public void parameters(YldGraphics graphics) {

    }

    @Override
    public void previous(YldGraphics graphics) {

    }

    @Override
    public void process(Obj obj) {
        obj.x = (int) (getEntity().getTransform().position.x - width / 2);
        obj.y = (int) (getEntity().getTransform().position.y - height / 2);
        obj.x2 = (int) (getEntity().getTransform().position.x - width / 2 + getEntity().getTransform().scale.x * width);
        obj.y2 = (int) (getEntity().getTransform().position.y - height / 2 + getEntity().getTransform().scale.y * height);
        obj.filled = filled;
        obj.type = Obj.ShapeType.RECT;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
