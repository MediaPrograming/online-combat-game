package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.view.action.UIEvent;
import game.view.panel.SelectPanel;
import game.view.service.IChangeTextTest;
import game.view.panel.StartPanel;
import game.view.service.IShowPanel;
import game.view.state.ShowPanelState;
import game.view.state.StartState;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class SelectPanelContainer  {
    public SelectPanelContainer(SelectPanel panel) {
        panel.connect(mapState, mapDispatch);
        panel.state = new ShowPanelState();

    }

    Unit unit = new Unit();
    Function<ShowPanelState, ShowPanelState> mapState = (state) -> state;
    Function<IDispatcher, IShowPanel> mapDispatch = dispatch ->
            new IShowPanel() {
                @Override
                public void ShowStartPanel() {
                    dispatch.dispatch(UIEvent.ShowStartPanel.Create(unit));
                }

                @Override
                public void ShowSelectionPanel() {
                    dispatch.dispatch(UIEvent.ShowSelectionPanel.Create(unit));
                }

                @Override
                public void ShowCombatPanel() {
                    dispatch.dispatch(UIEvent.ShowCombatPanel.Create(unit));
                }
            };
}
