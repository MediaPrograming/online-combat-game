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
import game.config.Character;
import game.config.PATH;
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

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class WaitRoomPanel extends BasePanel<WaitRoomState, IWaitRoom> implements Initializable {
    //List<User> userList = new ArrayList<>();
    @FXML Button showBackButton, showCombatButton;
    @FXML ListView listView;
    @FXML Canvas Self,Enemy;
    @FXML ImageView Gura,Kiara,Ame,Ina,Calli;
    @FXML Button Gr,Kr,Am,In,Cl;
    GraphicsContext gc1,gc2;
    Image self,enemy,gura,kiara,ame,shaark,kikkeriki,wartson,selfVoice,enemyVoice,unlock;
    WaitRoomContainer container;
    CharacterState state1;
    CharaAnimationPlayer player_Gura,player_Kiara,player_Ame;
    boolean gr=false,kr=false,am=false,in=false,cl=false;
    CharacterType selfChara=CharacterType.Inanis,ps=CharacterType.Inanis,enemyChara=CharacterType.Inanis,pe=CharacterType.Inanis;


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
            System.out.println("[isHost]" + state.isHost);
            if(state.isHost) props.GameStartRequest(state.self, state.currentRoom);
            else props.IsReadyRequest(!ok, state.self, state.currentRoom);
        });

        //Host側とclient側でのボタンのテキストの変更
//        String text;
//        text = state.isHost ? "ゲーム開始" : "準備完了";
        //text = isHost ?  "" : "非準備完了";
