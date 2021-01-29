package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import game.config.CharaData.Gura;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayAme extends PlayCharacter{
    public PlayAme(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 275;
        this.offsetX = 0;
        this.offsetY = -5;
    }

    @Override
    protected void ShowAttackPolygon(CharacterState state){
        double AtkX = state.getAtkX() , AtkY = state.getAtkY();
        double AtkW = 0, AtkH = 0;
        if(!(AtkX == -1.0 && AtkY == -1.0)){
            switch (state.getBehavior()){

            }
            gc.strokeRect(state.getAtkX(),state.getAtkY(),state.getAtkW(),state.getAtkH());
        }
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Amelia);
        ShowAttackPolygon(state);
        super.play();
    }
}
