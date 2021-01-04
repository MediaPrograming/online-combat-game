package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.function.Function.Function2;
import game.view.action.UIEvent;
import game.view.state.CharacterState;
import game.view.state.StartState;

import java.util.function.Consumer;

public class CharacterReducer implements IReducer<CharacterState> {
    @Override
    public ReducerBuilder<CharacterState> apply(Action<?> action, CharacterState init) {
        return ReducerBuilder.Create(action, init);
    }
}
