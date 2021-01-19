package Animation.CharacterPlayer;

import Animation.playAnimation;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayGura extends PlayCharacter{
    public PlayGura(GraphicsContext gc,playAnimation player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 250;
    }

    @Override
    public void play() {
        super.play();
    }
}
