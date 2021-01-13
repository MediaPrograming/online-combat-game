package game.view.reducer;
/*
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.state.CharacterState;

public class CharacterReducer implements IReducer<CharacterState> {
    @Override
    public ReducerBuilder<CharacterState> apply(Action<?> action, CharacterState init) {
        return ReducerBuilder.Create(action, init);
    }
}
