package com.taku.util.flux.view;

import com.taku.util.flux.service.IUpdate;
import game.util.Time;
import javafx.scene.input.KeyEvent;

/**
 * @author Takuya Isaki on 2021/01/31
 * @project online-combat-game
 */
public abstract class FunctionPanel<IDispatchProps> implements IUpdate {
    private IDispatchProps props;

    public IDispatchProps getProps() {
        return props;
    }

    /**
     * @param mapDispatch processing function of dispatch
     * @return the function result
     */
    public void connect(IDispatchProps mapDispatch){
        this.props = mapDispatch;
        Time.Instance.addListener(this);
    }

    /*
     * Virtual function for updating every frame
     * if panel is inactive, this method don't listen. So, it only runs when active
     */
    @Override
    public void EveryFrameUpdate(){}

    @Override
    public void KeyPressed(KeyEvent key){}
    @Override
    public void KeyReleased(KeyEvent key){}
}
