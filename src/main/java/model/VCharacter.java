package model;

import javax.swing.*;

public class VCharacter extends Component{

    //public int Hp = 120;
    //public Direction dir;
    //public IPhysics physics = new Physics(this); // Init

    private final ImageIcon imageIcon;
    public enum Direction {
        left, right
    }
    private VCharacter(ImageIcon icon){
        this.imageIcon =icon;
    }
    public static VCharacter Create(Transform transform, ImageIcon imageIcon){
        var character = new VCharacter(imageIcon);
        character.setIcon(imageIcon);
        character.setLocation(transform.x, transform.y);
        return character;
    }

}
