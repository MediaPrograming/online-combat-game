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
        return c.getVx() >= 0 ? Direction.RIGHT : Direction.LEFT;
    }

    public static void CharacterUpdate(Input input, Room room, Hashtable<Integer, UserState> table, Integer senderId) {
        var characters = table.values().stream().map(x -> x.character);
        var self = table.get(senderId).character;
        var physicsObj = room.getGrounds();
        characters.forEach(PhysicsObject::fall);
        self.keycheck(input.getW(), input.getA(), input.getS(), input.getD());

        table.entrySet()
                .stream()
                .filter(x -> x != self)
                .map(x -> x.getValue().character)
                .forEach(x -> PhysicsCalcUtil.isAttackHit(self, self.attack, x, x.attack));

        //キャラ同士が衝突しないように調整する　
        table.entrySet()
                .stream()
                .filter(x -> x.getKey() != senderId)
                .forEach(x -> PhysicsCalcUtil.CharacterCollision(self, x.getValue().character));

        //キャラと画面内オブジェクトが衝突しないように調整する　
        for (var obj : physicsObj) {
            PhysicsCalcUtil.CharacterCollision(self, obj);
        }
        //とりあえず平面で
        for (PhysicsObject physicsObject : physicsObj) {
            characters.filter(x ->x != self).forEach(enemy -> {
                if (!self.intersects(physicsObject.getX() - 1 - self.getVx(), self.getY() - 2 - self.getVy(),
                        physicsObject.getWidth() + 1, physicsObject.getHeight() + 1) &&
                        !self.intersects(enemy.getX() - 1 - self.getVx(), enemy.getY() - 2 - self.getVy(),
                                enemy.getWidth() + 1, enemy.getHeight() + 1)) {
                    self.setRanded(false);
                }
            });

        }
        self.move();
    }


}
