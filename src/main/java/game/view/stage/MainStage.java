package game.view.stage;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import game.store.StoreManager;
import javafx.stage.Stage;

/**
 * This is stage of prototype
 */
public class MainStage extends Stage {
    public MainStage(){
        this.setTitle("Combat Game");
//        this.setWidth(Config.WINDOW_WIDTH);
//        this.setHeight(Config.WINDOW_HEIGHT);
        this.setOnCloseRequest(e ->{ StoreManager.Instance.client.Close(); });
    }
}
