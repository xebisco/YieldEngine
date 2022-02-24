package com.xebisco.yield;

import com.xebisco.yield.components.Transform;
import com.xebisco.yield.input.YldInput;

import java.util.ArrayList;

public abstract class Component extends YldB
{
    private int frames;
    private Entity entity;
    protected Transform transform;
    protected YldScene scene;
    protected YldTime time;
    protected YldInput input;
    protected YldGraphics graphics;

    @Override
    public void create()
    {

    }

    public void start()
    {

    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public void update(float delta)
    {

    }

    public Entity instantiate(String name)
    {
        return entity.instantiate(name);
    }

    public Entity instantiate()
    {
        return instantiate(null);
    }

    public void destroy()
    {
        this.entity.destroy();
    }

    public <E extends Entity> void destroy(Class<E> type) {
        this.entity.destroy(type);
    }

    public int getFrames()
    {
        return frames;
    }

    public void setFrames(int frames)
    {
        this.frames = frames;
    }

    public void addComponent(Component component)
    {
        entity.addComponent(component);
    }

    public <T extends Component> T getComponent(Class<T> type)
    {
        return entity.getComponent(type);
    }

    public <T extends Component> T getComponentInChildren(Class<T> type)
    {
        return entity.getComponentInChildren(type);
    }

    public <T extends Component> T getComponentInParent(Class<T> type)
    {
        return entity.getComponentInParent(type);
    }

    public void setMaterial(Material material)
    {
        entity.setMaterial(material);
    }

    public Material getMaterial()
    {
        return entity.getMaterial();
    }

    public ArrayList<Component> getComponents()
    {
        return entity.getComponents();
    }

    public <T extends Component> boolean containsComponent(Class<T> type)
    {
        return entity.containsComponent(type);
    }

    public Entity getEntity()
    {
        return entity;
    }

    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    public Transform getTransform()
    {
        return entity.getTransform();
    }

    public Transform getSelfTransform()
    {
        return entity.getSelfTransform();
    }

    public void setSelfTransform(Transform selfTransform)
    {
        entity.setSelfTransform(selfTransform);
    }

    public YldInput getInput()
    {
        return input;
    }

    public void setInput(YldInput input)
    {
        this.input = input;
    }

    public YldScene getScene()
    {
        return scene;
    }

    public void setScene(YldScene scene)
    {
        this.scene = scene;
    }

    public YldTime getTime()
    {
        return time;
    }

    public void setTime(YldTime time)
    {
        this.time = time;
    }

    public YldGraphics getGraphics()
    {
        return graphics;
    }

    public void setGraphics(YldGraphics graphics)
    {
        this.graphics = graphics;
    }
}
