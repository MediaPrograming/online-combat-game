package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import game.config.CharaData.Gura;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayCalli extends PlayCharacter{
    public PlayCalli(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 400;
        this.offsetX = -80;
        this.offsetY = -50;
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
        drawShadow(gc, CharacterType.Calliope);
        ShowAttackPolygon(state);
        super.play();
    }
}
