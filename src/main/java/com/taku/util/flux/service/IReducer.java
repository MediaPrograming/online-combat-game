package com.taku.util.flux.service;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.view.ReducerBuilder;

public interface IReducer<TState> {
    ReducerBuilder<TState> apply(Action<?> action, TState state);
}
