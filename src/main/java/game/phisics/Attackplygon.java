package game.phisics;

public class Attackplygon extends PhysicsObject {
    private int damage=0;
    private int damagetime=50; //攻撃を食らった相手が動けるまでの時間
    private int vecx=0,vecy=0;//吹っ飛ばす距離
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

    public int getVecx() {
        return vecx;
    }

    public void setVecx(int ax) {
        this.vecx = ax;
    }

    public int getVecy() {
        return vecy;
    }

    public void setVecy(int ay) {
        this.vecy = ay;
    }
}
