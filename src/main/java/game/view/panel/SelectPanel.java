package game.view.panel;

import com.taku.util.flux.view.BasePanel;
import game.view.container.SelectPanelContainer;
import game.view.reducer.ShowPanelReducer;
import game.view.service.IShowPanel;
import game.view.state.ShowPanelState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectPanel extends BasePanel<ShowPanelState, IShowPanel> implements Initializable {
    @FXML
    private Button b1, b2, b3, b4, b5;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SelectPanelContainer(this);
        System.out.println("Initial");
        b1.setOnAction(e -> {});
        b2.setOnAction(e -> {});
        b3.setOnAction(e -> System.out.println("b3をクリックしました"));
        b4.setOnAction(e -> System.out.println("b4をクリックしました"));
        b5.setOnAction(e -> this.props.ShowCombatPanel());
    }
}
