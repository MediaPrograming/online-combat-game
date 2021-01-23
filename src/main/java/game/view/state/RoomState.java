package game.view.state;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.GrpcRoomInfo;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;

import java.util.Hashtable;

public class RoomState {
    public boolean joined;
    public boolean isHost;
    public User currentUser;
    public GrpcRoom currentRoom;
    public GrpcRoomInfo info;
    public Hashtable<Integer, CharacterState> characters = new Hashtable<>();

}
