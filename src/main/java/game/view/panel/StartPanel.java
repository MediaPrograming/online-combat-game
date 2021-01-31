package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.PurePanel;
import game.util.ShowPanelUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel extends PurePanel implements Initializable{
    @FXML
    private Button showCreateRoomButton ,showSelectionButton, quitButton;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCreateRoomButton.setOnAction(e -> ShowPanelUtil.ShowCreateRoomPanel());
        showSelectionButton.setOnAction(e -> ShowPanelUtil.ShowSelectionPanel());
        quitButton.setOnAction(e -> System.exit(0));

        gc = canvas.getGraphicsContext2D();

    }

    void draw(){

    }

    @Override
    public void EveryFrameUpdate() {
        draw();
    }


}