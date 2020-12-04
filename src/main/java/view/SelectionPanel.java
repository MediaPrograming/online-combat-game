package view;

import config.PATH;
import com.taku.util.ui.Event;
import model.VCharacter;
import util.EventManager;
import com.taku.util.function.Action0.Action;
import view.main.MainContainer;
import view.main.MainDispatch;
import view.main.UIEvent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//キャラ選択Panel
public class SelectionPanel extends JPanel {
    MainDispatch dispatch = new MainContainer();
    public List<JButton> buttonList = new ArrayList<>(){};
    public SelectionPanel(String name){
        this.setName(name);
        this.add(new Label("ここはキャラ選択画面"));
        var c1 = new JButton("character 1", new ImageIcon(PATH.Img1));
        var c2 = new JButton("character 2", new ImageIcon(PATH.Img1));
        var c3 = new JButton("character 3", new ImageIcon(PATH.Img1));
        var c4 = new JButton("character 4", new ImageIcon(PATH.Img1));
        buttonList.add(c1);
        buttonList.add(c2);
        buttonList.add(c3);
        buttonList.add(c4);

        for (JButton b:buttonList) {
            //b.addActionListener(e -> dispatch(new VCharacter(b.getIcon())));
            this.add(b);
        }


    }

    public void SetNext(Action action) {
        for (JButton b:buttonList) {
            b.addActionListener(e -> action.Invoke());
        }
    }
    public void SetBack(Action action) {
    }
}
