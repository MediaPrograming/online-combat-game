package Animation;

import game.util.Time;
import javafx.scene.image.Image;

public class EffectAnimationPlayer {
    private double firstFrame;
    private Animation effect;

    public EffectAnimationPlayer(String key,double firstFrame){
        this.firstFrame = firstFrame;
        effect = AnimationHolder.getEffectAnimation(key);
    }

    public Image play(){
        var duration = Time.Instance.getTotalTime() - firstFrame;
        int one = (int)(duration* effect.getSpeed())%effect.getAnim().length;
        int two = (int)(duration/effect.getAnim().length*effect.getSpeed())%effect.getAnim()[0].length;

        if(!effect.getLoop()){
            if(duration > effect.getAnim().length*effect.getAnim()[0].length * effect.getSpeed() ){
                one = effect.getAnim().length - 1;
                two = effect.getAnim()[0].length - 1;
            }
        }
        return effect.getAnim()[one][two];
    }

}
