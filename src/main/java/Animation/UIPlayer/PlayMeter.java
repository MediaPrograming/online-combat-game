package Animation.UIPlayer;

import game.config.PATH;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayMeter {
    int amount;
    int nowValue;
    int x,y;
    int width,height;
    int maxWidth,maxHeight;
    Color color,blue,yellow,red;
    GraphicsContext gc;
    boolean left;
    int margin1,margin2;
    private Path imPath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\hpバー.png");
    private Image BarFrame = new Image(imPath.toUri().toString());
    public PlayMeter(GraphicsContext gc, boolean left, int amount){
        this.gc = gc;
        this.amount = this.nowValue = amount;
        margin1 = 20;margin2=30;
        this.width =this.maxWidth= (int)gc.getCanvas().getWidth()/2-margin1-margin2;
        this.height =this.maxHeight = (int)50;
        blue = new Color(0.1,0.8,1,0.8);
        yellow = new Color(1,0.8,0.1,0.8);
        red = new Color(1,0.1,0.1,0.8);
        this.left = left;
        if(left) this.x = margin1; else this.x = (int)(gc.getCanvas().getWidth()/2)+margin2 ;
        this.y = 50;
    }
    public void setNowValue(int nowValue) { this.nowValue = nowValue; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public void updateMeter(int nowValue){
        this.nowValue = nowValue;
        setWidth((int)((double)nowValue/(double)amount*(double)maxWidth));
        if(left){
        }
        else{
            x = (int)(gc.getCanvas().getWidth()/2) + maxWidth-width+margin2;
        }
        double per = (double)nowValue/(double)amount;
        if(per>0.6) color = blue;
        else if(per<=0.6 && per>0.3) color = yellow;
        else color = red;
        gc.setFill(color);
    }

    public void play(int nowValue){
        updateMeter(nowValue);
        gc.fillRect(x,y,width,height);
        gc.drawImage(BarFrame,x+(left ? 0 : -x+gc.getCanvas().getWidth()-margin1),y-height/4,(maxWidth)*(left ? 1 : -1),(double)height*1.3);

        //debug
//        if(left) gc.fillText("x->"+x+"width->"+width+"value->"+nowValue+"amount->"+amount,gc.getCanvas().getWidth()/4,gc.getCanvas().getHeight()/2+100);
//        else gc.fillText("x->"+x+"width->"+width+"value->"+nowValue+"amount->"+amount,gc.getCanvas().getWidth()/4*3,gc.getCanvas().getHeight()/2);
    }
}
