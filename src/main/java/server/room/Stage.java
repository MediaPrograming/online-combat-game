package server.room;

import game.phisics.PhysicsObject;

import java.util.ArrayList;

/**
 * @author Takuya Isaki on 2021/01/21
 * @project online-combat-game
 */
public class Stage {
    public ArrayList<PhysicsObject> objects;

    public Stage() {
        objects = new ArrayList<>();
        //物理オブジェクトの追加
        PhysicsObject flore = new PhysicsObject(50, 500, 1000, 100);
        objects.add(flore);
    }
}