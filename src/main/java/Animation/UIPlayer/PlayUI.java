package Animation.UIPlayer;

import game.config.CharaData.*;
import game.config.Character;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;

import java.util.Hashtable;

public class PlayUI {
    private static GraphicsContext gc;
    private static CharacterState stateL,stateR;
    private static Hashtable<Integer,CharacterState> stateTable = new Hashtable<>();
    private static Hashtable<Integer,PlayMeter> meterTable = new Hashtable<>();
    public PlayUI(GraphicsContext gc, User left, User right){
        this.gc = gc;
        meterTable.put(left.getId(),new PlayMeter(gc,true,getHP(left.getCharacterType())));
        meterTable.put(right.getId(),new PlayMeter(gc,true,getHP(right.getCharacterType())));
        stateL = CharacterState.newBuilder().setHP(getHP(left.getCharacterType())).build();
        stateR = CharacterState.newBuilder().setHP(getHP(right.getCharacterType())).build();
        stateTable.put(left.getId(),stateL);
        stateTable.put(right.getId(),stateR);
    }

    private static int getHP(CharacterType type){
        switch (type){
            case Gura :
                return Gura.HP;
            case Inanis :
                return Ina.HP;
            case Calliope :
                return Calli.HP;
            case Amelia :
                return Ame.HP;
            case Kiara:
                return Kiara.HP;
        }
        return 0;
    }

    public static void updateState(CharacterState state){
        stateTable.put(state.getId(),state);
    }

    public static void play(){
        meterTable.forEach((k,v) -> v.play(stateTable.get(k).getHP()));
    }
}
