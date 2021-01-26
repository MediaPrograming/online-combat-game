package game.view.reducer;

import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.store.StoreManager;
import game.view.action.AnimationEvent;
import game.view.action.UIEvent;
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
                    //System.out.println("id : " +  characterState.getId() + "put : " + "x: "+  characterState.getX() + "y " + characterState.getY());
                    return uoPanelState;
                }))
                .Case(AnimationEvent.STREAM_EVENT, ((uoPanelState, positionHubMessage) -> {
                    switch (positionHubMessage.getType()){
                        case INIT : {break;}
                        case GAME_FINISH : {uoPanelState.quitPaneVisible = true; break;}
                    }
                    return uoPanelState;
                })).Case(AnimationEvent.CONTINUE, ((uoPanelState, unit) -> {
                    StoreManager.Instance.store.Invoke(unit,UIEvent.SHOW_WAIT_ROOM_PANEL.Create(unit));
                    return uoPanelState;
                })).Case(AnimationEvent.QUIT, ((uoPanelState, unit) -> {
                    System.exit(0);
                    return uoPanelState;
                }));
    }
}
