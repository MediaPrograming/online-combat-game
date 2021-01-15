package game.view.reducer;
/*
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.action.CombatEvent;
import game.view.state.UoPanelState;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;

public class CharacterReducer implements IReducer<UoPanelState> {
    @Override
    public ReducerBuilder<UoPanelState> apply(Action<?> action, UoPanelState init) {
        return ReducerBuilder.Create(action, init)
                .Case(CombatEvent.UPDATE_ROOM, (state, payload) -> {
                    state.characters.clear();
                    var hostName = payload.getHostName();
                    var roomName = payload.getRoomName();
                    var users = payload.getUserList();
                    for(var user : users)
                        state.characters.put(user.getId(), CharacterState
                                .newBuilder()
                                .setX(20) //初期座標
                                .setY(20) //初期座補油
                                .setId(user.getId())
                                .setBehavior(Behavior.NORMAL)
                                .build());
                    state.roomName = roomName;
                    state.hostName = hostName;
                    return state;
                }).Case(CombatEvent.MOVE, (state, payload) ->{
                    state.characters.replace(payload.getId(),payload);
                    return state;
                });
    }
}
