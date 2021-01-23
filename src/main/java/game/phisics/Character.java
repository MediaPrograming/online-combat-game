package game.phisics;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.Direction;

public class Character extends PhysicsObject {
    protected boolean jumped=false,atk=false,a=false,d=false,s=false,w=false;
    public PhysicsObject attack;//あくまでattackは攻撃判定なので描写とずれるかも
    protected int timetomove=0/*0の時キー入力を受け付ける*/,HP=100;
    protected Behavior action= Behavior.NORMAL;
    protected Direction muki=Direction.LEFT;
    Character(double x, double y, double w, double h){
        super(x,y,w,h);
    }
    public Character(double x, double y, double w, double h, PhysicsObject a){
        super(x,y,w,h);
        attack=a;
    }
    public void keycheck(boolean w, boolean a, boolean s, boolean d){
        if(timetomove<=0){
            if(atk){//攻撃範囲と硬直時間を設定
                if(a){
                    attack.setX(this.getX()-this.getWidth()/2);
                    attack.setY(this.getY()+this.getHeight()/4);
                    attack.setWidth(this.getWidth()/2);
                    attack.setHeight(this.getHeight()/2);
                    action=Behavior.ATTACK1;
                    muki=Direction.LEFT;
                }else if(d){
                    attack.setX(this.getX()+this.getWidth());
                    attack.setY(this.getY()+this.getHeight()/4);
                    attack.setWidth(this.getWidth()/2);
                    attack.setHeight(this.getHeight()/2);
                    action=Behavior.ATTACK2;
                    muki=Direction.RIGHT;
                }else if(w){
                    attack.setX(this.getX()+this.getWidth()/4);
                    attack.setY(this.getY()-this.getHeight()/2);
                    attack.setWidth(this.getWidth()/2);
                    attack.setHeight(this.getHeight()/2);
                    action=Behavior.ATTACK3;
                }else if(s){
                    attack.setX(this.getX()-this.getWidth()/4);
                    attack.setY(this.getY()+3*this.getHeight()/4);
                    attack.setWidth(3*this.getWidth()/2);
                    attack.setHeight(this.getHeight()/4);
                    action=Behavior.ATTACK4;
                }else{
                    if(muki==Direction.LEFT){
                        attack.setX(this.getX()-this.getWidth()/2);
                        attack.setY(this.getY()+this.getHeight()/4);
                        attack.setWidth(this.getWidth()/2);
                        attack.setHeight(this.getHeight()/2);
                        action=Behavior.ATTACK1;
                    }else{
                        attack.setX(this.getX()+this.getWidth());
                        attack.setY(this.getY()+this.getHeight()/4);
                        attack.setWidth(this.getWidth()/2);
                        attack.setHeight(this.getHeight()/2);
                        action=Behavior.ATTACK2;
                    }
                }
                attack.setVisible(true);
                atk=a=s=d=w=false;
                timetomove=60;
            }else {
                attack.setVisible(false);
                attack.setX(-1);
                attack.setY(-1);
                attack.setWidth(1);
                attack.setHeight(1);
            }
            if(a&&vx>-5){
                vx+=-5;
                muki=Direction.LEFT;
                if(randed)action=Behavior.RUN;
            }else if(a&&vx<=-5){
                vx=-10;
                muki=Direction.LEFT;
                if(randed)action=Behavior.RUN;
            }else if(!a&&vx<=0){
                vx=(int)(vx/5.0);
                action=Behavior.NORMAL;
            }
            if(d&&vx<5){
                vx+=5;
                muki=Direction.RIGHT;
                if(randed)action=Behavior.RUN;
            }else if(d&&vx>=5){
                vx=10;
                muki=Direction.RIGHT;
                if(randed)action=Behavior.RUN;
            }else if(!d&&vx>=0){
                vx=(int)(vx/5.0);
                action=Behavior.NORMAL;
            }
            if(w&&randed){
                vector(0,-5);
                action=Behavior.JUMP;
            }

        }else{
            timetomove--;
            if(attack.getX()!=-1){
                attack.setVx(this.getVx());
                attack.setVy(this.getVy());
                attack.move();
            }
        }
    }
    public int getTimetomove(){
        return timetomove;
    }
    public int setTimetomove(int t){
        timetomove=t;
        return t;
    }
    public boolean getatk(){
        return  atk;
    }
    public int Damage(int d){
        HP=HP-d;
        return HP;
    }
    public int getHP(){
        return HP;
    }
    @Override
    public boolean setRanded(boolean b) {
        if(b){ jumped=false;}
        return super.setRanded(b);
    }
    public  boolean getA(){
        return  a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public boolean getS(){
        return s;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean getD(){
        return  d;
    }
    public boolean getW(){
        return  w;
    }
    public void setW(boolean w) {
        this.w = w;
    }

    public void setAction(Behavior action) {
        this.action = action;
    }

    public Behavior getAction() {
        return action;
    }

    public void setDirection(Direction muki) {
        this.muki = muki;
    }

    public Direction getDirection() {
        return muki;
    }
}