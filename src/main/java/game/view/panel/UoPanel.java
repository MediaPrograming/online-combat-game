package game.view.panel;
import com.taku.util.flux.view.BasePanel;
import game.config.PATH;
import game.view.container.CharacterContainer;
import game.view.service.ICharacter;
import game.view.state.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.*;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

public class UoPanel extends BasePanel<CharacterState, ICharacter> implements Initializable {
    @FXML
    private Label ground;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CharacterContainer(this);

        var props = this.getProps();
        var state = this.getState();

        gc = canvas.getGraphicsContext2D();
        this.draw();
    }

    @Override
    public void Update(CharacterState s) {
        super.Update(s);
    }

    void draw(){
        Image earth = new Image(new File(PATH.Img3).toURI().toString());
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.fillRect(50, 50, 150, 150);
        gc.strokeOval(100, 100, 50, 50);
        gc.drawImage(earth,500,500,100,100);
    }
}