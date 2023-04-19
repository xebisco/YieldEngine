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

/**
 * The ComponentCreation class represents the creation of a component with a specified behavior and
 * modifier.
 */
public final class ComponentCreation {
    private final Class<? extends ComponentBehavior> componentClass;
    private final ComponentModifier componentModifier;

    public ComponentCreation(Class<? extends ComponentBehavior> componentClass, ComponentModifier componentModifier) {
        this.componentClass = componentClass;
        this.componentModifier = componentModifier;
    }

    public ComponentCreation(Class<? extends ComponentBehavior> componentClass) {
        this(componentClass, null);
    }

    /**
     * This function returns the class of a component's behavior.
     *
     * @return The method is returning an object of type `Class` that extends `ComponentBehavior`. The specific class being
     * returned is determined by the value of the `componentClass` variable.
     */
    public Class<? extends ComponentBehavior> getComponentClass() {
        return componentClass;
    }

    /**
     * The function returns a ComponentModifier object.
     *
     * @return The method is returning an object of type `ComponentModifier`.
     */
    public ComponentModifier getComponentModifier() {
        return componentModifier;
    }
}
