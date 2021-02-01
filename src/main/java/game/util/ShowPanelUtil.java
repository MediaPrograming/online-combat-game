package game.util;

import game.store.StoreManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Takuya Isaki on 2021/01/31
 * @project online-combat-game
 */

/**
 * パネル表示用静的関数クラス
 */
public final class ShowPanelUtil {
    public static void ShowStartPanel() {
        Platform.runLater(() ->{
        try {
            StoreManager.getInstance().store.clearView();
            Time.Instance.clearUpdates();
            Parent parent = FXMLLoader.load(StoreManager.class.getResource("../view/start.fxml"));
            var scene = new Scene(parent);
            StoreManager.stage.setScene(scene);
            StoreManager.stage.show();
            scene.setOnKeyPressed(k -> Time.Instance.KeyPressed(k));
            scene.setOnKeyReleased(k -> Time.Instance.KeyReleased(k));
        } catch (Exception err) {
            System.out.println("[ERROR]" + err.toString());
        }
        });
    }

    public static void ShowSelectionPanel() {
        Platform.runLater(() -> {
            try {
                StoreManager.getInstance().store.clearView();
                Time.Instance.clearUpdates();
                Parent parent = FXMLLoader.load(StoreManager.class.getResource("../view/select.fxml"));
                var scene = new Scene(parent);
                StoreManager.stage.setScene(scene);
                StoreManager.stage.show();
                scene.setOnKeyPressed(k -> Time.Instance.KeyPressed(k));
                scene.setOnKeyReleased(k -> Time.Instance.KeyReleased(k));
            } catch (Exception err) {
                System.out.println("[ERROR]" + err.toString());
            }
        });
    }

    public static void ShowCreateRoomPanel() {
        Platform.runLater(() -> {
            try {
                StoreManager.getInstance().store.clearView();
                Time.Instance.clearUpdates();
                Parent parent = FXMLLoader.load(StoreManager.class.getResource("../view/createRoom.fxml"));
                var scene = new Scene(parent);
                StoreManager.stage.setScene(scene);
                StoreManager.stage.show();
                scene.setOnKeyPressed(k -> Time.Instance.KeyPressed(k));
                scene.setOnKeyReleased(k -> Time.Instance.KeyReleased(k));
            } catch (Exception err) {
                System.out.println("[ERROR]" + err.toString());
            }
        });
    }

    public static void ShowWaitRoomPanel() {
        Platform.runLater(() -> {
            try {
                StoreManager.getInstance().store.clearView();
                Time.Instance.clearUpdates();
                Parent parent = FXMLLoader.load(StoreManager.class.getResource("../view/waitRoom.fxml"));
                var scene = new Scene(parent);
                StoreManager.stage.setScene(scene);
                StoreManager.stage.show();
                scene.setOnKeyPressed(k -> Time.Instance.KeyPressed(k));
                scene.setOnKeyReleased(k -> Time.Instance.KeyReleased(k));
            } catch (Exception err) {
                System.out.println("[ERROR]" + err.toString());
            }
        });
    }

    public static void ShowUoPanel() {
        Platform.runLater(() -> {
            try {
                StoreManager.getInstance().store.clearView();
                Time.Instance.clearUpdates();
                Parent parent = FXMLLoader.load(StoreManager.class.getResource("../view/uo.fxml"));
                var scene = new Scene(parent);
                StoreManager.stage.setScene(scene);
                StoreManager.stage.show();
                scene.setOnKeyPressed(k -> Time.Instance.KeyPressed(k));
                scene.setOnKeyReleased(k -> Time.Instance.KeyReleased(k));
            } catch (Exception err) {
                System.out.println("[ERROR] : " + err.toString());
            }
        });
    }
}
