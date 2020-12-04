package model;

import com.taku.util.function.Action1.Action;

public class Key {
    private final int keyCode, weight;
    private Action<Object> action;
    public static final int WEIGHT_ACTION = 1;
    public static final int WEIGHT_MOVE = 2;
    public static final int WEIGHT_JUMP = 3;

    public Key(int keyCode, int weight, Action<Object> action){
        this.keyCode =keyCode;
        this.weight= weight;
        this.action = action;
    }

    public void Invoke(Object obj){ if(obj != null) this.action.Invoke(obj); }
    public int getKeyCode(){
        return this.keyCode;
    }
    public int getWeight(){
        return this.weight;
    }
}