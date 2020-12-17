package game.model;

import com.taku.util.function.Consumer.Consumer0;
import javafx.scene.input.KeyCode;

public class Key {
    private final KeyCode keyCode;
    private final int weight;
    private final Consumer0 action;
//    public static final int WEIGHT_ACTION = 1;
//    public static final int WEIGHT_MOVE = 2;
//    public static final int WEIGHT_JUMP = 3;

    public Key(KeyCode keyCode, int weight, Consumer0 action){
        this.keyCode =keyCode;
        this.weight= weight;
        this.action = action;
    }

    public void Invoke(){ this.action.apply(); }
    public KeyCode getKeyCode(){
        return this.keyCode;
    }
    public int getWeight(){
        return this.weight;
    }
}