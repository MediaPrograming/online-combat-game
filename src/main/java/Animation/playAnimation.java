package Animation;

import game.util.Time;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.image.Image;

public class playAnimation {
    private Integer ID;
    private String Chara;
    private Animation nowAnim;
    private CharacterState nowState;
    private Double firstFrame;
    public playAnimation(Integer ID, String Character){
        nowState = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
        this.ID = ID;
        this.Chara = Character;
        nowAnim = animationHolder.getAnimation(Chara, Behavior.NORMAL);
        firstFrame = Time.Instance.getTotalTime();
    }

    public Image playAnimation(CharacterState state){
        /*stateが変更されたらAnimation入れ替え*/
        if(nowState.getBehavior() != state.getBehavior()) {
            nowAnim = animationHolder.getAnimation(Chara, state.getBehavior());
            nowState = state;
            firstFrame = Time.Instance.getTotalTime();
        }

        var duration = Time.Instance.getTotalTime() - firstFrame;
        return nowAnim.getAnim()[(int)(duration*nowAnim.getSpeed())%nowAnim.getAnim().length][(int)(duration/nowAnim.getAnim().length*nowAnim.getSpeed())%nowAnim.getAnim()[0].length];
    }
}
