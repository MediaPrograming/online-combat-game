package Animation;

import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class EffectAnimationManager {
    private static List<PlayEffectAnimationOnCharacter> list = new ArrayList<>();
    private static ArrayList<GraphicsContext> gcList = new ArrayList<>();
    public EffectAnimationManager(GraphicsContext gc){}

    public static void setGc(GraphicsContext gc){ gcList.add(gc); }

    public static void play(){
        ArrayList<PlayEffectAnimationOnCharacter> remove = new ArrayList<>();
        EffectAnimationManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) remove.add(e);});
        EffectAnimationManager.list.removeAll(remove);
        list.forEach(PlayEffectAnimationOnCharacter::play);
    }

    public static void addEffectAnimation(String key, double playTime, double firstFrame, CharacterState state){
        list.add(new PlayEffectAnimationOnCharacter(gcList.get(0),playTime,firstFrame,state));
    }
}
