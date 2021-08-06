package com.herb_mc.extra_enchants.lib;

import com.google.gson.JsonObject;

public class ValueContainer {

    private static Object value;

    public ValueContainer(Object in) {
        value = in;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getValueType() {
        return value.getClass();
    }

    public void setValue(Object in) {
        value = in;
    }

    public int getInt() {
        return (int) value;
    }

    public boolean getBool() {
        return (boolean) value;
    }

    public double getDouble() {
        return (double) value;
    }

    public float getFloat() {
        return (float) value;
    }

}
