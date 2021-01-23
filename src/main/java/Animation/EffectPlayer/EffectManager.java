package Animation.EffectPlayer;

import game.view.panel.UoPanel;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class EffectManager {
    private static class SingletonHolder{
        private static final EffectManager INSTANCE = new EffectManager(UoPanel.gc1,UoPanel.gc2,UoPanel.gc3);
    }
    private ArrayList<PlayEffect> list;
    private ArrayList<GraphicsContext> gc;
    private EffectManager(GraphicsContext gc1,GraphicsContext gc2,GraphicsContext gc3){
       list = new ArrayList<>();
       gc.add(gc1);
       gc.add(gc2);
       gc.add(gc3);
    }
    public static EffectManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addEffect(PlayEffect effect){
        list.add(effect);
    }
    public void play(){
        list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) list.remove(e);});
        list.forEach(e -> { e.play();});
    }

    public void addBloom(double firstFrame,double playTime,double threshold){
        gc.forEach(gc -> addEffect(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }
}
