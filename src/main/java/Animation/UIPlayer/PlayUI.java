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
        meterTable.put(right.getId(),new PlayMeter(gc,false,getHP(right.getCharacterType())));
        stateL = CharacterState.newBuilder().setHP(getHP(left.getCharacterType())).build();
        stateR = CharacterState.newBuilder().setHP(getHP(right.getCharacterType())).build();
        stateTable.put(left.getId(),stateL);
        stateTable.put(right.getId(),stateR);
    }

    private static int getHP(CharacterType type){
        int HP =100;
        switch (type){
            case Gura :
                HP = Gura.HP;
            case Inanis :
                HP = Ina.HP;
            case Calliope :
                HP = Calli.HP;
            case Amelia :
                HP = Ame.HP;
            case Kiara:
                HP = Kiara.HP;
        }
        return HP;
    }

    public static void updateState(CharacterState state){
        stateTable.put(state.getId(),state);
    }

    public static void play(){
        meterTable.forEach((k,v) -> v.play(stateTable.get(k).getHP()));
    }

    public static void debug(int value){meterTable.forEach((k,v) -> v.play(value));}
}
