package game.view.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Vector2;
import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

public interface ICharacter {
    public void Move(GraphicsContext context, Vector2 transform);
    public void MoveRequest();
}
