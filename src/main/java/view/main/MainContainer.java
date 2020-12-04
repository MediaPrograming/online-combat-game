package view.main;

import com.taku.util.model.Unit;
import model.VCharacter;
import store.Store;

import java.awt.*;

public class MainContainer implements MainDispatch {
    public MainContainer(){}
    public static CardLayout layout;
    private Unit unit = new Unit();
    @Override
    public void ShowSelectionPanel() {
        Store.Dispatch(UIEvent.ShowSelectionPanel.apply(unit));
    }

    @Override
    public void ShowStartPanel() {
        Store.Dispatch(UIEvent.ShowStartPanel.apply(unit));
    }

    @Override
    public void SelectCharacter(VCharacter character) {
        Store.Dispatch(UIEvent.SelectCharacterEvent.apply(character));
    }

    @Override
    public void ShowCombatPanel() {
        Store.Dispatch(UIEvent.ShowCombatPanel.apply(unit));
    }

    @Override
    public void SetFocus() {
        Store.Dispatch(UIEvent.setFocus.apply(unit));

    }
}