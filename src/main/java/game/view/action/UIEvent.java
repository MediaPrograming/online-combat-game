package game.view.action;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;
import io.game.hub.messageHub.GrpcRoom;

public final class UIEvent {

    public static final ActionCreator<Unit> INIT = new ActionCreator<>("INIT");
    public static final ActionCreator<Unit> SHOW_SELECTION_PANEL = new ActionCreator<>("SHOW_SELECTION_PANEL");
    public static final ActionCreator<Unit> SHOW_START_PANEL =  new ActionCreator<>("SHOW_START_PANEL");
    public static final ActionCreator<Unit> SHOW_COMBAT_PANEL = new ActionCreator<>("SHOW_COMBAT_PANEL");
    public static final ActionCreator<Unit> SHOW_CREATE_PANEL = new ActionCreator<>("SHOW_CREATE_PANEL");
    public static final ActionCreator<GrpcRoom> SHOW_WAIT_ROOM_PANEL = new ActionCreator<>("SHOW_WAIT_ROOM_PANEL");
    public static final ActionCreator<Unit> SHOW_UO_PANEL = new ActionCreator<>("SHOW_UO_PANEL");
}
