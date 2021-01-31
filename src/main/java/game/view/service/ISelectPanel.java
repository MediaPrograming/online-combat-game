package game.view.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.grpc.Grpc;

public interface ISelectPanel {
    /**
     * Get room request
     */
    void GetRoomRequest();
    /**
     * Join by StreamEvent
     * @param user
     * @param gRoom
     */
    void JoinRequest(User user, GrpcRoom gRoom);
}
