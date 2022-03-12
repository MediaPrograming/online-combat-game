package game.view.state;

import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
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
    public User beforeSelf, beforeEnemy;
    public CharacterType mouseOverCharacter = CharacterType.UNRECOGNIZED;
    public CharacterState displayState = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
    public List<String> characters = new ArrayList<>();
    public WaitRoomState(User user, GrpcRoom gRoom){
        this.self = user;
        this.beforeSelf = user;
        this.beforeEnemy = null;
        currentRoom = gRoom;
        isHost = self.getId() == currentRoom.getHostId();
    }
}
