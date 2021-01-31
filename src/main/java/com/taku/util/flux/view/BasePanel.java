package com.taku.util.flux.view;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import com.taku.util.flux.service.IUpdate;
import game.store.StoreManager;
import game.util.Time;
import javafx.scene.input.KeyEvent;

import java.util.function.Function;

/*
 * IStateProps そのUIが持つState
 * IDispatchProps そのUIが持つDispatcher
 */
public abstract class BasePanel<IStateProps, IDispatchProps> implements IDispatcher, IUpdate{
    private IStateProps state;
    private IDispatchProps props;
    private Function<IStateProps, IStateProps> mapStateToProps;
    private Function<IDispatcher, IDispatchProps> mapDispatchToProps;

    public void Initialize(IStateProps state){
        this.state = state;
    }

    public IStateProps getState() {
        return state;
    }

    public IDispatchProps getProps() {
        return props;
    }

    /**
     * @param init  initialize value
     * @param mapState processing function of state
     * @param mapDispatch processing function of dispatch
     * @return the function result
     */
    public void connect(IStateProps init, Function<IStateProps, IStateProps> mapState, Function<IDispatcher, IDispatchProps> mapDispatch){
        StoreManager.getInstance().store.addView(this);
        this.mapStateToProps = mapState;
        this.mapDispatchToProps = mapDispatch;
        state = init;
        props = mapDispatch.apply(this);
        Time.Instance.addListener(this);

    }

    /*
     * This method is Updated when state is updated
     */
    public final void Update(IStateProps s) { state = mapStateToProps.apply(s); }

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

    @Override
    public <T> void dispatch(Action<T> action) {
        if(state != null) StoreManager.getInstance().store.Invoke(state, action);
    }
}
