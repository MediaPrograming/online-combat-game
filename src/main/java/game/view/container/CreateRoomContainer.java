package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.UIEvent;
import game.view.panel.CreateRoomPanel;
import game.view.service.ICreateRoom;
import game.view.state.ShowPanelState;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.ResponseCode;
import io.game.hub.messageHub.RoomMessage;
import io.game.hub.messageHub.User;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class CreateRoomContainer {
    public CreateRoomContainer(CreateRoomPanel panel){
        panel.connect(new Unit(), state -> state, dispatcher -> new ICreateRoom() {

            @Override public void CreateRoom(String roomName, String userName) {
                var client  =StoreManager.Instance.client;
                User user = User.newBuilder().setId(UUID.randomUUID().hashCode()).setName(userName).build();
                GrpcRoom room = GrpcRoom.newBuilder().setRoomName(roomName).setHostName(userName).build();
                var createRequest = RoomMessage.newBuilder().setUser(user).setRoom(room).build();
                client.stub.createRoom(createRequest, new StreamObserver<ResponseCode>() {
                    @Override public void onNext(ResponseCode value) {
                        if(value.getCode() == 200)
                            StoreManager.Instance.store.Invoke(new ShowPanelState(), UIEvent.SHOW_WAIT_ROOM_PANEL.Create(room));
                    }
                    @Override public void onError(Throwable t) { System.out.println("[ERROR]" + t.toString());}
                    @Override public void onCompleted() { }
                });
            }

            /**
             * Start画面に戻れるようにするため
             */
            @Override public void ShowStartPanel() {  StoreManager.Instance.store.Invoke(new ShowPanelState(), UIEvent.SHOW_START_PANEL.Create(new Unit())); }
        });
    }
}
