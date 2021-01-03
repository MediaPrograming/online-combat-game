package com.taku.util.flux.service;

import com.taku.util.flux.model.Action;

public interface IDispatcher {
    <T> void dispatch(Action<T> e);
}
