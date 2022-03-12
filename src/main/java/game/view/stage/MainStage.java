package game.view.stage;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Store;
import game.store.StoreManager;
import game.util.RequestUtil;
import game.util.ShowPanelUtil;
import game.view.action.ClientEvent;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.Message;
import io.game.hub.messageHub.ResponseCode;
import io.game.hub.messageHub.RoomMessage;
import io.game.hub.messageHub.Type;
import io.grpc.stub.StreamObserver;
import javafx.stage.Stage;
import game.config.PATH;
import Audio.AudioClip;
import Audio.AudioPlayer;
import java.io.File;
/**
 * This is stage of prototype
 */
public class MainStage extends Stage {
    public MainStage(){
        this.setTitle("HoloRagnarok - holoMyth Fighting Game -");
//        this.setWidth(Config.WINDOW_WIDTH);
//        this.setHeight(Config.WINDOW_HEIGHT);
        this.setOnCloseRequest(e -> {
            System.out.println("Stage close");

        });
    }

    public void Exit(){
        System.out.println("Close");

        var client = StoreManager.getInstance().client;
        if(!client.IsConnected()) return;
        if(StoreManager.getInstance().client.user != null && StoreManager.getInstance().client.grpcRoom != null) {
            var message = Message.newBuilder().setType(Type.LEAVE).setUser(client.user).setRoom(client.grpcRoom).build();
            var observer = client.stub.streamEvent(new StreamObserver<Message>() {
                @Override
                public void onNext(Message value) {
                    StoreManager.getInstance().store.Invoke(new WaitRoomState(client.user, client.grpcRoom), ClientEvent.STREAM_EVENT.Create(value));
                }

                @Override
                public void onError(Throwable t) {
                }

                @Override
                public void onCompleted() {
                }
            });
            observer.onNext(message);
        }
        client.Close();
        //stage.close();
//        stage = null;
//        Instance = null;
        this.close();
    }


}
