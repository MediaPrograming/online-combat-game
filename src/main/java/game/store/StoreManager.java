package game.store;

import com.sun.tools.javac.Main;
import com.taku.util.flux.model.Store;
import game.client.GrpcClient;
import game.view.MainApp;
import game.view.reducer.CharacterReducer;
import game.view.reducer.FetchReducer;
import game.view.reducer.ShowPanelReducer;
import game.view.reducer.StartReducer;
import game.view.stage.MainStage;
import game.view.state.CharacterState;
import game.view.state.RoomState;
import game.view.state.ShowPanelState;
import game.view.state.StartState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;


public final class StoreManager {
    public static StoreManager Instance = new StoreManager();    //再代入不可
    private MainStage stage = new MainStage();
    public GrpcClient client = new GrpcClient();
    public Store store = Store.CreateStore(
            new Pair<>(StartState.class, new StartReducer()),
            new Pair<>(ShowPanelState.class, new ShowPanelReducer()),
            new Pair<>(CharacterState.class, new CharacterReducer()),
            new Pair<>(RoomState.class,new FetchReducer())

    );

    private StoreManager(){}

    public void Init(){
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
        stage.close();
        stage = null;
        Instance = null;
    }
}
