package Animation.EffectPlayer;

import game.util.Time;
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
    public static void resetGraphicsContext(){gcList = new ArrayList<>();}

    public static void play(){
        ArrayList<PlayEffect> remove = new ArrayList<>();
        EffectManager.list.forEach(e -> {if(e.getDuration() > e.getPlayTime()) remove.add(e);});
        EffectManager.list.removeAll(remove);
        list.forEach(PlayEffect::play);
//        if(!EffectManager.list.isEmpty()) {EffectManager.list.forEach(e->System.out.println(e));System.out.println("----------------------");}
    }

    public static double easeInCubic(double playTime,double firstFrame){
        double duration = Time.Instance.getTotalTime() - firstFrame;
        if(duration<playTime) return Math.pow(1-(duration/playTime),3);
        else return 0;
    }

    public static void addBloom(double firstFrame,double playTime,double threshold){
        gcList.forEach(gc -> list.add(new PlayBloom(gc,firstFrame,playTime,threshold)));
    }

    public static void addDisplacementMap(double firstFrame,double playTime,double fX,double fY,boolean wiggle){
        list.add(new PlayDisplacementMap(gcList.get(1),firstFrame,playTime,fX,fY,5,wiggle,0,0));
        list.add(new PlayDisplacementMap(gcList.get(2),firstFrame+0.1,playTime,fX/1.1f,fY/1.1f,5,wiggle,0,0));
        list.add(new PlayDisplacementMap(gcList.get(0),firstFrame+0.2,playTime,fX/3f,fY/3f,8,wiggle,0,0));
    }

    public static void addSelectionWiggle(boolean self, double firstFrame,boolean wiggle,int SdefaltX,int SdefaltY,int EdefaltX,int EdefaltY){
        double playTime = 3;
        float fX = 50,fY = 80;
        if(self) {
            list.add(new PlayDisplacementMap(gcList.get(0),firstFrame,playTime,fX,fY,5,wiggle,SdefaltX,SdefaltY));
//            list.add(new PlayDisplacementMap(gcList.get(1),firstFrame+0.1,playTime,-fX/2.0f,fY/2.0f,5,wiggle,EdefaltX,EdefaltY));
        }else{
            list.add(new PlayDisplacementMap(gcList.get(1),firstFrame,playTime,-fX,fY,5,wiggle,EdefaltX,EdefaltY));
//            list.add(new PlayDisplacementMap(gcList.get(0),firstFrame+0.1,playTime,fX/2.0f,fY/2.0f,5,wiggle,SdefaltX,SdefaltY));
        }
    }

    public static void addColorAdjust(double firstFrame,double playTime,double contrast,double hue,double brightness,double saturation,boolean ease){
        gcList.forEach(gc -> list.add(new PlayColorAdjust(gc,firstFrame,playTime,contrast,hue,brightness,saturation,ease)));
    }

}
