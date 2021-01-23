package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class EffectManager {
    public static ArrayList<PlayEffect> list;
    public static ArrayList<GraphicsContext> gc;
    public EffectManager(GraphicsContext gc1,GraphicsContext gc2,GraphicsContext gc3){
        EffectManager.list = new ArrayList<>();
        EffectManager.gc.add(gc1);
        EffectManager.gc.add(gc2);
        EffectManager.gc.add(gc3);
    }
    public static void addEffect(PlayEffect effect){
        EffectManager.list.add(effect);
    }
    public static void play(){
        EffectManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) EffectManager.list.remove(e);});
        EffectManager.list.forEach(e -> { e.play();});
    }

    public static void addBloom(double firstFrame,double playTime,double threshold){
        EffectManager.gc.forEach(gc -> EffectManager.addEffect(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }
}
