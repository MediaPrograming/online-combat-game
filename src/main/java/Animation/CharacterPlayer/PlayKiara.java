package Animation.CharacterPlayer;

import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import Audio.AudioPlayer;
import io.game.hub.messageHub.CharacterType;
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
        double rand = Math.random();
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
                if(rand>0.4){AudioPlayer.PlayVoice(AudioHolder.Kiarastopit);
                }else {      AudioPlayer.PlayVoice(AudioHolder.Kiarayabe);
                }
                if(state.getDirection()==Direction.RIGHT){
                    this.offsetX = -150;
                    this.offsetY = -60;
                }else{
                    this.offsetX = -170;
                    this.offsetY = -60;
                }
                break;
            case ATTACK1:
                if(rand>0.6){
                    AudioPlayer.PlayVoice(AudioHolder.Kiarayouwannafight);
                }else if(rand<=0.6&&rand>0.3){
                    AudioPlayer.PlayVoice(AudioHolder.Kiarabigbumb);
                }
                break;
            case ATTACK2:
                if(rand>0.6){
                    AudioPlayer.PlayVoice(AudioHolder.Kiarayouwannafight);
                }else if(rand<=0.6&&rand>0.3){
                    AudioPlayer.PlayVoice(AudioHolder.Kiarabigbumb);
                }
                break;
            case ATTACK4:
                if(rand>0.7){
                    AudioPlayer.PlayVoice(AudioHolder.Kiaraeat);
                }
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
