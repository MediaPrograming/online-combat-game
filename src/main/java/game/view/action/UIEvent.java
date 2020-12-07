package game.view.action;

import com.taku.util.function.Function.VoidFunction;
import com.taku.util.model.Unit;
import game.model.VCharacter;
import com.taku.util.ui.Event;

import java.util.function.Function;

public final class UIEvent {
    public static final String SetFocusType = "SetFocus";
    public static final String ShowSelectPanelType = "ShowSelectionPanel";
    public static final String ShowStartPanelType = "ShowStartPanel";
    public static final String ShowCombatPanelType = "ShowCombatPanel";
    public static final String SelectCharacterEventType = "SelectEvent";

    public static final VoidFunction<Event> setFocus = () -> Event.Create(SetFocusType);
    public static final VoidFunction<Event> ShowSelectionPanel = () -> Event.Create(ShowSelectPanelType);
    public static final VoidFunction<Event> ShowStartPanel = () ->  Event.Create(ShowStartPanelType);
    public static final VoidFunction<Event> ShowCombatPanel = () -> Event.Create(ShowCombatPanelType);
    public static final Function<VCharacter, Event> SelectCharacterEvent = (unit) -> Event.Create(SelectCharacterEventType);
}
