package game.view.dispatcher;

import com.taku.util.model.Unit;
import game.model.VCharacter;
import game.store.Store;
import game.view.action.UIEvent;

import java.awt.*;

public class MainContainer implements MainDispatch {
    public MainContainer(){}
    @Override
    public void ShowSelectionPanel() {
        Store.Dispatch(UIEvent.ShowSelectionPanel.apply());
    }
    @Override
    public void ShowStartPanel() {
        Store.Dispatch(UIEvent.ShowStartPanel.apply());
    }

    @Override
    public void SelectCharacter(VCharacter character) {
        Store.Dispatch(UIEvent.SelectCharacterEvent.apply(character));
    }

    @Override
    public void ShowCombatPanel() {
        Store.Dispatch(UIEvent.ShowCombatPanel.apply());
    }

    @Override
    public void ApplicationQuit() {
        System.exit(0);
    }
}