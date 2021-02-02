package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import Audio.AudioPlayer;
import game.config.CharaData.Gura;
import game.config.PATH;
import game.store.StoreManager;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

public class PlayAme extends PlayCharacter{
    private boolean flag;
    private Direction direction;
    Image injector =new Image(StoreManager.class.getResourceAsStream(PATH.Amelia_Atk_obj));
    public PlayAme(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 275;
        this.offsetX = -80;
        this.offsetY = -30;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        double rand = Math.random();
        switch (state.getBehavior()){
            case NORMAL:
                if(state.getDirection()==Direction.LEFT){
                    this.offsetX = -80;
                    this.offsetY = -30;
                }else{
                    this.offsetX = -100;
                    this.offsetY = -30;
                }
            break;
            case ATTACK1, ATTACK2:
                direction = state.getDirection();
                if(rand>0.7) AudioPlayer.PlayVoice(AudioHolder.BYEBYE);
                else if(rand>0.3) AudioPlayer.PlayVoice(AudioHolder.AHAHA);
                else AudioPlayer.PlayVoice(AudioHolder.GYAAA);
            break;
            case ATTACK3:
                if(rand>0.5) AudioPlayer.PlayVoice(AudioHolder.SUS);
                else AudioPlayer.PlayVoice(AudioHolder.YouAwful);
                break;
            case ATTACK4:
                if(rand>0.5) AudioPlayer.PlayVoice(AudioHolder.AHAHA);
                else AudioPlayer.PlayVoice(AudioHolder.HIC1);
                break;
            case DAMAGE:
                if(rand>0.5) AudioPlayer.PlayVoice(AudioHolder.FUCKAAA);
                else AudioPlayer.PlayVoice(AudioHolder.Damn);
                break;
        }
    }

    @Override
    protected void ShowAttackPolygon(CharacterState state){
        double AtkX = state.getAtkX() , AtkY = state.getAtkY();
        double AtkW = 0, AtkH = 0;
        if(!(AtkX == -1.0 && AtkY == -1.0)){
            switch (state.getBehavior()){
                case ATTACK1, ATTACK2 :
                    flag = true;
                    AtkW = state.getAtkW();
                    AtkH = state.getAtkH();
                    switch (direction){
                        case RIGHT :gc.drawImage(injector,state.getAtkX(), state.getAtkY(),AtkW,AtkH);
                            break;
                        case LEFT: gc.drawImage(injector,state.getAtkX()+AtkW, state.getAtkY(),-AtkW,AtkH);
                            break;
                    }
            }
//            gc.strokeRect(state.getAtkX(),state.getAtkY(),state.getAtkW(),state.getAtkH());
        }else{
        if(flag) { flag = false;}
    }
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Amelia);
        ShowAttackPolygon(state);
        if(Math.random()<0.001)
        {double hic = Math.random();
        if(hic>0.7) AudioPlayer.PlayVoice(AudioHolder.HIC1);
        else if(hic>0.3) AudioPlayer.PlayVoice(AudioHolder.HIC2);
        else AudioPlayer.PlayVoice(AudioHolder.HIC3);
        }
        super.play();
    }
}
