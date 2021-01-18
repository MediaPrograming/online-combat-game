package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;

public class PlayDisplacementMap extends PlayEffect{
    private int width,height;
    public PlayDisplacementMap(){}

    public void play(GraphicsContext gc, double firstFrame, double time,float fX,float fY){
        duration = this.getDuration(firstFrame);
        if(duration >= 0 && duration <= time ) {
            FloatMap floatMap = new FloatMap();
            floatMap.setWidth(width);
            floatMap.setHeight(height);
            DisplacementMap displacementMap = new DisplacementMap();
            width = (int) gc.getCanvas().getWidth();
            height = (int) gc.getCanvas().getHeight();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    floatMap.setSamples(i, j,fX , fY);
                }
            }
            displacementMap.setMapData(floatMap);
            gc.applyEffect(displacementMap);
        }
    }

//    public float[] wiggler(double duration){
//        return ;
//    }

}
