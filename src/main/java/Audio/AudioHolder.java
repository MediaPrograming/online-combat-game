package Audio;

import game.config.PATH;

import javax.sound.sampled.Clip;
import java.io.File;

public class AudioHolder {
    public static Clip damage;
    public static Clip a;
    public static Clip peti1,peti2,peti3,peti4,peti5;
    public static Clip TRIDENT,MAHIMAHI,SHAAAARK,HUMM,FUTUN,HUCHA;
    public static Clip AAAA,AHHHFine,AU,AU_UN;
    public static Clip Kikkeriki,Kiarastopit,Kiarabigbumb,Kiaraeat,Kiarayouwannafight,Kiarayabe;
    private AudioHolder(){}
    public static void Initialize(){
        damage = AudioClip.createClip("../sounds/hit.wav");
        a = AudioClip.createClip("../sounds/Gura/a.wav");
        peti1 = AudioClip.createClip("../sounds/Gura/peti1.wav");
        peti2 = AudioClip.createClip("../sounds/Gura/peti2.wav");
        peti3 = AudioClip.createClip("../sounds/Gura/peti3.wav");
        peti4 = AudioClip.createClip("../sounds/Gura/peti4.wav");
        peti5 = AudioClip.createClip("../sounds/Gura/peti5.wav");
        peti5 = AudioClip.createClip("../sounds/Gura/peti5.wav");
        TRIDENT = AudioClip.createClip("../sounds/Gura/OHTRIDENT.wav");
        MAHIMAHI = AudioClip.createClip("../sounds/Gura/MAHIMAHI.wav");
        SHAAAARK = AudioClip.createClip("../sounds/Gura/SHAAAAARK.wav");
        HUMM = AudioClip.createClip("../sounds/Gura/HUMM.wav");
        FUTUN = AudioClip.createClip("../sounds/Gura/Futun.wav");
        HUCHA = AudioClip.createClip("../sounds/Gura/hutya.wav");
        AAAA = AudioClip.createClip("../sounds/Gura/AAAA.wav");
        AHHHFine = AudioClip.createClip("../sounds/Gura/AHHHFine.wav");
        AU = AudioClip.createClip("../sounds/Gura/AU.wav");
        AU_UN = AudioClip.createClip("../sounds/Gura/AU_UN.wav");

        Kikkeriki = AudioClip.createClip( "../sounds/Kiara/Kikkeriki---------.wav");
        Kiarastopit = AudioClip.createClip( "../sounds/Kiara/Kiarastopit.wav");
        Kiarabigbumb = AudioClip.createClip( "../sounds/Kiara/kiarabigbumb.wav");
        Kiarayabe = AudioClip.createClip( "../sounds/Kiara/Kiarayabe.wav");
        Kiaraeat = AudioClip.createClip("../sounds/Kiara/kiaraeat.wav");
        Kiarayouwannafight = AudioClip.createClip( "../sounds/Kiara/kiarayouwannafight.wav");
    }

}
