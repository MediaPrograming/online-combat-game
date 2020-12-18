package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Vector2;

import java.util.Vector;

public class ChEvent {
    private static final String OperaiteType = "Op";
    public static final ActionCreator<Vector2> Operaite = new ActionCreator(OperaiteType);

}
