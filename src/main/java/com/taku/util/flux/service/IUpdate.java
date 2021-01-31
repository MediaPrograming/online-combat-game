package com.taku.util.flux.service;

import javafx.scene.input.KeyEvent;

/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
public interface IUpdate {
    /**
     * executed every frame
     */
    void EveryFrameUpdate();

    /**
     * Key pressed Event on javafx Panel
     * @param key
     */
    void KeyPressed(KeyEvent key);

    /**
     * Key released Event on javafx panel
     * @param key
     */
    void KeyReleased(KeyEvent key);
}
