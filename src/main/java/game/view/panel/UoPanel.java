package game.view.panel;
import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import Animation.DrawPolygon;
import Animation.EffectPlayer.EffectManager;
import Animation.UIPlayer.PlayUI;
import Audio.AudioHolder;
import com.taku.util.flux.view.BasePanel;
import Audio.AudioClip;
import game.config.CharaData.Gura;
import game.config.Character;
import game.config.PATH;
import game.store.StoreManager;
import game.util.Time;
import game.view.container.CombatContainer;
import game.view.service.IPositionStream;
import game.view.state.UoPanelState;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.effect.*;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.security.Key;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class UoPanel extends BasePanel<UoPanelState, IPositionStream> implements Initializable {
    @FXML
    private Canvas canvas1,canvas2,canvas3;
    GraphicsContext gc1,gc2,gc3;
    double initTime;

    Hashtable<Integer, PlayCharacter> playerTable;

    //test
    private int uouo,hoge;
    private Text text;
    private CharacterState state1;
    private boolean debug;
    Hashtable<Integer, DrawPolygon> polyTable;
    Hashtable<Integer, CharacterType> charaTable;

    Image floor,back;

    ColorAdjust colorAdjust;
    Bloom bloom;
    DisplacementMap displacementMap;
    FloatMap floatMap;
    int width,height;

    Input input;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CombatContainer(this);
        new AnimationHolder();
        new AudioHolder();

        var props = this.getProps();
        var state = this.getState();

        gc1 = canvas1.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        gc3 = canvas3.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

        text = new Text(String.valueOf(initTime));
        text.setStroke(Color.BLACK);



        Timer timer = new Timer();

        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();

        playerTable = new Hashtable<>();

        polyTable = new Hashtable<>();

        charaTable = new Hashtable<>();

        getState().room.getUserList().forEach(user ->
        {
            System.out.println("InitialID\t" + user.getId());
            System.out.println("selfID\t" + getState().self.getId());
            switch (user.getCharacterType()) {
                case Gura:
                    playerTable.put(user.getId(), new PlayGura(gc2, new CharaAnimationPlayer(user.getId(), Character.Gura), state1));
                    break;
                case Kiara:
                    playerTable.put(user.getId(), new PlayKiara(gc2, new CharaAnimationPlayer(user.getId(), Character.Kiara), state1));
                    break;
                case Amelia:
                    playerTable.put(user.getId(), new PlayAme(gc2, new CharaAnimationPlayer(user.getId(), Character.Ame), state1));
                    break;
                case Inanis:
                    playerTable.put(user.getId(), new PlayIna(gc2, new CharaAnimationPlayer(user.getId(), Character.Ina), state1));
                    break;
                case Calliope:
                    playerTable.put(user.getId(), new PlayCalli(gc2, new CharaAnimationPlayer(user.getId(), Character.Calli), state1));
                    break;
            }
            polyTable.put(user.getId(),new DrawPolygon());
            charaTable.put(user.getId(),user.getCharacterType());
        });

        polyTable.put(0,new DrawPolygon());
        polyTable.get(0).update(0, 600, 1280, 100);

        playerTable.forEach((k,v) -> {if(k == getState().room.getUser(1).getId()) v.setPosition(200,500); else v.setPosition(1200,500);});

        input = Input.newBuilder().setW(false).setA(false).setS(false).setD(false).setK(false)
                .setId(getState().self.getId())
                .setRoomName(getState().self.getRoomName())
                .build();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getProps().SendInput(input);
            }
        }, 1000, 30);


        Clip bgm = AudioClip.createClip(new File(PATH.RIP));
        bgm.start();
        FloatControl ctrl = (FloatControl)bgm.getControl(FloatControl.Type.MASTER_GAIN);
        ctrl.setValue((float)Math.log10(0.1) * 20);

        new PlayUI(gc3,getState().room.getUser(0),getState().room.getUser(1));
        debug = true;
        hoge=100;

        Path imagePath = Paths.get(PATH.Back);
        back  = new Image(imagePath.toUri().toString(),1280,720,false,false);
        Path imagPath = Paths.get(PATH.Floor);
        floor = new Image(imagPath.toUri().toString(),1280,720,false,false);

        new EffectManager(gc1,gc2,gc3);
    }

    @Override
    public void KeyPressed(KeyEvent key) {
        String c = key.getCode().toString();
        input = keyPressed(c, input);
    }

    @Override
    public void KeyReleased(KeyEvent key) {
        String c = key.getCode().toString();
        input = keyReleased(c, input);
    }

    void draw(){
        /*gc1,canvas1*/
        gc1.clearRect(0,0,canvas1.getWidth(),canvas1.getHeight());
//        gc1.setFill(Color.RED);
//        gc1.fillRect(0,0,canvas1.getWidth(),canvas1.getHeight());

        gc1.drawImage(back,-20,-20,1320,742.5);

//        displacementMap.play(gc1,firstFrame,0,0.5f,0.5f);
//        colorAdjust.play(gc1,0,0,0,-0.1);
//        bloom.play(gc1,0.9,firstFrame,8);
//        dropShadow.play(gc1,0.5,10,10,Color.color(0.1,0.05,0.1,0.6));
//        motionBlur.play(gc1,firstFrame,6,30,0);


        /*gc2,canvas2*/

        var character  = getState().stateBlockingQueue.poll();
        while (character != null) {
            System.out.println("ID"+character.getId());
            playerTable.get(character.getId()).updateState(character);
            PlayUI.updateState(character);
            polyTable.get(character.getId()).updateChara((int)character.getX(),(int)character.getY(),charaTable.get(character.getId()));//debug用
            character  = getState().stateBlockingQueue.poll();
        }

        gc2.clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());
        gc2.drawImage(floor,-20,-20,1320,742.5);

        playerTable.forEach((k,v) -> v.play());

