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
        System.out.println("uo");
        super.changedAction(state);
        double rand = Math.random();
        switch (state.getBehavior()){
            case NORMAL:
                this.offsetX = -80;
                this.offsetY = -50;
                break;
            case DAMAGE :
                break;
            case ATTACK1,ATTACK2:
//                EffectManager.addBloom(Time.Instance.getTotalTime(),3,0.5);
                EffectManager.addDisplacementMap(Time.Instance.getTotalTime(),3, state.getAx(), state.getAy(), true);
                System.out.println("state.getAX,Y()->"+state.getAx()+","+state.getAy());
                if(rand>0.8) {AudioHolder.peti1.loop(1);AudioHolder.HUCHA.loop(1);}
                else if(rand<=0.8 && rand>0.6) {AudioHolder.peti2.loop(1);AudioHolder.MAHIMAHI.loop(1);}
                else if(rand<=0.6 && rand>0.4) {AudioHolder.peti3.loop(1);AudioHolder.FUTUN.loop(1);}
                else if(rand<=0.4 && rand>0.2) AudioHolder.peti4.loop(1);
                else AudioHolder.peti5.loop(1);
                break;
            case ATTACK3:
                if(rand>0.5)AudioHolder.TRIDENT.loop(1);
                else AudioHolder.SHAAAARK.loop(1);
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
            gc.strokeRect(state.getAtkX(),state.getAtkY(),state.getAtkW(),state.getAtkH());
        }else{
            if(flag) {EffectManager.addDisplacementMap(Time.Instance.getTotalTime(),3, 10, 2, true); flag = false;}
        }
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Gura);
        ShowAttackPolygon(state);
        super.play();
    }
}
