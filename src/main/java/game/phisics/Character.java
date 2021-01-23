package game.phisics;
import io.game.hub.positionHub.Behavior;
import io.game.hub.positionHub.Direction;
import io.game.hub.messageHub.CharacterType;
import game.phisics.Attackplygon;
import game.phisics.Controller.*;

public class Character extends PhysicsObject {
    protected boolean jumped=false,atk=false,a=false,d=false,s=false,w=false;
    public Attackplygon attack;//あくまでattackは攻撃判定なので描写とずれるかも
    protected int timetomove=0/*0の時キー入力を受け付ける*/,HP=100;
    protected Behavior action= Behavior.NORMAL;
    protected Direction muki=Direction.LEFT;
    protected CharaController controller;
    Character(double x, double y, double w, double h){
        super(x,y,w,h);
    }
    public Character(double x, double y, double w, double h,Attackplygon a){
        super(x,y,w,h);
        attack=a;
    }
    public Character(double x, double y, double w, double h,int hp, Attackplygon a){
        super(x,y,w,h);
        HP=hp;
        attack=a;
    }
    public Character(double x, double y, double w, double h,int hp,CharacterType ct, Attackplygon a){
        super(x,y,w,h);
        HP=hp;
        attack=a;
        switch (ct){
            case Gura:
                controller=new GuraController(this,a);
                break;
            case Kiara:
                controller=new KiaraController(this,a);
                break;
            case Amelia:
                controller=new AmeliaController(this,a);
                break;
            case Inanis:
                controller=new InanisController(this,a);
                break;
            case Calliope:
                controller=new CalliopeController(this,a);
                break;
        }
    }

    public void keycheck(boolean w, boolean a, boolean s, boolean d){
        if(timetomove<=0){
            if(atk){//攻撃範囲と硬直時間を設定
                if(a){
                    timetomove=controller.attack1();
                    action=Behavior.ATTACK1;
                    muki=Direction.LEFT;
                }else if(d){
                    timetomove=controller.attack2();
                    action=Behavior.ATTACK2;
                    muki=Direction.RIGHT;
                }else if(w){
                    timetomove=controller.attack3();
                    action=Behavior.ATTACK3;
                }else if(s){
                    timetomove=controller.attack4();
                    action=Behavior.ATTACK4;
                }else{
                    if(muki==Direction.LEFT){
                        timetomove=controller.attack1();
                        action=Behavior.ATTACK1;
                    }else{
                        timetomove=controller.attack2();
                        action=Behavior.ATTACK2;
                    }
                }
                attack.setVisible(true);
                atk=a=s=d=w=false;
                
            }else {
                attack.setVisible(false);
                attack.setX(-1);
                attack.setY(-1);
                attack.setWidth(1);
                attack.setHeight(1);
                action=Behavior.NORMAL;
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
            }
            if(action==Behavior.RUN&&!a&&!d){
                    action=Behavior.NORMAL;
                }
            if(w&&randed){
                controller.jump();
                action=Behavior.JUMP;
            }
            if(!randed&&(action!=Behavior.DAMAGE&&action!=Behavior.ATTACK1&&action!=Behavior.ATTACK2&&action!=Behavior.ATTACK3&&action!=Behavior.ATTACK4)){
                action=Behavior.JUMP;
            }
            if(randed&&action==Behavior.JUMP){
                action=Behavior.NORMAL;
            }

        }else{
            timetomove--;
            controller.attacking();
            if(action!=Behavior.DAMAGE&&action!=Behavior.ATTACK1&&action!=Behavior.ATTACK2&&action!=Behavior.ATTACK3&&action!=Behavior.ATTACK4){
                action=Behavior.NORMAL;
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

    public void setAttack(boolean a) {
        this.atk=a;
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