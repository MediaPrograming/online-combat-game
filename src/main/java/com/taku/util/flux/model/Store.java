package com.taku.util.flux.model;

import com.taku.util.flux.service.IDispatchable;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.BasePanel;
import com.taku.util.flux.view.ReducerBuilder;
import javafx.util.Pair;

import java.util.*;

public class Store implements IDispatchable {
    private Pair<Class<?>, IReducer>[] pairs;
    private final List<BasePanel> panels = new ArrayList<>();


    @SafeVarargs
    private Store(Pair<Class<? >, IReducer>... pairs){
        this.pairs = pairs;
    }

    public void addView(BasePanel dispatcher){
        panels.add(dispatcher);
    }

    @SafeVarargs
    public static Store CreateStore(Pair<Class<?>, IReducer>... pairs){
        return new Store(pairs);
    }
    @Override
    public <TState, TPayload> void Invoke(TState state, Action<TPayload> action) {
        var pairStream= Arrays.stream(pairs)
                .filter(pair -> pair.getKey() == state.getClass())
                .map(p -> p.getValue().apply(action, state))
                .map(ReducerBuilder::getState);


        pairStream.forEach(newState -> {
            panels.stream()
                    .filter(dispatcher -> {
                        System.out.println("state : " + state + "\n dispatcher : "  + dispatcher.getState().getClass());
                        return state == dispatcher.getState().getClass();
                    })
                    .forEach(l-> l.Update(newState));
        });
    }
}

