package com.taku.util.flux.view;

import com.taku.util.flux.service.IUpdate;
import game.util.Time;
import javafx.scene.input.KeyEvent;

/**
 * @author Takuya Isaki on 2021/01/31
 * @project online-combat-game
 */
public class PurePanel implements IUpdate {
    public PurePanel(){
        Time.Instance.addListener(this);
    }

    @Override
    public void EveryFrameUpdate() { }

    @Override
    public void KeyPressed(KeyEvent key) {

    }

    @Override
    public void KeyReleased(KeyEvent key) {

    }
}
