package store;

import com.taku.util.function.Action0.Action;
import com.taku.util.ui.Event;
import view.CombatPanel;
import view.SelectionPanel;
import view.StartPanel;
import view.main.MainWindow;
import view.main.UIEvent;

import javax.swing.*;
import java.awt.*;

public final class Store {
    public static MainWindow mainWindow;
    public static JPanel mainPanel = new JPanel();
    public static view.StartPanel StartPanel = new StartPanel("Start");
    public static view.SelectionPanel SelectionPanel = new SelectionPanel("Selection");
    public static view.CombatPanel CombatPanel = new CombatPanel("Combat");
    public static CardLayout layout = new CardLayout();

    public static void Dispatch(Event event) {
        var type = event.getName();
        switch (type) {
            case UIEvent.ShowSelectPanelType:
                Store.layout.show(Store.mainPanel, Store.SelectionPanel.getName());
                break;
            case UIEvent.SelectCharacterEventType:
                break;
            case UIEvent.ShowCombatPanelType:
                Store.layout.show(Store.mainPanel, Store.CombatPanel.getName());
                break;
            case UIEvent.ShowStartPanelType:
                Store.layout.show(Store.mainPanel, Store.StartPanel.getName());
                break;
            case UIEvent.SetFocusType:
                mainWindow.requestFocus();
                break;
        }
    }
}
