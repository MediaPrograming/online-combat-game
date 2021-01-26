package game.view.panel;
import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import Animation.DrawPolygon;
import Animation.UIPlayer.PlayUI;
import Audio.AudioHolder;
import com.taku.util.flux.view.BasePanel;
import Audio.AudioClip;
import game.config.Character;
import game.config.PATH;
import game.util.Time;
import game.view.container.CombatContainer;
import game.view.service.IPositionStream;
import game.view.state.UoPanelState;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class UoPanel extends BasePanel<UoPanelState, IPositionStream> implements Initializable {
    @FXML
    private Canvas canvas1,canvas2,canvas3;

    @FXML
    private Pane quitPane;
    @FXML
    private Button continueButton, quitButton;
    GraphicsContext gc1,gc2,gc3;
    double initTime;
    //test
    private int uouo = 0,hoge;
    private Text text;
    private boolean debug;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CombatContainer(this);
        new AnimationHolder();
        new AudioHolder();

        var props = this.getProps();
        var state = this.getState();
        props.Init(state.self, state.room);
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();

//        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        text = new Text(String.valueOf(initTime));
        text.setStroke(Color.BLACK);
        this.getProps().UpdateCharacterTable(gc2);
        state.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getProps().SendInput(state.input);
            }
        }, 1000, 30);

        props.StartAudio();

        new PlayUI(gc3,getState().room.getUser(0),getState().room.getUser(1));
        debug = true;
        hoge=100;
        continueButton.setOnAction(e -> props.ContinueGame());
        quitButton.setOnAction(e -> props.QuitGame());
    }

    @Override
    public void KeyPressed(KeyEvent key) {
        String keycode = key.getCode().toString();
        this.getProps().ChangeInputPressed(keycode);
    }

    @Override
    public void KeyReleased(KeyEvent key) {
        String keycode= key.getCode().toString();
        this.getProps().ChangeInputReleased(keycode);
    }

    void draw(){
        var state = getState();
        var props = getProps();

        gc1.clearRect(0,0,canvas1.getWidth(),canvas1.getHeight());
        gc1.drawImage(state.back,-20,-20,1320,742.5);

        var character  = getState().stateBlockingQueue.poll();
        while (character != null) {
            System.out.println("ID"+character.getId());
            state.playerTable.get(character.getId()).updateState(character);
            PlayUI.updateState(character);
            state.polyTable.get(character.getId()).updateChara((int)character.getX(),(int)character.getY(),state.charaTable.get(character.getId()));//debug用
            character  = getState().stateBlockingQueue.poll();
        }

        gc2.clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());
        gc2.drawImage(state.floor,-20,-20,1320,742.5);
        state.playerTable.forEach((k,v) -> v.play());

        /*gc3,canvas3*/
        gc3.clearRect(0,0,canvas3.getWidth(),canvas3.getHeight());
        gc3.setFill(Color.WHITE);
        gc3.fillText(""+text, 300, 100);
        PlayUI.play();
        //PlayUI.debug(hoge);
        if(debug){
            state.polyTable.forEach((k,v) -> v.play(gc3));
        }
    }

    @Override
    public void EveryFrameUpdate(){
        var state = getState();
        quitPane.setVisible(state.quitPaneVisible);
        if(state.quitPaneVisible) state.timer.cancel();

        uouo++;
        //#region debug text
        if(debug) {
            System.out.println("fps" + Time.Instance.getFrameRate() + "uouo" + uouo);
            //gc.strokeText("Total Time : " + (Time.Instance.getTotalTime() - initTime) + "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);
        }
        //#endregion
        if(text == null) return;
        text.setText("uouo->"+uouo+"FPS->"+Time.Instance.getFrameRate());
        draw();
    }
}