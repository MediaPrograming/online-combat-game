/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
package server.room;

import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.User;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    //#region private field
    private final String roomName; //名前
    private final String hostName; //作成者
    private final int limit; //制限人数
    private final Map<Integer, StreamObserver<Message>> EventObservers;

    //#endregion

    public Room(String hostName, String roomName, int limit){
        this.hostName = hostName;
        this.roomName = roomName;
        this.limit = limit;
        this.EventObservers = new HashMap<>();
        //this.positionObservers = new HashMap<>();
    }
    //#region setter
    public boolean contain(Integer id) { return EventObservers.containsKey(id); }
    public void putUser(Integer id ,StreamObserver<Message> user){ EventObservers.put(id, user); }
    //public void putUser(){}
    public void removeUser(Integer id) { EventObservers.remove(id);}
    //#endregion

    //#region getter
    public String getHostName(){ return this.hostName;}
    public String getRoomName() { return roomName; }
    public int getCount(){ return EventObservers.size(); }
    public int getLimit() { return limit; }
    public Map<Integer, StreamObserver<Message>> getEventObservers(){ return this.EventObservers; }

    //#endregion
}