package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;
import io.game.hub.positionHub.CharacterState;

public final class AnimationEvent {
    public static final ActionCreator<Unit> SHOW_ANIMATION = new ActionCreator<>("SHOW_ANIMATION");

    public static final ActionCreator<CharacterState> STATE_UPDATE = new ActionCreator<>("STATE_UPDATE");
}
