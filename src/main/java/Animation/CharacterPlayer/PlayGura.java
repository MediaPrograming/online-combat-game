package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.EffectAnimationManager;
import Animation.EffectPlayer.EffectManager;
import Animation.EffectPlayer.PlayBloom;
import Audio.AudioHolder;
import Audio.AudioPlayer;
import game.config.CharaData.Gura;
import game.config.PATH;
import game.store.StoreManager;
import game.util.Time;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class PlayGura extends PlayCharacter{
    private boolean flag;
    private Direction trident_dir;
    Image trident =	new Image(StoreManager.class.getResourceAsStream(PATH.TRIDENT));
    public PlayGura(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 250;
        this.offsetX = -80;
        this.offsetY = -50;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        //System.out.println("state.getAX,Y()->"+state.getAx()+","+state.getAy()+"ID:"+state.getId());
        double rand = Math.random();
        switch (state.getBehavior()){
            case NORMAL:
                this.offsetX = -80;
                this.offsetY = -50;
                break;
            case DAMAGE :
                if(rand>0.8) { AudioPlayer.PlayVoice(AudioHolder.AU_UN);}
                else if(rand<=0.8 && rand>0.6) {AudioPlayer.PlayVoice(AudioHolder.AU);}
                else if(rand<=0.6 && rand>0.4) {AudioPlayer.PlayVoice(AudioHolder.AAAA);}
                else if(rand<=0.4 && rand>0.2) {AudioPlayer.PlayVoice(AudioHolder.AHHHFine);}
                else {}
                break;
            case ATTACK1,ATTACK2:
//                EffectManager.addBloom(Time.Instance.getTotalTime(),3,0.5);
                EffectManager.addDisplacementMap(Time.Instance.getTotalTime(),3, state.getAx(), state.getAy(), true);
                if(rand>0.8) {                  AudioPlayer.PlayVoice(AudioHolder.peti1);AudioPlayer.PlayVoice(AudioHolder.HUCHA);}
                else if(rand<=0.8 && rand>0.6) {AudioPlayer.PlayVoice(AudioHolder.peti2);AudioPlayer.PlayVoice(AudioHolder.MAHIMAHI);}
                else if(rand<=0.6 && rand>0.4) {AudioPlayer.PlayVoice(AudioHolder.peti3);AudioPlayer.PlayVoice(AudioHolder.FUTUN);}
                else if(rand<=0.4 && rand>0.2) {AudioPlayer.PlayVoice(AudioHolder.peti4);AudioPlayer.PlayVoice(AudioHolder.SHAAAARK);}
                else {                          AudioPlayer.PlayVoice(AudioHolder.peti5);AudioPlayer.PlayVoice(AudioHolder.HUMM);}
                break;
            case ATTACK3:
                if(rand>0.5)AudioPlayer.PlayVoice(AudioHolder.TRIDENT);
                else AudioPlayer.PlayVoice(AudioHolder.a);
                trident_dir = state.getDirection();
                break;
        }
    }

    @Override
    protected void ShowAttackPolygon(CharacterState state){
        double AtkX = state.getAtkX() , AtkY = state.getAtkY();
        double AtkW = 0, AtkH = 0;
        if(!(AtkX == -1.0 && AtkY == -1.0)){
            switch (state.getBehavior()){
                case ATTACK3 :
                    flag = true;
                    AtkW = state.getAtkW();
                    AtkH = state.getAtkH();
                    switch (trident_dir){
                        case RIGHT :gc.drawImage(trident,state.getAtkX(), state.getAtkY(),AtkW,AtkH);
                                    break;
                        case LEFT: gc.drawImage(trident,state.getAtkX()+AtkW, state.getAtkY(),-AtkW,AtkH);
                                    break;
                    }
                    break;
            }
//            gc.setStroke(Color.RED);
//            gc.strokeRect(state.getAtkX(),state.getAtkY(),state.getAtkW(),state.getAtkH());
        }else{
            if(flag) { flag = false;}
        }
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Gura);
        ShowAttackPolygon(state);
        super.play();
    }
}
