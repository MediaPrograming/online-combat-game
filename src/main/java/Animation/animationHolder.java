package Animation;

import game.config.Character;
import game.config.PATH;
import io.game.hub.positionHub.Behavior;

import java.util.Hashtable;

/*連番ImageをArrayListに保存しておくクラス*/
public class animationHolder {
    private static Hashtable<String,Hashtable<Behavior,Animation>> table = new Hashtable<>();
    private static Hashtable<Behavior,Animation> gura = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ame = new Hashtable<>();
    private static Hashtable<Behavior,Animation> ina = new Hashtable<>();
    private static Hashtable<Behavior,Animation> calli = new Hashtable<>();
    private static Hashtable<Behavior,Animation> kiara = new Hashtable<>();
    public animationHolder(){
        table.put(Character.Gura,gura);
        table.put(Character.Ame,ame);
        table.put(Character.Ina,ina);
        table.put(Character.Calli,calli);
        table.put(Character.Kiara,kiara);
        this.addAllAnimations();
    }

    private static void addAllAnimations() {
        animationHolder.addAnimation(Character.Gura, Behavior.NORMAL ,PATH.Gura_Normal,128,128,2,1,2,true);
        animationHolder.addAnimation(Character.Kiara,Behavior.NORMAL,PATH.Kiara_Normal,200,200,5,5,3,true);
    }

//    public ArrayList<Image[][]> getAnimations(){
//        return movingImgs;
//    }

    public static Animation getAnimation(String chara, Behavior key){
        var img = table.get(chara).get(key);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
//    public int getImgNum(){return movingImgs.size();}

    public static void addAnimation(String chara,Behavior key,String url,int pixelX,int pixelY,int numX,int numY,int speed,boolean loop){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        table.get(chara).put(key,new Animation(im.createAnimation(),speed,loop));
    }
}