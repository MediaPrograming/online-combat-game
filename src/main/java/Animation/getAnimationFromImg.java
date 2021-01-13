package Animation;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.nio.file.Path;
import java.nio.file.Paths;

/*画像から連番Imageを作成するクラス*/
public class getAnimationFromImg {
    Path imagePath;
    Image Img;
    int width,height,numX,numY;
    public getAnimationFromImg(String url,int pixelX,int pixelY,int numX,int numY){
        imagePath = Paths.get(url);
        Img = new Image(imagePath.toUri().toString());
        this.width = pixelX;
        this.height = pixelY;
        this.numX = numX;
        this.numY = numY;
    }

    protected Image[][] createAnimation(){
        Image MovingImg[][] = new Image[numX][numY];

        for(int i = 0; i < numY; i++){
            for(int j = 0; j < numX; j++){
                MovingImg[j][i] = new WritableImage(Img.getPixelReader(),width * j,height * i , width, height);
            }
        }
        return MovingImg;
    }
}
