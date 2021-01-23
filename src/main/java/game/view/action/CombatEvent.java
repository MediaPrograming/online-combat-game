package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.positionHub.CharacterState;

/**
 * @author Takuya Isaki on 2021/01/15
 * @project online-combat-game
 */
public class CombatEvent {
    public static ActionCreator<GrpcRoom> UPDATE_ROOM = new ActionCreator<>("UPDATE_ROOM_COMBAT");
    public static final ActionCreator<CharacterState> MOVE = new ActionCreator<io.game.hub.positionHub.CharacterState>("MOVE_EVENT");
}
