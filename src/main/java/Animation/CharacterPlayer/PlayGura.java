package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.EffectAnimationManager;
import Animation.EffectPlayer.EffectManager;
import Animation.EffectPlayer.PlayBloom;
import Audio.AudioHolder;
import game.config.CharaData.Gura;
import game.config.PATH;
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
    Image trident =	new Image(new File(PATH.TRIDENT).toURI().toString());
    public PlayGura(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 250;
        this.offsetX = -80;
        this.offsetY = -50;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        System.out.println("state.getAX,Y()->"+state.getAx()+","+state.getAy()+"ID:"+state.getId());
        double rand = Math.random();
        switch (state.getBehavior()){
            case NORMAL:
                this.offsetX = -80;
                this.offsetY = -50;
                break;
            case DAMAGE :
                if(rand>0.8) {AudioHolder.AU_UN.setFramePosition(0);AudioHolder.AU_UN.loop(0);}
                else if(rand<=0.8 && rand>0.6) {AudioHolder.AU_UN.setFramePosition(0);AudioHolder.AU.loop(0);}
                else if(rand<=0.6 && rand>0.4) {AudioHolder.AU_UN.setFramePosition(0);AudioHolder.AAAA.loop(0);}
                else if(rand<=0.4 && rand>0.2) {AudioHolder.AU_UN.setFramePosition(0);AudioHolder.AHHHFine.loop(0);}
                else {}
                break;
            case ATTACK1,ATTACK2:
//                EffectManager.addBloom(Time.Instance.getTotalTime(),3,0.5);
                EffectManager.addDisplacementMap(Time.Instance.getTotalTime(),3, state.getAx(), state.getAy(), true);
                if(rand>0.8) {AudioHolder.peti1.setFramePosition(0);AudioHolder.peti1.loop(0);AudioHolder.HUCHA.setFramePosition(0);AudioHolder.HUCHA.loop(0);}
                else if(rand<=0.8 && rand>0.6) {AudioHolder.peti2.setFramePosition(0);AudioHolder.peti2.loop(0);AudioHolder.MAHIMAHI.setFramePosition(0);AudioHolder.MAHIMAHI.loop(0);}
                else if(rand<=0.6 && rand>0.4) {AudioHolder.peti3.setFramePosition(0);AudioHolder.peti3.loop(0);AudioHolder.FUTUN.setFramePosition(0);AudioHolder.FUTUN.loop(0);}
                else if(rand<=0.4 && rand>0.2) {AudioHolder.peti4.setFramePosition(0);AudioHolder.peti4.loop(0);AudioHolder.SHAAAARK.setFramePosition(0);AudioHolder.SHAAAARK.loop(0);}
                else {AudioHolder.peti5.setFramePosition(0);AudioHolder.peti5.loop(0);AudioHolder.HUMM.setFramePosition(0);AudioHolder.HUMM.loop(0);}
                break;
            case ATTACK3:
                if(rand>0.5){AudioHolder.TRIDENT.setFramePosition(0);AudioHolder.TRIDENT.loop(0);}
                else {AudioHolder.a.setFramePosition(0);AudioHolder.a.loop(0);}
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
            gc.setStroke(Color.RED);
            gc.strokeRect(state.getAtkX(),state.getAtkY(),state.getAtkW(),state.getAtkH());
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
