package Animation;

import game.config.Character;
import game.config.PATH;
import io.game.hub.positionHub.Behavior;

import java.util.Hashtable;

/*連番ImageをArrayListに保存しておくクラス*/
public class AnimationHolder {
    private static Hashtable<String,Hashtable<Behavior,Animation>> table = new Hashtable<>();
    private static Hashtable<Behavior,Animation> gura = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ame = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ina = new Hashtable<>();
    private static Hashtable<Behavior,Animation> calli = new Hashtable<>();
    private static Hashtable<Behavior,Animation> kiara = new Hashtable<>();
    private static Hashtable<String,Animation> effects = new Hashtable<>();
    public AnimationHolder(){
        table.put(Character.Gura,gura);
        table.put(Character.Ame,ame);
        table.put(Character.Ina,ina);
        table.put(Character.Calli,calli);
        table.put(Character.Kiara,kiara);
        this.addAllAnimations();
    }

    private static void addAllAnimations() {
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.NORMAL ,PATH.Gura_Normal,128,128,4,2,2,true);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.RUN ,PATH.Gura_Run,128,128,4,2,4,true);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.DAMAGE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.JUMP ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Gura, Behavior.SQUAT ,PATH.Gura_Normal,128,128,4,2,2,false);

        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Kiara, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2,false);

        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ina, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2,false);

        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Calli, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2,false);

        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.NORMAL  ,PATH.Kiara_Normal,200,200,5,5,8,true );
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.RUN     ,PATH.Kiara_Run,   160,160,8,1,8,true );
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.ATTACK1 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.ATTACK2 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.ATTACK3 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.ATTACK4 ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.DEFENCE ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.DAMAGE  ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.JUMP    ,PATH.Gura_Normal,128,128,4,2,2,false);
        AnimationHolder.addCharaAnimation(Character.Ame, Behavior.SQUAT   ,PATH.Gura_Normal,128,128,4,2,2,false);

//        AnimationHolder.addEffectAnimation("HIT",PATH.Effect_HIT,100,100,5,1,8,false);
    }

//    public ArrayList<Image[][]> getAnimations(){
//        return movingImgs;
//    }

    public static Animation getCharaAnimation(String chara, Behavior key){
        var img = table.get(chara).get(key);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
    public static Animation getEffectAnimation(String effect){
        var img = effects.get(effect);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
//    public int getImgNum(){return movingImgs.size();}

    public static void addCharaAnimation(String chara,Behavior key,String url,int pixelX,int pixelY,int numX,int numY,int speed,boolean loop){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        table.get(chara).put(key,new Animation(im.createAnimation(),speed,loop));
    }

    public static void addEffectAnimation(String key, String url,int pixelX,int pixelY,int numX,int numY,int speed,boolean loop){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        effects.put(key,new Animation(im.createAnimation(),speed,loop));
    }
}