package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Vector2;
import game.view.panel.UoPanel;
import game.view.service.ICharacter;
import game.view.state.UoPanelState;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class CharacterContainer {
    public CharacterContainer(UoPanel panel){
        panel.connect(new UoPanelState(), state -> state, dispatcher ->
                new ICharacter() {
                    @Override
                    public void Move(GraphicsContext context, Vector2 transform) {
                    }

                    @Override
                    public void MoveRequest() {

                    }
                });
    }
}
