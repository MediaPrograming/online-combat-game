package game.view.panel;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import Animation.AnimationHolder;
import Animation.CharaAnimationPlayer;
import Animation.EffectPlayer.EffectManager;
import Audio.AudioHolder;
import com.taku.util.flux.view.BasePanel;
import game.config.PATH;
import game.store.StoreManager;
import game.util.Time;
import game.view.container.WaitRoomContainer;
import game.view.service.IWaitRoom;
import game.view.state.WaitRoomState;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.Clip;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaitRoomPanel extends BasePanel<WaitRoomState, IWaitRoom> implements Initializable {
    //List<User> userList = new ArrayList<>();
    @FXML Button showBackButton, showCombatButton;
    @FXML ListView listView;
    @FXML Canvas Self,Enemy;
    @FXML ImageView Gura,Kiara,Ame,Ina,Calli;
    @FXML Button Gr,Kr,Am,In,Cl;
    GraphicsContext gc1,gc2;
    Image selfImg, enemyImg,selfVoice,enemyVoice,unlock;
    WaitRoomContainer container;
    ArrayList<DisplayCharacter> characters = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container = new WaitRoomContainer(this);
        var props = this.getProps();
        var state = this.getState();
        props.GetRoomUserRequest(state.self);

        //room内のUserの更新
        showBackButton.setOnAction(e -> props.showBackPanel(state.self, state.currentRoom));
        showCombatButton.setOnAction(e -> {
            var ok = state.self.getIsReady();
            if(state.isHost) props.GameStartRequest(state.self, state.currentRoom);
            else props.IsReadyRequest(!ok, state.self, state.currentRoom);
        });
        //ルーム内のclientに通知
        props.IsReadyRequest(getState().isHost, state.self, state.currentRoom);
        //キャラクター再生用
        //Character用button
        Gr.setOnMouseEntered(event -> props.ChangeMouseOverCharacter(CharacterType.Gura));
        Gr.setOnMouseExited(event -> props.ChangeMouseOverCharacter(CharacterType.UNRECOGNIZED));
        Gr.setOnAction(event -> props.SetCharacterRequest(CharacterType.Gura_VALUE, state.self, state.currentRoom));
        Kr.setOnMouseEntered(event -> props.ChangeMouseOverCharacter(CharacterType.Kiara));
        Kr.setOnMouseExited(event -> props.ChangeMouseOverCharacter(CharacterType.UNRECOGNIZED));
        Kr.setOnAction(event -> props.SetCharacterRequest(CharacterType.Kiara_VALUE, state.self, state.currentRoom));
        Am.setOnMouseEntered(event -> props.ChangeMouseOverCharacter(CharacterType.Amelia));
        Am.setOnMouseExited(event -> props.ChangeMouseOverCharacter(CharacterType.UNRECOGNIZED));
        Am.setOnAction(event -> props.SetCharacterRequest(CharacterType.Amelia_VALUE, state.self, state.currentRoom));

        //選択キャラ表示
        gc1 = Self.getGraphicsContext2D();
        gc2 = Enemy.getGraphicsContext2D();
        EffectManager.addGraphicsContext(gc1);
        EffectManager.addGraphicsContext(gc2);
        EffectManager.addGraphicsContext(gc2);


        selfImg = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\null.png"));
        enemyImg = selfVoice = enemyVoice = selfImg;

        unlock = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\unlock.png"));

        //Gura
        var gura = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Gura\\Gura-Select.png"));
        var shaark = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Gura\\Shaaaark.png"));
        characters.add(new DisplayCharacter(
                CharacterType.Gura,
                Gura,
                new CharaAnimationPlayer(0, CharacterType.Gura),
                gura,
                shaark,
                AudioHolder.SHAAAARK,
                AudioHolder.a
                ));

        //Kiara
        var kiara = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Kiara\\小鳥遊キアラ-Select.png"));
        var kikkeriki = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Kiara\\Kikkeriki.png"));
        characters.add(new DisplayCharacter(
                CharacterType.Kiara,
                Kiara,
                new CharaAnimationPlayer(1, CharacterType.Kiara),
                kiara,
                kikkeriki,
                AudioHolder.Kikkeriki,
                AudioHolder.Kiarayouwannafight
        ));

        //Amelia
        var ame = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Amelia\\Ameria-Select.png"));
        var wartson = new Image(StoreManager.class.getResourceAsStream(PATH.img + "\\Amelia\\wartson.png"));
        characters.add(new DisplayCharacter(
                CharacterType.Amelia,
                Ame,
                new CharaAnimationPlayer(2, CharacterType.Amelia),
                ame,
                wartson,
                null,
                null
        ));
    }

    void draw(){
        var state = getState();
        for (DisplayCharacter character : characters) {
            character.drawView(state.mouseOverCharacter, state.displayState);
        }

        Ina.setImage(unlock);
        Calli.setImage(unlock);

        //Canvas
        gc1.clearRect(0,0,Self.getWidth(),Self.getHeight());
        gc2.clearRect(0,0,Enemy.getWidth(),Enemy.getHeight());

        if(state.beforeSelf.getCharacterType() != state.self.getCharacterType()){    //自キャラが変更されたら
            characters.stream().filter(x -> x.type == state.self.getCharacterType()).forEach(y ->{
                y.playAudio();
                selfImg = y.img;
            });
            EffectManager.addSelectionWiggle(true,Time.Instance.getTotalTime(),true,(int)Self.getLayoutX(),(int)Self.getLayoutY(),(int)Enemy.getLayoutX(),(int)Enemy.getLayoutY());
        }
//        var users = state.currentRoom.getUserList().stream().filter(x -> state.self.getId() != x.getId());
        if(state.currentRoom.getUserList().stream().count() > 1) {
            var enemy = state.currentRoom.getUserList().stream().filter(x -> x.getId() != state.self.getId()).findFirst().get();
            if (state.beforeEnemy == null || state.beforeEnemy.getCharacterType() != enemy.getCharacterType()) {  //敵キャラが変更されたら
                characters.stream().filter(x -> x.type == enemy.getCharacterType()).forEach(y -> {
                    y.playAudio();
                    enemyImg = y.img;
                });
                EffectManager.addSelectionWiggle(false, Time.Instance.getTotalTime(), true, (int) Self.getLayoutX(), (int) Self.getLayoutY(), (int) Enemy.getLayoutX(), (int) Enemy.getLayoutY());
            }
        }
        state.currentRoom.getUserList().forEach(x -> getProps().ChangeBeforeCharacter(x));
        gc1.drawImage(selfImg,0,0,Enemy.getWidth(),Enemy.getHeight());
        gc2.drawImage(enemyImg,Enemy.getWidth(),0,-Enemy.getWidth(),Enemy.getHeight());
        gc1.drawImage(selfVoice,Self.getWidth()/8,Self.getHeight()-Self.getHeight()/3,400,90);
        gc2.drawImage(enemyVoice,Enemy.getWidth()/8,Enemy.getHeight()-Enemy.getHeight()/3,400,90);
        EffectManager.play();
    }

    @Override
    public void EveryFrameUpdate() {
        var state = this.getState();
        //ボタンのテキストの更新
        if(state.self == null) {
            System.out.println("User(Self)の初期化が行われていません. Errorが起きていないか確認してください");
            StoreManager.stage.Exit();
        }
        var room = state.currentRoom;
        if(room == null) {
            System.out.println("UserクラスのRoom変数の初期化が行われていません. サーバー側でErrorが起きていないか確認してください");
            return;
        }

        var list = room.getUserList();
        ObservableList<String> observableList = FXCollections.observableArrayList();;
        var userNames = list
                .stream()
                .filter(u-> u != state.self)
                .map(u ->u.getName() + (u.getIsReady() ? "準備完了" : "準備中")
                        + "[" + u.getCharacterType().name()+ "]"
                );
        userNames.forEach(observableList::add);
        listView.setItems(observableList);

        draw();
    }
}
class DisplayCharacter {
    public final Image img, voiceImg;
    public final Clip audioClip1, audioClip2;
    public final CharacterType type;
    public final ImageView imageView;
    CharaAnimationPlayer player;
    public DisplayCharacter(CharacterType type, ImageView imageView, CharaAnimationPlayer player, Image img, Image voiceImg, Clip audioClip1, Clip audioClip2){
        this.type = type;
        this.imageView = imageView;
        this.img = img;
        this.voiceImg = voiceImg;
        this.audioClip1 = audioClip1;
        this.audioClip2 = audioClip2;
        this.player = player;
    }
    public void playAudio(){
        var rand = Math.random();
        if(rand>0.5){
            if(audioClip1 != null) {audioClip1.setFramePosition(0); audioClip1.loop(0);}
        }
        else {
            if(audioClip2 != null){ audioClip2.setFramePosition(0); audioClip2.loop(0);}
        }
    }

    public void drawView(CharacterType characterType, CharacterState state){

        if (this.type == characterType) {
            imageView.setImage(player.play(state));
        } else {
            imageView.setImage(AnimationHolder.getCharaAnimation(this.type, Behavior.NORMAL).getAnim()[0][0]);
        }
    }
}
