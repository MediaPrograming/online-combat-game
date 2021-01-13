package Animation;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/*連番ImageをArrayListに保存しておくクラス*/
public class animationHolder {
    private Hashtable<String,Image[][]> table = new Hashtable<>();
    public animationHolder(){
    }

//    public ArrayList<Image[][]> getAnimations(){
//        return movingImgs;
//    }

    public Image[][] getAnimation(String key){
        var gura = table.get(key);
        if(gura == null) System.out.println("キーが登録されていません");
        return gura;
    }
//    public int getImgNum(){return movingImgs.size();}

    public void addAnimation(String url,int pixelX,int pixelY,int numX,int numY,String key){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        table.put(key,im.createAnimation());
    }
}