package Animation;

import game.config.Action;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.State;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Hashtable;

public class playAnimation {
    private animationHolder animations;
//    private Hashtable<Integer,String> table;
    private Integer ID;
    private String Chara;
    private Image[][] nowAnim;
    private CharacterState nowState;
    private Double firstFrame;
    public playAnimation(Integer ID, String Character){
        animations = new animationHolder();
        this.ID = ID;
        this.Chara = Character;
        nowAnim = animations.getAnimation(Chara, Behavior.NORMAL);
    }

//    public void registerID(Integer ID, String Character){
//        table.put(ID,Character);
//    }

    public Image playAnimation(CharacterState state){
        if(nowState != state) {nowAnim = animations.getAnimation(Chara, state.getBehavior()); nowState = state;}// 後でStateから参照させる

        return nowAnim[0][0];
    }
}
