package com.taku.util.flux.view;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
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
        if(action.getType() == creator.getType()) {
            var param = (payload) action.getParam();
            function.apply(state, param);
        }
        return this;
    }
    public  static <State> ReducerBuilder<State> Create(Action<?> action, State state) {
        return new ReducerBuilder<State>(action, state);
    };

}
