/**
 * @author Takuya Isaki
 * @project online-combat-game
 */
package server.service;

import game.phisics.PhysicsCalcUtil;
import game.phisics.PhysicsObject;
import io.game.hub.positionHub.*;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.stub.StreamObserver;
import server.core.RoomManager;
import java.util.Map;

import static server.util.PositionHubUtil.*;

public class PositionHubImpl extends PositionHubGrpc.PositionHubImplBase {
    @Override
    public StreamObserver<Input> sendInput(StreamObserver<CharacterState> responseObserver) {
        return new StreamObserver<Input>() {
            @Override
            public void onNext(Input value) {
                var id = value.getId();
                var user = RoomManager.Instance.getRoom(value.getRoomName());
                var roomName = user.getRoomName();
                var room = RoomManager.Instance.getRoom(roomName);
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
                var self = observer.get(id).character;
                //通知
             /*   double x = self.getX();
                double y = self.getY();
                double ax = self.getAx();
                double ay = self.getAy();
                Behavior behavior = UpdateBehaviour(self);
                Direction direction = UpdateDirection(self);
                var characterState = CharacterState
                        .newBuilder()
                        .setId(id)
                        .setX(x)
                        .setY(y)
                        .setAx(ax)
                        .setAy(ay)
                        .setTime(0)
                        .setBehavior(behavior)
                        .setDirection(direction)
                        .build();
*/
                for (var r : room.getObserver().entrySet()) {
                    try {
                        if(r.getValue().positionObserver != null)
                            r.getValue().positionObserver.onNext(characterState);
                    }catch (Exception e){
                        System.out.println(e.toString());
                    }
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
}
