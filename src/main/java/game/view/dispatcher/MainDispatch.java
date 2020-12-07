package game.view.dispatcher;

import game.model.VCharacter;

public interface MainDispatch {
    //#region Show Window
    void ShowStartPanel();
    void ShowSelectionPanel();
    void ShowCombatPanel();

    //#endregion

    //#region Character

    /**
     * キャラクターをセットする
     * @param character
     */
    void SelectCharacter(VCharacter character);
    //#endregion

    //#region System
    /**
     * Application終了
     */
    void ApplicationQuit();
    //#endregion
}
