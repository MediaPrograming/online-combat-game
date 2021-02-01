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
        damage = AudioClip.createClip(PATH.Effect_HIT);
        a = AudioClip.createClip(PATH.sounds+"//Gura/a.wav");
        peti1 = AudioClip.createClip(PATH.sounds+"/Gura/peti1.wav");
        peti2 = AudioClip.createClip(PATH.sounds+"/Gura/peti2.wav");
        peti3 = AudioClip.createClip(PATH.sounds+"/Gura/peti3.wav");
        peti4 = AudioClip.createClip(PATH.sounds+"/Gura/peti4.wav");
        peti5 = AudioClip.createClip(PATH.sounds+"/Gura/peti5.wav");
        peti5 = AudioClip.createClip(PATH.sounds+"/Gura/peti5.wav");
        TRIDENT = AudioClip.createClip(PATH.sounds+"/Gura/OHTRIDENT.wav");
        MAHIMAHI = AudioClip.createClip(PATH.sounds+"/Gura/MAHIMAHI.wav");
        SHAAAARK = AudioClip.createClip(PATH.sounds+"/Gura/SHAAAAARK.wav");
        HUMM = AudioClip.createClip(PATH.sounds+"/Gura/HUMM.wav");
        FUTUN = AudioClip.createClip(PATH.sounds+"/Gura/Futun.wav");
        HUCHA = AudioClip.createClip(PATH.sounds+"/Gura/hutya.wav");
        AAAA = AudioClip.createClip(PATH.sounds+"/Gura/AAAA.wav");
        AHHHFine = AudioClip.createClip(PATH.sounds+"/Gura/AHHHFine.wav");
        AU = AudioClip.createClip(PATH.sounds+"/Gura/AU.wav");
        AU_UN = AudioClip.createClip(PATH.sounds+"/Gura/AU_UN.wav");

        Kikkeriki = AudioClip.createClip(PATH.sounds +"/Kiara/Kikkeriki---------.wav");
        Kiarastopit = AudioClip.createClip(PATH.sounds +"/Kiara/Kiarastopit.wav");
        Kiarabigbumb = AudioClip.createClip(PATH.sounds +"/Kiara/kiarabigbumb.wav");
        Kiarayabe = AudioClip.createClip(PATH.sounds +"/Kiara/Kiarayabe.wav");
        Kiaraeat = AudioClip.createClip(PATH.sounds +"/Kiara/kiaraeat.wav");
        Kiarayouwannafight = AudioClip.createClip(PATH.sounds +"/Kiara/kiarayouwannafight.wav");
    }

}
