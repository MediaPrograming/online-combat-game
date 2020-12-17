package game.view.action;

import com.taku.util.flux.model.ActionCreator;
import com.taku.util.function.Function.VoidFunction;
import com.taku.util.flux.model.Action;
import com.taku.util.model.Unit;

import java.util.function.Function;

public final class UIEvent {
    private static final String InitType = "Init";
    private static final String ApplicationQuitType = "ApplicationQuit";
    private static final String SetFocusType = "SetFocus";
    private static final String ShowSelectPanelType = "ShowSelectionPanel";
    private static final String ShowStartPanelType = "ShowStartPanel";
    private static final String ShowCombatPanelType = "ShowCombatPanel";
    private static final String SelectCharacterEventType = "SelectEvent";


    public static final ActionCreator<Unit> Init = new ActionCreator<>(InitType);
    public static final ActionCreator<Unit> ApplicationQuit = new ActionCreator<>(ApplicationQuitType);
    public static final ActionCreator<Unit> setFocus = new ActionCreator<>(SetFocusType);
    public static final ActionCreator<Unit> ShowSelectionPanel = new ActionCreator<>(ShowSelectPanelType);
    public static final ActionCreator<Unit> ShowStartPanel =  new ActionCreator<>(ShowStartPanelType);
    public static final  ActionCreator<Unit>  ShowCombatPanel = new ActionCreator<>(ShowCombatPanelType);
    public static final  ActionCreator<String> SelectCharacterEvent = new ActionCreator<>(SelectCharacterEventType);


    //Test
    private static final String ChangeTextTypeT1 = "ChangeText1";
    private static final String ChangeTextTypeT2 = "ChangeText2";
    public static final ActionCreator<String> ChangeTextT1 = new ActionCreator<String>(ChangeTextTypeT1);
    public static final ActionCreator<String> ChangeTextT2 = new ActionCreator<String>(ChangeTextTypeT2);
}
