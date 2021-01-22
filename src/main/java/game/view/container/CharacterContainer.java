package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Vector2;
import game.store.StoreManager;
import game.view.panel.UoPanel;
import game.view.service.ICharacter;
import game.view.state.UoPanelState;
import javafx.scene.canvas.GraphicsContext;

public class CharacterContainer {
    public CharacterContainer(UoPanel panel){
//        panel.connect(new UoPanelState(StoreManager.Instance.client.user,StoreManager.Instance.client.grpcRoom),
//                state -> state, dispatcher ->
//                new ICharacter() {
//
//                    @Override
//                    public void Move(GraphicsContext context, Vector2 transform) {
//
//                    }
//
//                    @Override
//                    public void MoveRequest() {
//
//                    }
//                });
    }
}
