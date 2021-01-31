package game.view.action;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.model.ActionCreator;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;

import java.util.List;


public class RoomEvent {
    public static ActionCreator<GrpcRoom> UPDATE_ROOM = new ActionCreator<>("GET_USERS");
    public static ActionCreator<GrpcRoom> START_GAME = new ActionCreator<>("START_GAME");
    public static ActionCreator<User> CHANGE_BEFORE_CHARACTER = new ActionCreator<>("CHANGE_BEFORE_CHARACTER");
    public static ActionCreator<Behavior> CHANGE_DISPLAY_CHARACTER_BEHAVIOUR = new ActionCreator<>("CHANGE_DISPLAY_BEHAVIOUR");
    public static ActionCreator<CharacterType> CHANGE_MOUSEOVER_TYPE = new ActionCreator<>("CHANGE_MOUSEOVER_TYPE");
}
