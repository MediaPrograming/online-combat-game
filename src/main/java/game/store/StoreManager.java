package game.store;

import com.sun.javafx.application.ParametersImpl;
import com.taku.util.flux.model.Store;
import game.view.MainApp;
import game.view.reducer.CharacterReducer;
import game.view.reducer.ShowPanelReducer;
import game.view.reducer.StartReducer;
import game.view.stage.MainStage;
import game.view.state.CharacterState;
import game.view.state.ShowPanelState;
import game.view.state.StartState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;


public final class StoreManager {
    public static StoreManager Instance = new StoreManager();
    public static MainStage stage = new MainStage();
    public Store store = Store.CreateStore(
            new Pair(StartState.class, new StartReducer()),
            new Pair(ShowPanelState.class, new ShowPanelReducer()),
            new Pair(CharacterState.class, new CharacterReducer())
    );

    public void InitLoad(){
        try {
            Parent start = FXMLLoader.load(MainApp.class.getResource("../view/start.fxml"));
            Scene StartPanel = new Scene(start);
            stage.setScene(StartPanel);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    void Close(){
        StoreManager.stage.close();
        StoreManager.stage = null;
        Instance = null;
    }

}
