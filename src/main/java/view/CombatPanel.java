package view;

import com.sun.tools.javac.Main;
import model.Transform;
import model.VCharacter;
import util.InputManager;
import com.taku.util.function.Action0.Action;
import view.main.MainContainer;
import view.main.MainDispatch;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class CombatPanel extends JPanel implements KeyListener {
    MainDispatch dispatch = new MainContainer();
    public List<Action> ExitEvents = new ArrayList<>();
    VCharacter c1, c2;
    public CombatPanel(String name){
        this.setName(name);
        JButton HomeButton = new JButton("bakc Home");
        HomeButton.addActionListener(e -> dispatch.ShowStartPanel());
        this.add(new JLabel("ここはCombatWindow"));
        this.add(HomeButton);
        addKeyListener(this);
    }

    public void Initialize(ImageIcon i1, ImageIcon i2){
        c1 = VCharacter.Create(new Transform(-20,0),i1);
        c2 = VCharacter.Create(new Transform(20,0),i2);
        this.add(c1);
        this.add(c2);
    }
    //毎フレーム実行
    public void Update(){
        if(c1 !=null) c1.setLocation(c1.transform.x, c1.transform.y);
        if(c2 != null) c2.setLocation(c1.transform.x, c2.transform.y);
    }

    @Override
    public void keyTyped(KeyEvent e) { InputManager.Instance.Register(e.getKeyCode()); }

    @Override
    public void keyPressed(KeyEvent e) { System.out.plintln(e.getKeyCode());}

    @Override
    public void keyReleased(KeyEvent e) { InputManager.Instance.Release(e.getKeyCode()); }

}