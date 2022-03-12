package Audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
public class AudioPlayer{
        static Clip bgm;
        static FloatControl ctrl ;
        static String p;
        public static void Play(String path){
                if(bgm!=null){
                        bgm.close();
                }
                System.out.println("audio cahnged");
                p=path;
                bgm = AudioClip.createClip(path);
                ctrl = (FloatControl)bgm.getControl(FloatControl.Type.MASTER_GAIN);
                bgm.loop(Clip.LOOP_CONTINUOUSLY);
                ctrl.setValue((float)Math.log10(0.1) * 20);
                bgm.start();              
        }
        public static void Stop(){
                if(bgm==null){
                        System.out.println("bgm is null");
                }
                bgm.stop();
                bgm.close();
        }

        public static void ChangeVolume(float volume){
                ctrl.setValue((float)Math.log10(volume) * 20);
        }
        public static String getBGM(){
                return p;
        }
        public static void PlayVoice(Clip Voice){Voice.setFramePosition(0);Voice.loop(0);}

}