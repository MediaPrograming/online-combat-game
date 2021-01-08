package game.view.state;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import io.game.hub.messageHub.GrpcRoomInfo;

public class RoomState {
    public boolean joined;
    public GrpcRoomInfo info;
}
