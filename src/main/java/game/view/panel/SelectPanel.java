package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.view.BasePanel;
import game.view.container.FetchContainer;
import game.view.service.IFetch;
import game.view.state.RoomState;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class SelectPanel extends BasePanel<RoomState, IFetch> implements Initializable {
    List<GrpcRoom> roomList = new ArrayList<>();
    @FXML private ListView listView;
    @FXML private Button updateButton, joinButton;
    @FXML private TextField usernameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FetchContainer(this);
        updateButton.setOnAction(e -> this.getProps().GetRoomRequest());
        joinButton.setOnAction(e -> Join());
    }
    public void Join(){
        var index = listView.getSelectionModel().getSelectedIndex();
        var room = roomList.get(index);
        var id = UUID.randomUUID().hashCode();
        var user = User.newBuilder().setId(id).setName(usernameField.getText()).setRoomInfo(room).build();
        this.getProps().JoinRequest(user, room);
    }

    @Override
    public void EveryFrameUpdate() {
        var state = this.getState();

        //ルームの状態の更新
        if(state.info == null)return;
        var list = state.info.getRoomList();

        if(!list.equals(roomList)){
            roomList = list;
            ObservableList<String> observableList = FXCollections.observableArrayList();;
            var roomNames = roomList.stream().map(GrpcRoom::getRoomName);
            roomNames.forEach(observableList::add);
            listView.setItems(observableList);
        }
    }
}
