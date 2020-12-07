package com.taku.util.function.Action;

@FunctionalInterface
public interface Action3<T1, T2, T3> {
    public void Invoke(T1 t1, T2 t2, T3 t3);
}
