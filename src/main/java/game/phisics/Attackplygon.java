package game.phisics;

public class Attackplygon extends PhysicsObject {
    private int damage=0;
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
}
