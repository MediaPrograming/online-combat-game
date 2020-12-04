package com.taku.util.function.Action2;

@FunctionalInterface
public interface Action<T1, T2> {
    public void Invoke(T1 t, T2 t2);
}
