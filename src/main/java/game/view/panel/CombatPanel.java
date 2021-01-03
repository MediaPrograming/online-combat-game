package game.view.panel;
import com.taku.util.flux.view.BasePanel;
import game.view.container.CharacterContainer;
import game.view.service.ICharacter;
import game.view.state.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CombatPanel extends BasePanel<CharacterState, ICharacter> implements Initializable {
    @FXML
    private Label ground;
    private Canvas canvas;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new CharacterContainer(this);
        var props = this.getProps();
        var state = this.getState();
        var g = canvas.getGraphicsContext2D(); // Graphicsの取得
    }
}