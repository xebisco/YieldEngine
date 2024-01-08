package com.xebisco.yield.editor.app;

import java.awt.*;

public enum EditableValuesType {
    STRING, INT, FLOAT, LONG, DOUBLE, POSITION, ARRAY, COLOR;

    public static EditableValuesType getType(Class<?> c) {
        if(c.equals(String.class)) return STRING;
        if(c.equals(Integer.class) || c.equals(int.class)) return INT;
        if(c.equals(Float.class) || c.equals(float.class)) return FLOAT;
        if(c.equals(Long.class) || c.equals(long.class)) return LONG;
        if(c.equals(Double.class) || c.equals(double.class)) return DOUBLE;
        if(c.getName().equals("com.xebisco.yield.Vector2D")) return POSITION;
        if(c.getName().equals("com.xebisco.yield.Color")) return COLOR;
        if(c.isArray()) return ARRAY;
        return null;
    }
}
