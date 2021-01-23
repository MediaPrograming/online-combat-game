package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;

public class PlayColorAdjust extends PlayEffect {
    private double contrast,hue,brightness,saturation;
    public PlayColorAdjust(GraphicsContext gc,double firstFrame,double playTime,double contrast,double hue, double brightness, double saturation){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
        this.contrast = contrast;
        this.brightness = brightness;
        this.hue = hue;
        this.saturation = saturation;
    }

    public void play(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(contrast);
        colorAdjust.setHue(hue);
        colorAdjust.setBrightness(brightness);
        colorAdjust.setSaturation(saturation);

        gc.applyEffect(colorAdjust);
    }
}
