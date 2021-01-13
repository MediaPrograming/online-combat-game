package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.action.RoomEvent;
import game.view.state.WaitRoomState;

public class RoomReducer implements IReducer<WaitRoomState> {
    @Override
    public ReducerBuilder<WaitRoomState> apply(Action<?> action, WaitRoomState init) {
        return ReducerBuilder.Create(action, init)
                .Case(RoomEvent.UPDATE_ROOM, (state, payload) -> {
                    return state;
                });
    }
}
