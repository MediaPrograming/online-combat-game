package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;

public class PlayDisplacementMap extends PlayEffect{
    private double duration;
    private int width,height;
    private float fX,fY;
    private boolean wiggle;
    public PlayDisplacementMap(GraphicsContext gc,double firstFrame,double playTime,float fX,float fY,boolean wiggle){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.fX = fX;
        this.fY = fY;
        this.wiggle = wiggle;
    }

    public void play(){
        duration = this.getDuration();
        if(duration >= 0 && duration <= playTime ) {
            FloatMap floatMap = new FloatMap();
            floatMap.setWidth(width);
            floatMap.setHeight(height);
            DisplacementMap displacementMap = new DisplacementMap();
            width = (int) gc.getCanvas().getWidth();
            height = (int) gc.getCanvas().getHeight();

            if(wiggle) wiggler();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    floatMap.setSamples(i, j,fX , fY);
                }
            }
            displacementMap.setMapData(floatMap);
            gc.applyEffect(displacementMap);
        }
    }

    public void wiggler(){}

}
