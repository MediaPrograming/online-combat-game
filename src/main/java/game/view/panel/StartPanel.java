package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Unit;
import game.util.Time;
import game.view.container.ShowPanelContainer;
import game.view.service.IShowPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel extends BasePanel<Unit, IShowPanel> implements Initializable{
    ShowPanelContainer container;
    @FXML
    private Button showCreateRoomButton ,showSelectionButton, quitButton;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container = new ShowPanelContainer(this);
        var props = this.getProps();
        showCreateRoomButton.setOnAction(e -> props.ShowCreateRoomPanel());
        showSelectionButton.setOnAction(e ->props.ShowSelectionPanel());
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