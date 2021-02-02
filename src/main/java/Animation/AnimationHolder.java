package Animation;

import game.config.PATH;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;

import java.util.Hashtable;

/*連番ImageをArrayListに保存しておくクラス*/
public class AnimationHolder {
    private AnimationHolder(){}
    private static Hashtable<CharacterType,Hashtable<Behavior,Animation>> table = new Hashtable<>();
    private static Hashtable<Behavior,Animation> gura = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ame = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ina = new Hashtable<>();
    private static Hashtable<Behavior,Animation> calli = new Hashtable<>();
    private static Hashtable<Behavior,Animation> kiara = new Hashtable<>();
    private static Hashtable<String,Animation> effects = new Hashtable<>();
    public static void Initialize(){
        table.put(CharacterType.Gura,gura);
        table.put(CharacterType.Amelia,ame);
        table.put(CharacterType.Inanis,ina);
        table.put(CharacterType.Calliope,calli);
        table.put(CharacterType.Kiara,kiara);
        addAllAnimations();
    }

    private static void addAllAnimations() {
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.NORMAL ,PATH.Gura_Normal,128,128,4,2,2,true);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.RUN ,PATH.Gura_Run,128,128,4,2,4,true);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.ATTACK1 ,PATH.Gura_Attack1,128,128,7,2,12,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.ATTACK2 ,PATH.Gura_Attack1,128,128,7,2,12,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.ATTACK3 ,PATH.Gura_Attack3,128,128,2,1,1,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.ATTACK4 ,PATH.Gura_Attack4,128,128,5,3,10,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.DAMAGE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.JUMP ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Gura, Behavior.SQUAT ,PATH.Gura_Normal,128,128,4,2,2, true);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.ATTACK1 ,PATH.Kiara_Attack1,160,160,3,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.ATTACK2 ,PATH.Kiara_Attack1,160,160,3,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.ATTACK3 ,PATH.Kiara_Attack3,160,160,3,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.ATTACK4 ,PATH.Kiara_Attack4,160,160,2,1,4,false);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.DAMAGE  ,PATH.Kiara_Damage,160,160,2,2,8,true);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.JUMP    ,PATH.Kiara_Jump,160,160,1,1,2,true);
        AnimationHolder.addCharaAnimation(CharacterType.Kiara, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2, true);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Inanis, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2, false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(CharacterType.Calliope, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2, false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.NORMAL  ,PATH.Amelia_Normal,150,150,4,3,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.RUN     ,PATH.Amelia_Run,   128,128,4,2,8,true );
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.ATTACK1 ,PATH.Amelia_Attack1,150,150,5,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.ATTACK2 ,PATH.Amelia_Attack1,150,150,5,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.ATTACK3 ,PATH.Amelia_Attack3,128,128,4,3,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.ATTACK4 ,PATH.Amelia_Attack4,128,128,3,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.DEFENCE ,PATH.Amelia_Damaged,128,128,4,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.DAMAGE  ,PATH.Amelia_Damaged,128,128,4,2,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.JUMP    ,PATH.Amelia_Normal,128,128,4,3,8,false);
        AnimationHolder.addCharaAnimation(CharacterType.Amelia, Behavior.SQUAT   ,PATH.Amelia_Normal,128,128,4,3,8,false);

        AnimationHolder.addEffectAnimation("HIT",PATH.HIT,100,100,5,1,8,false);
        AnimationHolder.addEffectAnimation("Bloop", PATH.img+"/hizyousyoku-Sheet .png",100,100,2,2,2,true);
    }

//    public ArrayList<Image[][]> getAnimations(){
//        return movingImgs;
//    }

    public static Animation getCharaAnimation(CharacterType type, Behavior key){
        var img = table.get(type).get(key);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
    public static Animation getEffectAnimation(String effect){
        var img = effects.get(effect);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
//    public int getImgNum(){return movingImgs.size();}

    public static void addCharaAnimation(CharacterType type, Behavior key, String url, int pixelX, int pixelY, int numX, int numY, double speed, boolean loop){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        table.get(type).put(key,new Animation(im.createAnimation(),speed,loop));
    }

    public static void addEffectAnimation(String key, String url,int pixelX,int pixelY,int numX,int numY,double speed,boolean loop){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        effects.put(key,new Animation(im.createAnimation(),speed,loop));
    }
}