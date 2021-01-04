package com.taku.util.flux.service;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import game.store.StoreManager;

public interface IDispatcher {
    <T> void dispatch(Action<T> e);
}
