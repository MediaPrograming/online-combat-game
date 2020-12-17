package com.taku.util.flux.view;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.UIEvent;


import java.util.Timer;
import java.util.function.Function;

/*
 * IStateProps そのUIが持つState
 * IDispatchProps そのUIが持つDispatcher
 */
public abstract class BasePanel<IStateProps , IDispatchProps> implements IDispatcher<IStateProps> {
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
     * @param init  初期値
     * @param mapState Stateの処理関数
     * @param mapDispatch Dispatchの処理関数
     * @return the function result
     */
    public void connect(IStateProps init, Function<IStateProps, IStateProps> mapState, Function<IDispatcher, IDispatchProps>  mapDispatch){
        StoreManager.Instance.store.addView(this);
        this.mapStateToProps = mapState;
        this.mapDispatchToProps = mapDispatch;
        state = init;
        props = mapDispatch.apply(this);
        dispatch(UIEvent.Init.Create(new Unit()));
    }
    @Override
    public void Update(IStateProps s) { state = mapStateToProps.apply(s); }

    @Override
    public <T> void dispatch(Action<T> action) {
        if(state != null) StoreManager.Instance.store.Invoke(state, action);
    }
}
