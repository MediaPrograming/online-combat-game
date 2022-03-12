package server.util;

import io.game.hub.messageHub.GrpcRoom;

import io.game.hub.messageHub.User;
import io.game.hub.positionHub.PositionHubMessage;
import server.room.Room;

/**
 * @author Takuya Isaki on 2021/01/24
 * @project online-combat-game
 */
public class RoomUtil {
    public static GrpcRoom createGrpcRoom(Room room) {
        var grpcRoom = GrpcRoom.newBuilder()
                .setRoomName(room.getRoomName())
                .setHostName(room.getHostName())
                .setHostId(room.getHostId());

        for (var r : room.getObserver().entrySet()) {
            grpcRoom.addUser(r.getValue().user);
        }
        return grpcRoom.build();
    }

    public static PositionHubMessage createMessage(User user, GrpcRoom room , io.game.hub.positionHub.Type type, String message){
        return PositionHubMessage.newBuilder().setUser(user).setRoom(room).setType(type).setMessage(message).build();
    }
}
