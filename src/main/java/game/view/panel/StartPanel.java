package game.view.panel;

import game.view.dispatcher.MainContainer;
import game.view.dispatcher.MainDispatch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel implements Initializable {
    MainDispatch dispatcher = new MainContainer();
    @FXML
    private Button b1, b2, b3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        b1.setOnAction(e -> dispatcher.ShowSelectionPanel());
        b2.setOnAction(e -> System.out.println("クリックしたよ"));
        b3.setOnAction(e -> dispatcher.ApplicationQuit());
        //b4.setOnAction(e -> MainApp);
    }
}
