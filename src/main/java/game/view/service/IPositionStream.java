package game.view.service;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Input;

/**
 * @project online-combat-game
 * @author Takuya Isaki on 2021/01/20
 */
public interface IPositionStream{
    void SendInput(Input input);
    void ContinueGame();
    void QuitGame();
    void Init(User user, GrpcRoom room);
}