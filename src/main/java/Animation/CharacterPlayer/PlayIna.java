package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayIna extends PlayCharacter{
    public PlayIna(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 400;
        this.offsetX = -80;
        this.offsetY = -50;
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Inanis);
        super.play();
    }
}