//        showCombatButton.setText(text);

        //ルーム内のclientに通知
        props.IsReadyRequest(getState().isHost, state.self, state.currentRoom);


        //キャラクター再生用
        state1 = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
        player_Gura = new CharaAnimationPlayer(0, Character.Gura);
        player_Kiara = new CharaAnimationPlayer(1, Character.Kiara);
        player_Ame = new CharaAnimationPlayer(2, Character.Ame);


        //Character用button
        Gr.setOnMouseEntered(event -> {gr = true;});
        Gr.setOnMouseExited(event -> {gr = false;});
        Gr.setOnAction(event -> {props.SetCharacterRequest(CharacterType.Gura_VALUE, state.self, state.currentRoom);});
        Kr.setOnMouseEntered(event -> {kr = true;});
        Kr.setOnMouseExited(event -> {kr= false;});
        Kr.setOnAction(event -> {props.SetCharacterRequest(CharacterType.Kiara_VALUE, state.self, state.currentRoom);});
        Am.setOnMouseEntered(event -> {am = true;});
        Am.setOnMouseExited(event -> {am= false;});
        Am.setOnAction(event -> {props.SetCharacterRequest(CharacterType.Amelia_VALUE, state.self, state.currentRoom);});

        //選択キャラ表示
        gc1 = Self.getGraphicsContext2D();
        gc2 = Enemy.getGraphicsContext2D();
        EffectManager.addGraphicsContext(gc1);
        EffectManager.addGraphicsContext(gc2);
        EffectManager.addGraphicsContext(gc2);
        Path imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\null.png");
        self = new Image(imagePath.toUri().toString());
        enemy =selfVoice=enemyVoice= self;
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Gura\\Gura-Select.png");
        gura = new Image(imagePath.toUri().toString());
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Amelia\\Ameria-Select.png");
        ame = new Image(imagePath.toUri().toString());
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Kiara\\小鳥遊キアラ-Select.png");
        kiara = new Image(imagePath.toUri().toString());
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\unlock.png");
        unlock = new Image(imagePath.toUri().toString());

        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Gura\\Shaaaark.png");
        shaark = new Image(imagePath.toUri().toString());
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Kiara\\Kikkeriki.png");
        kikkeriki = new Image(imagePath.toUri().toString());
        imagePath = Paths.get(PATH.root + "\\src\\main\\resources\\game\\img\\Amelia\\wartson.png");
        wartson = new Image(imagePath.toUri().toString());

    }

    void draw(){
        double rand = Math.random();
        //ImageView
        if(gr){Gura.setImage(player_Gura.play(state1));}else{
            Gura.setImage(AnimationHolder.getCharaAnimation(Character.Gura,Behavior.NORMAL).getAnim()[0][0]);
        }
        if(kr){Kiara.setImage(player_Kiara.play(state1));}else{
            Kiara.setImage(AnimationHolder.getCharaAnimation(Character.Kiara,Behavior.NORMAL).getAnim()[0][0]);
        }
        if(am){Ame.setImage(player_Ame.play(state1));}else{
            Ame.setImage(AnimationHolder.getCharaAnimation(Character.Ame,Behavior.NORMAL).getAnim()[0][0]);
        }
        Ina.setImage(unlock);
        Calli.setImage(unlock);

        //Canvas

        gc1.clearRect(0,0,Self.getWidth(),Self.getHeight());
        gc2.clearRect(0,0,Enemy.getWidth(),Enemy.getHeight());

        getState().currentRoom.getUserList().forEach(e->{
            if(e.getId() == getState().self.getId()){ps = selfChara; selfChara = e.getCharacterType();}
            else {pe = enemyChara; enemyChara = e.getCharacterType();}
        });
        if(ps != selfChara){    //自キャラが変更されたら
            switch (selfChara){
                case Gura :
                    self = gura;
                    selfVoice = shaark;
                    if(rand>0.5){
                        AudioHolder.SHAAAARK.setFramePosition(0);AudioHolder.SHAAAARK.loop(0);}
                    else {AudioHolder.a.setFramePosition(0);AudioHolder.a.loop(0);}
                    break;
                case Kiara:
                    self = kiara;
                    selfVoice = kikkeriki;
                    if(rand>0.5){
                        AudioHolder.Kikkeriki.setFramePosition(0);AudioHolder.Kikkeriki.loop(0);}
                    else {AudioHolder.Kiarayouwannafight.setFramePosition(0);AudioHolder.Kiarayouwannafight.loop(0);}
                    break;
                case Amelia:
                    self = ame;
                    selfVoice = wartson;
                    break;
                default:
            }
            EffectManager.addSelectionWiggle(true,Time.Instance.getTotalTime(),true,(int)Self.getLayoutX(),(int)Self.getLayoutY(),(int)Enemy.getLayoutX(),(int)Enemy.getLayoutY());
        }
        if(pe != enemyChara){  //敵キャラが変更されたら
            switch (enemyChara){
                case Gura :
                    enemy = gura;
                    enemyVoice = shaark;
                    if(rand>0.5){
                        AudioHolder.SHAAAARK.setFramePosition(0);AudioHolder.SHAAAARK.loop(0);}
                    else {AudioHolder.a.setFramePosition(0);AudioHolder.a.loop(0);}
                    break;
                case Kiara:
                    enemy = kiara;
                    enemyVoice = kikkeriki;
                    if(rand>0.5){
                        AudioHolder.Kikkeriki.setFramePosition(0);AudioHolder.Kikkeriki.loop(0);}
                    else {AudioHolder.Kiarayouwannafight.setFramePosition(0);AudioHolder.Kiarayouwannafight.loop(0);}
                    break;
                case Amelia:
                    enemy = ame;
                    enemyVoice = wartson;
                    break;
                default:
            }
            EffectManager.addSelectionWiggle(false,Time.Instance.getTotalTime(),true,(int)Self.getLayoutX(),(int)Self.getLayoutY(),(int)Enemy.getLayoutX(),(int)Enemy.getLayoutY());
        }
        gc1.drawImage(self,0,0,Enemy.getWidth(),Enemy.getHeight());
        gc2.drawImage(enemy,Enemy.getWidth(),0,-Enemy.getWidth(),Enemy.getHeight());
        gc1.drawImage(selfVoice,Self.getWidth()/8,Self.getHeight()-Self.getHeight()/3,400,90);
        gc2.drawImage(enemyVoice,Enemy.getWidth()/8,Enemy.getHeight()-Enemy.getHeight()/3,400,90);
        EffectManager.play();
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
