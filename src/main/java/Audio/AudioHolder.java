package Audio;

import game.config.PATH;

import javax.sound.sampled.Clip;
import java.io.File;

public class AudioHolder {
    public static Clip damage;
    public static Clip a;
    public static Clip peti1,peti2,peti3,peti4,peti5;
    public static Clip TRIDENT,MAHIMAHI,SHAAAARK,HUMM,FUTUN,HUCHA;
    public AudioHolder(){
        damage = AudioClip.createClip(new File(PATH.Effect_HIT));
        peti1 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti1.wav"));
        peti2 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti2.wav"));
        peti3 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti3.wav"));
        peti4 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti4.wav"));
        peti5 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti5.wav"));
        peti5 = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\peti5.wav"));
        TRIDENT = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\OHTRIDENT.wav"));
        MAHIMAHI = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\MAHIMAHI.wav"));
        SHAAAARK = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\SHAAAAARK.wav"));
        HUMM = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\HUMM.wav"));
        FUTUN = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\Futun.wav"));
        HUCHA = AudioClip.createClip(new File(PATH.root + "\\src\\main\\resources\\game\\sounds\\Gura\\hutya.wav"));


    }

}
