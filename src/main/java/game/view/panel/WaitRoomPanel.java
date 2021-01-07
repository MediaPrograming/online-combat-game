package game.view.panel;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import game.view.container.WaitRoomContainer;
import game.view.service.IWaitRoom;
import game.view.state.WaitRoomState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitRoomPanel extends BasePanel<WaitRoomState, IWaitRoom> implements Initializable {
    @FXML Button showCombatButton;
    @FXML ListView listView;
    WaitRoomContainer container;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container = new WaitRoomContainer(this);
        var props = this.getProps();
        showCombatButton.setOnAction(e -> props.CombatStartRequest());
    }

    @Override
    public void EveryFrameUpdate() {
        var state = this.getState();
        var props = this.getProps();

        var room = state.room;
        var selectionModel = listView.getSelectionModel();

    }
}
