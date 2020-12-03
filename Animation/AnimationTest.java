import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ProtocolException;
import java.util.*;
import javax.swing.border.*;

//////////////////////////////////////////////////
// main class

class AnimationTest extends JFrame {
    CharaModel charaModel;
    ViewPanel view1;
    CharaController charaCont;
    public AnimationTest(){
        charaModel=new CharaModel();
        charaCont=new CharaController(charaModel);
        view1=new ViewPanel(charaModel,charaCont);
        this.setLayout(new GridLayout(1,1));
        this.setTitle("Animation Test");
        this.setSize(1000,1000);
        this.add(view1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String argv[]){
        new AnimationTest();
    }
}

///////////////////////////////////////////////////
// model

class CharaModel extends Observable {
    protected ArrayList<Character> Chara;
    protected Character operatingCharacter;
    public CharaModel() {
        Chara = new ArrayList<Character>();
        operatingCharacter = null;
    }
    public ArrayList<Character> getCharacters(){
        return Chara;
    }
    public Character getCharacter(int idx) {
        return Chara.get(idx);
    }
    public void createCharacter(int x, int y, int w, int h) {
        Character c;
        c = new CharaGIF(x, y, w, h);
        Chara.add(c);
        operatingCharacter = c;
        // setChanged();
        // notifyObservers();
    }
    public void moveCharacter(int vx,int vy){
        if (operatingCharacter != null) {
            operatingCharacter.move(vx,vy);
            // setChanged();
            // notifyObservers();
        }
    }
}

///////////////////////////////////////////////////
// controller

class CharaController implements KeyListener,ActionListener{
    private javax.swing.Timer timer;
    protected CharaModel charaModel;
    protected int vx,vy;
    public CharaController(CharaModel a) {
        charaModel = a;
        timer = new javax.swing.Timer(10,this);
        timer.start();
    }
    public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        switch(c){
          case 'f':
          charaModel.createCharacter(500,500, 100, 100);
            break;
        }
    }
    public void keyReleased(KeyEvent e){
        char c = e.getKeyChar();
        switch(c){
            case 'w':
            vy = 0;
              break;
            case 'a':
            vx = 0;
              break;
            case 's':
            vy = 0;
              break;
            case 'd':
            vx = 0;
              break;
        }
    }
    public void keyPressed(KeyEvent e){
        char c = e.getKeyChar();
        switch(c){
          case 'w':
          vy = -5;
            break;
          case 'a':
          vx = -5;
            break;
          case 's':
          vy = 5;
            break;
          case 'd':
          vx = 5;
            break;
        }
    }  
    public void actionPerformed(ActionEvent e){
        charaModel.moveCharacter(vx, vy);
      }
}

///////////////////////////////////////////////////
// view

class ViewPanel extends JPanel implements Observer, ActionListener {
  private javax.swing.Timer timer;
  protected CharaModel charaModel;
  public ViewPanel(CharaModel m,CharaController c) {
    this.setBackground(Color.white);
    this.addKeyListener(c);
    charaModel = m;
    charaModel.addObserver(this);
    this.setFocusable(true);
    timer = new javax.swing.Timer(10,this);
    timer.start();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Character c:charaModel.getCharacters()){
      c.draw(g);
    }
  }
  public void update(Observable o,Object arg){
    repaint();
  }

  public void actionPerformed(ActionEvent e){
    for(Character c:charaModel.getCharacters()){
      this.update(charaModel,c);
    } 
  }
}

///////////////////////////////////////////////////
// Character

class Character extends JFrame{
    protected int x,y,width,height;
    public Character(int x,int y,int w,int h){
        this.x = x; this.y = y; width = w; height = h;
    }
    public void setSize(int w,int h){
        width = w; height = h;
    }
    public void setLocation(int x,int y){
        this.x = x; this.y = y;
    }
    public void move(int dx, int dy){
        x = x + dx; y = y + dy;
    }
    public void draw(Graphics g) {}
}

class CharaPNG extends Character {
    public CharaPNG(int x,int y,int w,int h){
        super(x,y,w,h);
    }
    public void draw(Graphics g) {
        Image icon = new ImageIcon("./img/kuno.png").getImage();
        g.drawImage(icon,x,y,width,height,this);
    }
}

class CharaGIF extends Character {
  Image icon;
    public CharaGIF(int x,int y,int w,int h){
        super(x,y,w,h);
        icon = new ImageIcon("./img/kuno.gif").getImage();
    }
    public void draw(Graphics g) {
        g.drawImage(icon,x,y,width,height,this);
    }
}