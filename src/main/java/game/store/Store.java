package game.store;

import com.taku.util.ui.Event;
import game.view.stage.MainStage;
import game.view.action.UIEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Store {
    //private static Scene StartPanel, SelectPanel, CombatPanel;
    private static Stage stage = new MainStage();


    public static void Dispatch(Event event){
        try {

            var type = event.getName();
            switch (type) {
                //#region ShowWindow
                case UIEvent.ShowStartPanelType:
                    System.out.println("Show starts");
                    Parent start = FXMLLoader.load(Store.class.getResource("../view/start.fxml"));
                    var StartPanel = new Scene(start);
                    stage.setScene(StartPanel);
                    stage.show();
                    break;
                case UIEvent.ShowSelectPanelType:
                    Parent select = FXMLLoader.load(Store.class.getResource("../view/select.fxml"));
                    var SelectPanel = new Scene(select);
                    stage.setScene(SelectPanel);
                    stage.show();
                    break;
                case UIEvent.ShowCombatPanelType:
                    //stage.setScene(CombatPanel);
                    //stage.show();
                    break;

                //#endregion
                case UIEvent.SelectCharacterEventType:
                    break;
                case UIEvent.SetFocusType:
                    break;
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
