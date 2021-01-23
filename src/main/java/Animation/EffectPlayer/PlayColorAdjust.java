package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;

public class PlayColorAdjust extends PlayEffect {
    public PlayColorAdjust(GraphicsContext gc,double firstFrame,double playTime){
        this.gc = gc;
        this.firstFrame = firstFrame;
        this.playTime = playTime;
    }

    public void play(GraphicsContext gc,double contrast,double hue, double brightness, double saturation){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(contrast);
        colorAdjust.setHue(hue);
        colorAdjust.setBrightness(brightness);
        colorAdjust.setSaturation(saturation);

        gc.applyEffect(colorAdjust);
    }
}
