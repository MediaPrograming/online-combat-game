package Animation.UIPlayer;

import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayMeter {
    int amount;
    int nowValue;
    int x,y;
    int width,height;
    Color color;
    GraphicsContext gc;
    boolean left;
    int margin1;
    public PlayMeter(GraphicsContext gc, boolean left, int amount){
        this.gc = gc;
        this.amount = this.nowValue = amount;
        margin1 = 20;
        this.width = (int)gc.getCanvas().getWidth()/2-margin1;
        height = (int)100;
        color = new Color(0.1,0.8,1,0.8);
        this.left = left;
        if(left) this.x = margin1; else this.x = (int)(gc.getCanvas().getWidth()/2) ;
        this.y = 100;
    }
    public void setAmount(int amount){ this.amount = amount;}
    public void setNowValue(int nowValue) { this.nowValue = nowValue; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public void updateMeter(int nowValue){
        this.nowValue = nowValue;
        setWidth((int)(nowValue/amount)*width);
        if(left){

        }
        else{
            x += amount-width;
        }
    }

    public void play(int nowValue){
        updateMeter(nowValue);
        gc.fillRect(x,y,width,height);
    }
}
