/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
package server.core;

import io.game.hub.messageHub.GrpcRoom;
import server.room.Room;

import java.util.*;

/**
 * This is for room management.
 * This is made by singleton.
 */
public class RoomManager {
    public final static RoomManager Instance = new RoomManager();
    private Hashtable<String, Room> rooms = new Hashtable<>();
    private RoomManager(){
    }

    /**
     * Get current room list
     * @return Hash list. (Key is RoomName, Value is Room Instance)
     */
    public Hashtable<String, Room> getRooms() { return rooms; }

    /**
     * Register new Room
     * @param room
     */
    public void pushRoom(Room room){
        if(rooms.contains(room.getRoomName())) {
            System.out.println("Roomは既に存在しています");
            return;
        }

        rooms.put(room.getRoomName(), room);
    }

    public boolean contain(String name){
        return rooms.contains(name);
    }

    /**
     * Get Room instance
     * @param name room of name
     * @return room instance
     */
    public Room getRoom(String name){
        return rooms.get(name);
    }

    public void leaveRoom(int id){
        for (var room : rooms.values())
            if(room.contain(id))
                room.removeUser(id);
    }
    public void deleteRoom(GrpcRoom gRoom){
        var roomName = gRoom.getRoomName();
        var hostName = gRoom.getHostName();
        if(rooms.contains(roomName)) {
            var room = rooms.get(gRoom.getRoomName());
            if (hostName == room.getHostName()) {
                rooms.remove(roomName);
                return;
            }
        }

        System.out.println("削除するRoomが存在しません"); //Debug print
    }

    public void deleteRoom(Room room) { rooms.remove(room.getRoomName()); }
    public void deleteRoom(String roomName){ rooms.remove(roomName); }
    /**
     * Roomの数を返す
     * @return
     */
    public int getRoomCount(){ return this.rooms.size(); }

    /**
     * ルームの全削除
     */
    public void clear(){ this.rooms.clear(); }
}

