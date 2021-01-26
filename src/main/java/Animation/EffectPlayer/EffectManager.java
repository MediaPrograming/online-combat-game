package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class EffectManager {
    public static ArrayList<PlayEffect> list;
    public static ArrayList<GraphicsContext> gc;
    public EffectManager(){
        EffectManager.list = new ArrayList<>();
    }
    public static void addGraphicsContext(GraphicsContext g){
        EffectManager.gc.add(g);
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
