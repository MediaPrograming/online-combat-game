package game.view.stage;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import game.store.StoreManager;
import javafx.stage.Stage;
import game.config.PATH;
import Audio.AudioClip;
import Audio.AudioPlayer;
import java.io.File;
/**
 * This is stage of prototype
 */
public class MainStage extends Stage {
    public MainStage(){
        this.setTitle("Combat Game");
//        this.setWidth(Config.WINDOW_WIDTH);
//        this.setHeight(Config.WINDOW_HEIGHT);
        this.setOnCloseRequest(
                e ->{ StoreManager.getInstance().client.Close(); }
                );
    }
}
