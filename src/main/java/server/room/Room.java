/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
package server.room;

import game.phisics.Character;
import game.phisics.PhysicsObject;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.User;
import io.grpc.stub.StreamObserver;

import java.util.*;

public class Room {
    //#region private field

    private final String roomName; //名前
    private final String hostName; //作成者
    private final Integer hostId;
    private final int limit; //制限人数
    private final Hashtable<Integer, UserState> Observers;
    public final ArrayList<PhysicsObject> ground;
    //#endregion



    public Room(User user, GrpcRoom room, int limit){
        this.hostName = user.getName();
        this.hostId = user.getId();
        this.roomName = room.getRoomName();
        this.limit = limit;
        this.Observers = new Hashtable<>();
        this.ground = new ArrayList<>();
        //this.positionObservers = new HashMap<>();
    }
    //#region setter
    public boolean contain(Integer id) { return Observers.containsKey(id); }
    public void putUser(Integer id , UserState userState){
        Observers.put(id, userState); }
    public void removeUser(Integer id) { Observers.remove(id); }
    public void setCharacter(Integer id, Character character){ Observers.get(id).character = character;}

    public void updateRoom(Integer id, User user){ Observers.get(id).user = user; }
    //#endregion

    //#region getter
    public String getHostName(){ return this.hostName;}
    public String getRoomName() { return roomName; }
    public Integer getHostId() {return this.hostId;}
    public int getCount(){ return Observers.size(); }
    public int getLimit() { return limit; }
    public Hashtable<Integer, UserState> getObserver(){ return this.Observers; }
    public ArrayList<PhysicsObject> getGrounds(){ return ground; }
    //#endregion
}
