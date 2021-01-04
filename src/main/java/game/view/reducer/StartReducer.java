package game.view.reducer;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.action.UIEvent;
import game.view.state.StartState;

public class StartReducer implements IReducer<StartState> {
    @Override
    public ReducerBuilder apply(Action<?> action, StartState startState) {
        return ReducerBuilder.Create(action, startState)
                .Case(UIEvent.INIT, (state, payload) ->{
                   return new StartState();
                }).Case(UIEvent.SHOW_COMBAT_PANEL, (state, payload) ->{
                    return new StartState();
                }).Case(UIEvent.SHOW_SELECTION_PANEL, (state, payload) ->{
                    return new StartState();
                });
    }
}

