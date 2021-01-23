package server.room;

import game.phisics.Character;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.PositionHubMessage;
import io.grpc.stub.StreamObserver;

/**
 * @author Takuya Isaki on 2021/01/19
 * @project online-combat-game
 */
public class UserState{
    public User user;
    public StreamObserver<Message> eventObserver;
    public StreamObserver<CharacterState> positionObserver;
    public StreamObserver<PositionHubMessage> positionHubMessageStreamObserver;
    public Character character;
}