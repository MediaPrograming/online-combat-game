package view;

import com.taku.util.function.Action0.Action;
import view.main.MainContainer;
import view.main.MainDispatch;

import javax.swing.*;

public class StartPanel extends JPanel  {
    MainDispatch dispatch = new MainContainer();
    public JButton
            next= new JButton("Next"),
            back = new JButton("Back");
    public StartPanel(String name)
    {
        this.setName(name);
        this.add(new JLabel("ここはStartPanel"));
        this.add(next);
        this.add(back);

        next.addActionListener(e -> dispatch.ShowCombatPanel());
        back.addActionListener(e -> dispatch.ShowSelectionPanel());
    }
}

