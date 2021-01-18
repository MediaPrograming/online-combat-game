package Animation.EffectPlayer;

import game.util.Time;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;

public class PlayColorAdjust extends PlayEffect {
    public PlayColorAdjust(){}

    public void play(GraphicsContext gc,double contrast,double hue, double brightness, double saturation){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(contrast);
        colorAdjust.setHue(hue);
        colorAdjust.setBrightness(brightness);
        colorAdjust.setSaturation(saturation);

        gc.applyEffect(colorAdjust);
    }
}
