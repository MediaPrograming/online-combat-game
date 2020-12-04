package store;

import com.taku.util.ui.Event;
import view.main.UIEvent;

public final class Store {

    public static void Dispatch(Event event){
        var type = event.getName();
        if(type == UIEvent.setFocus.getName()){

        }else if (type == UIEvent.SelectCharacterEvent.getName()){

        }else if(type == UIEvent.ShowCombatPanel.getName()){

        }else if(type == UIEvent.ShowStartPanel.getName()){

        }
    }
}
