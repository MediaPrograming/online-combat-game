package game.view.state;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;


public class WaitRoomState {
    public boolean isHost;
    public User userInfo;
    public GrpcRoom room;
}
