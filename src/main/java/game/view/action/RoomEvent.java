package game.view.action;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.model.ActionCreator;
import io.game.hub.messageHub.GrpcRoom;
import io.grpc.Grpc;


public class RoomEvent {
    public static ActionCreator<GrpcRoom> UPDATE_ROOM = new ActionCreator<>("UPDATE_ROOM");
    public static ActionCreator<GrpcRoom> START_GAME = new ActionCreator<>("START_GAME");
}
