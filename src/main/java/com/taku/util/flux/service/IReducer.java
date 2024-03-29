package com.taku.util.flux.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.view.ReducerBuilder;

public interface IReducer<TState> {
    ReducerBuilder<TState> apply(Action<?> action, TState state);
}
