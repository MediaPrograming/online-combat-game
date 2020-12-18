package game.view.reducer;

import com.sun.glass.ui.Application;
import com.taku.util.flux.model.Action;
import com.taku.util.flux.service.IReducer;
import com.taku.util.flux.view.ReducerBuilder;
import game.store.StoreManager;
import game.view.action.UIEvent;
import game.view.state.ShowPanelState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ShowPanelReducer implements IReducer<ShowPanelState> {
    @Override
    public ReducerBuilder<ShowPanelState> apply(Action<?> action, ShowPanelState init) {
        return ReducerBuilder.Create(action, init)
                .Case(UIEvent.ShowStartPanel,(state, payload) -> {
                    try {
                        System.out.println("ShowStartPanel");
                        Parent start = FXMLLoader.load(StoreManager.class.getResource("../view/start.fxml"));
                        var StartPanel = new Scene(start);
                        StoreManager.stage.setScene(StartPanel);
                        StoreManager.stage.show();
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                    return state;
                })
                .Case(UIEvent.ShowSelectionPanel, (state, payload) ->{
                    try {
                        System.out.println("ShowSelection");
                        Parent select = FXMLLoader.load(StoreManager.class.getResource("../view/select.fxml"));
                        var selectPanel = new Scene(select);
                        StoreManager.stage.setScene(selectPanel);
                        StoreManager.stage.show();
                    }catch (Exception err){
                        System.out.println(err);
                    }
                    return state;
                })
                .Case(UIEvent.ShowCombatPanel, (state, payload) ->{
                    try {
                        Parent combat = FXMLLoader.load(StoreManager.class.getResource("../view/combat.fxml"));
                        var combatPanel = new Scene(combat);
                        StoreManager.stage.setScene(combatPanel);
                        StoreManager.stage.show();
                    }catch (Exception err){
                        System.out.println(err);
                    }
                    return state;
                })
                .Case(UIEvent.ShowUoPanel, (state, payload) ->{
                    try {
                        Parent uo = FXMLLoader.load(StoreManager.class.getResource("../view/uo.fxml"));
                        var uoPanel = new Scene(uo);
                        StoreManager.stage.setScene(uoPanel);
                        StoreManager.stage.show();
                    }catch (Exception err){
                        System.out.println(err);
                    }
                    return state;
                });
    }

}
