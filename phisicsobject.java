import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.lang.Thread;
public class phisicsobject extends Polygon{
//vector()に加速度成分を引数として速度と現在位置(x,y)を更新する
//vector(0,y)を各フレームごとに呼び出すと重力に引っ張られるオブジェクトが作れる。yは重力加速度
    protected int x;//オブジェクト全体の一番左のx座標
    protected int y;//オブジェクト全体の最上点のy座標
    protected double vx=0;//オブジェクトの速度x軸成分
    protected double vy=0;//オブジェクトの速度y軸成分
    public void vector(double px,double py){
        vx=px+vx;
        vy=py+vy;
        x=x+(int)vx;
        y=y+(int)vy;
        translate((int)vx,(int)vy);
    }
    public void move(){
      x=x+(int)vx;
      y=y+(int)vy;
      translate((int)vx,(int)vy);
    }
    //各値を返す。
    public int getx() {return x;}
    public int gety(){return y;}
    public double getvx(){
        return vx;
    }
    public double getvy(){
        return vy;
    }
    //各値を設定する
    public void setx(int x) {
      int dx=x-this.x;  
      this.x = x;
        translate(dx,0);
    }
    public void sety(int y){
      int dy=y-this.y;  
      this.y=y;
        translate(0,dy);
    }
    public double setvx(double x) {
        vx = x;
        return vx;
    }
    public double setvy(double y){
        vy=y;
        return vy;
    }
}

// model viewer 本番でのcharactor
class Figure extends phisicsobject{
  protected int size;
  protected Color color;
  protected final static Color cl=Color.BLACK;
  protected boolean randed,a=false,s=false,d=false,w=false;
  protected char direction='r';//右向きがデフォルト
  Figure() {
    x = (int)(Math.random()*450);
    y = (int)(Math.random()*450);
    size=(int)(Math.random()*30+20);
    color=cl;
  }
  void draw(Graphics g) {}
  void fall(){
      vector(0.0,0.15);
    }
  public void jump(){
      vector(0,-4);
    }

  public int attack(){
    //技の硬直時間を返値にする。
    int needtime=0;
    //個別に技を設定する。
    System.out.println("攻撃");
    ////
    return needtime;
  }
  public void setranded(boolean b){
    randed=b;
  }
  public void setdirection(char c){
    direction=c;
  }
  public char getdirection(){
    return direction;
  }
  public boolean is_randed(){
    return randed;
  }
  public void apressing(boolean b){
    if(getvx()>=-0.5&&b){
      setvx(getvx()-2);
    }else if(getvx()<-0.5&&!b){
      setvx(getvx()+2);
    }
    a=b;
    if(d==false){
        direction='l';
    }
  }
  public void spressing(boolean b){
    s=b;
  }
  public void wpressing(boolean b){
    if(randed==true&&b){
      jump();
      randed=false;
    }

    b=w;
  }
  public void dpressing(boolean b){
    if(getvx()<=0.5&&b){
      setvx(getvx()+2);
    }else if(getvx()>0.5&&!b){
      setvx(getvx()-2);
    }
      if(a==false){
          direction='r';
      }
    d=b;
  }
  public void is_anykeypressing(){
    apressing(a);spressing(s);dpressing(d);wpressing(w);
  }
}

//本番での個別のキャラクター
class Box extends Figure {
    protected  int h,w;
    public  int getH(){return  h;}
    public  int getW(){return  w;}
  Box(){}
  Box(int x,int y){
    super();
    this.x=x;
    this.y=y;
    w=size;h=size;
    this.addPoint(x+w, y);
    this.addPoint(x+w, y+h);
    this.addPoint(x, y+h);
    this.addPoint(x, y);
  }
  Box (int x,int y,int w,int h){
      super();
      this.x=x;
      this.y=y;
      this.h=h;
      this.w=w;
      this.addPoint(x+w, y);
      this.addPoint(x+w, y+h);
      this.addPoint(x, y+h);
      this.addPoint(x, y);
  }
  void draw(Graphics g) {
    g.setColor(color);
    g.drawRect(x,y,w,h);
  }
  public int getsize(){
    return size;
  }
  
}

