package Animation.CharacterPlayer;

import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Audio.AudioHolder;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class PlayGura extends PlayCharacter{
    public PlayGura(GraphicsContext gc, CharaAnimationPlayer player, CharacterState state){
        super(gc, player, state);
        this.width = this.height = 250;
    }

    @Override
    public void changedAction(CharacterState state) {
        super.changedAction(state);
        switch (state.getBehavior()){
            case DAMAGE :
                AudioHolder.damage.start();
                break;
            case ATTACK1:
                double rand = Math.random();
                if(rand>0.8) AudioHolder.peti1.start();
                else if(rand<=0.8 && rand>0.6) AudioHolder.peti2.start();
                else if(rand<=0.6 && rand>0.4) AudioHolder.peti3.start();
                else if(rand<=0.4 && rand>0.2) AudioHolder.peti4.start();
                else AudioHolder.peti5.start();
                break;
        }
    }

    @Override
    public void play() {
//        if(this.state.getBehavior() == Behavior.DAMAGE) gc.drawImage(AnimationHolder.getEffectAnimation("HIT").getAnim()[0][0],this.X,this.Y,50,50);
        super.play();
    }
}
