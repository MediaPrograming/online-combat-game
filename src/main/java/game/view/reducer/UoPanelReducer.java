package game.view.reducer;

import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import Animation.DrawPolygon;
import Audio.AudioClip;
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.config.Character;
import game.config.PATH;
import game.store.StoreManager;
import game.view.action.UoPanelEvent;
import game.view.action.UIEvent;
import game.view.state.UoPanelState;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * @author Takuya Isaki on 2021/01/20
 * @project online-combat-game
 */
public class UoPanelReducer implements IReducer<UoPanelState> {

    @Override
    public ReducerBuilder<UoPanelState> apply(Action<?> action, UoPanelState init) {
        return ReducerBuilder.Create(action, init)
                .Case(UoPanelEvent.STATE_UPDATE, ((uoPanelState, characterState) -> {
                    uoPanelState.stateBlockingQueue.add(characterState);
                    //System.out.println("id : " +  characterState.getId() + "put : " + "x: "+  characterState.getX() + "y " + characterState.getY());
                    return uoPanelState;
                }))
                .Case(UoPanelEvent.STREAM_EVENT, ((uoPanelState, positionHubMessage) -> {
                    switch (positionHubMessage.getType()){
                        case INIT : {break;}
                        case GAME_FINISH : {uoPanelState.quitPaneVisible = true; break;}
                    }
                    return uoPanelState;
                })).Case(UoPanelEvent.CONTINUE, ((uoPanelState, unit) -> {
                    StoreManager.Instance.store.Invoke(unit,UIEvent.SHOW_WAIT_ROOM_PANEL.Create(unit));
                    return uoPanelState;
                })).Case(UoPanelEvent.QUIT, ((uoPanelState, unit) -> {
                    System.exit(0);
                    return uoPanelState;
                }))
                .Case(UoPanelEvent.UPDATE_INPUT_PRESSED, ((panelState, key) -> {
                    var input = panelState.input;
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
//                        case "M":
//                            hoge ++;
//                            break;
//                        case "N":
//                            hoge --;
//                            break;
                    }
                    panelState.input  = input.toBuilder().setW(w).setA(a).setS(s).setD(d).setK(atk).build();
                    return panelState;
                }))
                .Case(UoPanelEvent.UPDATE_INPUT_RELEASED, ((panelState, key) -> {
                    var input = panelState.input;
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

                    panelState.input = input.toBuilder().setW(w).setA(a).setS(s).setD(d).setK(atk).build();
                    return panelState;
                }))
                .Case(UoPanelEvent.UPDATE_CHARACTER_TABLE, (panelState, gc) ->{
                    var self = panelState.self;
                    var selfState = panelState.selfState;
                    var playerTable = panelState.playerTable;
                    var polyTable = panelState.polyTable;
                    var charaTable = panelState.charaTable;
                    //テーブルの初期化
                    playerTable.clear();

                    panelState.room.getUserList().forEach(user ->
                    {
                        System.out.println("InitialID\t" + user.getId());
                        System.out.println("selfID\t" + self.getId());
                        switch (user.getCharacterType()) {
                            case Gura:
                                playerTable.put(user.getId(), new PlayGura(gc, new CharaAnimationPlayer(user.getId(), Character.Gura), selfState));
                                break;
                            case Kiara:
                                playerTable.put(user.getId(), new PlayKiara(gc, new CharaAnimationPlayer(user.getId(), Character.Kiara), selfState));
                                break;
                            case Amelia:
                                playerTable.put(user.getId(), new PlayAme(gc, new CharaAnimationPlayer(user.getId(), Character.Ame), selfState));
                                break;
                            case Inanis:
                                playerTable.put(user.getId(), new PlayIna(gc, new CharaAnimationPlayer(user.getId(), Character.Ina), selfState));
                                break;
                            case Calliope:
                                playerTable.put(user.getId(), new PlayCalli(gc, new CharaAnimationPlayer(user.getId(), Character.Calli), selfState));
                                break;
                        }
                        polyTable.put(user.getId(),new DrawPolygon());
                        charaTable.put(user.getId(),user.getCharacterType());
                    });

                    polyTable.put(0,new DrawPolygon());
                    polyTable.get(0).update(0, 600, 1280, 100);

                    playerTable.forEach((k,v) -> {if(k == panelState.room.getUser(1).getId()) v.setPosition(200,500); else v.setPosition(1200,500);});
                    return panelState;
                })
                .Case(UoPanelEvent.START_AUDIO, ((panelState, unit) -> {
                    var bgm = AudioClip.createClip(new File(PATH.BattleBGM));
                    bgm.loop(Clip.LOOP_CONTINUOUSLY);
                    FloatControl ctrl = (FloatControl)bgm.getControl(FloatControl.Type.MASTER_GAIN);
                    ctrl.setValue((float)Math.log10(0.1) * 20);
                    panelState.bgm = bgm;
                    return panelState;
                }))
                .Case(UoPanelEvent.STOP_AUDIO, ((panelState, unit) -> {
                    if(panelState.bgm != null)
                        panelState.bgm.stop();
                    return panelState;
                }));
    }
}
