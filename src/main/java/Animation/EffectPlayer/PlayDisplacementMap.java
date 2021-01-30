package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;

public class PlayDisplacementMap extends PlayEffect{
    private double duration;
    private int width,height;
    private float fX,fY,ffx,ffy;
    private double gamma;
    private boolean wiggle;
    public PlayDisplacementMap(GraphicsContext gc,double firstFrame,double playTime,double fX,double fY,double gamma,boolean wiggle){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.fX = (float)fX;
        this.fY = (float)fY;
        this.wiggle = wiggle;
        this.gamma = gamma;
        width = (int) gc.getCanvas().getWidth();
        height = (int) gc.getCanvas().getHeight();
    }

    public void play(){
        duration = this.getDuration();
        if(duration >= 0 && duration <= playTime ) {

            if(wiggle) wiggler();

            gc.getCanvas().setLayoutX(gc.getCanvas().getWidth()*ffx); //DisplacementMapは凄く重いのでCanvasを移動
            gc.getCanvas().setLayoutY(gc.getCanvas().getHeight()*ffy);

        }
    }

    public void wiggler(){
        ffx = fX/1000.0f*(float)(Math.exp(-gamma*duration)*Math.cos(20.0*duration));
        ffy = fY/1000.0f*(float)(Math.exp(-gamma*duration)*Math.cos(20.0*duration));
    }
}
