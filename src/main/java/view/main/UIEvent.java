package view.main;

import model.VCharacter;
import com.taku.util.ui.Event;

public final class UIEvent {
    public static final Event setFocus = new Event("SetFocus");
    public static final Event ShowSelectionPanel = new Event("ShowSelectionPanel");
    public static final Event ShowStartPanel = new Event("ShowStartPanel");
    public static final Event ShowCombatPanel = new Event("ShowCombatPanel");
    public static final Event<VCharacter> SelectCharacterEvent = new Event<>("SelectEvent");
}
