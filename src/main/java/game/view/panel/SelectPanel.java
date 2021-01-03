package game.view.panel;

import com.taku.util.flux.view.BasePanel;
import game.view.container.FetchContainer;
import game.view.service.IFetch;
import game.view.state.RoomState;
import io.game.hub.messageHub.GrpcRoom;
import io.grpc.Grpc;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import javax.security.auth.login.AccountLockedException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.ResourceBundle;

public class SelectPanel extends BasePanel<RoomState, IFetch> implements Initializable {
    List<GrpcRoom> roomList = new ArrayList<>();
    @FXML private ListView listView;
    @FXML private Button updateButton;
    @FXML private TextField usernameField;
    Pane pane = new Pane();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FetchContainer(this);
        var props = this.getProps();
        updateButton.setOnAction(e -> props.GetRoomRequest());
    }

    @Override
    public void EveryFrameUpdate() {
        var state = this.getState();

        //ルームの状態の更新
        if(state.info == null)return;
        var list = state.info.getRoomList();

        if(!list.equals(roomList)){
            roomList = list;
            for (GrpcRoom grpcRoom : roomList) {
                var roomName = grpcRoom.getRoomName();
                var hostName = grpcRoom.getHostName();
                Button button = new Button();
                button.setText(roomName);
                button.setOnAction(e -> {System.out.println("Join");});
                var roomNames = roomList.stream().map(x -> x.getRoomName());
                ObservableList<String> lm = FXCollections.observableArrayList();
                roomNames.forEach(x -> {
                    lm.add(x);
                });

                listView.setItems(lm);
            }
        }
        //System.out.println("Total Time : " + Time.Instance.getTotalTime()+ "\n delta Time : " + Time.Instance.getDeltaTime());
    }

}
