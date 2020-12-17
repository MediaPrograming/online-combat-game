package com.taku.util.flux.model;

public class Action<T> {
    private String type;
    private T param;
    public Action(String name, T param){
        this.type = name;
        this.param = param;
    }

    public String getType()
    {
        return this.type;
    }
    public T getParam(){
        return this.param;
    }
}
