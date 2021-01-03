package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.view.action.UIEvent;
import game.view.panel.SelectPanel;
import game.view.panel.StartPanel;
import game.view.service.IShowPanel;
import game.view.state.RoomState;
import game.view.state.ShowPanelState;
import server.room.Room;

import java.util.function.Function;

public class SelectPanelContainer  {
    Unit unit = new Unit();
    public SelectPanelContainer(StartPanel panel) {
        panel.connect(new ShowPanelState(), state -> state, dispatcher -> new IShowPanel() {
            @Override public void ShowStartPanel() { dispatcher.dispatch(UIEvent.ShowStartPanel.Create(unit)); }
            @Override public void ShowSelectionPanel() { dispatcher.dispatch(UIEvent.ShowSelectionPanel.Create(unit)); }
            @Override public void ShowCombatPanel() { dispatcher.dispatch(UIEvent.ShowCombatPanel.Create(unit)); }
        });
    }
}
