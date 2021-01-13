package game.view.panel;
import Animation.animationHolder;
import Animation.animationKeyHolder;
import com.taku.util.flux.view.BasePanel;
import game.config.PATH;
import game.util.Time;
import game.view.container.CharacterContainer;
import game.view.service.ICharacter;
import game.view.state.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.*;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UoPanel extends BasePanel<CharacterState, ICharacter> implements Initializable {
    @FXML
    private Label ground;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    double initTime;

    //test
    private int uouo;
    private Text text;
    private animationHolder anim;
    private Image[][] Gura;
    @FXML
    private ImageView imgv;

    private Image clip;
    private WritableImage wclip;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CharacterContainer(this);

        var props = this.getProps();
        var state = this.getState();

        gc = canvas.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

//        this.draw();
        text = new Text (String.valueOf(initTime));
        text.setStroke(Color.BLACK);


//        imgv = new ImageView(new File(PATH.Gura).toURI().toString());

        Gura = new Image[5][2];

        anim = new animationHolder();
        anim.addAnimation(PATH.Gura,120,120,5,2,new String("gura"));
        Gura = anim.getAnimation("gura");

//        clip = new Image(new File(PATH.Gura).toURI().toString());
//        Gura[0][0] = new WritableImage(clip.getPixelReader(),0,0 , 120, 120);

    }

    void draw(){
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        Image earth = new Image(new File(PATH.Img3).toURI().toString());
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.fillRect(uouo, 50, 150, 150);
        gc.strokeOval(uouo, 300, 50, 50);

//        imgv.setImage(earth);
        gc.drawImage(Gura[1][0], 100,500,100,100);

        gc.setFill(Color.RED);
        gc.fillText(""+text, 300, 100);

    }

    @Override
    public void EveryFrameUpdate(){
        System.out.println("fps"+Time.Instance.getFrameRate()+"uouo"+uouo);
        uouo++;
        gc.setStroke(Color.RED);
//        gc.strokeText("Total Time : " + (Time.Instance.getTotalTime()-initTime)+ "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);

        text.setText(""+uouo+"init"+initTime);
        draw();
    }
}