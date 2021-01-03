package game.view.reducer;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.action.ClientEvent;
import game.view.state.RoomState;
import io.game.hub.messageHub.Type;
import io.game.hub.messageHub.UnitRequest;

public class FetchReducer  implements IReducer<RoomState> {
    static Type type = Type.JOIN;
    @Override
    public ReducerBuilder<RoomState> apply(Action<?> action, RoomState init) {
        UnitRequest unitRequest = UnitRequest.newBuilder().build(); //中身が空のrequest

        return ReducerBuilder.Create(action, init)
                .Case(ClientEvent.CREATE_ROOM, ((state, responseCode) -> {
                    //とりあえず200OKの時に成功
                    System.out.println("Success to make room");
                    //state.joined = responseCode.getCode() == 200;
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
                            break;
                        case LEAVE:
                            System.out.println(message.getUser().getName() + "さんが退室しました");
                            state.joined = false;
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

