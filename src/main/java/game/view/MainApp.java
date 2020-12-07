package game.view;

import com.taku.util.model.Unit;
import game.store.Store;
import game.view.action.UIEvent;
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
        Store.Dispatch(UIEvent.ShowStartPanel.apply()); //StartPanelの表示
    }
}
