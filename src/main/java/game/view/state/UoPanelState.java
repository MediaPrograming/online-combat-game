package game.view.state;

import Animation.CharaAnimationPlayer;
import Animation.CharacterPlayer.*;
import Animation.DrawPolygon;
import game.config.Character;
import game.config.PATH;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import io.grpc.Grpc;
import javafx.scene.image.Image;

import javax.sound.sampled.Clip;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Timer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public final class UoPanelState {
    public final User self;
    public final GrpcRoom room;
    public CharacterState selfState;
    public Input input;
    public boolean quitPaneVisible = false;
    public BlockingQueue<CharacterState> stateBlockingQueue;
    public final Hashtable<Integer, DrawPolygon> polyTable;
    public final Hashtable<Integer, CharacterType> charaTable;
    public final Hashtable<Integer, PlayCharacter> playerTable;
    public Timer timer;
    public Clip bgm;
    public Image floor,back,kusa;
    public UoPanelState(User self, GrpcRoom room){
        this.self = self;
        this.room = room;
        input = Input.newBuilder().setW(false).setA(false).setS(false).setD(false).setK(false)
                .setId(self.getId())
                .setRoomName(self.getRoomName())
                .build();
        selfState = CharacterState.newBuilder().setBehavior(Behavior.NORMAL).build();
        stateBlockingQueue = new LinkedBlockingDeque<>();
        polyTable = new Hashtable<>();
        charaTable = new Hashtable<>();
        playerTable = new Hashtable<>();
        timer = new Timer();

        Path imagePath = Paths.get(PATH.Back);
        back  = new Image(getClass().getResourceAsStream(imagePath.toUri().toString()),1280,720,false,false);
        Path imagPath = Paths.get(PATH.Floor);
        floor = new Image(getClass().getResourceAsStream(imagPath.toUri().toString()),1280,720,false,false);
        Path imaPath = Paths.get(PATH.Kusa);
        kusa = new Image(getClass().getResourceAsStream(imaPath.toUri().toString()),1280,720,false,false);
    }


}
