package game.view.panel;
import Animation.Animation;
import Animation.AnimationHolder;
import Animation.EffectPlayer.*;
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

//import javafx.scene.effect.*;
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
    private Canvas canvas1,canvas2;
    GraphicsContext gc;
    double initTime;

    private ArrayList<playAnimation> player;

    //test
    private int uouo;
    private Text text;
    private CharacterState state1;

    PlayColorAdjust colorAdjust;
    PlayDisplacementMap displacementMap;
    PlayBloom bloom;
    PlayDropShadow dropShadow;
    PlayMotionBlur motionBlur;
    PlayGaussianBlur gaussianBlur;
    double firstFrame = 0;




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        new CharacterContainer(this);
        new AnimationHolder();

        var props = this.getProps();
        var state = this.getState();

        gc = canvas1.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

        text = new Text (String.valueOf(initTime));
        text.setStroke(Color.BLACK);

        player = new ArrayList<>();

        player.add(new playAnimation(1,Character.Gura));
        player.add(new playAnimation(2,Character.Kiara));

        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();

        colorAdjust = new PlayColorAdjust();
        displacementMap = new PlayDisplacementMap();
        bloom = new PlayBloom();
        dropShadow = new PlayDropShadow();
        motionBlur = new PlayMotionBlur();
        gaussianBlur = new PlayGaussianBlur();


    }

    void draw(){
        /*effect*/



//        draw
        gc.clearRect(0,0,canvas1.getWidth(),canvas1.getHeight());

        gc.drawImage(player.get(1).play(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()), 700,300,400,400);
        gc.drawImage(player.get(0).play(state1), 400,200,250,250);



        gc.setFill(Color.RED);
        gc.fillText(""+text, 300, 100);


//        displacementMap.play(gc,firstFrame,0,0.5f,0.5f);
//        colorAdjust.play(gc,0,0,0,-0.1);
//        bloom.play(gc,0.9,firstFrame,8);
//        dropShadow.play(gc,0.5,10,10,Color.color(0.1,0.05,0.1,0.6));
//        motionBlur.play(gc,firstFrame,6,30,0);
        gaussianBlur.play(gc,firstFrame,10,gaussianBlur.exponentialDecay(gaussianBlur.getDuration(firstFrame),50,1.0));
    }

    @Override
    public void EveryFrameUpdate(){
        System.out.println("fps"+Time.Instance.getFrameRate()+"uouo"+uouo);
        uouo++;
//        gc.strokeText("Total Time : " + (Time.Instance.getTotalTime()-initTime)+ "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);

        text.setText(""+uouo+"init"+initTime);
        if(uouo > 200) state1 = CharacterState.newBuilder().setBehavior(Behavior.RUN).build();
        if(uouo == 300) firstFrame = Time.Instance.getTotalTime();
        draw();
    }
}