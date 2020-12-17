package game.view;

import com.taku.util.flux.model.Store;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.UIEvent;
import game.view.panel.StartPanel;
import game.view.reducer.ShowPanelReducer;
import game.view.state.ShowPanelState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

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
        StoreManager.Instance.InitLoad();
    }
}
