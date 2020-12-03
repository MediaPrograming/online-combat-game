import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// 描画した図形を記録する Figure クラス (継承して利用する)
class Figure extends JFrame{
  protected int x,y,width,height;
  protected Color color;
  public Figure(int x,int y,int w,int h,Color c) {
    this.x = x; this.y = y;  // this.x, this.y はフィールド変数を指します．
    width = w; height = h;   // ローカル変数で同名の変数がある場合は，this
    color = c;               // を付けると，フィールド変数を指すことになります．
  }
  public void setSize(int w,int h) {
    width = w; height = h;
  }
  public void setLocation(int x,int y) {
    this.x = x; this.y = y;
  }
  public void reshape(int x1,int y1,int x2,int y2) {
    int neww = x1-x2;
    int newh = y1-y2;
    int newx,newy;
    if(neww<0)newx = Math.min(x1,x2)-neww;
    else newx = Math.min(x1,x2);
    if(newh<0)newy = Math.min(y1,y2)-newh;
    else newy = Math.min(y1,y2);
    setLocation(newx,newy);
    setSize(neww,newh);
  }
  public void draw(Graphics g) {}
}

class RectangleFigure extends Figure {
  public RectangleFigure(int x,int y,int w,int h,Color c) {
    super(x,y,w,h,c);
    // 引数付きのコンストラクタは継承されないので，コンストラクタを定義．
    // superで親のコンストラクタを呼び出すだけ．
  }
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawRect(x,y,width,height);
  }
}

class FillRectFigure extends Figure {
  public FillRectFigure(int x,int y,int w,int h,Color c) {
    super(x,y,w,h,c);
    // 引数付きのコンストラクタは継承されないので，コンストラクタを定義．
    // superで親のコンストラクタを呼び出すだけ．
  }
  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRect(x,y,width,height);
  }
}

class CharacterFigure extends Figure {
    public CharacterFigure(int x,int y,int w,int h,Color c) {
      super(x,y,w,h,c);
    }
    public void draw(Graphics g) {
      Image icon = new ImageIcon("./img/Kuno.gif").getImage();
      g.setColor(color);
      g.drawImage(icon,x,y,width,height,this);
    }
  }

////////////////////////////////////////////////
// Model (M)

// modelは java.util.Observableを継承する．Viewに監視される．
class DrawModel extends Observable implements KeyListener{
  protected ArrayList<Figure> fig;
  protected Figure drawingFigure;
  protected Color currentColor;
  protected Boolean fill;
  public DrawModel() {
    fig = new ArrayList<Figure>();
    drawingFigure = null;  // null は定数．C言語のNULLと同じで，何も入っていないという意味．
    currentColor = Color.red;  // 色はとりあえず赤で固定．容易に変更可能に拡張できます．
    fill = false;
  }
  public ArrayList<Figure> getFigures() {
    return fig;
  }
  public Figure getFigure(int idx) {
    return fig.get(idx);
  }
  public void createFigure(int x,int y) {
    Figure f;
    if(!fill) { f = new RectangleFigure(x,y,0,0,currentColor);}
    else { f = new CharacterFigure(x,y,0,0,currentColor);}
    fig.add(f);
    drawingFigure = f;
    setChanged();
    notifyObservers();
  }
  public void reshapeFigure(int x1,int y1,int x2,int y2) {
    if (drawingFigure != null) {
      drawingFigure.reshape(x1,y1,x2,y2);
      setChanged();
      notifyObservers();
    }
  }
  public void keyPressed(KeyEvent e){}
   public void keyTyped(KeyEvent e){
    char c = e.getKeyChar();
    switch(c){
      case 'f':
        fill = true;
        break;
      case 'e':
        fill = false;
        break;
    }
   }
   public void keyReleased(KeyEvent e){}
}

////////////////////////////////////////////////
// View (V)

// Viewは，Observerをimplementsする．Modelを監視して，
// モデルが更新されたupdateする．実際には，Modelから
// update が呼び出される．
class ViewPanel extends JPanel implements Observer,ActionListener {
  private javax.swing.Timer timer;
  protected DrawModel model;
  public ViewPanel(DrawModel m,DrawController c) {
    this.setBackground(Color.white);
    this.addMouseListener(c);
    this.addMouseMotionListener(c);
    this.addKeyListener(c);
    model = m;
    model.addObserver(this);
    this.addKeyListener(m);
    this.setFocusable(true);
    timer = new javax.swing.Timer(10,this);
    timer.start();
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Figure f:model.getFigures()){
      f.draw(g);
    }
  }
  public void update(Observable o,Object arg){
    repaint();
  }

  public void actionPerformed(ActionEvent e){
    for(Figure f:model.getFigures()){
      this.update(model,f);
    } 
  }
}

//////////////////////////////////////////////////
// main class

class Render extends JFrame {
  DrawModel model;
  ViewPanel view1;
  DrawController cont;
   public Render(){
      model=new DrawModel();
      cont =new DrawController(model);
      view1=new ViewPanel(model,cont);
      this.setLayout(new GridLayout(1,1));
      this.setTitle("Draw Editor");
      this.setSize(1000,1000);
      this.add(view1);
      view1.setBorder(new LineBorder(Color.black,3));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new Render();
   }
}

////////////////////////////////////////////////
// Controller (C)

class DrawController implements MouseListener,MouseMotionListener,KeyListener{
  protected DrawModel model;
  protected int dragStartX,dragStartY;
  public DrawController(DrawModel a) {
    model = a;
  }
  public void mouseClicked(MouseEvent e) { }
  public void mousePressed(MouseEvent e) {
    dragStartX = e.getX(); dragStartY = e.getY();
    model.createFigure(dragStartX,dragStartY);
  }
  public void mouseDragged(MouseEvent e) {
    model.reshapeFigure(dragStartX,dragStartY,e.getX(),e.getY());
  }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }

  public void keyTyped(KeyEvent e){ 
    char c = e.getKeyChar();
    switch(c){
      case 'b':
        model.currentColor = Color.black;
        break;
      case 'y':
      model.currentColor = Color.yellow;
        break;
      case 'g':
      model.currentColor = Color.green;
        break;
      case 'r':
      model.currentColor = Color.red; 
        break;
    }
  }
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){}  
}

