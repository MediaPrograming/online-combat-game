package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;

public class PlayBloom extends PlayEffect{
    private double duration;
    private double threshold;
    public PlayBloom(GraphicsContext gc,double firstFrame,double playTime,double threshold){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.threshold = threshold;
    }

    @Override
    public void play(){

        duration = this.getDuration();
        if(duration >= 0 && duration <= playTime) {
            Bloom bloom = new Bloom();
            bloom.setThreshold(threshold);
            gc.applyEffect(bloom);
        }
    }
}
