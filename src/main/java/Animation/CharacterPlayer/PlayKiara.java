package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayKiara extends PlayCharacter{
    public PlayKiara(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 400;
    }

    @Override
    public void play() {
//        if(this.state.getBehavior() == Behavior.DAMAGE) gc.drawImage(AnimationHolder.getEffectAnimation("HIT").getAnim()[0][0],this.X,this.Y,50,50);
        super.play();
    }
}