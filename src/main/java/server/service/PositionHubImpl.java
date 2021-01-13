/**
 * @author Takuya Isaki
 * @project online-combat-game
 */
package server.service;

import game.phisics.Physics;
import game.phisics.PhysicsCalcUtil;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import io.game.hub.positionHub.Position;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.stub.StreamObserver;
import javafx.geometry.Pos;
import server.core.RoomManager;
import server.room.Room;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class PositionHubImpl extends PositionHubGrpc.PositionHubImplBase {
    @Override
    public StreamObserver<Input> sendInput(StreamObserver<CharacterState> responseObserver) {
        return new StreamObserver<Input>() {
            @Override
            public void onNext(Input value) {
                var user = value.getUser();
                var id = user.getId();
                var name = user.getName();
                var roomName = user.getRoomInfo().getRoomName();
                var room = RoomManager.Instance.getRoom(roomName);
                //Roomにオブザーバーが登録されていなければ追加する
                if(room.PositionObservers.contains(id)){
                    //init
                    room.PositionObservers.put(id, responseObserver);
                }

                //物理オブジェクトの取得
                var characters = room.getCharacters();
                var self = characters.get(id);

                var physicsObj = room.getGrounds();
                for(var s : characters.values()){ s.fall(); }
                self.keycheck(value.getW(), value.getA(), value.getS(), value.getD());

                //PhysicsCalcUtil.isAttackHit(,chareattack,enemy,enemyattack);
                //キャラ同士が衝突しないように調整する　
                characters.entrySet()
                        .stream()
                        .filter(x -> x.getKey() != id)
                        .forEach(x ->PhysicsCalcUtil.CharacterCollision(self, x.getValue()));
                //キャラと画面内オブジェクトが衝突しないように調整する　
                for(var obj : physicsObj){PhysicsCalcUtil.CharacterCollision(self, obj);}
                var state = CharacterState.newBuilder().build();
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
