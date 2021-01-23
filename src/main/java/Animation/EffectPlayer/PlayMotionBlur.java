package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;

public class PlayMotionBlur extends PlayEffect{

    public PlayMotionBlur(){}

    public void play(GraphicsContext gc, double firstFrame, double time , double radius,double angle){
        duration = this.getDuration(firstFrame);
        if(duration >= 0 && duration <= time) {
            MotionBlur motionBlur = new MotionBlur();
            motionBlur.setRadius(radius);
            motionBlur.setAngle(angle);
            gc.applyEffect(motionBlur);
        }
    }
}
