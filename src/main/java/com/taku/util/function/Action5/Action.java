package com.taku.util.function.Action5;
@FunctionalInterface
public interface Action<T1, T2, T3, T4, T5>{
    public void Invoke(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
}