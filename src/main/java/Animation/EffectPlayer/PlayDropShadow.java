package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class PlayDropShadow extends PlayEffect{

    public PlayDropShadow(){}

    public void play(GraphicsContext gc,double radius, double offsetX,double offsetY,Color color){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(offsetX);
        dropShadow.setOffsetY(offsetY);
        dropShadow.setColor(color);
        gc.applyEffect(dropShadow);
    }
}