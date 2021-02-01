package Animation;

import game.store.StoreManager;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.nio.file.Path;
import java.nio.file.Paths;

/*画像から連番Imageを作成するクラス*/
public class getAnimationFromImg {
    private Path imagePath;
    private Image Img;
    private int q;
    private int width,height,numX,numY;
    public getAnimationFromImg(String url,int pixelX,int pixelY,int numX,int numY){
        q = 2;
        Img = new Image(StoreManager.class.getResourceAsStream(url),pixelX*numX*q,pixelY*numY*q,false,false);
        this.width = pixelX;
        this.height = pixelY;
        this.numX = numX;
        this.numY = numY;
    }

    protected Image[][] createAnimation(){
        Image MovingImg[][] = new Image[numX][numY];

        for(int i = 0; i < numY; i++){
            for(int j = 0; j < numX; j++){
                MovingImg[j][i] = new WritableImage(Img.getPixelReader(),width*q * j,height*q * i , width*q, height*q);
            }
        }
        return MovingImg;
    }
}
