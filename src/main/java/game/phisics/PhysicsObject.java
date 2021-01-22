package game.phisics;

import javafx.scene.shape.Rectangle;

/*画像を表示するためのクラスを親にする。 */
public class PhysicsObject extends Rectangle {
    protected double vx=0;
    protected double vy=0;
    protected double ax = 0;
    protected double ay = 0;
    protected boolean randed=false;
    //多分LayoutX,LayoutYが起点になるx,y座標かと思ったけど違うぽい
    PhysicsObject(){
        super();
    }
    public PhysicsObject(double x, double y, double w, double h){
        super(x,y,w,h);
    }
    public void move(){
        setX((int)(getX()+vx));
        setY((int)(getY()+vy));
    }
    public void vector(double x,double y){
        ax = x;
        ay = y;
        vx=vx+x;
        vy=vy+y;
    }
    public void fall(){
        if(!randed){vy+=1;}
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }

    public double getAx() {return this.ax;}
    public double getAy() { return this.ay;}
    public double setVy(double y){
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

