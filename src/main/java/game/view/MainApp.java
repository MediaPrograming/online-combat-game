package game.view;
/**
 * @author Takuya Isaki
 * @project online-combat-game
 */

import Animation.AnimationHolder;
import com.taku.util.model.Unit;
import game.util.ShowPanelUtil;
import game.util.Time;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import Audio.AudioPlayer;
import Audio.AudioHolder;
import game.config.PATH;

/**
 * This class is entry point
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        new AnimationHolder();
        new AudioHolder();
        launch(args);
    }

    /**
     * Initialize Main Window by calling launch
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close(); //このwindowは使わん
        var unit = new Unit();
        AudioPlayer.Play(PATH.HomeBGM);
        ShowPanelUtil.ShowStartPanel();
        Thread thread = new Thread(() -> Platform.runLater(Time.Instance));
        thread.start();
    }
}
