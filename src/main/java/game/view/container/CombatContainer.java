package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import game.store.StoreManager;
import game.util.Input;
import game.view.action.ClientEvent;
import game.view.action.CombatEvent;
import game.view.action.UIEvent;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.GrpcRoomInfo;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.Grpc;
import io.grpc.stub.StreamObserver;

import java.awt.*;
import java.util.function.Function;

/**
 * @author Takuya Isaki on 2021/01/15
 * @project online-combat-game
 */
public class CombatContainer {
//    public CombatContainer(UoPanel panel) {
//        panel.connect(new CharacterState(), state -> state, dispatcher -> new ICharacter() {
//        });
//    }
    private final PositionHubGrpc.PositionHubStub stub = StoreManager.Instance.client.positionHubStub;
    Function<IDispatcher, IPositionStream> function = dispatcher -> new IPositionStream() {
        @Override
        public void Update(GrpcRoom room) { dispatcher.dispatch(CombatEvent.UPDATE_ROOM.Create(room)); }

        @Override
        public void SendInput(Input input) {
            var request = io.game.hub.positionHub.Input.newBuilder().setUser(User.newBuilder()).build();
            //req.onNext(request);
            var req = stub.sendInput(new StreamObserver<CharacterState>() {
                @Override public void onNext(CharacterState value) { dispatcher.dispatch(CombatEvent.MOVE.Create(value));}
                @Override public void onError(Throwable t) { System.out.println(t.toString());}
                @Override public void onCompleted() { }
            });

        }
    } ;

//    StreamObserver<io.game.hub.positionHub.Input> req =  stub.sendInput(new StreamObserver<CharacterState>() {
//        @Override public void onNext(CharacterState value) { }
//        @Override public void onError(Throwable t) { System.out.println(t.toString());}
//        @Override public void onCompleted() { }
//    });
}

interface IPositionStream{
    void Update(GrpcRoom room);
    void SendInput(Input input);
}