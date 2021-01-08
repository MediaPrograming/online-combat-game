package com.taku.util.function;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
public class Tuple<T, V> {
    private T t;
    private V v;
    public Tuple(T t, V v){
        this.t = t;
        this.v = v;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public Tuple<T,V> Combine(T t, V v)
    {
        return new Tuple<>(t, v);
    }

}
