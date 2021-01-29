package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import game.config.CharaData.Gura;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;

public class PlayKiara extends PlayCharacter{
    public PlayKiara(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 400;
        this.offsetX = -170;
        this.offsetY = -60;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        switch (state.getBehavior()){
            case NORMAL:
                if(state.getDirection()==Direction.RIGHT){
                    this.offsetX = -150;
                    this.offsetY = -60;
                }else{
                    this.offsetX = -170;
                    this.offsetY = -60;
                }
                break;
            case DAMAGE :
                AudioHolder.damage.loop(1);
                if(state.getDirection()==Direction.RIGHT){
                    this.offsetX = -150;
                    this.offsetY = -60;
                }else{
                    this.offsetX = -170;
                    this.offsetY = -60;
                }
                break;
            case ATTACK1:
                break;
            case JUMP:
                if(state.getDirection()==Direction.RIGHT){
                    this.offsetX = -160;
                    this.offsetY = -60;
                }else{
                    this.offsetX = -160;
                    this.offsetY = -60;
                }
                break;
        }
    }


    @Override
    public void play() {
        drawShadow(gc, CharacterType.Kiara);
        super.play();
    }
}
