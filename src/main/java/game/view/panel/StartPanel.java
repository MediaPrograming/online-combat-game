package game.view.panel;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import Animation.Animation;
import Animation.AnimationHolder;
import com.taku.util.flux.view.PurePanel;
import game.util.ShowPanelUtil;
import game.util.Time;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import game.config.PATH;
import Audio.AudioPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class StartPanel extends PurePanel implements Initializable{
    @FXML
    private Button showCreateRoomButton ,showSelectionButton, quitButton;
    @FXML
    private Canvas canvas;
    GraphicsContext gc;
    int gura,kiara,ame;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AudioPlayer.Play(PATH.HomeBGM);
      
        showCreateRoomButton.setOnAction(e -> ShowPanelUtil.ShowCreateRoomPanel());
        showSelectionButton.setOnAction(e -> ShowPanelUtil.ShowSelectionPanel());
        quitButton.setOnAction(e -> System.exit(0));

        gc = canvas.getGraphicsContext2D();
        gura = 1280;
        kiara = gura + 300;
        ame = kiara + 300;

    }

    void draw(){
        int dx = 1;
        gura -= dx;
        kiara -= dx;
        ame -= dx;

        if(gura < -300) gura = (int)canvas.getWidth()+300;
        if(kiara < -300) kiara = (int)canvas.getWidth()+300;
        if(ame < -300) ame = (int)canvas.getWidth()+300;


        gc.clearRect(canvas.getLayoutX(),canvas.getLayoutY(),canvas.getWidth(), canvas.getHeight());
        gc.drawImage(getRunAnim(CharacterType.Gura),gura,350,-100,100);
        gc.drawImage(getRunAnim(CharacterType.Kiara),kiara,350,-150,150);
        gc.drawImage(getRunAnim(CharacterType.Amelia),ame,350,-100,100);

    }

    @Override
    public void EveryFrameUpdate() {
        draw();
    }

    Image getRunAnim(CharacterType type){
        Animation anim = AnimationHolder.getCharaAnimation(type, Behavior.RUN);
        return anim.getAnim()[(int)(Time.Instance.getTotalTime()*anim.getSpeed())%anim.getAnim().length][(int)(Time.Instance.getTotalTime()/anim.getAnim().length*anim.getSpeed())%anim.getAnim()[0].length];
    }

}