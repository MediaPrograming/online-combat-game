package Animation;

import game.util.Time;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.image.Image;

import java.awt.*;

public class CharaAnimationPlayer {
    private Integer ID;
    private String Chara;
    private Animation nowAnim;
    private CharacterState nowState;
    private double firstFrame;
    public CharaAnimationPlayer(Integer ID, String Character){

        this.ID = ID;
        this.Chara = Character;
        try{
        nowAnim = AnimationHolder.getCharaAnimation(Chara, Behavior.NORMAL);
        }catch (Exception e){System.out.println("UOOOOO"+e.toString());}
        firstFrame = Time.Instance.getTotalTime();
        nowState = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
        }

        public Integer getID(){return ID;}

    public Image play(CharacterState state){
        /*stateが変更されたらAnimation入れ替え*/
        if(nowState.getBehavior() != state.getBehavior()) {
            nowAnim = AnimationHolder.getCharaAnimation(Chara, state.getBehavior());
//            System.out.println("nowState_"+nowState+"->newState_"+state);
            nowState = state;
            firstFrame = Time.Instance.getTotalTime();
        }

        var duration = Time.Instance.getTotalTime() - firstFrame;
        int one = (int)(duration*nowAnim.getSpeed())%nowAnim.getAnim().length;
        int two = (int)(duration/nowAnim.getAnim().length*nowAnim.getSpeed())%nowAnim.getAnim()[0].length;

        if(!nowAnim.getLoop()){
            if(duration > (double)nowAnim.getAnim().length*(double)nowAnim.getAnim()[0].length / (double)nowAnim.getSpeed() ){
                one = nowAnim.getAnim().length - 1;
                two = nowAnim.getAnim()[0].length - 1;
//                System.out.println("OVER");
            }
//            System.out.println("nowPlaying["+one+"]["+two+"]");
        }

        return nowAnim.getAnim()[one][two];
    }
}
