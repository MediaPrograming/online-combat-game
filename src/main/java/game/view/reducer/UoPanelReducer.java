package game.view.reducer;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.view.action.AnimationEvent;
import game.view.state.UoPanelState;

/**
 * @author Takuya Isaki on 2021/01/20
 * @project online-combat-game
 */
public class UoPanelReducer implements IReducer<UoPanelState> {

    @Override
    public ReducerBuilder<UoPanelState> apply(Action<?> action, UoPanelState init) {
        return ReducerBuilder.Create(action, init)
                .Case(AnimationEvent.STATE_UPDATE, ((uoPanelState, characterState) -> {
                    uoPanelState.stateBlockingQueue.add(characterState);
                    System.out.println("id : " +  characterState.getId() + "put : " + "x: "+  characterState.getX() + "y " + characterState.getY());
                    return uoPanelState;
                }));
    }
}
