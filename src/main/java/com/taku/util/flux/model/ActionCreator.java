package com.taku.util.flux.model;

import java.util.ArrayDeque;
import java.util.Dictionary;
import java.util.Queue;

public class ActionCreator<T> {
    private String type;
    public ActionCreator(String type){
        this.type = type;
    }
    public Action<T> Create(T param){
        return new Action<T>(type, param);
    }
    public String getType() {
        return this.type;
    }
}
