package Animation;

import game.config.Action;
import game.config.Character;
import game.config.PATH;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.State;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/*連番ImageをArrayListに保存しておくクラス*/
public class animationHolder {
    private Hashtable<String,Hashtable<Behavior,Image[][]>> table = new Hashtable<>();
    private Hashtable<Behavior,Image[][]> gura = new Hashtable<>();
    private Hashtable<Behavior,Image[][]> ame = new Hashtable<>();
    private Hashtable<Behavior,Image[][]> ina = new Hashtable<>();
    private Hashtable<Behavior,Image[][]> calli = new Hashtable<>();
    private Hashtable<Behavior,Image[][]> kiara = new Hashtable<>();
    public animationHolder(){
        table.put(Character.Gura,gura);
        table.put(Character.Ame,ame);
        table.put(Character.Ina,ina);
        table.put(Character.Calli,calli);
        table.put(Character.Kiara,kiara);
        this.addAllAnimations();
    }

    private void addAllAnimations() {
        this.addAnimation(Character.Gura, Behavior.NORMAL ,PATH.Gura_Normal,128,128,2,1);
        this.addAnimation(Character.Kiara,Behavior.NORMAL,PATH.Kiara_Normal,200,200,5,5);
    }

//    public ArrayList<Image[][]> getAnimations(){
//        return movingImgs;
//    }

    public Image[][] getAnimation(String chara, Behavior key){
        var img = table.get(chara).get(key);
        if(img == null) System.out.println("キーが登録されていません");
        return img;
    }
//    public int getImgNum(){return movingImgs.size();}

    public void addAnimation(String chara,Behavior key,String url,int pixelX,int pixelY,int numX,int numY){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        table.get(chara).put(key,im.createAnimation());
    }
}