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

package com.xebisco.yieldengine.core;

import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Entity extends OnSceneBehavior implements Comparable<Entity> {
    private final String name;
    private Entity parent;
    private final List<Entity> children = new ArrayList<>();
    private final ArrayList<Component> components = new ArrayList<>();
    private final Transform transform;
    private int preferredIndex;
    private final ArrayList<String> tags = new ArrayList<>();
    private final Transform worldTransform = new Transform();

    public Entity(String name, Transform transform) {
        this.name = name;
        this.transform = transform;
    }

    private void sortChildren() {
        Collections.sort(children);
    }

    public void addToParent(Entity parent) {
        Global.getCurrentScene().getEntities().remove(this);
        setParent(parent);
        parent.children.add(this);
    }

    public Entity[] getAllChildren() {
        List<Entity> entities = new ArrayList<>(getChildren());
        for (Entity e : getChildren()) {
            Collections.addAll(entities, e.getAllChildren());
        }
        return entities.toArray(new Entity[0]);
    }

    public void removeFromParent() {
        parent.children.remove(this);
        setParent(null);
    }

    public void addToSceneRoot() {
        if (parent != null) removeFromParent();
        Global.getCurrentScene().getEntities().add(this);
    }

    @Override
    public void onCreate() {
        sortChildren();
        components.forEach(c -> {
            c.setEntity(this);
            c.setWorldTransform(worldTransform);
        });
        components.forEach(Component::onCreate);
        children.forEach(Entity::onCreate);
    }

    @Override
    public void onStart() {
        sortChildren();
        updateWorldTransform();
        components.forEach(Component::onStart);
        children.forEach(Entity::onStart);
    }

    @Override
    public void onUpdate() {
        sortChildren();
        components.forEach(Component::onUpdate);
        children.forEach(Entity::onUpdate);
        setFrames(getFrames() + 1);
    }

    @Override
    public void onLateUpdate() {
        updateWorldTransform();
        components.forEach(Component::onLateUpdate);
        children.forEach(Entity::onLateUpdate);
    }

    @Override
    public void dispose() {
        super.dispose();
        components.forEach(Component::dispose);
        children.forEach(Entity::dispose);
        children.clear();
    }

    @Override
    public int compareTo(Entity o) {
        return Integer.compare(o.preferredIndex, preferredIndex);
    }

    public Component getComponent(Class<? extends Component> componentClass, int index) {
        int i = 0;
        for (Component component : components) {
            if (component.getClass().isAssignableFrom(componentClass)) {
                if (i++ >= index) {
                    return component;
                }
            }
        }
        return null;
    }

    public Component getComponent(Class<? extends Component> componentClass) {
        return getComponent(componentClass, 0);
    }

    public boolean containsComponent(Class<? extends Component> componentClass) {
        return getComponent(componentClass) != null;
    }

    public void updateWorldTransform() {
        worldTransform.getTransformMatrix().set(getNewWorldTransform().getTransformMatrix());
    }

    public Entity addComponents(Component... components) {
        Collections.addAll(getComponents(), components);
        return this;
    }

    private Transform getNewWorldTransform() {
        if (parent == null) return transform;
        Transform worldTransform = new Transform(parent.getNewWorldTransform());
        worldTransform.getTransformMatrix().translationRotateScale(worldTransform.getTranslation(), worldTransform.getRotation().get(new Quaternionf()), worldTransform.getScale());
        return worldTransform;
    }

    public String getName() {
        return name;
    }

    public Entity getParent() {
        return parent;
    }

    private Entity setParent(Entity parent) {
        this.parent = parent;
        return this;
    }

    public List<Entity> getChildren() {
        return children;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public Transform getTransform() {
        return transform;
    }

    public int getPreferredIndex() {
        return preferredIndex;
    }

    public Entity setPreferredIndex(int preferredIndex) {
        this.preferredIndex = preferredIndex;
        return this;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Transform getWorldTransform() {
        return worldTransform;
    }
}
