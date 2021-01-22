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
                //observer = RoomManager.Instance.getRoom(roomName).getObserver();
                //物理オブジェクトの取得
                var characters = observer.values().stream().map(x -> x.character);
                var self = observer.get(id).character;

                var physicsObj = room.getGrounds();
                characters.forEach(PhysicsObject::fall);
                self.keycheck(value.getW(), value.getA(), value.getS(), value.getD());

                //キャラの
                var enemies = observer.entrySet()
                        .stream()
                        .filter(x -> x.getKey() != id)
                        .map(Map.Entry::getValue);
                observer.entrySet()
                        .stream()
                        .filter(x -> x.getKey() != id)
                        .map(x -> x.getValue().character)
                        .forEach(x -> PhysicsCalcUtil.isAttackHit(self, self.attack, x, x.attack));

                //キャラ同士が衝突しないように調整する　
                observer.entrySet()
                        .stream()
                        .filter(x -> x.getKey() != id)
                        .forEach(x -> PhysicsCalcUtil.CharacterCollision(self, x.getValue().character));

                //キャラと画面内オブジェクトが衝突しないように調整する　
                for (var obj : physicsObj) {
                    PhysicsCalcUtil.CharacterCollision(self, obj);
                }
                //とりあえず平面で
                for (PhysicsObject physicsObject : physicsObj) {

                    enemies.map(x -> x.character).forEach(enemy -> {
                        if (!self.intersects(physicsObject.getX() - 1 - self.getVx(), self.getY() - 2 - self.getVy(),
                                physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                                !self.intersects(enemy.getX() - 1 - self.getVx(), enemy.getY() - 2 - self.getVy(),
                                        enemy.getWidth() + 1, enemy.getHeight() + 1)) {
                            self.setRanded(false);
                        }
                    });

                }
//                if(!self.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-chare.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1)){
//                    chare.setRanded(false);
//                };
//                if(!enemy.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-enemy.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!enemy.intersects(chare.getX()-1-enemy.getVx(),chare.getY()-2-enemy.getVy(),chare.getWidth()+1,chare.getHeight()+1)){
//                    enemy.setRanded(false);
//                }
//                //System.out.println(chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1));
                self.move();
//                enemy.move();

                //通知
                double x = self.getX();
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

//                SynchronizationContext context = new SynchronizationContext(new Thread.UncaughtExceptionHandler() {
//                    @Override
//                    public void uncaughtException(Thread t, Throwable e) {
//                        System.out.println("Exception Happened");
//                    }
//                });

//                context.execute(() -> {
//                    for (UserState userState : RoomManager.Instance.getRoom(roomName).getObserver().values()) {
//                        if (userState.positionObserver != null)
//                           userState.positionObserver.onNext(characterState);
//                    }
//                });

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

    Behavior UpdateBehaviour(game.phisics.Character c) {
        Behavior behavior;
        if (c.getatk()) behavior = Behavior.ATTACK1;
        else if (c.getRanded()) behavior = Behavior.ATTACK1;
        else behavior = Behavior.NORMAL;
        return behavior;
    }

    Direction UpdateDirection(game.phisics.Character c) {
        return c.getVx() >= 0 ? Direction.RIGHT : Direction.LEFT;
    }
}
