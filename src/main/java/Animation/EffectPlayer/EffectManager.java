package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class EffectManager {
    private static List<PlayEffect> list = new ArrayList<>();
    private static ArrayList<GraphicsContext> gcList = new ArrayList<>();
    private EffectManager(){}
    public static void addGraphicsContext(GraphicsContext g){
        gcList.add(g);
    }

    public static void play(){
//        EffectManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) EffectManager.list.remove(e);});
        list.forEach(PlayEffect::play);
    }

    public static void addBloom(double firstFrame,double playTime,double threshold){
        gcList.forEach(gc -> list.add(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }
}
