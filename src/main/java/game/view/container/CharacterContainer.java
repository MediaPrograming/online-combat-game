package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Unit;
import com.taku.util.model.Vector2;
import game.view.action.ChEvent;
import game.view.service.ICharacter;
import game.view.state.CharacterState;

import java.util.function.Function;

public class CharacterContainer {
    public CharacterContainer(BasePanel panel){
        panel.connect(new CharacterState(), mapState, mapDispatch);
    }
    Unit unit = new Unit();
    Function<CharacterState, CharacterState> mapState = (state) -> state;
    Function<IDispatcher, ICharacter> mapDispatch = dispatch ->
            new ICharacter() {
                @Override
                public void Move(Vector2 transform) {
                    dispatch.dispatch(ChEvent.Operaite.Create(transform));
                }
            };
}
