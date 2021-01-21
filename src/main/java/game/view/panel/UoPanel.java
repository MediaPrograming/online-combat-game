package game.view.panel;
import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import com.taku.util.flux.view.BasePanel;
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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.awt.*;

import javafx.scene.effect.*;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.security.Key;
import java.util.*;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UoPanel extends BasePanel<UoPanelState, IPositionStream> implements Initializable {
    @FXML
    private Label ground;
    @FXML
    private Canvas canvas1,canvas2,canvas3;
    GraphicsContext gc1,gc2,gc3;
    double initTime;

//    private ArrayList<CharaAnimationPlayer> player;
    Hashtable<Integer, PlayCharacter> playerTable;

    //test
    private int uouo;
    private Text text;
    private CharacterState state1;



    PlayGura gura;

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

//        gura = new PlayGura(gc2,player.get(0),state1);

        playerTable = new Hashtable<>();

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
        }
    );

        input = Input.newBuilder().setW(false).setA(false).setS(false).setD(false).setK(false)
                .setId(getState().self.getId())
                .setRoomName(getState().self.getRoomName())
                .build();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getProps().SendInput(input);
            }
        }, 1000, 30);}

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

        gc1.setFill(Color.RED);
        gc1.fillRect(0,0,canvas1.getWidth(),canvas1.getHeight());

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
            character  = getState().stateBlockingQueue.poll();
        }

//        gc2.drawImage(player.get(1).play(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),c1.getX(), c1.getY(), 400, 400);
//        gc2.drawImage(player.get(0).play(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),c2.getX(), c2.getY(), 400, 400);

        gc2.clearRect(0,0,canvas2.getWidth(),canvas2.getHeight());
        playerTable.forEach((k,v) -> v.play());

//        gura.play();
//        gc2.drawImage(player.get(0).play(state1), 400,200,250,250);
//        gc2.drawImage(player.get(1).play(state1), 700,300,400,400);

//        gaussianBlur.play(gc2,firstFrame,10,gaussianBlur.exponentialDecay(gaussianBlur.getDuration(firstFrame),50,1.0));

        /*gc3,canvas3*/
        gc3.clearRect(0,0,canvas3.getWidth(),canvas3.getHeight());
        gc3.setFill(Color.WHITE);
        gc3.fillText(""+text, 300, 100);

    }
    CharacterState c1 = CharacterState.newBuilder().setX(200).setY(0).setBehavior(Behavior.NORMAL).build(),
            c2 = CharacterState.newBuilder().setX(600).setY(0).setBehavior(Behavior.NORMAL).build();;
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
        //System.out.println("fps"+Time.Instance.getFrameRate()+"uouo"+uouo);
        uouo++;
//        gc.strokeText("Total Time : " + (Time.Instance.getTotalTime()-initTime)+ "\n delta Time : " + Time.Instance.getDeltaTime(), 250, 200);

        if(text == null) return;
        text.setText(""+uouo+"init"+initTime);
        if(uouo > 200) state1 = CharacterState.newBuilder().setBehavior(Behavior.RUN).build();
        draw();
    }
}