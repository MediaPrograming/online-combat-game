package game.store;

import com.taku.util.flux.model.Store;
import com.taku.util.model.Unit;
import game.client.GrpcClient;
import game.view.MainApp;
import game.view.reducer.*;
import game.view.stage.MainStage;
import game.view.state.RoomState;
import game.view.state.ShowPanelState;
import game.view.state.StartState;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;


public final class StoreManager {
    public static StoreManager Instance = new StoreManager();    //再代入不可
    public static MainStage stage = new MainStage();
    public GrpcClient client = new GrpcClient();
    public Store store = Store.CreateStore(
            new Pair<>(StartState.class, new StartReducer()),
            new Pair<>(Unit.class, new ShowPanelReducer()),
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

    public void Close(){
        stage.close();
        stage = null;
        Instance = null;
    }
}
