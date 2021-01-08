package com.taku.util.flux.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;

public interface IDispatchable {
    public <TState, TPayload > void Invoke(TState state, Action<TPayload> action);
}
