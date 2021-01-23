package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class PlayDropShadow extends PlayEffect{
    private double radius,offsetX,offsetY;
    private Color color;
    public PlayDropShadow(GraphicsContext gc,double firstFrame,double playTime,double radius, double offsetX,double offsetY,Color color){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.radius = radius;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.color = color;
    }

    public void play(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(offsetX);
        dropShadow.setOffsetY(offsetY);
        dropShadow.setColor(color);
        gc.applyEffect(dropShadow);
    }
}
