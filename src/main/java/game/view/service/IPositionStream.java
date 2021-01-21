package game.view.service;

import io.game.hub.positionHub.Input;

/**
 * @project online-combat-game
 * @author Takuya Isaki on 2021/01/20
 */
public interface IPositionStream{
    void SendInput(Input input);
}