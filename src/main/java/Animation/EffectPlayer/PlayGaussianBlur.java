package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;

public class PlayGaussianBlur extends PlayEffect{

    public PlayGaussianBlur(GraphicsContext gc,double firstFrame,double playTime){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
    }

    public void play(GraphicsContext gc, double firstFrame, double time , double radius){
        duration = this.getDuration(firstFrame);
        if(duration >= 0 && duration <= time) {
            GaussianBlur gaussianBlur = new GaussianBlur();
            gaussianBlur.setRadius(radius);
            gc.applyEffect(gaussianBlur);
        }
    }

    public double exponentialDecay(double duration,double maxRadius,double speed){
        return (double)maxRadius*Math.exp(-(double)(duration*speed));
    }
}
