package com.taku.util.flux.model;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.service.IDispatchable;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.BasePanel;
import com.taku.util.flux.view.ReducerBuilder;
import game.store.StoreManager;
import javafx.collections.ObservableArray;
import javafx.scene.input.KeyEvent;
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
                    .filter(dispatcher -> state == dispatcher.getState().getClass())
                    .forEach(l-> l.Update(newState));
        });
    }

    public void KeyPressed(KeyEvent keyEvent){
        for(var l : panels)
            l.KeyPressed(keyEvent);
    }

    public void KeyReleased(KeyEvent keyEvent){
        for (var l : panels)
            l.KeyReleased(keyEvent);
    }
}