package Animation.UIPlayer;

import game.config.CharaData.*;
import game.config.Character;
import game.config.PATH;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

public class PlayUI {
    private static GraphicsContext gc;
    private static CharacterState stateL,stateR;
    private static Hashtable<Integer,CharacterState> stateTable = new Hashtable<>();
    private static Hashtable<Integer,PlayMeter> meterTable = new Hashtable<>();
    private static User left,right;
    public PlayUI(GraphicsContext gc, User left, User right){
        this.gc = gc;
        meterTable.put(left.getId(),new PlayMeter(gc,true,getHP(left.getCharacterType())));
        meterTable.put(right.getId(),new PlayMeter(gc,false,getHP(right.getCharacterType())));
        stateL = CharacterState.newBuilder().setHP(getHP(left.getCharacterType())).build();
        stateR = CharacterState.newBuilder().setHP(getHP(right.getCharacterType())).build();
        stateTable.put(left.getId(),stateL);
        stateTable.put(right.getId(),stateR);
        this.left = left;
        this.right = right;
    }

    private static int getHP(CharacterType type){
        int HP =100;
        switch (type){
            case Gura :
                HP = Gura.HP;
                break;
            case Inanis :
                HP = Ina.HP;
                break;
            case Calliope :
                HP = Calli.HP;
                break;
            case Amelia :
                HP = Ame.HP;
                break;
            case Kiara:
                HP = Kiara.HP;
                break;
        }
        return HP;
    }

    public static void updateState(CharacterState state){
        stateTable.put(state.getId(),state);
    }

    public static void play(){
        meterTable.forEach((k,v) -> v.play(stateTable.get(k).getHP()));
//        gc.setFill(Color.PAPAYAWHIP);
//        gc.setFont(Font.font(50));
//        gc.fillText(left.getName(),100,170,300);
//        gc.fillText(right.getName(),980,170,300);
    }

    public static void debug(int value){meterTable.forEach((k,v) -> v.play(value));}
}
