package game.view.panel;
import Animation.animationHolder;
import Animation.playAnimation;
import com.taku.util.flux.view.BasePanel;
import game.config.Character;
import game.config.PATH;
import game.store.StoreManager;
import game.util.Time;
import game.view.container.CombatContainer;
import game.view.service.IPositionStream;
import game.view.state.UoPanelState;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UoPanel extends BasePanel<UoPanelState, IPositionStream> implements Initializable {
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

    Input input;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new CombatContainer(this);
        new animationHolder();

        var props = this.getProps();
        var state = this.getState();

        String debug = "";
        for (var user : state.room.getUserList()){
            debug += "name : " + user.getName() + " roomName:" +user.getRoomName() + " id : " + user.getId() + "\n";
        }
        System.out.println(debug);
        gc = canvas.getGraphicsContext2D();

        Time.Instance.run();
        initTime = Time.Instance.getTotalTime();
        uouo = 0;

        text = new Text(String.valueOf(initTime));
        text.setStroke(Color.BLACK);

        player = new ArrayList<>();

        player.add(new playAnimation(1,Character.Gura));
        player.add(new playAnimation(2,Character.Kiara));
        Timer timer = new Timer();

        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
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
        System.out.println("id :" + getState().self.getId());
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        Image earth = new Image(new File(PATH.Img3).toURI().toString());
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.fillRect(uouo, 50, 150, 150);
        gc.strokeOval(uouo, 300, 50, 50);

//        gc.drawImage(player.get(1).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),
//                uouo,400,400,400);
//        gc.drawImage(player.get(0).playAnimation(state1), uouo,200,250,250);

        gc.setFill(Color.RED);
        gc.fillText(""+text, 300, 100);

//        gc.applyEffect(displacementMap);



        var character  = getState().stateBlockingQueue.poll();
        while (character != null) {
            if (character.getId() != getState().self.getId()) {
                c1 = character;
            } else {
                c2 = character;
            }
            character  = getState().stateBlockingQueue.poll();
        }
        gc.drawImage(player.get(1).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),
                c1.getX(), c1.getY(), 400, 400);
        gc.drawImage(player.get(0).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),
                c2.getX(), c2.getY(), 400, 400);
//        if (character.getId() != getState().self.getId()) {
//            gc.drawImage(player.get(1).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),
//                    c1.getX(), c1.getY(), 400, 400);
//        } else {
//            gc.drawImage(player.get(0).playAnimation(CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build()),
//                    c2.getX(), c2.getY(), 400, 400);
//        }
    }
    CharacterState c1 = CharacterState.newBuilder().setX(300).setY(0).setBehavior(Behavior.NORMAL).build(),
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