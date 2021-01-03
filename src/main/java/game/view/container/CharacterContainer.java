package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Vector2;
import game.view.service.ICharacter;
import game.view.service.IFetch;
import game.view.state.CharacterState;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Function;

public class CharacterContainer {
    public CharacterContainer(BasePanel panel){
        panel.connect(new CharacterState(), state -> state, dispatcher ->
                new ICharacter() {
                    @Override
                    public void Move(GraphicsContext context, Vector2 transform) {
                    }
                });
    }
}
