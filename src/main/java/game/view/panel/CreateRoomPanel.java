package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import com.taku.util.model.Unit;
import game.view.service.ICreateRoom;
import game.view.service.IShowPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRoomPanel extends BasePanel<Unit, ICreateRoom> implements Initializable {
    @FXML private TextField roomNameField, userNameField;
    @FXML private Button createButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var props = this.getProps();
        createButton.setOnAction(e -> {
            var roomName = roomNameField.getText();
            var userName = userNameField.getText();
            props.CreateRoom(roomName, userName);
        });
    }
}
