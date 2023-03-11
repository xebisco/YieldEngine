package com.xebisco.yield.physics;

import com.xebisco.yield.ComponentBehavior;
import com.xebisco.yield.editoruse.VisibleOnInspector;
import org.jbox2d.collision.shapes.Shape;

public abstract class Collider extends ComponentBehavior {
    @VisibleOnInspector
    private float density = 1f, friction = 1f;
    @VisibleOnInspector
    private boolean sensor;
    public abstract Shape getShape();

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public boolean isSensor() {
        return sensor;
    }

    public void setSensor(boolean sensor) {
        this.sensor = sensor;
    }
}
