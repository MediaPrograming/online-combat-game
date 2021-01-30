package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;

public class PlayColorAdjust extends PlayEffect {
    private double contrast,hue,brightness,saturation;
    private boolean ease;
    public PlayColorAdjust(GraphicsContext gc,double firstFrame,double playTime,double contrast,double hue, double brightness, double saturation,boolean ease){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.contrast = contrast;
        this.brightness = brightness;
        this.hue = hue;
        this.saturation = saturation;
        this.ease = ease;
    }

    public void play(){
        ColorAdjust colorAdjust = new ColorAdjust();
        if(ease){
            colorAdjust.setContrast(EffectManager.easeInCubic(playTime,firstFrame)*contrast);
            colorAdjust.setHue(EffectManager.easeInCubic(playTime,firstFrame)*hue);
            colorAdjust.setBrightness(EffectManager.easeInCubic(playTime,firstFrame)*brightness);
            colorAdjust.setSaturation(EffectManager.easeInCubic(playTime,firstFrame)*saturation);
        }else {
            colorAdjust.setContrast(contrast);
            colorAdjust.setHue(hue);
            colorAdjust.setBrightness(brightness);
            colorAdjust.setSaturation(saturation);
        }
        gc.applyEffect(colorAdjust);
    }
}
