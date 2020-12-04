package view.main;

import com.taku.util.function.Action0.Action;
import com.taku.util.model.Unit;
import model.VCharacter;
import com.taku.util.ui.Event;

import java.util.function.Function;

public final class UIEvent {
    public static final String SetFocusType = "SetFocus";
    public static final String ShowSelectPanelType = "ShowSelectionPanel";
    public static final String ShowStartPanelType = "ShowStartPanel";
    public static final String ShowCombatPanelType = "ShowCombatPanel";
    public static final String SelectCharacterEventType = "SelectEvent";

    public static final Function<Unit, Event> setFocus = (unit) -> Event.Create(SetFocusType);
    public static final Function<Unit, Event> ShowSelectionPanel = (unit) -> Event.Create(ShowSelectPanelType);
    public static final Function<Unit, Event> ShowStartPanel = (unit) ->  Event.Create(ShowStartPanelType);
    public static final  Function<Unit, Event> ShowCombatPanel = (unit) -> Event.Create(ShowCombatPanelType);
    public static final  Function<VCharacter, Event> SelectCharacterEvent = (unit) -> Event.Create(SelectCharacterEventType);
}
