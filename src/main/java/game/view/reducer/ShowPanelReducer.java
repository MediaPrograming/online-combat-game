package game.view.reducer;
/**
 * @author Takuya Isaki on 2021/01/05
 * @project online-combat-game
 */
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import com.taku.util.model.Unit;
import game.store.StoreManager;
import game.util.Time;
import game.view.action.RoomEvent;
import game.view.action.UIEvent;
import game.view.state.RoomState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ShowPanelReducer implements IReducer<Unit> {
    @Override
    public ReducerBuilder<Unit> apply(Action<?> action, Unit init) {
        //<Warning>
        // 画面が変更した状態で前画面の購読が停止されないため、
        // とりあえず、画面変えたらすべての購読を解除することにした
        Time.Instance.clearUpdates();
        return ReducerBuilder.Create(action, init)
                .Case(UIEvent.SHOW_START_PANEL,(state, payload) -> {
                    Platform.runLater(() -> {
                        try {
                            Parent start = FXMLLoader.load(StoreManager.class.getResource("../view/start.fxml"));
                            var StartPanel = new Scene(start);
                            StoreManager.stage.setScene(StartPanel);
                            StoreManager.stage.show();
                        } catch (Exception err) {
                            System.out.println("[ERROR]" + err.toString());
                        }
                    });
                    return state;
                })
                .Case(UIEvent.SHOW_SELECTION_PANEL, (state, payload) ->{
                    Platform.runLater(() -> {
                        try {
                            Parent select = FXMLLoader.load(StoreManager.class.getResource("../view/select.fxml"));
                            var selectPanel = new Scene(select);
                            StoreManager.stage.setScene(selectPanel);
                            StoreManager.stage.show();
                        } catch (Exception err) {
                            System.out.println("[ERROR]" + err.toString());
                        }
                    });
                    return state;
                })
                .Case(UIEvent.SHOW_COMBAT_PANEL, (state, payload) ->{
                    Platform.runLater(() -> {
                        try {
                            Parent combat = FXMLLoader.load(StoreManager.class.getResource("../view/combat.fxml"));
                            var combatPanel = new Scene(combat);
                            StoreManager.stage.setScene(combatPanel);
                            StoreManager.stage.show();
                        }catch (Exception err){
                            System.out.println("[ERROR]" + err.toString());
                        }
                    });
                    return state;
                })
                .Case(UIEvent.SHOW_CREATE_PANEL, (state, payload) -> {
                    Platform.runLater(() -> {
                        try {
                            Parent combat = FXMLLoader.load(StoreManager.class.getResource("../view/createRoom.fxml"));
                            var combatPanel = new Scene(combat);
                            StoreManager.stage.setScene(combatPanel);
                            StoreManager.stage.show();
                        }catch (Exception err){
                            System.out.println("[ERROR]" + err.toString());
                        }
                    });
                    return state;
                })
                .Case(UIEvent.SHOW_WAIT_ROOM_PANEL, (state, payload) -> {
                    Platform.runLater(() -> {
                        try {
                            Parent start = FXMLLoader.load(StoreManager.class.getResource("../view/waitRoom.fxml"));
                            var StartPanel = new Scene(start);
                            StoreManager.stage.setScene(StartPanel);
                            StoreManager.stage.show();
                        } catch (Exception err) {
                            System.out.println("[ERROR]" + err.toString());
                        }
                    });
                    return state;
                })
                .Case(UIEvent.SHOW_UO_PANEL, (state, payload) ->{
                    Platform.runLater(() -> {
                        try {
                            Parent uo = FXMLLoader.load(StoreManager.class.getResource("../view/uo.fxml"));
                            var uoPanel = new Scene(uo);
                            StoreManager.stage.setScene(uoPanel);
                            StoreManager.stage.show();
                        } catch (Exception err) {
                            System.out.println(err);
                        }
                    });
                    return state;

                });
    }
}
