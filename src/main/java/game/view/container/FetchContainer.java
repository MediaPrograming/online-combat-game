package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.ClientEvent;
import game.view.action.UIEvent;
import game.view.panel.SelectPanel;
import game.view.service.IFetch;
import game.view.state.RoomState;
import io.game.hub.messageHub.*;
import io.grpc.stub.StreamObserver;

import java.util.function.Function;

public class FetchContainer {
    public FetchContainer(SelectPanel panel){
        panel.connect(new RoomState(), state -> state, mapDispatch);
    }
    MessageHubGrpc.MessageHubStub stub = StoreManager.Instance.client.stub;

    //unit
    Unit unit = new Unit();

    UnitRequest unitRequest = UnitRequest.newBuilder().build();
    Function<IDispatcher, IFetch> mapDispatch = dispatcher ->
            new IFetch() {
                @Override
                public void GetRoomRequest() {
                    //非同期リクエストの送信
                    stub.getRooms(unitRequest, new StreamObserver<GrpcRoomInfo>() {
                        @Override
                        public void onNext(GrpcRoomInfo value) {
                            var rooms = value.getRoomList();
                            //#region debug print
                            System.out.println("ルームの一覧を表示します");
                            int i = 0;
                            for (var r: rooms){
                                System.out.println( "[" + i++ + "]" + r);
                            }
                            //#endregion
                            dispatcher.dispatch(ClientEvent.GET_ROOMS.Create(value));
                        }
                        @Override public void onError(Throwable t) { System.out.println(t.toString());}
                        @Override public void onCompleted() { }
                    });
                }

                @Override
                public void CreateRoom(User user, GrpcRoom gRoom) {
                    var request = RoomMessage.newBuilder().setRoom(gRoom).setUser(user).build();
                    stub.createRoom(request, new StreamObserver<ResponseCode>() {
                        @Override public void onNext(ResponseCode value) { dispatcher.dispatch(ClientEvent.CREATE_ROOM.Create(value)); }
                        @Override public void onError(Throwable t) {System.out.println(t.toString()); }
                        @Override public void onCompleted() { }
                    });
                }

                @Override
                public void DeleteRoom(User user) {
                    var request = RoomMessage.newBuilder().setUser(user).build();
                    stub.deleteRoom(request, new StreamObserver<ResponseCode>() {
                        @Override public void onNext(ResponseCode value) { dispatcher.dispatch(ClientEvent.DELETE_ROOM.Create(value)); }
                        @Override public void onError(Throwable t) { System.out.println(t.toString());}
                        @Override public void onCompleted() {}
                    });
                }

                @Override
                public void JoinRequest(User user, GrpcRoom gRoom){
                    var request = Message.newBuilder().setType(Type.JOIN).setMessage(gRoom.getRoomName()).setUser(user).build();
                    var observer = streamEventCreator.apply(dispatcher);
                    observer.onNext(request);
                }

                @Override
                public void Leave(User user){
                    var request = Message.newBuilder().setType(Type.LEAVE).setUser(user).build();
                    var observer = streamEventCreator.apply(dispatcher);
                    observer.onNext(request);
                }
                @Override public void ShowStartPanel() { dispatcher.dispatch(UIEvent.SHOW_START_PANEL.Create(unit)); }
                @Override public void ShowSelectionPanel() { dispatcher.dispatch(UIEvent.SHOW_SELECTION_PANEL.Create(unit)); }
                @Override public void ShowCombatPanel() { dispatcher.dispatch(UIEvent.SHOW_COMBAT_PANEL.Create(unit)); }
            };

      Function<IDispatcher, StreamObserver<Message>> streamEventCreator = (dispatcher) ->  stub.streamEvent(new StreamObserver<Message>() {
              @Override public void onNext(Message value) { dispatcher.dispatch(ClientEvent.STREAM_EVENT.Create(value)); }
              @Override public void onError(Throwable t) { System.out.println(t.toString());}
              @Override public void onCompleted() { }
          });

}
