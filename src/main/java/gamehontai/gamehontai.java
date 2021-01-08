package gamehontai;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import  java.lang.Math;

/*画像を表示するためのクラスを親にする。 */
class phisicsobject extends Rectangle {
    protected double vx=0;
    protected double vy=0;
    protected boolean randed=false;
    //多分LayoutX,LayoutYが起点になるx,y座標かと思ったけど違うぽい
    phisicsobject(){
        super();
    }
    phisicsobject(double x,double y,double w,double h){
        super(x,y,w,h);
    }
    public  void move(){
        setX((int)(getX()+vx));
        setY((int)(getY()+vy));
    }
    public  void vector(double x,double y){
        vx=x;vy=y;
    }
    public void fall(){
        if(!randed){vy+=0.1;}
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
    public  double setVy(double y){
        vy=y;
        return vy;
    }
    public double setVx(double x){
        vx=x;
        return vx;
    }
    public boolean setRanded(boolean b){
        randed=b;
        return randed;
    }
    public  boolean getRanded(){
        return  randed;
    }
}

class charactor extends phisicsobject{
    protected boolean a=false,s=false,d=false,w=false,jumped=false,atk=false;
    protected phisicsobject attack;
    charactor(double x,double y,double w,double h){
        super(x,y,w,h);
    }
    charactor(double x,double y,double w,double h,phisicsobject a){
        super(x,y,w,h);
        attack=a;
    }
    public void keycheck(){
        if(a&&vx>-5){
            vx+=-5;
        }else if(a&&vx<=-5){
            vx=-10;
        }else if(!a&&vx>=-10&&vx<=0){
            vx=0;//攻撃とかで吹っ飛ばす時は｜vx｜>10になるようにして
        }
        if(d&&vx<5){
            vx+=5;
        }else if(d&&vx>5){
            vx=10;
        }else if(!d&&vx<=10&&vx>=0){
            vx=0;
        }
        if(w&&randed){
            vector(0,-5);

        }
    }

    @Override
    public boolean setRanded(boolean b) {
        if(b){
            jumped=false;
        }
        return super.setRanded(b);
    }

