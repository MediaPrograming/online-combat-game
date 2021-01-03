package game.view.service;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;

public interface IFetch {
    /**
     *
     */
    void GetRoomRequest();
    void CreateRoom(User user, GrpcRoom gRoom);

    /**
     * Delete room
     * @param user if This method call, You must be host in the room.
     */
    void DeleteRoom(User user);

    /**
     * Join by StreamEvent
     * @param user
     * @param roomName
     */
    void Join(User user, String roomName);

    /**
     * Leave by StreamEvent
     * @param user　User must need to have the user variable roomInfo and must be joined
     */
    void Leave(User user);

    void ShowStartPanel();
    void ShowSelectionPanel();
    void ShowCombatPanel();

}
