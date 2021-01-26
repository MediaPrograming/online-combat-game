package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayKiara extends PlayCharacter{
    public PlayKiara(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 400;
        this.offsetX = -150;
        this.offsetY = -50;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        switch (state.getBehavior()){
            case NORMAL:
                this.offsetX = -80;
                this.offsetY = -50;
                break;
            case DAMAGE :
                AudioHolder.damage.loop(1);
                break;
            case ATTACK1:
                break;
        }
    }

    @Override
    public void play() {
       super.play();
    }
}
