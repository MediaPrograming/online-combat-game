package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Store;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.UIEvent;
import game.view.panel.CreateRoomPanel;
import game.view.service.ICreateRoom;
import io.game.hub.messageHub.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class CreateRoomContainer {
    public CreateRoomContainer(CreateRoomPanel panel){
        Unit unit = new Unit();
        panel.connect(unit, state -> state, dispatcher -> new ICreateRoom() {

            @Override public void CreateRoomRequest(String roomName, String userName) {
                var client = StoreManager.Instance.client;
                var id = UUID.randomUUID().hashCode();
                User user = User.newBuilder()
                        .setId(id)
                        .setName(userName)
                        .setRoomName(roomName)
                        .setIsReady(true)
                        .setCharacterType(CharacterType.forNumber(0))
                        .build();
                GrpcRoom room = GrpcRoom.newBuilder()
                        .setRoomName(roomName)
                        .setHostId(id)
                        .setHostName(userName)
                        .addUser(user)
                        .build();
                var createRequest = RoomMessage.newBuilder().setUser(user).setRoom(room).build();
                client.stub.createRoom(createRequest, new StreamObserver<ResponseCode>() {
                    @Override public void onNext(ResponseCode value) {
                        if(value.getCode() == 200) {
                            StoreManager.Instance.client.grpcRoom = room;
                            StoreManager.Instance.client.user = user;
                            StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_WAIT_ROOM_PANEL.Create(unit));
                        }else{
                            System.out.println("[" + value.getCode() + "]" + value.getMessage());
                        }
                    }
                    @Override public void onError(Throwable t) { System.out.println("[ERROR]" + t.toString());}
                    @Override public void onCompleted() { }
                });
            }

            /**
             * Start画面に戻れるようにするため
             */
            @Override public void ShowStartPanel() {  StoreManager.Instance.store.Invoke(new Unit(), UIEvent.SHOW_START_PANEL.Create(new Unit())); }
        });
    }
}
