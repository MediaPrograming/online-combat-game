package com.taku.util.flux.model;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
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
