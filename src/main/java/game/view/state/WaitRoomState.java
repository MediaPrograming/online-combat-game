package game.view.state;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.grpc.Grpc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Takuya Isaki on 2021/01/19
 * @project online-combat-game
 */
public class WaitRoomState {
    public final boolean isHost;
    public User self;
    public GrpcRoom currentRoom;
    public List<String> characters = new ArrayList<>();
    public WaitRoomState(User user, GrpcRoom gRoom){
        this.self = user;
        currentRoom = gRoom;
        isHost = self.getId() == currentRoom.getHostId();
    }
}
