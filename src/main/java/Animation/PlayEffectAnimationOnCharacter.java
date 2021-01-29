package Animation;

import game.util.Time;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayEffectAnimationOnCharacter {
    private double x,y,width,height;
    private double firstFrame,playTime;
    private GraphicsContext gc;
    private EffectAnimationPlayer effect;
    public PlayEffectAnimationOnCharacter(GraphicsContext gc, double playTime, double firstFrame, CharacterState state){
        this.gc = gc;
        effect = new EffectAnimationPlayer("HIT",firstFrame);
        this.x = state.getX()+50+(Math.random()-0.5)*50;
        this.y = state.getY()+50+(Math.random()-0.5)*50;
        this.width = 100;
        this.height = 100;
        this.playTime = playTime;
        this.firstFrame = firstFrame;
    }

    public double getDuration(){
        return Time.Instance.getTotalTime() - firstFrame;
    }

    public double getPlayTime() {
        return playTime;
    }

    public void play(){
        gc.drawImage(effect.play(), x,y,width,height);
    }
}
