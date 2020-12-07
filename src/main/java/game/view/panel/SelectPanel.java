package game.view.panel;


import game.view.dispatcher.MainContainer;
import game.view.dispatcher.MainDispatch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectPanel implements Initializable {
    MainDispatch dispatch = new MainContainer();
    @FXML
    private Button b1, b2, b3, b4, b5;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b1.setOnAction(e -> dispatch.ShowSelectionPanel());
        b2.setOnAction(e -> System.out.println("b2をクリックしました"));
        b3.setOnAction(e -> System.out.println("b3をクリックしました"));
        b4.setOnAction(e -> System.out.println("b4をクリックしました"));
        b5.setOnAction(e -> System.out.println("b5をクリックしました"));
    }
}
