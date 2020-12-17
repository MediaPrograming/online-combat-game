package com.taku.util.flux.view;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.model.ActionCreator;
import com.taku.util.function.Function.Function2;

public class ReducerBuilder<State> {
    final Action<?> action;
    final State state;

    public State getState() {
        return state;
    }

    private ReducerBuilder(Action<?> action, State state){
        this.action = action;
        this.state = state;
    }
  //  <T> State apply(ActionCreator<T> creator, Action<State> handler){return new State();}
    public <payload> ReducerBuilder<State> Case(ActionCreator<payload> creator, Function2<State, payload, State> function){
        var param = (payload) action.getParam();
        if(action.getType() == creator.getType())
        function.apply(state, param);
        return this;
    }
    public  static <State> ReducerBuilder<State> Create(Action<?> action, State state) {
        return new ReducerBuilder<State>(action, state);
    };

}
