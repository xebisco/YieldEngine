package com.xebisco.yield.physics;

import com.xebisco.yield.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;

public class PhysicsBody extends ComponentBehavior {

    private Body b2Body;
    private PhysicsType type = PhysicsType.DYNAMIC;
    private double gravityScale = 1, mass = 1;
    private boolean bullet, fixedRotation;

    @Override
    public void onStart() {
        BodyDef def = new BodyDef();
        def.userData = getEntity();
        b2Body = getApplication().getScene().getPhysicsSystem().getB2World().createBody(def);
    }

    @Override
    public void onUpdate() {
        for (Fixture f = b2Body.getFixtureList(); f != null; f = f.getNext()) {
            f.m_shape = ((Collider) f.getUserData()).getShape();
            f.setDensity(((Collider) f.getUserData()).getDensity());
            f.setFriction(((Collider) f.getUserData()).getFriction());
            f.setSensor(((Collider) f.getUserData()).isSensor());
            if(!getEntity().getComponents().contains((Collider) f.getUserData())) {
                b2Body.destroyFixture(f);
            }
        }
        for (int i = 0; i < getEntity().getComponents().size(); i++) {
            ComponentBehavior c = getEntity().getComponents().get(i);
            if(c instanceof Collider) {
                boolean contains = false;
                for (Fixture f = b2Body.getFixtureList(); f != null; f = f.getNext()) {
                    if(f.getUserData() == c) {
                        contains = true;
                        break;
                    }
                }
                if(!contains) {
                    Fixture f = b2Body.createFixture(((Collider) c).getShape(), ((Collider) c).getDensity());
                    f.setUserData(c);
                }
            }
        }
        b2Body.setType(switch (type) {
            case DYNAMIC -> BodyType.DYNAMIC;
            case STATIC -> BodyType.STATIC;
            case KINEMATIC -> BodyType.KINEMATIC;
        });
        b2Body.setGravityScale((float) gravityScale);
        b2Body.setBullet(bullet);
        b2Body.m_mass = (float) mass;
        b2Body.setFixedRotation(fixedRotation);
        getTransform().getPosition().set(b2Body.getPosition().x * getApplication().getPhysicsPpm(), (b2Body.getPosition().y + 2) * getApplication().getPhysicsPpm());
        getTransform().setzRotation(Math.toDegrees(b2Body.getAngle()));
    }

    public void addForce(Vector2D force, ForceType forceType) {
        switch (forceType) {
            case FORCE -> b2Body.applyForceToCenter(Global.toVec2(force));
            case LINEAR_IMPULSE -> b2Body.applyLinearImpulse(Global.toVec2(force), b2Body.getWorldCenter());
            default -> throw new IllegalArgumentException(forceType.name());
        }
    }

    public void addForce(double force, ForceType forceType) {
        switch (forceType) {
            case TORQUE -> b2Body.applyTorque((float) force);
            case ANGULAR_IMPULSE -> b2Body.applyAngularImpulse((float) force);
            default -> throw new IllegalArgumentException(forceType.name());
        }
    }

    public void setPosition(TwoAnchorRepresentation value) {
        b2Body.setTransform(new Vec2((float) (value.getX() / getApplication().getPhysicsPpm()), (float) (value.getY() / getApplication().getPhysicsPpm())), b2Body.getAngle());
    }

    public Vec2 getPosition() {
        return b2Body.getPosition();
    }

    public Vec2 getLinearVelocity() {
        return b2Body.getLinearVelocity();
    }

    public void setLinearVelocity(Vec2 linearVelocity) {
        b2Body.setLinearVelocity(linearVelocity);
    }

    public void setLinearVelocity(Vector2D linearVelocity) {
        setLinearVelocity(Global.toVec2(linearVelocity));
    }


    public double getAngularVelocity() {
        return b2Body.getAngularVelocity();
    }

    public void setAngularVelocity(double angularVelocity) {
        b2Body.setAngularVelocity((float) angularVelocity);
    }

    public PhysicsType getType() {
        return type;
    }

    public void setType(PhysicsType type) {
        this.type = type;
    }

    public double getGravityScale() {
        return gravityScale;
    }

    public void setGravityScale(double gravityScale) {
        this.gravityScale = gravityScale;
    }

    public Body getB2Body() {
        return b2Body;
    }

    public void setB2Body(Body b2Body) {
        this.b2Body = b2Body;
    }
}
