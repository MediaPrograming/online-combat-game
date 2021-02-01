package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;
import io.game.hub.positionHub.CharacterState;
import javafx.scene.canvas.GraphicsContext;
import io.game.hub.positionHub.PositionHubMessage;
public final class UoPanelEvent {
    public static final ActionCreator<String> UPDATE_INPUT_PRESSED = new ActionCreator<>("UPDATE_INPUT_PRESSED");
    public static final ActionCreator<String> UPDATE_INPUT_RELEASED = new ActionCreator<>("UPDATE_INPUT_RELEASED");
    public static final ActionCreator<GraphicsContext> UPDATE_CHARACTER_TABLE = new ActionCreator<>("UPDATE_CHARACTER_TABLE");
    public static final ActionCreator<PositionHubMessage> STREAM_EVENT = new ActionCreator<>("STREAM_EVENT");
    public static final ActionCreator<Unit> CONTINUE = new ActionCreator<>("CONTINUE_GAME");
    public static final ActionCreator<Unit> QUIT = new ActionCreator<>("QUIT_GAME");
    public static final ActionCreator<CharacterState> STATE_UPDATE = new ActionCreator<>("STATE_UPDATE");
}
