package com.taku.util.model;

public class Vector3 {
    public float x, y, z;

    public Vector3(){}
    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static double Distance(Vector3 vec1, Vector3 vec2){
        var dx = vec1.x - vec2.x;
        var dy = vec1.y - vec2.y;
        var dz = vec1.z - vec2.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}
