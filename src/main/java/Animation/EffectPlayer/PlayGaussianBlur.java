package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;

public class PlayGaussianBlur extends PlayEffect{
    private double duration;
    private double radius;
    public PlayGaussianBlur(GraphicsContext gc,double firstFrame,double playTime,double radius){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.radius = radius;
    }

    public void play(){
        duration = this.getDuration();
        if(duration >= 0 && duration <= playTime) {
            GaussianBlur gaussianBlur = new GaussianBlur();
            gaussianBlur.setRadius(radius);
            gc.applyEffect(gaussianBlur);
        }
    }

    public double exponentialDecay(double duration,double maxRadius,double speed){
        return (double)maxRadius*Math.exp(-(double)(duration*speed));
    }
}
