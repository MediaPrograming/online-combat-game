package game.view.panel;
import Animation.animationHolder;
import Animation.playAnimation;
import com.taku.util.flux.view.BasePanel;
import game.config.Character;
import game.config.PATH;
import game.util.Time;
import game.view.container.CharacterContainer;
import game.view.service.ICharacter;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    CharacterState state;

    //test
    private int uouo;
    private Text text;
    private animationHolder anim;

    private playAnimation player1;

    private Image[][] Gura,Kiara;
    @FXML
    private ImageView imgv;

    private Image clip;
    private WritableImage wclip;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CharacterContainer(this);

        var props = this.getProps();
        state = this.getState();

        gc = canvas.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

//        this.draw();
        text = new Text (String.valueOf(initTime));
        text.setStroke(Color.BLACK);


//        imgv = new ImageView(new File(PATH.Gura).toURI().toString());
//
//        Gura = new Image[5][2];

        anim = new animationHolder();
        anim.addAnimation(Character.Gura, Behavior.NORMAL,PATH.Gura_Normal,128,128,2,1);
        anim.addAnimation(Character.Kiara,Behavior.NORMAL,PATH.Kiara_Normal,200,200,5,5);
        Gura = anim.getAnimation(Character.Gura,Behavior.NORMAL);
        Kiara = anim.getAnimation(Character.Kiara,Behavior.NORMAL);

        player1 = new playAnimation(1,Character.Gura);

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
//        gc.drawImage(Gura[((int)Time.Instance.getTotalTime())%2][0], uouo,400,250,250);
        gc.drawImage(Kiara[((int)(Time.Instance.getTotalTime()*5))%5][((int)Time.Instance.getTotalTime())%5], uouo,200,400,400);

        gc.drawImage(player1.playAnimation(state), uouo,400,250,250);

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