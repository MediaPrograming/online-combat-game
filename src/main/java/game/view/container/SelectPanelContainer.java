package game.view.container;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.model.Unit;
import game.view.action.UIEvent;
import game.view.panel.StartPanel;
import game.view.service.IShowPanel;

public class SelectPanelContainer  {
    Unit unit = new Unit();
    public SelectPanelContainer(StartPanel panel) {
        panel.connect(new Unit(), state -> state, dispatcher -> new IShowPanel() {
            @Override public void ShowStartPanel() { dispatcher.dispatch(UIEvent.SHOW_START_PANEL.Create(unit)); }
            @Override public void ShowSelectionPanel() { dispatcher.dispatch(UIEvent.SHOW_SELECTION_PANEL.Create(unit)); }
            @Override public void ShowCombatPanel() { dispatcher.dispatch(UIEvent.SHOW_COMBAT_PANEL.Create(unit)); }

            @Override
            public void ShowUoPanel() {

            }

            @Override public void ShowCreateRoomPanel() { dispatcher.dispatch(UIEvent.SHOW_CREATE_PANEL.Create(unit));}
        });
    }
}
