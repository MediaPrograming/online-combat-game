package game.util;

import game.model.VCharacter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CharacterContainer {

    VCharacter c1, c2;
    void Initialize(String imagePath){
        c1 = new VCharacter();
        c2 = new VCharacter();
    }
}
