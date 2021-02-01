package game.store;

import com.taku.util.flux.model.Store;
import game.client.GrpcClient;
import game.view.action.ClientEvent;
import game.view.reducer.*;
import game.view.stage.MainStage;
import game.view.state.RoomState;
import game.view.state.UoPanelState;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.Type;
import io.grpc.stub.StreamObserver;
import javafx.util.Pair;


public final class StoreManager {
    public static StoreManager getInstance(){
        if(Instance == null) Instance = new StoreManager();
        return Instance;
    }
    private static StoreManager Instance;
    public static MainStage stage = new MainStage();
    public GrpcClient client = new GrpcClient();
    public Store store = Store.CreateStore(
            new Pair<>(RoomState.class,new SelectPanelReducer()),
            new Pair<>(WaitRoomState.class, new WaitRoomReducer()),
            new Pair<>(UoPanelState.class, new UoPanelReducer())
    );
    private StoreManager(){}
}
