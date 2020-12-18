package game.view.service;

import com.taku.util.model.Vector2;
import javafx.scene.canvas.GraphicsContext;

import java.util.Vector;

public interface ICharacter {
    public void Move(GraphicsContext context, Vector2 transform);
}
