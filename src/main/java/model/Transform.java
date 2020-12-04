package model;

public class Transform {
    public int x;
    public int y;


    public Transform(){}
    public Transform(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void Add(Transform transform){
        this.x += transform.x;
        this.y += transform.y;
    }
}
