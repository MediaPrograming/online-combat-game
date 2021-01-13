package game.view.container;

import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.ClientEvent;
import game.view.panel.WaitRoomPanel;
import game.view.service.IWaitRoom;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.Type;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
public class WaitRoomContainer {
    public WaitRoomContainer(WaitRoomPanel panel){
        panel.connect(new WaitRoomState(), state-> state, dispatcher -> new IWaitRoom() {
            @Override
            public void CombatStartRequest() {
                var state = panel.getState();
                if(state.userInfo == null) return;
                //ここから書きましょう
                StoreManager.Instance.store.Invoke(new Unit(),ClientEvent.STREAM_EVENT
                        .Create(Message.newBuilder().setUser(state.userInfo).setType(Type.MESSAGE).build()));
            }

            @Override
            public void DeleteRoomRequest() { }
        });
    }
}
