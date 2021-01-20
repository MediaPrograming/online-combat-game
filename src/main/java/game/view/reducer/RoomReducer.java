package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.RoomEvent;
import game.view.action.UIEvent;
import game.view.state.WaitRoomState;

public class RoomReducer implements IReducer<WaitRoomState> {
    @Override
    public ReducerBuilder<WaitRoomState> apply(Action<?> action, WaitRoomState init) {
        return ReducerBuilder.Create(action, init)
                .Case(RoomEvent.UPDATE_ROOM, (state, payload) -> {
                    return state;
                }).Case(RoomEvent.START_GAME, ((state, room) -> {
                    var unit = new Unit();
                    StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_UO_PANEL.Create(unit));
                    return state;
                }));
    }
}
