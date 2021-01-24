package game.view.container;

import com.taku.util.flux.service.IDispatcher;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.util.RequestUtil;
import game.view.action.UoPanelEvent;
import game.view.panel.UoPanel;
import game.view.service.IPositionStream;
import game.view.state.UoPanelState;
import io.game.hub.positionHub.CharacterState;
import io.game.hub.positionHub.PositionHubGrpc;
import io.grpc.stub.StreamObserver;
import io.game.hub.positionHub.Input;
import javafx.scene.canvas.GraphicsContext;
import io.game.hub.messageHub.GrpcRoom;
import io.game.hub.messageHub.User;
import io.game.hub.positionHub.*;

import java.util.function.Function;

/**
 * @author Takuya Isaki on 2021/01/15
 * @project online-combat-game
 */
public class CombatContainer {
    Unit unit = new Unit();
    public CombatContainer(UoPanel panel) {
        panel.connect(new UoPanelState(StoreManager.Instance.client.user,StoreManager.Instance.client.grpcRoom),
                state -> state, function);
    }
    private final PositionHubGrpc.PositionHubStub stub = StoreManager.Instance.client.positionHubStub;
    Function<IDispatcher, IPositionStream> function = dispatcher -> new IPositionStream() {
        @Override
        public void StartAudio() { dispatcher.dispatch(UoPanelEvent.START_AUDIO.Create(unit)); }

        @Override
        public void StopAudio() { dispatcher.dispatch(UoPanelEvent.STOP_AUDIO.Create(unit));}

        @Override
        public void SendInput(Input input) {
            var req = stub.sendInput(new StreamObserver<CharacterState>() {
                @Override public void onNext(CharacterState value) { dispatcher.dispatch(UoPanelEvent.STATE_UPDATE.Create(value));}
                @Override public void onError(Throwable t) { System.out.println("[ERROR] : " + t.toString() + "(" + CombatContainer.class+  ")");}
                @Override public void onCompleted() { }
            });
            req.onNext(input);
        }

        @Override
        public void ChangeInputPressed(String key) { dispatcher.dispatch(UoPanelEvent.UPDATE_INPUT_PRESSED.Create(key)); }

        @Override
        public void ChangeInputReleased(String key) { dispatcher.dispatch(UoPanelEvent.UPDATE_INPUT_RELEASED.Create(key)); }

        @Override
        public void UpdateCharacterTable(GraphicsContext gc) { dispatcher.dispatch(UoPanelEvent.UPDATE_CHARACTER_TABLE.Create(gc)); }
        public void Init(User user, GrpcRoom room){
            var observer = RequestUtil.streamPositionCreator.apply(dispatcher);
            observer.onNext(PositionHubMessage.newBuilder().setUser(user).setRoom(room).setType(Type.INIT).build());
        }
        @Override
        public void ContinueGame() { dispatcher.dispatch(UoPanelEvent.CONTINUE.Create(unit)); }

        @Override
        public void QuitGame() { dispatcher.dispatch(UoPanelEvent.QUIT.Create(unit));}
    };
}