package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.EffectPlayer.EffectManager;
import Animation.EffectPlayer.PlayBloom;
import Audio.AudioHolder;
import game.util.Time;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayGura extends PlayCharacter{
    public PlayGura(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 250;
        this.offsetX = -80;
        this.offsetY = -50;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        switch (state.getBehavior()){
            case NORMAL:
                this.offsetX = -80;
                this.offsetY = -50;
                break;
            case DAMAGE :
                AudioHolder.damage.loop(1);;
                break;
            case ATTACK1:
//                EffectManager.addBloom(Time.Instance.getTotalTime(),3,0.5);
                EffectManager.addDisplacementMap(Time.Instance.getTotalTime(),3, state.getAx(), state.getAy(), true);
                System.out.println("state.getAX,Y()->"+state.getAx()+","+state.getAy());
                double rand = Math.random();
                if(rand>0.8) AudioHolder.peti1.loop(1);
                else if(rand<=0.8 && rand>0.6) AudioHolder.peti2.loop(1);
                else if(rand<=0.6 && rand>0.4) AudioHolder.peti3.loop(1);
                else if(rand<=0.4 && rand>0.2) AudioHolder.peti4.loop(1);
                else AudioHolder.peti5.loop(1);
                break;
        }
    }

    @Override
    public void play() {
        drawShadow(gc, CharacterType.Gura);
        super.play();
    }
}
