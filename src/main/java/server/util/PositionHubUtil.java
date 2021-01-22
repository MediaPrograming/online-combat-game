package server.util;

import game.phisics.PhysicsCalcUtil;
import game.phisics.PhysicsObject;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.Direction;
import io.game.hub.positionHub.Input;
import server.room.Room;
import server.room.UserState;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Takuya Isaki on 2021/01/22
 * @project online-combat-game
 */
public class PositionHubUtil {
    public static Behavior UpdateBehaviour(game.phisics.Character c) {
        Behavior behavior;
        if (c.getatk()) behavior = Behavior.ATTACK1;
        else if (c.getRanded()) behavior = Behavior.ATTACK1;
        else behavior = Behavior.NORMAL;
        return behavior;
    }

    public static Direction UpdateDirection(game.phisics.Character c) {
        return c.getVx() >= 0 ? Direction.RIGHT : Direction.LEFT;
    }

    public static void CharacterUpdate(Input input, Room room, Hashtable<Integer, UserState> table, Integer senderId) {
        //クライアントから入力があるたびに呼び出してキャラクターの入力状態を更新
        var characters = table.values().stream().map(x -> x.character);
        var self = table.get(senderId).character;
        var physicsObj = room.getGrounds();
        characters.forEach(PhysicsObject::fall);
        self.setA()=input.getA();self.setS()=input.getS();self.setD()=input.getD;self.setW()=input.getW();

    }

    //サーバーから位置情報を送る直前に呼び出して各キャラの位置や情報を更新
    public void Characterposistionupdate(Input input, Room room, Hashtable<Integer, UserState> table){
            var array=tabel.value().toArray(Userstate[]::new);
            var player1 = array[0].character;
            var player2 = array[1].character;
            var physicsObj = room.getGrounds();
        player1.keycheck(player1.getW(), player1.getA(), player1.getS(), player1.getD());
        player2.keycheck(player2.getW(), player2.getA(), player2.getS(), player2.getD());

/*        table.entrySet()//keyvalue
                .stream()
                .filter(x -> x.getKey() != senderId)
                .map(x -> x.getValue().character)
                .forEach(x -> PhysicsCalcUtil.isAttackHit(self, self.attack, x, x.attack));
*/
        PositionHubUtil.isAttackHit(player1,player1.attack,player2,player2.attack);
        //キャラ同士が衝突しないように調整する　
        table.entrySet()
                .stream()
                .filter(x -> x.getKey() != senderId)
                .forEach(x -> PhysicsCalcUtil.CharacterCollision(self, x.getValue().character));

        for (var obj : physicsObj) {
            PhysicsCalcUtil.CharacterCollision(self, obj);
        }
        /*/とりあえず平面で
        for (PhysicsObject physicsObject : physicsObj) {
            characters.forEach(enemy -> {
                if (!self.intersects(physicsObject.getX() - 1 - self.getVx(), self.getY() - 2 - self.getVy(),
                        physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                        !self.intersects(enemy.getX() - 1 - self.getVx(), enemy.getY() - 2 - self.getVy(),
                                enemy.getWidth() + 1, enemy.getHeight() + 1)) {
                    self.setRanded(false);
                }
            });

        }*/
        int p1=1,p1=1;
        for (PhysicsObject physicsObject : physicsObj) {
            if (!player1.intersects(physicsObject.getX() - 1 - player1.getVx(), player1.getY() - 2 - player1.getVy(),
                    physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                    !player1.intersects(player2.getX() - 1 - player1.getVx(), player2.getY() - 2 - player1.getVy(),
                            player2.getWidth() + 1, player2.getHeight() + 1)) {
            }else{
                p1=0;
            }
            if (!player2.intersects(physicsObject.getX() - 1 - player2.getVx(), player2.getY() - 2 - player2.getVy(),
                    physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                    !player2.intersects(player1.getX() - 1 - player2.getVx(), player1.getY() - 2 - player2.getVy(),
                            player1.getWidth() + 1, player1.getHeight() + 1)) {
            }else{
                p2=0;
            }
        }
        if(p1==1){
            player1.setRanded(false);
        }
        if(p2==1){
            player2.setRanded(false);
        }
        //キャラと画面内オブジェクトが衝突しないように調整する　

        player1.move();
        player2.move();

    }

}
