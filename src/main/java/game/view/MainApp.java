package game.view;

import com.taku.util.flux.model.Store;
import game.store.StoreManager;
import game.util.Time;
import javafx.application.Application;
import javafx.stage.Stage;

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
        StoreManager.Instance.Init();

        Thread thread = new Thread(Time.Instance);
        thread.start();
    }
}
