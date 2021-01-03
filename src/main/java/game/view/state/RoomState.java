package game.view.state;

import io.game.hub.messageHub.GrpcRoomInfo;
import io.game.hub.messageHub.ResponseCode;

public class RoomState {
    public boolean joined;
    public GrpcRoomInfo info;
}
