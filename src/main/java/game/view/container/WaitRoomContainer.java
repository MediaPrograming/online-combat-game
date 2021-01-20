package game.view.container;

import game.store.StoreManager;
import game.util.RequestUtil;
import game.view.action.RoomEvent;
import game.view.panel.WaitRoomPanel;
import game.view.service.IWaitRoom;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.*;
import io.grpc.stub.StreamObserver;

/**
 * @author Takuya Isaki on 2021/01/19
 * @project online-combat-game
 */
public class WaitRoomContainer {
    public WaitRoomContainer(WaitRoomPanel panel){
        var state = new WaitRoomState(StoreManager.Instance.client.user, StoreManager.Instance.client.grpcRoom);
        var stub = StoreManager.Instance.client.stub;
        panel.connect(state, roomState -> roomState, dispatcher -> new IWaitRoom() {
            @Override
            public void IsReadyRequest(boolean isOk, User user, GrpcRoom room) {
                var updateUser = user.toBuilder().setIsReady(isOk).build();
                var message = Message.newBuilder().setUser(updateUser).setType(Type.UPDATE).setRoom(room).build();
                var observer = RequestUtil.streamEventCreator.apply(dispatcher);
                observer.onNext(message);
            }

            @Override
            public void SetCharacterRequest(int index, User user, GrpcRoom room) {
                var updateUser = user.toBuilder().setCharacterType(CharacterType.forNumber(index)).build();
                var message = Message.newBuilder().setUser(updateUser).setType(Type.UPDATE).setRoom(room).build();
                var observer = RequestUtil.streamEventCreator.apply(dispatcher);
                observer.onNext(message);
            }

            @Override
            public void showBackPanel(User user, GrpcRoom room) {
                //joinを取り消す
                var message = Message.newBuilder().setUser(user).setType(Type.LEAVE).setRoom(room).build();
                var observer = RequestUtil.streamEventCreator.apply(dispatcher);
                observer.onNext(message);
            }

            @Override
            public void GameStartRequest(User user, GrpcRoom room) {
                System.out.println("start");
                var message = Message.newBuilder().setUser(user).setRoom(room).setType(Type.GAME_START).build();
                var observer = RequestUtil.streamEventCreator.apply(dispatcher);
                observer.onNext(message);
            }

            @Override
            public void GetRoomUserRequest(User user) {
                stub.getUsers(user, new StreamObserver<GrpcRoom>() {
                    @Override
                    public void onNext(GrpcRoom value) {
                        var rooms = value;
                        System.out.println("ルームの一覧を表示します");
                        int i = 0;
                        for (var r: rooms.getUserList()) System.out.println( "[" + i++ + "]" + r);
                        dispatcher.dispatch(RoomEvent.UPDATE_ROOM.Create(value));
                    }
                    @Override public void onError(Throwable t) { System.out.println(t.toString());}
                    @Override public void onCompleted() { }
                });
            }
        });
    }


}
