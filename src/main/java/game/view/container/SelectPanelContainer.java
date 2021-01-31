package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.util.RequestUtil;
import game.view.action.ClientEvent;
import game.view.panel.SelectPanel;
import game.view.service.ISelectPanel;
import game.view.state.RoomState;
import io.game.hub.messageHub.*;
import io.grpc.stub.StreamObserver;

import java.util.function.Function;

public class SelectPanelContainer {
    final RoomState roomState = new RoomState();
    public SelectPanelContainer(SelectPanel panel){
        panel.connect(roomState, state -> state, mapDispatch);
    }

    private MessageHubGrpc.MessageHubStub stub = StoreManager.getInstance().client.stub;

    //unit
    Unit unit = new Unit();

    UnitRequest unitRequest = UnitRequest.newBuilder().build();
    Function<IDispatcher, ISelectPanel> mapDispatch = dispatcher ->
            new ISelectPanel() {
                @Override
                public void GetRoomRequest() {
                    //非同期リクエストの送信
                    stub.getRooms(unitRequest, new StreamObserver<GrpcRoomInfo>() {
                        @Override
                        public void onNext(GrpcRoomInfo value) {
                            var rooms = value.getRoomList();
                            //#region debug print
                            System.out.println("display room");
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
                public void JoinRequest(User user, GrpcRoom gRoom){
                    StoreManager.getInstance().client.user = user;
                    StoreManager.getInstance().client.grpcRoom = gRoom;
                    var request = Message.newBuilder().setType(Type.JOIN).setUser(user).setRoom(gRoom).build();
                    var observer = RequestUtil.streamEventCreator.apply(dispatcher);
                    observer.onNext(request);
                }
            };
}
