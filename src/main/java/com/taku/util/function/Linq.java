package com.taku.util.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Linq {
    public static <T, R> List<R> Select(Collection<T> source, Function<T, R> function){
        List<R> list = new ArrayList<>();
        for(T t: source){
            list.add(function.apply(t));
        }
        return list;
    }

    public static <T> T First(Collection<T> source, Predicate<T> function){
        for(T t: source){
            if(function.test(t)){
                return t;
            }
        }
        return null;
    }

}
