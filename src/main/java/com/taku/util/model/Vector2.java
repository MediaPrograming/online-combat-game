package com.taku.util.model;

public class Vector2 {
    public float x;
    public float y;


    public Vector2(){}
    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void Add(Vector2 transform){
        this.x += transform.x;
        this.y += transform.y;
    }
    public static double Distance(Vector2 vec1, Vector2 vec2){
        var dx = vec1.x - vec2.x;
        var dy = vec1.y - vec2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
