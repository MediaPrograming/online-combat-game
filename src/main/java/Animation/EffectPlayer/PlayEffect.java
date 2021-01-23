package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;

public abstract class PlayEffect {
    protected GraphicsContext gc;
    protected double playTime;
    protected double firstFrame;
    public PlayEffect(){}

    public abstract void play();

    public double getDuration(double firstFrame){
        return Time.Instance.getTotalTime() - firstFrame;
    }
    public double getPlayTime(){return playTime;}
    public double getFirstFrame(){return firstFrame;}
}
