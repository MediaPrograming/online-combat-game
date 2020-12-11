package Animation;

import javafx.scene.image.Image;

import java.util.ArrayList;

/*連番ImageをArrayListに保存しておくクラス*/
public class animationHolder {
    ArrayList<Image[][]> movingImgs;
    ArrayList<animationKeyHolder> keys;
    public animationHolder(){
        movingImgs = new ArrayList<Image[][]>();
        keys = new ArrayList<animationKeyHolder>();
    }

    public ArrayList<Image[][]> getAnimations(){
        return movingImgs;
    }
    public Image[][] getAnimation(String key){
        int idx = -1;
        for(animationKeyHolder k:keys){
            if(k.getKey().equals(key)){
                idx = k.getIdx();
                break;
            }
        }
        if(idx == -1) System.out.println("アニメーションが登録されていません");
        return movingImgs.get(idx);
    }
    public int getImgNum(){return movingImgs.size();}

    public void addAnimation(String url,int pixelX,int pixelY,int numX,int numY,String key){
        getAnimationFromImg im = new getAnimationFromImg(url, pixelX, pixelY, numX, numY);
        movingImgs.add(im.createAnimation());
        keys.add(new animationKeyHolder(key,movingImgs.size()));
    }
}