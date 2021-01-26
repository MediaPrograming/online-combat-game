package game.view;
/**
 * @author Takuya Isaki
 * @project online-combat-game
 */

import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.util.Time;
import game.view.action.UIEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * This class is entry point
 */
public class MainApp extends Application {

    public static void main(String[] args) {
       launch(args);
    }

    /**
     * Initialize Main Window by calling launch
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close(); //このwindowは使わん
        var unit = new Unit();

        StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_START_PANEL.Create(unit));

        Thread thread = new Thread(() -> Platform.runLater(Time.Instance));
        thread.start();
    }
}
