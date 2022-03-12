package game.view.service;

import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Input;
import javafx.scene.canvas.GraphicsContext;

/**
 * @project online-combat-game
 * @author Takuya Isaki on 2021/01/20
 */
public interface IUoPanel {

    /**
     * サーバーへInputの送信
     * @param input
     */
    void SendInput(Input input);

    /**
     * キーボードを推したときにInputを変更する
     * @param key
     */
    void ChangeInputPressed(String key);

    /**
     * キーボードを話したときにInputを変更する
     * @param key
     */
    void ChangeInputReleased(String key);

    /**
     * キャラテーブルの更新
     * Warning: 元のテーブルは消去される
     * @param gc
     */
    void UpdateCharacterTable(GraphicsContext gc);

    //void UpdateHP();
    void ContinueGame();
    void QuitGame();
    void Init(User user, GrpcRoom room);
}