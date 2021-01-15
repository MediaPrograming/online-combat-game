package game.view.action;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.ActionCreator;
import io.game.hub.messageHub.GrpcRoomInfo;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.ResponseCode;
import io.game.hub.positionHub.CharacterState;

public class ClientEvent {
    public static final ActionCreator<ResponseCode> CREATE_ROOM = new ActionCreator("CREATE");
    public static final ActionCreator<ResponseCode> DELETE_ROOM = new ActionCreator<>("DELETE");
    public static final ActionCreator<GrpcRoomInfo> GET_ROOMS = new ActionCreator("GETROOMS");
    public static final ActionCreator<Message> STREAM_EVENT = new ActionCreator<>("STREAM_EVENT");

    public static final ActionCreator<CharacterState> MOVE = new ActionCreator<io.game.hub.positionHub.CharacterState>("MOVE_EVENT");
}
