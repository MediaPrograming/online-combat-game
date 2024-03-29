/**
 * @author Takuya Isaki
 * @project online-combat-game
 */
package server.service;

import io.game.hub.positionHub.Type;
import io.game.hub.positionHub.*;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import server.core.RoomManager;
import server.util.RoomUtil;

import static server.util.PositionHubUtil.*;

public class PositionHubImpl extends PositionHubGrpc.PositionHubImplBase {
    @Override
    public StreamObserver<Input> sendInput(StreamObserver<CharacterState> responseObserver) {
        return new StreamObserver<Input>() {
            @Override
            public void onNext(Input value) {
                try {
                    var id = value.getId();
                    var user = RoomManager.getInstance().getRoom(value.getRoomName());
                    var roomName = user.getRoomName();
                    var room = RoomManager.getInstance().getRoom(roomName);
                    if (room == null) {
                        System.out.println("Room is null");
                        return;
                    }
                    var observer = room.getObserver();
                    //オブザーバーの追加
                    var state = observer.get(id);
                    state.positionObserver = responseObserver;
                    //物理オブジェクトの取得
                    CharacterUpdate(value, room, observer, id);
                } catch (Exception e) {

                    StatusRuntimeException exception = Status.INTERNAL
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException();
                    responseObserver.onError(exception);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.toString());
            }

            @Override
            public void onCompleted() {
            }
        };
    }

    @Override
    public StreamObserver<PositionHubMessage> streamEvent(StreamObserver<PositionHubMessage> responseObserver) {
        return new StreamObserver<PositionHubMessage>() {
            @Override
            public void onNext(PositionHubMessage value) {
                try {
                    var id = value.getUser().getId();
                    var room = RoomManager.getInstance().getRoom(value.getUser().getRoomName());

                    if (room == null) {
                        System.out.println("Room is null");
                        return;
                    }
                    var observer = room.getObserver();
                    //オブザーバーの追加
                    var state = observer.get(id);
                    state.positionHubMessageStreamObserver = responseObserver;

                    switch (value.getType()) {
                        case INIT: {
                            break;
                        }
                        case GAME_FINISH: {
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                } catch (Exception e) {

                    StatusRuntimeException exception = Status.INTERNAL
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException();
                    responseObserver.onError(exception);
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }
}
