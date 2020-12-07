package com.taku.util.function.Action;

@FunctionalInterface
public interface Action2<T1, T2> {
    public void Invoke(T1 t, T2 t2);
}
