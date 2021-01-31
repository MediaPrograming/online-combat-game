package game.view.panel;
import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import Animation.DrawPolygon;
import Animation.EffectAnimationManager;
import Animation.EffectPlayer.EffectManager;
import Animation.UIPlayer.PlayUI;
import Audio.AudioHolder;
import Audio.AudioPlayer;

import com.taku.util.flux.view.BasePanel;
import Audio.AudioClip;
import game.config.Character;
import game.config.PATH;
import game.util.Time;
import game.view.container.CombatContainer;
import game.view.service.IPositionStream;
import game.view.state.UoPanelState;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
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

        var props = this.getProps();
        var state = this.getState();
        props.Init(state.self, state.room);
        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();

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

        AudioPlayer.Play(PATH.BattleBGM);
        
        User host,client;
        if(getState().room.getUser(0).getId() == getState().room.getHostId()){
            host = getState().room.getUser(0); client = getState().room.getUser(1);
        }else{
            host = getState().room.getUser(1); client = getState().room.getUser(0);
        }
        new PlayUI(gc3,host,client);
        debug = true;
        hoge=100;
        continueButton.setOnAction(e -> {EffectManager.resetGraphicsContext(); props.ContinueGame();});
        quitButton.setOnAction(e -> {EffectManager.resetGraphicsContext(); props.QuitGame();});

        EffectAnimationManager.setGc(gc2);
        EffectManager.addGraphicsContext(gc1);
        EffectManager.addGraphicsContext(gc2);
        EffectManager.addGraphicsContext(gc3);
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
//            System.out.println("ID"+character.getId());
            state.playerTable.get(character.getId()).updateState(character);
            PlayUI.updateState(character);
            state.polyTable.get(character.getId()).updateChara((int)character.getX(),(int)character.getY(),state.charaTable.get(character.getId()));//debug用
            character  = getState().stateBlockingQueue.poll();
        }

        gc2.clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());
        gc2.drawImage(state.floor,-20,-20,1320,742.5);
        state.playerTable.forEach((k,v) -> v.play());
        EffectAnimationManager.play();

        /*gc3,canvas3*/
        gc3.clearRect(0,0,canvas3.getWidth(),canvas3.getHeight());
        gc3.setFill(Color.WHITE);
        gc3.fillText(""+text, 300, 100);

        PlayUI.play();
        //PlayUI.debug(hoge);
        if(debug){
            state.polyTable.forEach((k,v) -> v.play(gc3));
        }
        EffectManager.play();
    }

    @Override
    public void EveryFrameUpdate(){
        var state = getState();
        quitPane.setVisible(state.quitPaneVisible);
        if(state.quitPaneVisible){
            state.timer.cancel();
            //System.out.println("finishbgm");
            if(AudioPlayer.getBGM()!=PATH.FinishBGM){
                AudioPlayer.Play(PATH.FinishBGM);
            }
        } 

        uouo++;
        //#region debug text
        if(debug) {
//            System.out.println("fps" + Time.Instance.getFrameRate() + "uouo" + uouo);
            //gc.strokeText("Total Time : " + (Time.Instance.getTotalTime() - initTime) + "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);
        }
        //#endregion
        if(text == null) return;
        text.setText("uouo->"+uouo+"FPS->"+Time.Instance.getFrameRate());
        draw();
    }
}