package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;

public class PlayBloom extends PlayEffect{
    double duration;
    public PlayBloom(){}

    public void play(GraphicsContext gc, double threshold,double firstFrame, double time){
        duration = this.getDuration(firstFrame);
        if(duration >= 0 && duration <= time) {
            Bloom bloom = new Bloom();
            bloom.setThreshold(threshold);
            gc.applyEffect(bloom);
        }
    }
}
