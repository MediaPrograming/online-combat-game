package Animation.CharacterPlayer;

import Animation.CharaAnimationPlayer;
import Animation.EffectPlayer.*;
import Animation.CharaAnimationPlayer;
import game.config.Character;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayCharacter {
    protected double width,height;
    protected double X,Y;
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

    public void setPosition(double x,double y){ X = x; Y = y; }
    public void setWidth(double width){ this.width = width; }
    public void setHeight(double height){ this.height = height; }
    public void setPositionFromState(){
        if(state.getDirection() == Direction.RIGHT) X = state.getX();
        else X = state.getX() - this.width;
        Y = state.getY();
    }

    public CharaAnimationPlayer getPlayer(){return player;}

    public void updateState(CharacterState state) {
        this.state = state;
    }

    public void adjustDirection(CharacterState state){
        if(state.getDirection() == Direction.RIGHT) width = Math.abs(width);
        else width = -Math.abs(width);
    }

    public void play(){
        adjustDirection(state);
        setPositionFromState();
        gc.drawImage(player.play(state),X,Y,width,height);

        //test
        ShowStatus();
    }

    public void ShowStatus(){
        gc.setFill(Color.BLUE);
        gc.setFont(Font.font(20));
        switch (this.state.getBehavior()) {
            case NORMAL:
                gc.fillText("STATE:NORMAL",X,Y+100,300);
                break;
            case RUN:
                gc.fillText("STATE:RUN",X,Y+100,300);
                break;
            case JUMP:
                gc.fillText("STATE:JUMP",X,Y+100,300);
                break;
            case SQUAT:
                gc.fillText("STATE:SQUAT",X,Y+100,300);
                break;
            case DAMAGE:
                gc.fillText("STATE:DAMAGE",X,Y+100,300);
                break;
            case ATTACK1:
                gc.fillText("STATE:ATTACK1",X,Y+100,300);
                break;
            case ATTACK2:
                gc.fillText("STATE:ATTACK2",X,Y+100,300);
                break;
            case ATTACK3:
                gc.fillText("STATE:ATTACK3",X,Y+100,300);
                break;
            case ATTACK4:
                gc.fillText("STATE:ATTACK4",X,Y+100,300);
                break;
            case DEFENCE:
                gc.fillText("STATE:DEFENCE",X,Y+100,300);
                break;
        }

    }

}
