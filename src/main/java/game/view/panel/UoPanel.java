package game.view.panel;
import Animation.Animation;
import Animation.animationHolder;
import Animation.playAnimation;
import com.taku.util.flux.view.BasePanel;
import game.config.Character;
import game.config.PATH;
import game.util.Time;
import game.view.container.CharacterContainer;
import game.view.service.ICharacter;
import game.view.state.UoPanelState;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.*;

import javafx.scene.effect.*;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UoPanel extends BasePanel<UoPanelState, ICharacter> implements Initializable {
    @FXML
    private Label ground;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    double initTime;

    private ArrayList<playAnimation> player;

    //test
    private int uouo;
    private Text text;
    private CharacterState state1;

    ColorAdjust colorAdjust;
    Bloom bloom;
    DisplacementMap displacementMap;
    FloatMap floatMap;
    int width,height;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CharacterContainer(this);
        new animationHolder();

        var props = this.getProps();
        var state = this.getState();

        gc = canvas.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

        text = new Text (String.valueOf(initTime));
        text.setStroke(Color.BLACK);

        player = new ArrayList<>();

        player.add(new playAnimation(1,Character.Gura));
        player.add(new playAnimation(2,Character.Kiara));

        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();

//        colorAdjust = new ColorAdjust();
//        bloom = new Bloom();
//
//        floatMap = new FloatMap();
//        floatMap.setWidth(width);
//        floatMap.setHeight(height);
//        displacementMap = new DisplacementMap();
//        width = (int)gc.getCanvas().getWidth();
//        height = (int)gc.getCanvas().getHeight();


    }

    void draw(){
//        /*effect*/
//
//        colorAdjust.setContrast(0.1);
//        colorAdjust.setHue(-0.05);
//        colorAdjust.setBrightness(0.1);
//        colorAdjust.setSaturation(0.2);
//
//        bloom.setThreshold(0.1);
//
//
////        for (int i = 0; i < width; i++) {
////            double v = 10;
////            for (int j = 0; j < height; j++) {
////                floatMap.setSamples(i, j, -0.1f, -0.1f);
////            }
////        }
//
//        floatMap.setSamples(100, 100, -0.1f, -0.1f);
//
//        displacementMap.setMapData(floatMap);

//        draw
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        Image earth = new Image(new File(PATH.Img3).toURI().toString());
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.fillRect(uouo, 50, 150, 150);
        gc.strokeOval(uouo, 300, 50, 50);

        gc.drawImage(player.get(1).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()), uouo,400,400,400);
        gc.drawImage(player.get(0).playAnimation(state1), uouo,200,250,250);

        gc.setFill(Color.RED);
        gc.fillText(""+text, 300, 100);

//        gc.applyEffect(displacementMap);

    }

    @Override
    public void EveryFrameUpdate(){
        System.out.println("fps"+Time.Instance.getFrameRate()+"uouo"+uouo);
        uouo++;
//        gc.strokeText("Total Time : " + (Time.Instance.getTotalTime()-initTime)+ "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);

        text.setText(""+uouo+"init"+initTime);
        if(uouo > 200) state1 = CharacterState.newBuilder().setBehavior(Behavior.RUN).build();
        draw();
    }
}