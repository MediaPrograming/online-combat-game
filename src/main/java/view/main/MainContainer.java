package view.main;

import model.VCharacter;
import store.Store;

public class MainContainer implements MainDispatch {
    public MainContainer(){}

    @Override
    public void ShowSelectionPanel() {
        Store.Dispatch(UIEvent.ShowSelectionPanel);
    }

    @Override
    public void ShowStartPanel() {
        Store.Dispatch(UIEvent.ShowStartPanel);
    }

    @Override
    public void SelectCharacter(VCharacter character) {
        Store.Dispatch(UIEvent.SelectCharacterEvent);
    }

    @Override
    public void ShowCombatPanel() {
        Store.Dispatch(UIEvent.ShowCombatPanel);
    }

    @Override
    public void SetFocus() {
        Store.Dispatch(UIEvent.setFocus);

    }
}