/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
package server.room;

import game.phisics.Character;
import game.phisics.PhysicsObject;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Position;
import io.grpc.stub.StreamObserver;

import java.util.*;

public class Room {
    //#region private field
    private final String roomName; //名前
    private final String hostName; //作成者
    private final int limit; //制限人数
    private final Map<Integer, StreamObserver<Message>> EventObservers;
    public final Hashtable<Integer, StreamObserver<CharacterState>> PositionObservers;
    private final Map<Integer, Character> characters;
    private final ArrayList<PhysicsObject> ground;
    //#endregion

    public Room(String hostName, String roomName, int limit){
        this.hostName = hostName;
        this.roomName = roomName;
        this.limit = limit;
        this.EventObservers = new HashMap<>();
        this.characters = new HashMap<>();
        this.ground = new ArrayList<>();
        this.PositionObservers = new Hashtable<>();
        //this.positionObservers = new HashMap<>();
    }
    //#region setter
    public boolean contain(Integer id) { return EventObservers.containsKey(id); }
    public void putUser(Integer id ,StreamObserver<Message> user){ EventObservers.put(id, user); }
    public void removeUser(Integer id) { EventObservers.remove(id);}
    public void putCharacter(Integer id, Character character){ characters.put(id, character);}


    //#endregion

    //#region getter
    public String getHostName(){ return this.hostName;}
    public String getRoomName() { return roomName; }
    public int getCount(){ return EventObservers.size(); }
    public int getLimit() { return limit; }
    public Map<Integer, StreamObserver<Message>> getEventObservers(){ return this.EventObservers; }
    public Map<Integer, Character> getCharacters(){ return characters; }
    public ArrayList<PhysicsObject> getGrounds(){ return ground; }
    //#endregion
}