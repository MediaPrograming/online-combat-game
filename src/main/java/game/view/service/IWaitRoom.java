package game.view.service;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
public interface IWaitRoom {
    /*
        準備完了かどうか
     */
    void IsReadyRequest(boolean isOk, User user, GrpcRoom room);
    void SetCharacterRequest(int index, User user, GrpcRoom room);
    /*
        Characterセレクト画面に戻る
     */
    void showBackPanel(User user, GrpcRoom room);

    /*
     ゲーム開始のリクエストの送信(Hostのみ実行可能)
     */
    void GameStartRequest(User user, GrpcRoom room);

    void GetRoomUserRequest(User user);
}
