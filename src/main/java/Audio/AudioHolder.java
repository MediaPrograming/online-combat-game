package Audio;

import game.config.PATH;

import javax.sound.sampled.Clip;
import java.io.File;

public class AudioHolder {
    public static Clip damage;
    public static Clip a;
    public static Clip peti1;
    public static Clip peti2;
    public static Clip peti3;
    public static Clip peti4;
    public static Clip peti5;
    public AudioHolder(){
        damage = AudioClip.createClip(new File(PATH.Effect_HIT));
        peti1 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\peti1.wav"));
        peti2 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\peti2.wav"));
        peti3 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\peti3.wav"));
        peti4 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\peti4.wav"));
        peti5 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\peti5.wav"));
    }
}
