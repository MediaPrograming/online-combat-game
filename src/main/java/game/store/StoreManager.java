package game.store;

import com.taku.util.flux.model.Store;
import com.taku.util.model.Unit;
import game.client.GrpcClient;
import game.view.reducer.*;
import game.view.stage.MainStage;
import game.view.state.RoomState;
import game.view.state.UoPanelState;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.Scene;
import javafx.util.Pair;


public final class StoreManager {
    public static StoreManager Instance = new StoreManager();    //再代入不可
    public static MainStage stage = new MainStage();
    public GrpcClient client = new GrpcClient();
    public Store store = Store.CreateStore(
            new Pair<>(Unit.class, new ShowPanelReducer()),
            new Pair<>(RoomState.class,new FetchReducer()),
            new Pair<>(WaitRoomState.class, new WaitRoomReducer()),
            new Pair<>(UoPanelState.class, new UoPanelReducer())
    );
    private StoreManager(){}

    public void Close(){
        stage.close();
        stage = null;
        Instance = null;
    }
}
