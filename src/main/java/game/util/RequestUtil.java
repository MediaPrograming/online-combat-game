package game.util;

import com.taku.util.flux.model.Store;
import com.taku.util.flux.service.IDispatcher;
import game.store.StoreManager;
import game.view.action.AnimationEvent;
import game.view.action.ClientEvent;
import io.game.hub.messageHub.GrpcRoomInfo;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.MessageHubGrpc;
import io.game.hub.messageHub.UnitRequest;
import io.game.hub.positionHub.PositionHubGrpc;
import io.game.hub.positionHub.PositionHubMessage;
import io.grpc.stub.StreamObserver;

import java.util.function.Function;

/**
 * @author Takuya Isaki on 2021/01/19
 * @project online-combat-game
 */
public class RequestUtil {
    private static MessageHubGrpc.MessageHubStub stub = StoreManager.Instance.client.stub;
    public static Function<IDispatcher, StreamObserver<Message>> streamEventCreator = (dispatcher) -> stub.streamEvent(new StreamObserver<Message>() {
        @Override public void onNext(Message value) { dispatcher.dispatch(ClientEvent.STREAM_EVENT.Create(value)); }
        @Override public void onError(Throwable t) { System.out.println(t.toString());}
        @Override public void onCompleted() { }
    });

    private static PositionHubGrpc.PositionHubStub positionHubStub = StoreManager.Instance.client.positionHubStub;
    public static Function<IDispatcher, StreamObserver<PositionHubMessage>> streamPositionCreator = (dispatcher) -> positionHubStub.streamEvent(new StreamObserver<PositionHubMessage>() {
        @Override public void onNext(PositionHubMessage value) { dispatcher.dispatch(AnimationEvent.STREAM_EVENT.Create(value)); }
        @Override public void onError(Throwable t) { System.out.println(t.toString());}
        @Override public void onCompleted() { }
    });

}