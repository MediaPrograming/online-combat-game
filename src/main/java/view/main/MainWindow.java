package view.main;

import com.taku.util.function.Action0.Action;
import store.Store;
import view.CombatPanel;
import view.SelectionPanel;
import view.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame{
    MainDispatch container = new MainContainer();

    public MainWindow(String title, int w, int h) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Store.mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(w, h);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 最大化

        Store.mainPanel.setLayout(Store.layout);
        Store.mainPanel.add(Store.StartPanel, Store.StartPanel.getName());
        Store.mainPanel.add(Store.CombatPanel, Store.CombatPanel.getName());
        Store.mainPanel.add(Store.SelectionPanel, Store.SelectionPanel.getName());
        this.addKeyListener((KeyListener) Store.CombatPanel);
    }
}