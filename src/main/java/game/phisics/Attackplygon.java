package game.phisics;

public class Attackplygon extends PhysicsObject {
    private int damage=0;
    private int damagetime=50; //攻撃を食らった相手が動けるまでの時間
    private int ax=0,ay=0;
    Attackplygon(){
        super();
    }
    public Attackplygon(double x, double y, double w, double h){
        super(x,y,w,h);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamege(int damege) {
        this.damage = damege;
    }
    public void setDamagetime(int t){
        damagetime=t;
    }
    public int getDamagetime(){
        return damagetime;
    }

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }
}
