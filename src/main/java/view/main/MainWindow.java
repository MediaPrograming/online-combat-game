package view.main;

import com.taku.util.function.Action0.Action;
import view.CombatPanel;
import view.SelectionPanel;
import view.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame{
    MainDispatch container = new MainContainer();
    JPanel mainPanel = new JPanel();
    view.StartPanel StartPanel = new StartPanel("Start");
    view.SelectionPanel SelectionPanel = new SelectionPanel("Selection");
    view.CombatPanel CombatPanel = new CombatPanel("Combat");

    CardLayout layout = new CardLayout();
    public MainWindow(String title, int w, int h){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setVisible(true);
        this.setSize(w, h);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 最大化

        mainPanel.setLayout(layout);
        mainPanel.add(StartPanel, StartPanel.getName());
        mainPanel.add(CombatPanel, CombatPanel.getName());
        mainPanel.add(SelectionPanel, SelectionPanel.getName());
        this.addKeyListener((KeyListener) CombatPanel);


    }

    public final Action ShowSelectionPanel = () -> layout.show(mainPanel, SelectionPanel.getName());
    public final Action ShowStartPanel = () -> layout.show(mainPanel, StartPanel.getName());
    public final Action ShowCombatPanel = () -> layout.show(mainPanel, CombatPanel.getName());
    public final Action SetFocus = () -> this.requestFocus();



}