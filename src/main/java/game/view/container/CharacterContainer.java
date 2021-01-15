package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Vector2;
import game.view.panel.UoPanel;
import game.view.service.ICharacter;
import game.view.state.CharaState;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

public class CharacterContainer {
    public CharacterContainer(UoPanel panel){
        panel.connect(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build(), state -> state, dispatcher ->
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
