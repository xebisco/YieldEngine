package com.xebisco.yield.physics;

import com.xebisco.yield.ComponentBehavior;
import org.jbox2d.collision.shapes.Shape;

public abstract class Collider extends ComponentBehavior {
    private float density = 1f, friction = 1f;
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
}
