package server.core;

import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Direction;
import io.game.hub.positionHub.Type;
import javafx.collections.ObservableArray;
import server.room.Room;
import server.room.UserState;
import server.util.RoomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import static server.util.PositionHubUtil.Characterpositionupdate;

/**
 * @author Takuya Isaki on 2021/01/22
 * @project online-combat-game
 */
public class ServerTimer {
    private long time = 0;

    public ServerTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time += 0.03;
                if (rooms.isEmpty()) return;

                synchronized (this) {
                    try {
                        for (var room : rooms) {
                            Send(room);
                        }
                    }catch (Exception e){
                        System.out.println(e.toString());
                    }
                }
            }
        }, 1000, 30);
    }

    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void deleteRoom(Room room) {
        this.rooms.remove(room);
    }

    private void Send(Room room) {
        var observer = room.getObserver();
        Characterpositionupdate(room, observer);

        for (var state : observer.values()) {
            var c = state.character;
            double x = c.getX();
            double y = c.getY();
            double ax = c.getAx();
            double ay = c.getAy();
            double atkX =c.getAttack().getX();
            double atkY =c.getAttack().getY();
            double atkw=c.getAttack().getWidth();
            double atkh=c.getAttack().getHeight();
            int hp = c.getHP();
            Behavior behavior = c.getAction();
            Direction direction = c.getDirection();
            var characterState = CharacterState
                    .newBuilder()
                    .setId(state.user.getId())
                    .setX(x)
                    .setY(y)
                    .setAx(ax)
                    .setAy(ay)
                    .setHP(hp)
                    .setTime(time)
                    .setAtkX(atkX)
                    .setAtkY(atkY)
                    .setBehavior(behavior)
                    .setDirection(direction)
                    .setAtkW(atkw)
                    .setAtkH(atkh)
                    .build();


            for (var r : observer.entrySet()) {
                try {
                    if (r.getValue().positionObserver != null)
                        r.getValue().positionObserver.onNext(characterState);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }

        //HPが0の時
        for (var v : observer.values()) {
            if (v.character.getHP() <= 0) {
                for (var r : observer.entrySet()) {
                    try {
                        if (r.getValue().positionHubMessageStreamObserver != null)
                            r.getValue().positionHubMessageStreamObserver.onNext(RoomUtil.createMessage(v.user, RoomUtil.createGrpcRoom(room), Type.GAME_FINISH, ""));
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
                this.deleteRoom(room);
            }
        }
    }
}