    public void keypressed(String c){
        switch (c){
            case "A":
               // System.out.println("a is pressed");
                a=true;
                if(d){
                    d=false;
                }
                break;
            case "S":
                s=true;
                //System.out.println("s is pressed");
                break;
            case "D":
                d=true;
               // System.out.println("d is pressed");
                if(a){
                    a=false;
                }
                break;
            case "W":
               // System.out.println("w is pressed");
                w=true;
                break;
            case " ":
                atk=true;
                break;
        }
    }
    public void keyreleased(String c){
        switch (c){
            case "A":
                a=false;
       //         System.out.println("a is released");
                break;
            case "S":
                s=false;
     //           System.out.println("s is released");
                break;
            case "D":
                d=false;
   //             System.out.println("d is released");
                break;
            case "W":
                w=false;
 //               System.out.println("w is released");
                break;
        }
    }
}

public class gamehontai extends Application
{
    public void charasyoutotu(phisicsobject a,phisicsobject b) {
        if (a.intersects(b.getX() + b.getVx() - 1 - a.getVx(), b.getY() + b.getVy() - 1 - a.getVy(), b.getWidth() + 1, b.getHeight() + 1)) {
            if (a.intersects(b.getX() + b.getVx() - 1 - a.getVx(), b.getY() - 1, b.getWidth() + 1, b.getHeight() + 1)) {
                if (a.getX() < b.getX()) {
                    //a.setVx((b.getX()-a.getWidth()-2-a.getX()));
                    if (a.getVx() <= 0) {
                        b.setVx(a.getX() + a.getVx() + a.getWidth() + 2 - b.getX());
                    } else if (b.getX() >= 0) {
                        a.setVx(b.getX() + b.getVx() - a.getWidth() - 2 - a.getX());
                    } else {
                        double d = b.getX() - a.getX() - a.getWidth();
                        a.setVx((d * (a.getVx() / (a.getVx() - b.getVx()))));
                        b.setVx((d * (b.getVx() / (a.getVx() - b.getVx()))));
                    }
                } else {
                    if (b.getVx() <= 0) {
                        a.setVx(b.getX() + b.getVx() + b.getWidth() + 2 - a.getX());
                    } else if (a.getVx() >= 0) {
                        b.setVx(a.getX() + a.getVx() - b.getWidth() - 2 - b.getX());
                    } else {
                        double d = a.getX() - b.getX() - b.getWidth();
                        a.setVx((d * (a.getVx() / (-a.getVx() + b.getVx()))));
                        b.setVx((d * (b.getVx() / (-a.getVx() + b.getVx()))));
                    }
                }
            }
            if (a.intersects(b.getX() - 1, b.getY() + b.getVy() - 1 - a.getVy(), b.getWidth() + 1, b.getHeight() + 1)) {
                if (a.getY() < b.getY()) {
                    if (a.getVy() <= 0) {
                        b.setVy((int)(a.getY() + a.getVy() + a.getHeight() + 2 - b.getY()));
                    } else if (b.getVy() >= 0) {
                        a.setVy((int)(b.getY() + b.getVy() - a.getHeight() - 2 - a.getY()));
                    } else {
                        double d=b.getY()-a.getY()-a.getHeight();
                        a.setVy((int)((d*(a.getVy()/(a.getVy()-b.getVy())))));
                        b.setVy((int)((d*(b.getVy()/(a.getVy()-b.getVy())))));
                    }
                    a.setRanded(true);
                } else {
                    if(b.getVy()<=0){
                        a.setVy((int)(b.getY()+b.getVy()+b.getHeight()+2-a.getY()));
                    }else if (a.getVy() >= 0) {
                        b.setVy((int)(a.getY() + a.getVy() - b.getHeight() - 2 - b.getY()));
                    } else {
                        double d=a.getY()-b.getY()-b.getHeight();
                        a.setVy((int)((d*(a.getVy()/(-a.getVy()+b.getVy())))));
                        b.setVy((int)((d*(b.getVy()/(-a.getVy()+b.getVy())))));
                    }
                    b.setRanded(true);
                }

            }

        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
    public void start(Stage theStage)
    {

        Group root =new Group();
        phisicsobject chareattack=new phisicsobject(0,0,1,1);
        charactor chare=new charactor(50,50,100,100,chareattack);
        phisicsobject enemyattack=new phisicsobject(0,0,1,1);
        charactor enemy=new charactor(500,50,100,100,enemyattack);
        phisicsobject flore=new phisicsobject(50,500,1000,100);
        root.getChildren().add(chare);
        root.getChildren().add(chareattack);
        root.getChildren().add(enemy);
        root.getChildren().add(enemyattack);
        root.getChildren().add(flore);
        Scene scene =new Scene(root);
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String c =event.getCode().toString();
                        chare.keypressed(c);
                    }
                }
        );
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String c =event.getCode().toString();
                        chare.keyreleased(c);
                    }
                }
        );
        Timeline timer=new Timeline(new KeyFrame(Duration.millis(17),new EventHandler<ActionEvent>(){
            int count=0;
            public void handle(ActionEvent event) {
                //毎フレームごとに呼び出すメゾット
                chare.fall();
                enemy.fall();
                chare.keycheck();

                if(count==0){
                    enemy.keypressed("W");
                    enemy.keyreleased("D");
                    enemy.keypressed("A");
                }
                if(count==20){
                    enemy.keyreleased("A");
                }
                if(count==40){
                    enemy.keypressed("D");
                }
                count++;
                count=count%60;
                enemy.keycheck();
                charasyoutotu(chare,flore);
                charasyoutotu(enemy,flore);
                charasyoutotu(chare,enemy);
                if(!chare.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-chare.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1)){
                    chare.setRanded(false);
                };
                if(!enemy.intersects(flore.getX()-1-chare.getVx(),flore.getY()-2-enemy.getVy(),flore.getWidth()+1,flore.getHeight()+1)&&!enemy.intersects(chare.getX()-1-enemy.getVx(),chare.getY()-2-enemy.getVy(),chare.getWidth()+1,chare.getHeight()+1)){
                    enemy.setRanded(false);
                }
                //System.out.println(chare.intersects(enemy.getX()-1-chare.getVx(),enemy.getY()-2-chare.getVy(),enemy.getWidth()+1,enemy.getHeight()+1));
                chare.move();
                enemy.move();
               }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        theStage.setScene(scene);
        theStage.setTitle("Hello, World!");
        theStage.show();


    }
}
