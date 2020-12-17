package com.taku.util.flux.model;

import com.taku.util.flux.service.IDispatchable;
import com.taku.util.flux.service.IDispatcher;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.function.Function.Function2;
import javafx.util.Pair;

import java.util.*;
import java.util.function.Function;

public class Store implements IDispatchable {
    private Pair<Class<?>, IReducer>[] pairs;
    private List<IDispatcher> dispatchers = new ArrayList<>();
    private Store(Pair<Class<? >, IReducer>... d){
        this.pairs = d;
    }

    public void addView(IDispatcher dispatcher){
        dispatchers.add(dispatcher);
    }

    public static <T> Store CreateStore(Pair<Class<?>, IReducer>... pairs){
        return new Store(pairs);
    }
    @Override
    public <TState, TPayload> void Invoke(TState state, Action<TPayload> action) {
        System.out.println(state);
        for (var pair : pairs) {
            if(pair.getKey() == state.getClass()){
                var newState = pair.getValue().apply(action, state);
                var list = dispatchers.stream()
                        .filter(dispatcher -> state== dispatcher.getClass());
                list.forEach(l-> {
                    l.Update(newState.getState());
                });
            }
        }
    }
}

