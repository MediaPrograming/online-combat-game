package Animation;

import game.config.CharaData.*;
import game.config.Character;
import io.game.hub.messageHub.CharacterType;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class DrawPolygon {
    private int ID;
    private int x,y,width,height;
    public DrawPolygon(){
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public void update(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void updateChara(int x, int y, CharacterType type){
        this.x = x;
        this.y = y;
        switch (type){
            case Gura :
                this.width = Gura.width;
                this.height = Gura.height;
                break;
            case Calliope:
                this.width = Calli.width;
                this.height = Calli.height;
                break;
            case Inanis:
                this.width = Ina.width;
                this.height = Ina.height;
                break;
            case Amelia:
                this.width = Ame.width;
                this.height = Ame.height;
                break;
            case Kiara:
                this.width = Kiara.width;
                this.height = Kiara.height;
                break;
        }
    }


    public void play(GraphicsContext gc){
        gc.setFill(Color.GREENYELLOW);
        gc.strokeRect(x,y,width,height);
    }
}
