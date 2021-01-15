package game.view.reducer;
/*
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.state.CharaState;

public class CharacterReducer implements IReducer<CharaState> {
    @Override
    public ReducerBuilder<CharaState> apply(Action<?> action, CharaState init) {
        return ReducerBuilder.Create(action, init);
    }
}
