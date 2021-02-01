package game.view.reducer;
/*
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.util.ShowPanelUtil;
import game.view.action.ClientEvent;
import game.view.state.RoomState;
import io.game.hub.messageHub.Type;

public class SelectPanelReducer implements IReducer<RoomState> {
    static Type type = Type.JOIN;
    @Override
    public ReducerBuilder<RoomState> apply(Action<?> action, RoomState init) {
        return ReducerBuilder.Create(action, init)
                .Case(ClientEvent.CREATE_ROOM, ((state, responseCode) -> {
                    //とりあえず200OKの時に成功
                    if(responseCode.getCode() == 200) {
                        System.out.println("Success to make room");
                    }
                    return state;
                }))
                .Case(ClientEvent.DELETE_ROOM, ((state, responseCode) -> {
                    System.out.println("Success to delete room");
                    return state;
                }))
                .Case(ClientEvent.STREAM_EVENT, ((state, message) -> {
                    switch (type){
                        case JOIN:
                            System.out.println(message.getUser().getName() + "さんが入室しました");
                            state.joined = true; //入室できなかった場合Errorが返ってくるように実装する
                            ShowPanelUtil.ShowWaitRoomPanel();
                            break;
                        case LEAVE:
                            System.out.println(message.getUser().getName() + "さんが退室しました");
                            //StoreManager.Instance.store.Invoke(new RoomState(), UIEvent.SHOW_SELECTION_PANEL.Create(unit));
                            //state.joined = false;
                            break;
                        case MESSAGE:
                            System.out.println("[" + message.getUser().getName() + "]" + message.getMessage());
                            break;
                        case ERROR:
                            System.out.println("[ERROR]" + message.getMessage());
                            break;
                        default:
                            break;
                    }
                    return state;
                }))
                .Case(ClientEvent.GET_ROOMS, (state, info)->{
                    state.info = info;
                    return state;
                });
    }
}

