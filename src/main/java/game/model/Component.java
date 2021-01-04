package game.model;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Component {
    private Image image;
    public Vector2 position = new Vector2(0, 0 );
    private Vector2 rotation = new Vector2(0, 0 );
    private Vector2 scale = new Vector2(0,0);
    public void setImage(Image image){
        this.image = image;
        scale.x = (float) image.getWidth();
        scale.y = (float) image.getHeight();
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(float x, float y){
        this.position.x = x;
        this.position.y = y;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, position.x, position.y);
    }

}
