package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;

public class PlayEffect {
    protected double duration;
    public PlayEffect(){}

    public void play(){}

    public double getDuration(double firstFrame){
        return Time.Instance.getTotalTime() - firstFrame;
    }
}
