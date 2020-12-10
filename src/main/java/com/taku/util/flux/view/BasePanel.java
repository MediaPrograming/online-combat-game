package com.taku.util.flux.view;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.view.action.UIEvent;


import java.util.function.Function;

/*
 * IStateProps そのUIが持つState
 * IDispatchProps そのUIが持つDispatcher
 */
public abstract class BasePanel<IStateProps , IDispatchProps> implements IDispatcher<IStateProps> {
    public IStateProps state;
    public IDispatchProps props;
    private Function<IStateProps, IStateProps> mapStateToProps;
    private Function<IDispatcher, IDispatchProps> mapDispatchToProps;

    public void connect(Function<IStateProps, IStateProps> mapState, Function<IDispatcher, IDispatchProps>  mapDispatch){
        StoreManager.Instance.store.addView(this);
        this.mapStateToProps = mapState;
        this.mapDispatchToProps = mapDispatch;
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
