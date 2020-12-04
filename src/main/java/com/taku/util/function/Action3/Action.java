package com.taku.util.function.Action3;

@FunctionalInterface
public interface Action<T1, T2, T3> {
    public void Invoke(T1 t1, T2 t2, T3 t3);
}
