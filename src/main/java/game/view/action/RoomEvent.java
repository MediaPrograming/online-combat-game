package game.view.action;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.ActionCreator;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;

import java.util.List;


public class RoomEvent {
    public static ActionCreator<GrpcRoom> UPDATE_ROOM = new ActionCreator<>("GET_USERS");
    public static ActionCreator<GrpcRoom> START_GAME = new ActionCreator<>("START_GAME");
}
