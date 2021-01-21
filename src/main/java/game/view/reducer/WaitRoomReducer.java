package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */

import com.taku.util.flux.model.Action;
import com.taku.util.flux.model.Store;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.ClientEvent;
import game.view.action.RoomEvent;
import game.view.action.UIEvent;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.Type;
import io.grpc.BindableService;

import javax.management.remote.JMXServerErrorException;

public class WaitRoomReducer implements IReducer<WaitRoomState> {
    @Override
    public ReducerBuilder<WaitRoomState> apply(Action<?> action, WaitRoomState init) {
        var unit = new Unit();
        return ReducerBuilder.Create(action, init)
                .Case(RoomEvent.START_GAME, ((state, room) -> {
                    StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_UO_PANEL.Create(unit));
                    return state;
                }))
                .Case(ClientEvent.STREAM_EVENT, ((roomState, message) -> {

                    if(message.getType() == Type.JOIN){
                    }else if(message.getType() == Type.LEAVE){
                        //HostがLEAVEした場合,もしくは自分が退出した場合はStart画面に移動する
                        if(message.getUser().getId() == roomState.currentRoom.getHostId() || message.getUser().getId() == roomState.self.getId()) {
                            System.out.println("Start画面の表示");
                            StoreManager.Instance.client.grpcRoom = null;
                            StoreManager.Instance.client.user = null;
                            StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_START_PANEL.Create(unit));
                        }else {
                            roomState.currentRoom = message.getRoom();
                        }
                    }else if(message.getType() == Type.UPDATE){
                        roomState.currentRoom = message.getRoom();
                        if(message.getUser().getId() == roomState.self.getId())
                            roomState.self = message.getUser();
                    }else if(message.getType() == Type.GAME_START){
                        //UOPanelに移動
                        System.out.println("UOパネルの表示");
                        StoreManager.Instance.client.user = message.getRoom().getUserList().stream().filter(x->x.getId() == roomState.self.getId()).findFirst().get();
                        StoreManager.Instance.client.grpcRoom = message.getRoom();
                        StoreManager.Instance.store.Invoke(unit, UIEvent.SHOW_UO_PANEL.Create(unit));
                    }
                    else if(message.getType() == Type.ERROR){
                        System.out.println("[ERROR]" + message.getMessage());
                    }
                    return roomState;
                }));
    }
}
