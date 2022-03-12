package server.util;

import game.phisics.PhysicsCalcUtil;
import game.phisics.PhysicsObject;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.Direction;
import io.game.hub.positionHub.Input;
import server.room.Room;
import server.room.UserState;

import java.awt.*;
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
        return c.getDirection();
    }

    public static void CharacterUpdate(Input input, Room room, Hashtable<Integer, UserState> table, Integer senderId) {
        //クライアントから入力があるたびに呼び出してキャラクターの入力状態を更新したい
        var characters = table.values().stream().map(x -> x.character);
        var self = table.get(senderId).character;
        var physicsObj = room.getGrounds();
        characters.forEach(PhysicsObject::fall);
        self.setA(input.getA());self.setS(input.getS());self.setD(input.getD());self.setW(input.getW());
        self.setAttack(input.getK());

    }

    //サーバーから位置情報を送る直前に呼び出して各キャラの位置や情報を更新
    public static void Characterpositionupdate(Room room, Hashtable<Integer, UserState> table){
            var array = table.values().toArray(UserState[]::new);
            var player1 = array[0].character;
            var player2 = array[1].character;
            var physicsObj = room.getGrounds();
        player1.fall();
        player2.fall();
        player1.keycheck(player1.getW(), player1.getA(), player1.getS(), player1.getD());
        player2.keycheck(player2.getW(), player2.getA(), player2.getS(), player2.getD());
/*        table.entrySet()//keyvalue
                .stream()
                .filter(x -> x != self)
                .map(x -> x.getValue().character)
                .forEach(x -> PhysicsCalcUtil.isAttackHit(self, self.attack, x, x.attack));
*/
        PhysicsCalcUtil.isAttackHit(player1,player1.attack,player2,player2.attack);
        //キャラ同士が衝突しないように調整する　
        PhysicsCalcUtil.CharacterCollision(player1,player2);

        for (var obj : physicsObj) {
            PhysicsCalcUtil.CharacterCollision(player1, obj);
            PhysicsCalcUtil.CharacterCollision(player2, obj);
        }
        /*/とりあえず平面で
        for (PhysicsObject physicsObject : physicsObj) {
            characters.filter(x ->x != self).forEach(enemy -> {
                if (!self.intersects(physicsObject.getX() - 1 - self.getVx(), self.getY() - 2 - self.getVy(),
                        physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                        !self.intersects(enemy.getX() - 1 - self.getVx(), enemy.getY() - 2 - self.getVy(),
                                enemy.getWidth() + 1, enemy.getHeight() + 1)) {
                    self.setRanded(false);
                }
            });

        }*/
        int p1=1,p2=1;
        for (PhysicsObject physicsObject : physicsObj) {
            if (player1.intersects(physicsObject.getX() - 1 - player1.getVx(), physicsObject.getY() - 2 - player1.getVy(),
                    physicsObject.getWidth() + 1, physicsObject.getHeight() + 1)){
                p1=0;
            }
            if (player2.intersects(physicsObject.getX() - 1 - player2.getVx(), physicsObject.getY() - 2 - player2.getVy(),
                    physicsObject.getWidth() + 1, physicsObject.getHeight() + 1)) {
                p2=0;
            }
        }
        if(player1.intersects(player2.getX() - 1 - player1.getVx(), player2.getY() - 2 - player1.getVy(),
                player2.getWidth() + 1, player2.getHeight() + 1)){
                p1=0;
        }
        if(player2.intersects(player1.getX() - 1 - player2.getVx(), player1.getY() - 2 - player2.getVy(),
                player1.getWidth() + 1, player1.getHeight() + 1)){
                p2=0;
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
