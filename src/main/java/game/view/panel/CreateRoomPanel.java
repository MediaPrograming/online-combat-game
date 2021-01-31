package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.FunctionPanel;
import game.util.ShowPanelUtil;
import game.view.container.CreateRoomContainer;
import game.view.service.ICreateRoom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomPanel extends FunctionPanel<ICreateRoom> implements Initializable {
    @FXML private TextField roomNameField, userNameField;
    @FXML private Button createButton, backButton;

    private CreateRoomContainer container;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container = new CreateRoomContainer(this);
        var props = this.getProps();
        createButton.setOnAction(e -> {
            var roomName = roomNameField.getText();
            var userName = userNameField.getText();
            props.CreateRoomRequest(roomName, userName);
        });
        backButton.setOnAction(e -> ShowPanelUtil.ShowStartPanel());
    }
}
