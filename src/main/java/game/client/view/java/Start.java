package io.game.client.view.java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Start implements Initializable {
    @FXML
    private Button createButton, joinButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createButton.setOnAction(e-> {});
        joinButton.setOnAction(e ->{});
    }
}
