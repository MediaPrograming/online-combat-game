package Animation.CharacterPlayer;

import Animation.CharaAnimationPlayer;
import Animation.EffectAnimationManager;
import Animation.EffectPlayer.*;
import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import game.config.CharaData.*;
import game.config.Character;
import game.util.Time;
import io.game.hub.messageHub.CharacterType;
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
    protected boolean changed;

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
        changed = false;
    }

    public void setPosition(double x,double y){ X = x; Y = y; }
    public void setWidth(double width){ this.width = width; }
    public void setHeight(double height){ this.height = height; }
    public void setPositionFromState(){
        if(state.getDirection() == Direction.RIGHT) X = state.getX()+offsetX;
        else X = state.getX() - this.width+offsetX;
        Y = state.getY()+offsetY;
    }

    public CharaAnimationPlayer getPlayer(){return player;}

    public void updateState(CharacterState state) {
        if(state.getBehavior() != this.state.getBehavior()) changedAction(state);
        this.state = state;
    }

    public void changedAction(CharacterState state) {
        switch (state.getBehavior()){
            case DAMAGE :
                AudioHolder.damage.loop(1);;
                EffectAnimationManager.addEffectAnimation("HIT",1, Time.Instance.getTotalTime(),state);
                break;
        }
    } // Behaviorが変更されたとき1回だけ呼ばれる関数

    public void adjustDirection(CharacterState state){
        if(state.getDirection() == Direction.RIGHT) width = Math.abs(width);
        else width = -Math.abs(width);
    }

    public void drawShadow(GraphicsContext gc, CharacterType chara){
        int distance;
        switch (chara){
            case Gura :
                distance = 600 - (int)state.getY() - (int) Gura.height;
                break;
            case Calliope :
                distance = 600 - (int)state.getY() - (int) Calli.height;
                break;
            case Inanis :
                distance = 600 - (int)state.getY() - (int) Ina.height;
                break;
            case Amelia :
                distance = 600 - (int)state.getY() - (int) Ame.height;
                break;
            case Kiara :
                distance = 600 - (int)state.getY() - (int) Kiara.height;
                break;
            default :
                throw new IllegalStateException("Unexpected value: " + chara);
        }
        int ovalW=100,ovalH=20;
        if(distance >0) {ovalW=100-distance; ovalH = 20-distance/5;}
        gc.setFill(Color.color(0.1,0.1,0.2,0.4));
        gc.fillOval(state.getX()+Math.abs(width/4)-ovalW/2,580-ovalH/2,ovalW,ovalH);
    }

    public void play(){
        adjustDirection(state);
        setPositionFromState();
        gc.drawImage(player.play(state),X,Y,width,height);

        //test
        ShowStatus();
        gc.fillText("AtkX,Y->"+state.getAtkX()+","+state.getAtkY(),state.getX()-50, state.getY()-50);
        gc.fillRect(state.getAtkX(), state.getAtkY(), 20,20);
    }

    protected void ShowAttackPolygon(CharacterState state){}

    private void ShowStatus(){
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
