package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.model.Unit;
public class CreateRoomReducer implements IReducer<Unit> {
    @Override
    public ReducerBuilder<Unit> apply(Action<?> action, Unit init) {
        return ReducerBuilder.Create(action, init);
    }
}
