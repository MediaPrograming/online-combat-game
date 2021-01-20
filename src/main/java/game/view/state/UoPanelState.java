package game.view.state;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import io.grpc.Grpc;

import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class UoPanelState {
    public final User self;
    public final GrpcRoom room;
    public BlockingQueue<CharacterState> stateBlockingQueue;
    public UoPanelState(User self, GrpcRoom room){
        this.self = self;
        this.room = room;
        stateBlockingQueue = new LinkedBlockingDeque<>();
    }
}
