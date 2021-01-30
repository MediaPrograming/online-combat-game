package game.view.panel;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.PlayGura;
import com.taku.util.flux.view.BasePanel;
import game.config.Character;
import game.view.container.FetchContainer;
import game.view.container.WaitRoomContainer;
import game.view.service.IFetch;
import game.view.service.IWaitRoom;
import game.view.state.RoomState;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WaitRoomPanel extends BasePanel<WaitRoomState, IWaitRoom> implements Initializable {
    //List<User> userList = new ArrayList<>();
    @FXML Button showBackButton, showCombatButton;
    @FXML ListView listView, characterList;
    @FXML Canvas Self,Enemy;
    @FXML ImageView Gura,Kiara,Ame,Ina,Calli;
    WaitRoomContainer container;
    CharacterState state1;
    CharaAnimationPlayer player;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container = new WaitRoomContainer(this);
        var props = this.getProps();
        var state = this.getState();
        props.GetRoomUserRequest(state.self);

        //CharacterList
        for (int i = 0; i <= CharacterType.Inanis_VALUE; i++) {
            state.characters.add(CharacterType.forNumber(i).name());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList();
        state.characters.forEach(observableList::add);
        characterList.setItems(observableList);
        characterList.setOnMouseClicked(e -> {
            System.out.println("Select");
            var index = characterList.getSelectionModel().getSelectedIndex();
            props.SetCharacterRequest(index, state.self, state.currentRoom);
        });

        //room内のUserの更新
        showBackButton.setOnAction(e -> props.showBackPanel(state.self, state.currentRoom));
        showCombatButton.setOnAction(e -> {
            var ok = state.self.getIsReady();
            System.out.println("[isHost]" + state.isHost);
            if(state.isHost) props.GameStartRequest(state.self, state.currentRoom);
            else props.IsReadyRequest(!ok, state.self, state.currentRoom);
        });

        //Host側とclient側でのボタンのテキストの変更
        String text;
        text = state.isHost ? "ゲーム開始" : "準備完了";
        //text = isHost ?  "" : "非準備完了";
        showCombatButton.setText(text);

        //ルーム内のclientに通知
        props.IsReadyRequest(getState().isHost, state.self, state.currentRoom);


        //キャラクター再生用
        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
        player = new CharaAnimationPlayer(0, Character.Gura);
    }

    void draw(){
        //ImageView
        Gura.setImage(player.play(state1));
    }

    @Override
    public void EveryFrameUpdate() {
        var state = this.getState();
        var props = this.getProps();

        //ボタンのテキストの更新
        var isOk = getState().self.getIsReady();

        if(state.self == null) {
            System.out.println("User(Self)の初期化が行われていません. Errorが起きていないか確認してください");
            System.exit(0);
        }
        var room = state.currentRoom;
        if(room == null) {
            System.out.println("UserクラスのRoom変数の初期化が行われていません. サーバー側でErrorが起きていないか確認してください");
            return;
        }

        var list = room.getUserList();

        //めんどくさいから今は毎フレーム状態更新していくよ
        //if(!list.equals(userList)){
            //userList = list;
            ObservableList<String> observableList = FXCollections.observableArrayList();;
            var userNames = list
                    .stream()
                    .filter(u-> u != state.self)
                    .map(u ->u.getName() + (u.getIsReady() ? "準備完了" : "準備中")
                            + "[" + u.getCharacterType().name()+ "]"
                    );
            userNames.forEach(observableList::add);
            listView.setItems(observableList);
        //

        draw();
    }
}
