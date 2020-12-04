import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class MainJF extends JFrame implements ActionListener
{   SpriteJF    spr;
    Timer       time;
    int         currFrame;

    // Main()
    public  static void main(String args[])
    {   new MainJF();
    }

    // Constructor
    MainJF(String filename, int fwn, int fhn)
    {   super("Animation");
        spr = new SpriteJF(filename, fwn, fhn);
        currFrame = 0;
        time = new Timer(10, this);
        time.start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);
        setVisible(true);
    }
    MainJF()
    {   this("./img/bijin.jpg",4,2);
    }
    MainJF(String x)
    {   this(x,1,1);
    }

    public void paint(Graphics g)
    {   spr.view(g, currFrame, 50, 60);
    }

    public void actionPerformed(ActionEvent evt)
    {   Object source = evt.getSource();
        if (source == time)
        {   if (isShowing())
            {   currFrame = (currFrame+1) % spr.frameNum;
                repaint();
            }
        }
    }
}