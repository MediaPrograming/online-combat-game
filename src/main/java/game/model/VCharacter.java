package game.model;

import java.awt.*;

public class VCharacter extends Component{

    //public int Hp = 120;
    //public Direction dir;
    //public IPhysics physics = new Physics(this); // Init

    //private final Image imageIcon;
    public enum Direction {
        left, right
    }
    public static VCharacter Create(String ImagePath){
        //var character = new VCharacter(new Image(ImagePath));
//        character.setIcon(imageIcon);
//        character.setLocation(transform.x, transform.y);
        var character = new VCharacter();
        return character;
    }

}
