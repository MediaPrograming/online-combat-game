package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.model.Unit;

public final class AnimationEvent {
    public static final ActionCreator<Unit> SHOW_ANIMATION = new ActionCreator<>("SHOW_ANIMATION");
}
