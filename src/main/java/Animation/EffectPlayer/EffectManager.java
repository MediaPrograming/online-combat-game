package Animation.EffectPlayer;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class EffectManager {
    public static List<PlayEffect> list = Collections.synchronizedList(new ArrayList<>());
    public static ArrayList<GraphicsContext> gc = new ArrayList<>();
    public EffectManager(){}
    public static void addGraphicsContext(GraphicsContext g){
        EffectManager.gc.add(g);
    }
    public static void addEffect(PlayEffect effect){
        EffectManager.list.add(effect);
    }
    public static void play(){
//        EffectManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) EffectManager.list.remove(e);});
        EffectManager.list.forEach(e ->  Platform.runLater(() -> e.play()));
    }

    public static void addBloom(double firstFrame,double playTime,double threshold){
        EffectManager.gc.forEach(gc -> EffectManager.addEffect(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }
}
