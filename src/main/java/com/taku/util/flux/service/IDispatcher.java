package com.taku.util.flux.service;

import com.taku.util.flux.model.Action;

public interface IDispatcher<IState> {
    <T> void dispatch(Action<T> e);
    void Update(IState state);
}
