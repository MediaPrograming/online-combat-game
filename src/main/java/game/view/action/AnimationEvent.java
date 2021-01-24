package game.view.action;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.Input;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.nio.charset.IllegalCharsetNameException;
import io.game.hub.positionHub.PositionHubMessage;
public final class AnimationEvent {
    public static final ActionCreator<String> UPDATE_INPUT_PRESSED = new ActionCreator<>("UPDATE_INPUT_PRESSED");
    public static final ActionCreator<String> UPDATE_INPUT_RELEASED = new ActionCreator<>("UPDATE_INPUT_RELEASED");
    public static final ActionCreator<GraphicsContext> UPDATE_CHARACTER_TABLE = new ActionCreator<>("UPDATE_CHARACTER_TABLE");
    public static final ActionCreator<Unit> START_AUDIO = new ActionCreator("START_AUDIO");
    public static final ActionCreator<Unit> STOP_AUDIO = new ActionCreator("STOP_AUDIO");
    public static final ActionCreator<PositionHubMessage> STREAM_EVENT = new ActionCreator<>("STREAM_EVENT");
    public static final ActionCreator<Unit> CONTINUE = new ActionCreator<>("CONTINUE_GAME");
    public static final ActionCreator<Unit> QUIT = new ActionCreator<>("QUIT_GAME");
    public static final ActionCreator<CharacterState> STATE_UPDATE = new ActionCreator<>("STATE_UPDATE");
}
