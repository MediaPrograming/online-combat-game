package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Vector2;
import game.view.service.ICharacter;
import game.view.state.CharaState;
import javafx.scene.canvas.GraphicsContext;

public class CharacterContainer {
    public CharacterContainer(BasePanel panel){
        panel.connect(new CharaState(), state -> state, dispatcher ->
                new ICharacter() {
                    @Override
                    public void Move(GraphicsContext context, Vector2 transform) {
                    }
                });
    }
}
