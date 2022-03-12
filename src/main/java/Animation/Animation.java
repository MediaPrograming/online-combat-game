package Animation;

import javafx.scene.image.Image;

public class Animation {
    private Image anim[][];
    private Double speed;
    private Boolean loop;

    public Animation(Image[][] animation, Double speed, Boolean loop){
        this.anim = animation;
        this.speed = speed;
        this.loop = loop;
    }

    public Image[][] getAnim(){
        return anim;
    }

    public Double getSpeed(){
        return speed;
    }

    public Boolean getLoop(){
        return loop;
    }

}
