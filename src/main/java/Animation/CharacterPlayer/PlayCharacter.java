package Animation.CharacterPlayer;

import Animation.CharaAnimationPlayer;
import Animation.EffectPlayer.*;
import Animation.CharaAnimationPlayer;
import game.config.Character;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;

public class PlayCharacter {
    protected double width,height;
    protected int X,Y;
    protected double offsetX,offsetY;
    protected GraphicsContext gc;
    protected CharaAnimationPlayer player;
    protected CharacterState state;

    protected PlayColorAdjust colorAdjust;
    protected PlayDisplacementMap displacementMap;
    protected PlayBloom bloom;
    protected PlayDropShadow dropShadow;
    protected PlayMotionBlur motionBlur;
    protected PlayGaussianBlur gaussianBlur;

    public PlayCharacter(GraphicsContext gc,CharaAnimationPlayer player, CharacterState state){
        this.gc = gc;
        this.player = player;
        this.state = state;
        colorAdjust = new PlayColorAdjust();
        displacementMap = new PlayDisplacementMap();
        bloom = new PlayBloom();
        dropShadow = new PlayDropShadow();
        motionBlur = new PlayMotionBlur();
        gaussianBlur = new PlayGaussianBlur();
    }

    public void setWidth(double width){ this.width = width; }
    public void setHeight(double height){ this.height = height; }
    public void setPositionFromState(){
        X = (int)state.getX();
        Y = (int)state.getY();
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public void adjustDirection(CharacterState state){
        if(state.getDirection() == Direction.RIGHT) width = Math.abs(width);
        else width = -Math.abs(width);
    }

    public void play(){
        //test
        X = Y = 400;

//        setPositionFromState();
        adjustDirection(state);
        gc.drawImage(player.play(state),X,Y,width,height);
    }

}