class RandomPanel extends JPanel implements ActionListener{
  private Box fig;
  private Figure arts1;
  private Box enemy;
  private Figure arts2;
  private Box flore;
  private Controller controller;
  Timer timer;
  public void is_charactorcrush(Box b1,Box b2){
      //y軸について
      if(b1.intersects(b2.getx(),b2.gety()+b1.getvy()+1,b2.getW(),b2.getH())&& (b1.gety()+ b1.getH()> b2.gety())){
          b1.setvy(0);
          b1.sety(b2.gety()-b1.getH()-1);
          b1.setranded(true);
      }else if(!b1.is_randed()) {
          b1.fall();
          if(b1.intersects(b2.getx(),b2.gety()+b1.getvy()+1,b2.getW(),b2.getH())){
              b1.setvy(0);
              b1.sety(b2.gety()-b1.getH()-1);
              b1.setranded(true);
          }
      }

      //x軸について衝突判定
      //右側に壁の場合
      if((b1.intersects(b2.getx()+b1.getvx()+1,b2.gety(),b2.getW(),b2.getH()))){
          b1.setvx(0);
          b1.setx(b2.getx()-b1.getW());
      }
      //左側に壁の場合
      if(b1.intersects(b2.getx()+b1.getvx()-1,b2.gety(),b2.getW(),b2.getH())){
          b1.setvx(0);
          b1.setx(b2.getx()+b2.getW()+1);

      }
      b1.setx(b1.getx()+(int)b1.getvx());
  }

  public void actionPerformed(ActionEvent e){
    /*try{
      controller.sleep(50);
    }catch(InterruptedException ev){
    }*/
    fig.is_anykeypressing();
    is_charactorcrush(fig,flore);
    is_charactorcrush(fig,enemy);

    this.repaint(); 
  }
  
  RandomPanel(){
    fig=new Box(100,0);
    enemy=new Box(350,350,50,50);
    flore =new Box(10,400,450,50);
    timer=new Timer(10,this);
    timer.start();
    controller=new Controller(this,fig,timer);
    //controller.start();
    this.addKeyListener(controller);
    this.setFocusable(true);
   }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    fig.draw(g);
    flore.draw(g);
    enemy.draw(g);
  }
  
}

class RandomFrame6 extends JFrame {
    public RandomFrame6(){
      this.setTitle("Random Frame");
      this.setSize(500,530);
      this.add(new RandomPanel());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public static void main(String argv[]) {
      new RandomFrame6();
   }
}

class Controller implements KeyListener{
 protected RandomPanel randomPanel;
 protected Figure fig;
 protected Timer timer;
// public void run(){
// }
 Controller(RandomPanel r,Figure f,Timer t){
      randomPanel=r;
      fig=f;
      timer=t;
 }
  public void keyPressed(KeyEvent e){
    int i=e.getKeyCode();
    switch(i){
      case '\u0003':
      
      break;

    }
  }
  public void keyReleased(KeyEvent e){
    char c=e.getKeyChar(); 
    switch(c){
      case 'a':
        fig.apressing(false);
      break;
      case 's':
        fig.spressing(false);
      break;
      case 'w':
        fig.wpressing(false);
      break;
      case 'd':
       fig.dpressing(false);
      break;
    }
  }
  public void keyTyped(KeyEvent e){
    char c=e.getKeyChar(); 
    switch(c){
      case 'a':
        fig.apressing(true);
      break;
      case 's':
        fig.spressing(true);
      break;
      case 'w':
        fig.wpressing(true);
      break;
      case 'd':
       fig.dpressing(true);
      break;
      case '\n':
        fig.attack();
      break;
    }

  }
}