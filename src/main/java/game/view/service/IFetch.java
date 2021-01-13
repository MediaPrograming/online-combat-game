package game.view.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.grpc.Grpc;

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
     * @param gRoom
     */
    void JoinRequest(User user, GrpcRoom gRoom);

    /**
     * Leave by StreamEvent
     * @param user　User must need to have the user variable roomInfo and must be joined
     */
    void Leave(User user);

    void ShowStartPanel();
    void ShowSelectionPanel();
    void ShowCombatPanel();

}