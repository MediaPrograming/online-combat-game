package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.PositionHubMessage;

public final class AnimationEvent {
    public static final ActionCreator<Unit> SHOW_ANIMATION = new ActionCreator<>("SHOW_ANIMATION");
    public static final ActionCreator<PositionHubMessage> STREAM_EVENT = new ActionCreator<>("STREAM_EVENT");
    public static final ActionCreator<Unit> CONTINUE = new ActionCreator<>("CONTINUE_GAME");
    public static final ActionCreator<Unit> QUIT = new ActionCreator<>("QUIT_GAME");
    public static final ActionCreator<CharacterState> STATE_UPDATE = new ActionCreator<>("STATE_UPDATE");
}
