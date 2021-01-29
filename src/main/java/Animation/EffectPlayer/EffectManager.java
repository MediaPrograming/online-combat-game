package Animation.EffectPlayer;

import javafx.scene.canvas.GraphicsContext;

import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class EffectManager {
    private static List<PlayEffect> list = new ArrayList<>();
    private static ArrayList<GraphicsContext> gcList = new ArrayList<>();
    private EffectManager(){}
    public static void addGraphicsContext(GraphicsContext g){
        gcList.add(g);
    }

    public static void play(){
        ArrayList<PlayEffect> remove = new ArrayList<>();
        EffectManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) remove.add(e);});
        EffectManager.list.removeAll(remove);
        list.forEach(PlayEffect::play);
//        if(!EffectManager.list.isEmpty()) {EffectManager.list.forEach(e->System.out.println(e));System.out.println("----------------------");}
    }

    public static void addBloom(double firstFrame,double playTime,double threshold){
        gcList.forEach(gc -> list.add(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }

    public static void addDisplacementMap(double firstFrame,double playTime,double fX,double fY,boolean wiggle){
        list.add(new PlayDisplacementMap(gcList.get(1),firstFrame,playTime,fX,fY,5,wiggle));
        list.add(new PlayDisplacementMap(gcList.get(2),firstFrame+0.1,playTime,fX/1.1f,fY/1.1f,5,wiggle));
        list.add(new PlayDisplacementMap(gcList.get(0),firstFrame+0.2,playTime,fX/3f,fY/3f,8,wiggle));
    }

}
