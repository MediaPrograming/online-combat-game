package view.main;

import model.VCharacter;

public interface MainDispatch {
    void ShowSelectionPanel();

    void ShowStartPanel();

    void SelectCharacter(VCharacter character);
    void ShowCombatPanel();

    void SetFocus();
}
