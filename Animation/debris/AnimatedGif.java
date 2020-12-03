import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.JLabel;

public class AnimatedGif extends JFrame{
    public AnimatedGif(){
        Icon imgIcon = new ImageIcon(this.getClass().getResource("./img/Kuno.gif"));
        JLabel label = new JLabel(imgIcon);
        label.setSize(100,100);
        this.getContentPane().add(label);
        setSize(150,150);
        setVisible(true);
    }    

    public static void main(String[] args){
        new AnimatedGif();
    }
}
