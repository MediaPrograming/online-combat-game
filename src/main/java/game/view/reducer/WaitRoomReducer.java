package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import Animation.EffectPlayer.EffectManager;
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.store.StoreManager;
import game.util.ShowPanelUtil;
import game.view.action.ClientEvent;
import game.view.action.RoomEvent;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.Type;
import server.room.Room;

public class WaitRoomReducer implements IReducer<WaitRoomState> {
    @Override
    public ReducerBuilder<WaitRoomState> apply(Action<?> action, WaitRoomState init) {
        return ReducerBuilder.Create(action, init)
                .Case(RoomEvent.START_GAME, ((state, room) -> {
                    ShowPanelUtil.ShowUoPanel();
                    return state;
                    
                }))
                .Case(ClientEvent.STREAM_EVENT, ((roomState, message) -> {

                    if(message.getType() == Type.JOIN){
                    }else if(message.getType() == Type.LEAVE){
                        //HostがLEAVEした場合,もしくは自分が退出した場合はStart画面に移動する
                        EffectManager.resetGraphicsContext();
                        if(message.getUser().getId() == roomState.currentRoom.getHostId() || message.getUser().getId() == roomState.self.getId()) {
                            System.out.println("Start画面の表示---------------------------");
                            StoreManager.getInstance().client.grpcRoom = null;
                            StoreManager.getInstance().client.user = null;
                            ShowPanelUtil.ShowStartPanel();
                        }else {
                            roomState.currentRoom = message.getRoom();
                        }
                    }else if(message.getType() == Type.UPDATE){
                        roomState.currentRoom = message.getRoom();
                        if(message.getUser().getId() == roomState.self.getId())
                            roomState.self = message.getUser();
                    }else if(message.getType() == Type.GAME_START){
                        EffectManager.resetGraphicsContext();
                        //UOPanelに移動
                        System.out.println("UOパネルの表示");
                        StoreManager.getInstance().client.user = message.getRoom().getUserList().stream().filter(x->x.getId() == roomState.self.getId()).findFirst().get();
                        StoreManager.getInstance().client.grpcRoom = message.getRoom();
                        ShowPanelUtil.ShowUoPanel();
                    }
                    else if(message.getType() == Type.ERROR) {
                        System.out.println("[ERROR]" + message.getMessage());
                    }
                    return roomState;
                }))
                .Case(RoomEvent.CHANGE_DISPLAY_CHARACTER_BEHAVIOUR, ((state, behavior) -> {
                    return state;
                }))
                .Case(RoomEvent.CHANGE_MOUSEOVER_TYPE, ((state, type) -> {
                    state.mouseOverCharacter = type;
                    return state;
                }))
                .Case(RoomEvent.CHANGE_BEFORE_CHARACTER, ((state, user) -> {
                    if(user.getId() == state.self.getId()){
                        state.beforeSelf = user;
                    }else{
                        state.beforeEnemy = user;
                    }
                    return state;
                }));
    }
}