//        gaussianBlur.play(gc2,firstFrame,10,gaussianBlur.exponentialDecay(gaussianBlur.getDuration(firstFrame),50,1.0));

        /*gc3,canvas3*/
        gc3.clearRect(0,0,canvas3.getWidth(),canvas3.getHeight());
        gc3.setFill(Color.WHITE);
        gc3.fillText(""+text, 300, 100);
        PlayUI.play();
//        PlayUI.debug(hoge);
        if(debug){
            polyTable.forEach((k,v) -> v.play(gc3));
        }
    }

    public Input keyPressed(String key, Input input){
        boolean w = input.getW(), a = input.getA(), s = input.getS(), d = input.getD(), atk = input.getK();
        switch (key){
            case "A":
                a=true;
                if(d) d=false;
                break;
            case "S":
                s=true;
                break;
            case "D":
                d=true;
                if(a) a=false;
                break;
            case "W":
                w=true;
                break;
            case "K":
                atk=true;
                break;
            case "M":
                hoge ++;
                break;
            case "N":
                hoge --;
                break;
        }
        return input.toBuilder().setW(w).setA(a).setS(s).setD(d).setK(atk).build();
    }
    public Input keyReleased(String key, Input input){
        boolean w = input.getW(), a = input.getA(), s = input.getS(), d = input.getD(), atk = input.getK();
        switch (key){
            case "A":
                a=false;
                break;
            case "S":
                s=false;
                break;
            case "D":
                d=false;
                break;
            case "W":
                w=false;
                break;
            case "K":
                atk=false;
                break;
        }
        return input.toBuilder().setW(w).setA(a).setS(s).setD(d).setK(atk).build();
    }

    @Override
    public void EveryFrameUpdate(){
        System.out.println("fps"+Time.Instance.getFrameRate()+"uouo"+uouo);
        uouo++;
//        gc.strokeText("Total Time : " + (Time.Instance.getTotalTime()-initTime)+ "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);

        if(text == null) return;
        text.setText("uouo->"+uouo+"FPS->"+Time.Instance.getFrameRate());
        if(uouo > 200) state1 = CharacterState.newBuilder().setHP(hoge).build();
        draw();
    }
}