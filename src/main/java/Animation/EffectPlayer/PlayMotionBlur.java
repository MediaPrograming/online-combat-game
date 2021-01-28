package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;

public class PlayMotionBlur extends PlayEffect{
    private double duration;
    private double radius,angle;
    public PlayMotionBlur(GraphicsContext gc,double firstFrame,double playTime,double radius,double angle){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.radius = radius;
        this.angle = angle;
    }

    public void play(){
        duration = this.getDuration();
        if(duration >= 0 && duration <= playTime) {
            MotionBlur motionBlur = new MotionBlur();
            motionBlur.setRadius(radius);
            motionBlur.setAngle(angle);
            gc.applyEffect(motionBlur);
        }
    }
}
