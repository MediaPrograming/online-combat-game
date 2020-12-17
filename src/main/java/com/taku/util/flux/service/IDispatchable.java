package com.taku.util.flux.service;

import com.taku.util.flux.model.Action;

public interface IDispatchable {
    public <TState, TPayload > void Invoke(TState state, Action<TPayload> action);
}
