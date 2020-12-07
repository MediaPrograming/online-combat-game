package com.taku.util.ui;

import java.lang.reflect.Type;

public class Event<T> {
    private String name ;
    private T[] param;
    private Event(String name, T... param){
        this.name = name;
        this.param = param;
    }

    public static Event Create(String name, Object... param)
    {
        var event = new Event(name, param);
        return event;
    }

    public String getName()
    {
        return this.name;
    }
    public T[] getParam() {
        return this.param;
    }
}
