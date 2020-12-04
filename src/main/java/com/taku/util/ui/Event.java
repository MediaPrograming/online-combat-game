package com.taku.util.ui;

import java.lang.reflect.Type;

public class Event<T> {
    private String name ;
    private T[] param;
    public Event(String name, T... param){
        this.name = name;
        this.param = param;
    }

    public String getName()
    {
        return this.name;
    }
    public T[] getParam() {
        return this.param;
    }
}
