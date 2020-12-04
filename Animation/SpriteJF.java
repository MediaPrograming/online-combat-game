import java.awt.*;
import javax.swing.*;

class SpriteJF extends JFrame 
{   private Image   img;
    private int Height,Width,Hnum,Wnum;
    int     frameNum;

    // Constructor
    SpriteJF(String filename, int wn, int hn)
    {   Wnum= wn;
        Hnum= hn;
        Width=Height= 32;
        frameNum = Wnum*Hnum;
        img= getToolkit().getImage(filename);
        while(img.getHeight(null)==-1);
        Width= img.getWidth(null)/Wnum;
        Height= img.getHeight(null)/Hnum;
    }

    SpriteJF()
    {   this("Bijin.jpg",4,2);
    }

    SpriteJF(String x)
    {   this(x,1,1);
    }

    public void view(Graphics g, int n, int dx, int dy)
    {   int sx, sy;
        if (n >= frameNum)  return;
        sx = (n % Wnum) * Width;
        sy = (n / Wnum) * Height;
        if (img != null)
        {   g.drawImage(img,dx,dy,dx+Width,dy+Height,sx,sy,sx+Width,sy+Height,this);
        }
    }
}