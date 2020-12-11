package game.view.panel;

import com.taku.util.flux.view.BasePanel;
import game.view.container.ShowPanelContainer;
import game.view.service.IShowPanel;
import game.view.state.ShowPanelState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel extends BasePanel<ShowPanelState, IShowPanel> implements Initializable{
    ShowPanelContainer container;
    @FXML
    private Button b1, b2, b3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container = new ShowPanelContainer(this);

        var props = this.getProps();
        //this.props.ChangeTextT1("hoge1");
        //System.out.println(this.state.t1);
        b1.setOnAction(e -> props.ShowStartPanel());
        b2.setOnAction(e ->props.ShowSelectionPanel());
        b3.setOnAction(e -> props.ShowCombatPanel());
        //b4.setOnAction(e -> MainApp);
    }


    @Override
    public void Update(ShowPanelState s) {
        super.Update(s);
        //ここにState


    }
}